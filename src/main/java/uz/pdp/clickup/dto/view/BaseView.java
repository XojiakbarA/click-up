package uz.pdp.clickup.dto.view;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
public class BaseView {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserAuditorView createdBy;
    private UserAuditorView updatedBy;
}
