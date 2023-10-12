package uz.pdp.clickup.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.clickup.enums.AuthorityType;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "list_users")
public class ListUser extends BaseEntity {
    @ManyToOne(optional = false)
    private List list;

    @ManyToOne(optional = false)
    private User person;

    @Column(nullable = false)
    private AuthorityType taskAuthority;
}
