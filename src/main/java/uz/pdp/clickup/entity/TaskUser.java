package uz.pdp.clickup.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "task_users")
public class TaskUser extends BaseEntity {
    @ManyToOne(optional = false)
    private Task task;

    @ManyToOne(optional = false)
    private User assignedUser;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "assignedUser", cascade = CascadeType.REMOVE)
    private Set<Item> assignedItems = new HashSet<>();
}
