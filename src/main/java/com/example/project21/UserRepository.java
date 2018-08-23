package com.example.project21;


import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<AppUser, Long>{
    AppUser findByUserName(String username);

    AppUser findByEmail(String email);
    Long countByEmail(String email);
    Long countByUserName(String username);
}

