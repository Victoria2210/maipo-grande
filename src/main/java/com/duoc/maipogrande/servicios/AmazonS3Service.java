package com.duoc.maipogrande.servicios;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class AmazonS3Service {

    @Autowired
    private String awsS3AudioBucket;
    private final String RUTACARPETARECHAZADO = "reportes/rechazado/";
    private final String RUTACARPETAACEPTADO = "reportes/aceptado/";
    private AmazonS3 amazonS3;
    private Logger logger = LoggerFactory.getLogger(AmazonS3Service.class);

    @Autowired
    public AmazonS3Service(Region awsRegion, AWSCredentialsProvider awsCredentialsProvider) {
        this.amazonS3 = AmazonS3ClientBuilder.standard()
                .withCredentials(awsCredentialsProvider)
                .withRegion(awsRegion.getName()).build();
    }

    public boolean subirArchivoS3Bucket(String nombrePdf) {
        File file = null;
        try {
            file = new File(nombrePdf);
            if (nombrePdf.contains("pdfAceptado")) {
                this.amazonS3.putObject(new PutObjectRequest(this.awsS3AudioBucket, RUTACARPETAACEPTADO.concat(file.getName()), file));
            } else {
                this.amazonS3.putObject(new PutObjectRequest(this.awsS3AudioBucket, RUTACARPETARECHAZADO.concat(file.getName()), file));
            }
            return true;
        } catch (AmazonS3Exception ex) {
            logger.error(String.format("Error: %s, ocurrido al subir %s", ex.getMessage(), file.getName()));
            return false;
        }
    }
}

