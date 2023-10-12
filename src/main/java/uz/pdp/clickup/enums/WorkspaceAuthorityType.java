package uz.pdp.clickup.enums;

import org.springframework.security.core.GrantedAuthority;

public enum WorkspaceAuthorityType implements GrantedAuthority {
    ADD_REMOVE_MEMBERS, GIT, EDIT_STATUSES, MANAGE_TAGS, SEND_EMAIL, ADD_EMAIL_ACCOUNTS;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
