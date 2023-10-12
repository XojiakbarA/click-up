package uz.pdp.clickup.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import uz.pdp.clickup.enums.AccessType;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "folders")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "name", "space_id" }) })
public class Folder extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    private Space space;

    @ManyToOne
    private Color color;

    @Column
    private Boolean archived;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccessType accessType;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "folder", cascade = CascadeType.REMOVE)
    private Set<FolderUser> persons = new HashSet<>();

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "folder", cascade = CascadeType.REMOVE)
    private Set<List> lists = new HashSet<>();
}
