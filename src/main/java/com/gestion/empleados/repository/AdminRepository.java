package com.gestion.empleados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.empleados.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    public Admin findByEmail(String email);
}
