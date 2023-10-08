package uz.pdp.clickup.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "histories")
public class History extends BaseEntity {
    @ManyToOne(optional = false)
    private Task task;

    @Column(nullable = false)
    private String field;

    @Column
    private String before;

    @Column
    private String after;

    @Column
    private String data;
}
