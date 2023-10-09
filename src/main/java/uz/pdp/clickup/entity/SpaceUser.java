package uz.pdp.clickup.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "space_users")
public class SpaceUser extends BaseEntity {
    @ManyToOne(optional = false)
    private Space space;

    @ManyToOne(optional = false)
    private User member;
}
