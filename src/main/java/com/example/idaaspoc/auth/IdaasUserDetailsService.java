package com.example.idaaspoc.auth;

import com.example.idaaspoc.entity.Roles;
import com.example.idaaspoc.entity.User;
import com.example.idaaspoc.repository.RolesRepository;
import com.example.idaaspoc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IdaasUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RolesRepository rolesRepository;

    public static List<GrantedAuthority> grantList;

    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken preAuthenticatedAuthenticationToken) throws UsernameNotFoundException {
        String technicalId = preAuthenticatedAuthenticationToken.getPrincipal().toString();
        String credential = preAuthenticatedAuthenticationToken.getCredentials().toString();
        User user = userRepository.getUserByTechnicalId(technicalId);
        if (user == null) {
            throw new UsernameNotFoundException("User with technical id'" + technicalId + "' not found.");
        }
        List<Roles> roleNames = rolesRepository.getRolesByUserId(user.getId());
        if (roleNames == null) {
            throw new UsernameNotFoundException("User with technical id'" + technicalId + "' not found.");
        }
        grantList = new ArrayList<GrantedAuthority>();

        for (Roles role : roleNames) {
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+role.getName());
            grantList.add(authority);
        }
        return new org.springframework.security.core.userdetails.User(technicalId, credential, grantList);
//        return new org.springframework.security.core.userdetails.User(technicalId, credential,  Collections.singletonList(new SimpleGrantedAuthority(roleNames.get(0).getName())));

    }
}
