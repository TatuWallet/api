package br.com.tatuwallet.service;
/*
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
 */

// @Service
public class ArquivoService {

    /*
    private final S3Client s3Client;

    public ArquivoService(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String upload(MultipartFile arquivo) throws IOException {

        String nomeArquivo = UUID.randomUUID() + "-" + arquivo.getOriginalFilename();

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket("arquivos-projeto")
                .key(nomeArquivo)
                .contentType(arquivo.getContentType())
                .build();

        s3Client.putObject(
                request,
                RequestBody.fromInputStream(
                        arquivo.getInputStream(),
                        arquivo.getSize()
                )
        );

        return nomeArquivo;
    }

     */
}