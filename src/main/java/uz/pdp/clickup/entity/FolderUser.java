package uz.pdp.clickup.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.clickup.enums.AuthorityType;
import uz.pdp.clickup.enums.TaskAuthorityType;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity(name = "folder_users")
public class FolderUser extends BaseEntity {
    @ManyToOne(optional = false)
    private Folder folder;

    @ManyToOne(optional = false)
    private User person;

    @Column(nullable = false)
    private TaskAuthorityType taskAuthority;

    public FolderUser(Folder folder, User person, TaskAuthorityType taskAuthority) {
        this.folder = folder;
        this.person = person;
        this.taskAuthority = taskAuthority;
    }
}
