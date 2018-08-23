package com.example.project21;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AppUser findByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public Long countByEmail(String email){
        return userRepository.countByEmail(email);
    }
    public AppUser findByUsername(String username){
        return userRepository.findByUserName(username);
    }
    public void saveUser(AppUser appUser){
        appUser.setRoles(Arrays.asList(roleRepository.findByAppRole("USER")));
        appUser.setEnabled(true);
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        userRepository.save(appUser);
    }
    public void saveAdmin(AppUser appUser){
        appUser.setRoles(Arrays.asList(roleRepository.findByAppRole("ADMIN")));
        appUser.setEnabled(true);
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        userRepository.save(appUser);
    }
}
