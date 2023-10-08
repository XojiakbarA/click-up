package uz.pdp.clickup.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "icons")
public class Icon extends BaseEntity {
    @OneToOne(cascade = CascadeType.ALL)
    private Attachment attachment;
}
