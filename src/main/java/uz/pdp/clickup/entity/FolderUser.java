package uz.pdp.clickup.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.clickup.enums.AuthorityType;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "folder_users")
public class FolderUser extends BaseEntity {
    @ManyToOne(optional = false)
    private Folder folder;

    @ManyToOne(optional = false)
    private User person;

    @Column(nullable = false)
    private AuthorityType taskAuthority;
}
