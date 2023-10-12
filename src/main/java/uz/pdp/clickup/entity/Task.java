package uz.pdp.clickup.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "tasks")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "name", "list_id" }) })
public class Task extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    @ManyToOne(optional = false)
    private Status status;

    @ManyToOne(optional = false)
    private List list;

    @ManyToOne
    private Priority priority;

    @ManyToOne
    private Task parentTask;

    @Column
    private LocalDate startDate;

    @Column
    private LocalTime startTime;

    @Column
    private LocalDate endDate;

    @Column
    private LocalTime endTime;

    @Column
    private LocalDateTime activatedAt;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "task", cascade = CascadeType.REMOVE)
    private Set<Comment> comments = new HashSet<>();

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "task", cascade = CascadeType.REMOVE)
    private Set<Checklist> checklists = new HashSet<>();

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "task", cascade = CascadeType.REMOVE)
    private Set<TaskUser> persons = new HashSet<>();

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "task", cascade = CascadeType.REMOVE)
    private Set<TaskAttachment> attachments = new HashSet<>();

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "task", cascade = CascadeType.REMOVE)
    private Set<History> histories = new HashSet<>();

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany
    private Set<Tag> tags = new HashSet<>();
}
