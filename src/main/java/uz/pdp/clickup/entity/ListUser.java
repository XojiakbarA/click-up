package uz.pdp.clickup.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import uz.pdp.clickup.enums.AuthorityType;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "list_users")
public class ListUser extends BaseEntity {
    @ManyToOne(optional = false)
    private List list;

    @ManyToOne(optional = false)
    private User member;

    @Column(nullable = false)
    private AuthorityType taskAuthority;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Item> assignedItems = new HashSet<>();
}
