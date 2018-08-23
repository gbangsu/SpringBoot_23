package com.example.project21;

import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<AppRole,Long>{


    AppRole findByAppRole(String appRole);
}
