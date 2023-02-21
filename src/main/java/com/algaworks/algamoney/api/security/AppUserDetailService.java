package com.algaworks.algamoney.api.security;

import com.algaworks.algamoney.api.models.AppUser;
import com.algaworks.algamoney.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AppUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AppUser> userOptional = userRepository.findByEmail(email);
        AppUser appUser = userOptional.orElseThrow(() -> new UsernameNotFoundException("User and/or password invalid!"));
        return new User(email, appUser.getPassword(), getPermissions(appUser));
    }

    private Collection<? extends GrantedAuthority> getPermissions(AppUser appUser) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        appUser.getPermissions().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getDescription().toUpperCase())));
        return authorities;
    }
}
