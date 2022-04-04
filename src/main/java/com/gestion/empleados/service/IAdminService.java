package com.gestion.empleados.service;

import java.util.Collection;

import com.gestion.empleados.entity.Admin;
import com.gestion.empleados.model.AdminModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IAdminService extends UserDetailsService {
	Admin guardar(AdminModel model);
	AdminModel obtenerAdmin(Integer id);
}
