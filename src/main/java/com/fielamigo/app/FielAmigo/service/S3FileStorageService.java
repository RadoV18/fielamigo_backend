package com.fielamigo.app.FielAmigo.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.fielamigo.app.FielAmigo.utils.FielAmigoException;

@Service
public class S3FileStorageService {

    private final AmazonS3 amazonS3;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    public S3FileStorageService(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }
    /**
     * Method that uploads a file to S3
     * @param file the file to upload
     * @return the url of the file
     */
    public String upload(MultipartFile file) throws FielAmigoException {
        try {
            // file metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(file.getSize());
            objectMetadata.setContentType(file.getContentType());

            // file name
            UUID uuid = UUID.randomUUID();
            String randomUUIDString = uuid.toString();

            // upload file
            amazonS3.putObject(bucketName, randomUUIDString, file.getInputStream(), objectMetadata);
            String url = amazonS3.getUrl(bucketName, randomUUIDString).toString();

            return url;
        } catch(AmazonServiceException e) {
            throw new FielAmigoException("Error uploading image");
        } catch (IOException e) {
            throw new FielAmigoException("Error uploading image");
        }
    }
}
