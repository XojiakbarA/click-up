package uz.pdp.clickup.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity(name = "space_users")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "space_id", "person_id" }) })
public class SpaceUser extends BaseEntity {
    @ManyToOne(optional = false)
    private Space space;

    @ManyToOne(optional = false)
    private User person;

    public SpaceUser(Space space, User person) {
        this.space = space;
        this.person = person;
    }
}
