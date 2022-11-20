package com.fielamigo.app.FielAmigo.service;

import org.springframework.web.multipart.MultipartFile;

import com.fielamigo.app.FielAmigo.utils.FielAmigoException;

public interface FileStorageService {
    public String upload(MultipartFile file) throws FielAmigoException;
}
