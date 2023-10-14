package uz.pdp.clickup.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "tags")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "name", "workspace_id" }) })
public class Tag extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    private Workspace workspace;

    @ManyToOne
    private Color color;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "tags")
    private Set<Task> tasks = new HashSet<>();

    public void addTask(Task task) {
        this.tasks.add(task);
        task.getTags().add(this);
    }
  
    public void removeTask(Task task) {
        this.tasks.remove(task);
        task.getTags().remove(this);
    }
}
