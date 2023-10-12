package uz.pdp.clickup.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "views")
public class View extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne(optional = false)
    private Icon icon;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "views")
    private Set<Space> spaces = new HashSet<>();

    public void removeSpace(Space space) {
        this.spaces.remove(space);
        space.getViews().remove(this);
    }
}
