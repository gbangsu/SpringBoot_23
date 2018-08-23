package com.example.project20;


import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<AppUser, Long>{
    AppUser findByUserName(String username);
}

