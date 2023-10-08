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
@Entity(name = "spaces")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "name", "workspace_id" }) })
public class Space extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Color color;

    @ManyToOne(optional = false)
    private Workspace workspace;

    @ManyToOne
    private Icon icon;

    @OneToOne(cascade = CascadeType.ALL)
    private Attachment avatar;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany
    private Set<ClickApp> clickApps = new HashSet<>();

    public Character getInitialLetter() {
        return name.charAt(0);
    }
}