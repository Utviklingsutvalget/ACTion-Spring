package no.swact.action.services;

import no.swact.action.models.UploadedImage;
import org.springframework.stereotype.Service;

@Service
public interface ImageUploadService {
    UploadedImage save(UploadedImage file);

    void delete(UploadedImage file);

    UploadedImage findOne(String uploadedFileId);
}
