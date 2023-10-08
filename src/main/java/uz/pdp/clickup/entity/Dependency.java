package uz.pdp.clickup.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.clickup.enums.DependencyType;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "dependencies")
public class Dependency extends BaseEntity {
    @ManyToOne(optional = false)
    private Task task;

    @ManyToOne(optional = false)
    private Task dependencyTask;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DependencyType type;
}
