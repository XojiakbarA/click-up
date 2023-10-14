package uz.pdp.clickup.service;

import org.springframework.web.multipart.MultipartFile;

import uz.pdp.clickup.entity.Attachment;

public interface AttachmentService {
    Attachment save(MultipartFile file);
}
