package uz.pdp.clickup.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.clickup.enums.TaskAuthorityType;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity(name = "list_users")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "list_id", "person_id" }) })
public class ListUser extends BaseEntity {
    @ManyToOne(optional = false)
    private List list;

    @ManyToOne(optional = false)
    private User person;

    @Column(nullable = false)
    private TaskAuthorityType taskAuthority;

    public ListUser(List list, User person, TaskAuthorityType taskAuthority) {
        this.list = list;
        this.person = person;
        this.taskAuthority = taskAuthority;
    }
}
