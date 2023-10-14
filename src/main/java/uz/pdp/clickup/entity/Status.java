package uz.pdp.clickup.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.clickup.enums.StatusType;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity(name = "statuses")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "name", "list_id" }) })
public class Status extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    private List list;

    @ManyToOne
    private Color color;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusType type;

    @Column(nullable = false)
    private Integer orderIndex;

    public Status(String name, List list, StatusType type, Integer orderIndex) {
        this.name = name;
        this.list = list;
        this.type = type;
        this.orderIndex = orderIndex;
    }
}
