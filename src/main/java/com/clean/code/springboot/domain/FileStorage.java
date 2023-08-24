package com.clean.code.springboot.domain;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class FileStorage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtensions() {
        return extensions;
    }

    public void setExtensions(String extensions) {
        this.extensions = extensions;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getHashId() {
        return hashId;
    }

    public void setHashId(String hashId) {
        this.hashId = hashId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public FileStorageStatus getFileStorageStatus() {
        return fileStorageStatus;
    }

    public void setFileStorageStatus(FileStorageStatus fileStorageStatus) {
        this.fileStorageStatus = fileStorageStatus;
    }

    private String extensions;

    private Long fileSize;

    private String hashId;

    private String contentType;

    private String uploadPath;

    @Enumerated(EnumType.STRING)
    private FileStorageStatus fileStorageStatus;
}
