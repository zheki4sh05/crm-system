package com.wisecrm.filestorage.dto;

import lombok.*;
import org.springframework.boot.context.properties.*;
import org.springframework.stereotype.*;


@Service
@Getter
@Setter
@ConfigurationProperties(prefix = "minio")
public class MinioProperties {

    private String bucket;
    private String url;
    private String accessKey;
    private String secretKey;

    public String getDocumentsBucket() {
        return bucket;
    }

    @Override
    public String toString() {
        return "MinioProperties{" +
                "bucket='" + bucket + '\'' +
                ", url='" + url + '\'' +
                ", accessKey='" + accessKey + '\'' +
                ", secretKey='" + secretKey + '\'' +
                '}';
    }
}
