package uz.pdp.clickup.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "items")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "name", "checklist_id" }) })
public class Item extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    private Checklist checklist;

    @ManyToOne
    private TaskUser assignedUser;

    @Column(nullable = false)
    private Boolean completed = false;
}
