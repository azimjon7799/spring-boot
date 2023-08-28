package com.clean.code.springboot.repository;

import com.clean.code.springboot.domain.FileStorage;
import com.clean.code.springboot.domain.FileStorageStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileStorageRepo extends JpaRepository<FileStorage, Long> {
    FileStorage findByHashId(String hashId);
    List<FileStorage> findAllByFileStorageStatus(FileStorageStatus status);
}
