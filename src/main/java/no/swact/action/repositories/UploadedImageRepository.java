package no.swact.action.repositories;

import no.swact.action.models.UploadedImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadedImageRepository extends JpaRepository<UploadedImage, String> {
}
