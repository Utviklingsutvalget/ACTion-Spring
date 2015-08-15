package no.swact.action.controllers.api;

import no.swact.action.models.UploadedImage;
import no.swact.action.services.ImageUploadService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/file/upload")
public class FileUploadRestController {


    @Inject
    private ImageUploadService fileService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public UploadedImage postFile(@RequestParam("file") MultipartFile file) throws IOException {
        UploadedImage s3File = new UploadedImage();
        s3File.setName(file.getName());
        try (InputStream inputStream = file.getInputStream()) {
            s3File.setInputStream(inputStream);
            return fileService.save(s3File);
        }

    }
}
