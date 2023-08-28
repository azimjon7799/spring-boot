package com.clean.code.springboot.service;

import com.clean.code.springboot.domain.FileStorage;
import com.clean.code.springboot.domain.FileStorageStatus;
import com.clean.code.springboot.repository.FileStorageRepo;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class FileStorageService {
    private final FileStorageRepo fileStorageRepo;

    @Value("${upload.folder}")
    private String uploadFolder;

    private final Hashids hashids;

    public FileStorageService(FileStorageRepo fileStorageRepo){
        this.fileStorageRepo = fileStorageRepo;
        this.hashids = new Hashids(getClass().getName(), 6);
    }

    public void save(MultipartFile multipartFile){
        FileStorage fileStorage = new FileStorage();
        fileStorage.setName(multipartFile.getOriginalFilename());
        fileStorage.setExtensions(getExt(multipartFile.getOriginalFilename()));
        fileStorage.setFileSize(multipartFile.getSize());
        fileStorage.setContentType(multipartFile.getContentType());
        fileStorage.setFileStorageStatus(FileStorageStatus.DRAFT);
        fileStorageRepo.save(fileStorage);

        Date now = new Date();
        File uploadFolder = new File(String.format(
                "%s/upload_files/%d/%d/%d/",
                this.uploadFolder,
                1900 + now.getYear(),
                1 + now.getMonth(),
                now.getDate()
        ));
        if(!uploadFolder.exists() && uploadFolder.mkdirs()){
            System.out.println("Aytilgan papkalar yaratildi");
        }
        fileStorage.setHashId(hashids.encode(fileStorage.getId()));
        fileStorage.setUploadPath(String.format(
                "upload_files/%d/%d/%d/%s.%s",
                1900 + now.getYear(),
                1 + now.getMonth(),
                now.getDate(),
                fileStorage.getHashId(),
                fileStorage.getExtensions()
        ));
        fileStorageRepo.save(fileStorage);
        uploadFolder = uploadFolder.getAbsoluteFile();
        File file = new File(uploadFolder, String.format("%s.%s", fileStorage.getHashId(), fileStorage.getExtensions()));
        try {
            multipartFile.transferTo(file);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public FileStorage findByHashId(String hashId){
        return fileStorageRepo.findByHashId(hashId);
    }

    public void delete(String hashId){
        FileStorage fileStorage = findByHashId(hashId);
        File file = new File(String.format("%s/%s", this.uploadFolder, fileStorage.getUploadPath()));
        if(file.delete()){
            fileStorageRepo.delete(fileStorage);
        }
    }

    @Scheduled(cron = "0 0 0 * * *")// cron = second minute hour day month year
    public void deleteAllDraft(){
        List<FileStorage> fileStorageList = fileStorageRepo.findAllByFileStorageStatus(FileStorageStatus.DRAFT);
        for(FileStorage fileStorage: fileStorageList){
            delete(fileStorage.getHashId());
        }
//        fileStorageList.forEach(fileStorage -> {
//            delete(fileStorage.getHashId());
//        });
    }
    private String getExt(String fileName){
        String ext = null;
        if (fileName != null && !fileName.isEmpty()){
            int dot = fileName.lastIndexOf('.');
            if(dot > 0 && dot <= fileName.length() - 2){
                ext = fileName.substring(dot + 1);
            }
        }
        return ext;
    }
}
