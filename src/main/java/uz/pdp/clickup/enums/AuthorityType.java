package uz.pdp.clickup.enums;

import org.springframework.security.core.GrantedAuthority;

public enum AuthorityType implements GrantedAuthority {
    ;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
