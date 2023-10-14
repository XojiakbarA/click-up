package uz.pdp.clickup.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import uz.pdp.clickup.enums.AccessType;

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

    @ManyToOne(optional = false)
    private User owner;

    @ManyToOne
    private Icon icon;

    @OneToOne(cascade = CascadeType.ALL)
    private Attachment avatar;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccessType accessType = AccessType.PUBLIC;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany
    private Set<ClickApp> clickApps = new HashSet<>();

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany
    private Set<View> views = new HashSet<>();

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "space", cascade = CascadeType.ALL)
    private Set<SpaceUser> persons = new HashSet<>();

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "space", cascade = CascadeType.REMOVE)
    private Set<Folder> folders = new HashSet<>();

    public Character getInitialLetter() {
        return name.charAt(0);
    }

    public void addClickApp(ClickApp clickApp) {
        this.clickApps.add(clickApp);
        clickApp.getSpaces().add(this);
    }
  
    public void removeClickApp(ClickApp clickApp) {
        this.clickApps.remove(clickApp);
        clickApp.getSpaces().remove(this);
    }

    public void addView(View view) {
        this.views.add(view);
        view.getSpaces().add(this);
    }
  
    public void removeView(View view) {
        this.views.remove(view);
        view.getSpaces().remove(this);
    }
}
