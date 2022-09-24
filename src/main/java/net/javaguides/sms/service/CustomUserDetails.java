package net.javaguides.sms.service;


import net.javaguides.sms.entity.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    @Autowired
    private final AppUser appUser;

    public CustomUserDetails(AppUser appUser) {
        super();
        this.appUser = appUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> listRole = new ArrayList<>();
        listRole.add(new SimpleGrantedAuthority(appUser.getUsername()));
        return listRole;
    }

    @Override
    public String getPassword() {
        return  appUser.getPassword();
    }
    public String getEmail(){
        return appUser.getEmail();
    }
    public String getPhoneNumber(){
        return appUser.getPhoneNumber();
    }
    public String getRegistrationNumber(){
        return appUser.getRegistrationNumber();
    }

    @Override
    public String getUsername() {
        return appUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
