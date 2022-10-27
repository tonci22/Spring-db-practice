package com.example.demo.service.Implementation;

import com.example.demo.domain.Privilege;
import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.dto.request.UserRequestDto;
import com.example.demo.dto.request.UserLoginRequestDto;
import com.example.demo.enums.RoleType;
import com.example.demo.mapper.RoleMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("userDetailsServiceImpl")
@Transactional
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username:" + email);
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), user.isEnabled(), true, true,
                true, getAuthorities(user.getRoles()));
    }

    public UserRequestDto save(UserLoginRequestDto userLoginRequestDto) {
        UserRequestDto user = new UserRequestDto();
        user.setEmail(userLoginRequestDto.getUsername());
        user.setEnabled(true);
        user.setFirstName(userLoginRequestDto.getFirstName());
        user.setLastName(userLoginRequestDto.getLastName());
        user.setPassword(BCrypt.hashpw(userLoginRequestDto.getPassword(), BCrypt.gensalt()));
        user.setRoles(List.of(roleMapper.mapToDto(roleRepository.findByName(RoleType.ROLE_USER.toString()))));
        userRepository.save(userMapper.mapToDto(user));

        return user;
    }

    public void initAdminUser() {
        UserRequestDto user = new UserRequestDto();
        user.setEmail("admin");
        user.setEnabled(true);
        user.setFirstName("name");
        user.setLastName("last_name");
        user.setPassword(BCrypt.hashpw("admin", BCrypt.gensalt()));
        user.setRoles(List.of(roleMapper.mapToDto(roleRepository.findByName(RoleType.ROLE_ADMIN.toString()))));
        userRepository.save(userMapper.mapToDto(user));
    }

    public void initUser() {
        UserRequestDto user = new UserRequestDto();
        user.setEmail("user");
        user.setEnabled(true);
        user.setFirstName("name");
        user.setLastName("last_name");
        user.setPassword(BCrypt.hashpw("user", BCrypt.gensalt()));
        user.setRoles(List.of(roleMapper.mapToDto(roleRepository.findByName(RoleType.ROLE_USER.toString()))));
        userRepository.save(userMapper.mapToDto(user));
    }


    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<String> getPrivileges(Collection<Role> roles) {

        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();

        for (Role role : roles) {
            privileges.add(role.getName());
            collection.addAll(role.getPrivileges());
        }
        for (Privilege item : collection) {
            privileges.add(item.getName());
        }
        return privileges;
    }

    public List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {

        List<GrantedAuthority> authorities = new ArrayList<>();

        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}
