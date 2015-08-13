package no.swact.action.repositories;

import no.swact.action.models.UploadedImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UploadedImageRepository extends JpaRepository<UploadedImage, UUID> {
}
