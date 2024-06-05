package com.github.danilogmoura.algafood.core.storage;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.github.danilogmoura.algafood.core.storage.StorageProperties.TipoStorage;
import com.github.danilogmoura.algafood.domain.service.FotoStorageService;
import com.github.danilogmoura.algafood.infrastructure.service.storage.LocalFotoStorageService;
import com.github.danilogmoura.algafood.infrastructure.service.storage.S3FotoStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {

    @Autowired
    private StorageProperties storageProperties;

    @Bean
    @ConditionalOnProperty(name = "algafood.storage.tipo", havingValue = "s3")
    public AmazonS3 amazonS3() {
        var credentials = new BasicAWSCredentials(
            storageProperties.getS3().getAccessKeyId(),
            storageProperties.getS3().getSecretAccessKey());

        var endPoint = new AwsClientBuilder.EndpointConfiguration(
            storageProperties.getS3().getEndPoint(),
            storageProperties.getS3().getRegion().getName()
        );

        return AmazonS3ClientBuilder.standard()
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .withEndpointConfiguration(endPoint)
            .withPathStyleAccessEnabled(true) // Importante apenas se estiver usando LocalStack
            .build();
    }

    @Bean
    public FotoStorageService fotoStorageService() {
        if (TipoStorage.S3.equals(storageProperties.getTipo())) {
            return new S3FotoStorageService();
        } else {
            return new LocalFotoStorageService();
        }
    }
}
