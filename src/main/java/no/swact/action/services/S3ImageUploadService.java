package no.swact.action.services;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import no.swact.action.models.UploadedImage;
import no.swact.action.repositories.UploadedImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class S3ImageUploadService implements ImageUploadService {
    private static final Logger LOG = LoggerFactory.getLogger(S3ImageUploadService.class);
    private String s3Bucket;
    private AmazonS3 amazonS3;
    @Autowired
    private UploadedImageRepository uploadedImageRepository;

    public S3ImageUploadService(final String accessKey, final String secretKey, final String s3Bucket) {
        this.s3Bucket = s3Bucket;
        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        amazonS3 = new AmazonS3Client(awsCredentials, clientConfiguration);
        amazonS3.createBucket(s3Bucket);
        LOG.info("Using S3 Bucket: " + s3Bucket);
    }

    @Override
    public UploadedImage save(UploadedImage file) {
        file.setBucket(s3Bucket);
        String id = UUID.randomUUID().toString().replace("-", "");
        LOG.info("Setting id: " + id);
        file.setId(id);
        uploadedImageRepository.save(file); // assigns an id
        LOG.info("Id saved as: " + file.getId());
        ObjectMetadata metadata = new ObjectMetadata();
        PutObjectRequest putObjectRequest = new PutObjectRequest(s3Bucket, file.getActualFileName(), file.getInputStream(), metadata);
        putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead); // public for all
        amazonS3.putObject(putObjectRequest); // upload file
        return file;
    }

    @Override
    public void delete(UploadedImage file) {
        amazonS3.deleteObject(s3Bucket, file.getActualFileName());
        uploadedImageRepository.delete(file);
    }

    @Override
    public UploadedImage findOne(final String uploadedFileId) {
        LOG.info("Querying for uploaded file:" + uploadedFileId);
        return uploadedImageRepository.findOne(uploadedFileId);
    }
}
