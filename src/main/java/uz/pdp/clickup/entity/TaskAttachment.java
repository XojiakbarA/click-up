package uz.pdp.clickup.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "task_attachments")
public class TaskAttachment extends BaseEntity {
    @ManyToOne(optional = false)
    private Task task;

    @OneToOne(optional = false)
    private Attachment attachment;

    @Column(nullable = false)
    private Boolean pinned = false;
}
