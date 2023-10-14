package uz.pdp.clickup.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import uz.pdp.clickup.entity.Attachment;
import uz.pdp.clickup.repository.AttachmentRepository;
import uz.pdp.clickup.service.AttachmentService;

@Service
public class AttachmentServiceImpl implements AttachmentService {
    @Autowired
    private AttachmentRepository attachmentRepository;

    private Attachment save(Attachment attachment) {
        return attachmentRepository.save(attachment);
    }

    @Override
    public Attachment save(MultipartFile file) {
        Attachment attachment = new Attachment();

        String[] split = file.getOriginalFilename().split("\\.");

        String name = UUID.randomUUID().toString() + "." + split[split.length - 1];

        attachment.setName(name);
        attachment.setOriginalName(file.getOriginalFilename());
        attachment.setContentType(file.getContentType());
        attachment.setSize(file.getSize());

        try {
            Path path = Paths.get("files/" + name);
            Files.copy(file.getInputStream(), path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return save(attachment);
    }
}
