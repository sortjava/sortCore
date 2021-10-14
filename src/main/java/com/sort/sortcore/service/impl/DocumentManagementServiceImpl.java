package com.sort.sortcore.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.sort.sortcore.config.S3ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentManagementServiceImpl {

    @Autowired
    private AmazonS3 amazonS3Client;

    @Autowired
    private S3ApplicationProperties applicationProperties;

    public S3Object downloadFileFromS3bucket(String fileName, String bucketName) {
        S3Object object = amazonS3Client.getObject(bucketName, fileName);
        return object;
    }
}