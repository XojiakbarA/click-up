package uz.pdp.clickup.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.clickup.enums.StatusType;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "statuses")
public class Status extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne
    private Space space;

    @ManyToOne
    private Folder project;

    @ManyToOne
    private List category;

    @ManyToOne
    private Color color;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusType type;
}
