package uz.pdp.clickup.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "tags")
public class Tag extends BaseEntity {
    @Column(nullable = false, unique = true)
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
}
