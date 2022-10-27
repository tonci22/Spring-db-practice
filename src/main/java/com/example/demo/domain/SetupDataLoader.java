package com.example.demo.domain;


import com.example.demo.enums.PrivilegeType;
import com.example.demo.enums.RoleType;
import com.example.demo.repository.PrivilegeRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.Implementation.UserDetailsServiceImpl;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    boolean alreadySetup = false;


    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PrivilegeRepository privilegeRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserDetailsServiceImpl userDetailsService;

    public SetupDataLoader(UserRepository userRepository, RoleRepository roleRepository,
                           PrivilegeRepository privilegeRepository, PasswordEncoder passwordEncoder,
                           UserDetailsServiceImpl userDetailsService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;

        Privilege readPrivilege = createPrivilegeIfNotFound(PrivilegeType.READ_PRIVILEGE.toString());
        Privilege writePrivilege = createPrivilegeIfNotFound(PrivilegeType.WRITE_PRIVILEGE.toString());

        List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);

        createRoleIfNotFound(RoleType.ROLE_ADMIN.toString(), adminPrivileges);
        createRoleIfNotFound(RoleType.ROLE_USER.toString(), Arrays.asList(readPrivilege));

        userDetailsService.initAdminUser();
        userDetailsService.initUser();

        alreadySetup = true;
    }

    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {

        Privilege privilege = privilegeRepository.findByName(name);

        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {

        Role role = roleRepository.findByName(name);

        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }
}

