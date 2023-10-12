package uz.pdp.clickup.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "workspaces")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "name", "owner_id" }) })
public class Workspace extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Color color;

    @ManyToOne
    private User owner;

    @OneToOne(cascade = CascadeType.ALL)
    private Attachment avatar;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "workspace", cascade = CascadeType.REMOVE)
    private Set<Space> spaces = new HashSet<>();

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "workspace", cascade = CascadeType.ALL)
    private Set<WorkspaceUser> persons = new HashSet<>();

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "workspace", cascade = CascadeType.ALL)
    private Set<WorkspaceRole> workspaceRoles = new HashSet<>();

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany
    private Set<Tag> tags = new HashSet<>();

    public Character getInitialLetter() {
        return name.charAt(0);
    }
}
