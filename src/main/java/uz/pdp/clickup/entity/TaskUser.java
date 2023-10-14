package uz.pdp.clickup.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity(name = "task_users")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "task_id", "person_id" }) })
public class TaskUser extends BaseEntity {
    @ManyToOne(optional = false)
    private Task task;

    @ManyToOne(optional = false)
    private User person;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "assignedUser", cascade = CascadeType.REMOVE)
    private Set<Item> assignedItems = new HashSet<>();

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "assignedUser", cascade = CascadeType.REMOVE)
    private Set<Task> assignedTasks = new HashSet<>();

    public TaskUser(Task task, User person) {
        this.task = task;
        this.person = person;
    }
}
