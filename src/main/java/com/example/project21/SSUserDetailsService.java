package com.example.project21;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Transactional
@Service
public class SSUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public SSUserDetailsService(UserRepository userRepo){
        this.userRepository=userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
        throws UsernameNotFoundException {
        System.out.println("Entered method looking for "+username);
        try {
            AppUser appUser = userRepository.findByUserName(username);
            if (appUser == null) {
               throw new UsernameNotFoundException("User not found");
            }

            System.out.println(appUser.getUserName());
            System.out.println(appUser.getPassword()+" is the password passed to Spring Security");
            return new org.springframework.security.core.userdetails.User(
                    appUser.getUserName(),
                    appUser.getPassword(),
                    getAuthorities(appUser));

        }catch (Exception e){
            throw new UsernameNotFoundException("User not found");
        }
        }
        private Set<GrantedAuthority> getAuthorities(AppUser appUser) {
            System.out.println("Checking details of authorities");
            Set<GrantedAuthority> authorities
                    = new HashSet<GrantedAuthority>();
            for (AppRole appRole : appUser.getRoles()) {
                GrantedAuthority grantedAuthority
                        = new SimpleGrantedAuthority(appRole.getAppRole());
                authorities.add(grantedAuthority);

            }
            return authorities;
        }
        }

