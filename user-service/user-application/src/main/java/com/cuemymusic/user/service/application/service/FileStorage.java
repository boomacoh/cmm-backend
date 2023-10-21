package com.cuemymusic.user.service.application.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import com.cuemymusic.user.service.domain.valueobject.SongMetaData;
import com.cuemymusic.user.service.domain.valueobject.builder.SongMetaDataBuilder;
import com.groupdocs.metadata.Metadata;
import com.groupdocs.metadata.core.MP3RootPackage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.stream.Stream;

import com.cuemymusic.user.service.domain.exceptions.DomainException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

@Service
@Slf4j
public class FileStorage{

    private final Path root = Paths.get("uploads");

    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    public SongMetaData save(MultipartFile file) {

        try {
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            String filename = UUID.randomUUID()+"."+extension;
            Path absFilename = this.root.resolve(filename);
            Files.copy(file.getInputStream(), absFilename);
            SongMetaDataBuilder songMetaDataBuilder =  getMetaData(absFilename.toString());
            songMetaDataBuilder.setFileLocation(filename);
            songMetaDataBuilder.setUploadFileName(file.getOriginalFilename());
            return songMetaDataBuilder.createSongMetaData();
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new DomainException("A file of that name already exists.");
            }
            throw new DomainException(e.getMessage());
        }
    }
    public SongMetaDataBuilder getMetaData(String location) throws IOException, UnsupportedAudioFileException {
        SongMetaDataBuilder songMetaDataBuilder = new SongMetaDataBuilder();
        try (Metadata metadata = new Metadata(location)) {
            MP3RootPackage root = metadata.getRootPackageGeneric();
            if (root.getID3V2() != null) {
                songMetaDataBuilder.setArtist(root.getID3V2().getArtist());
                songMetaDataBuilder.setCopyRight(root.getID3V2().getCopyright());
                songMetaDataBuilder.setRecordLabel(null);
                songMetaDataBuilder.setTitle(root.getID3V2().getTitle());
                songMetaDataBuilder.setDuration(120);
            }
        }
        return songMetaDataBuilder;
    }
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }
    public void delete(String filename){
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                Files.delete(file);
            } else {
                throw new RuntimeException("Could not read the file! " + filename);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }
}