package uz.pdp.clickup.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "task_attachments")
public class TaskAttachment extends BaseEntity {
    @ManyToOne(optional = false)
    private Task task;

    @OneToOne(optional = false, cascade = CascadeType.REMOVE)
    private Attachment attachment;

    @Column(nullable = false)
    private Boolean pinned = false;
}
