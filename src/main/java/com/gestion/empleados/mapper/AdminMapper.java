package com.gestion.empleados.mapper;

import com.gestion.empleados.entity.Admin;
import com.gestion.empleados.model.AdminModel;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper{

	public Admin mapEntity(AdminModel model) {
		Admin admin = new Admin(
			model.getNombre(),
			model.getApellido(),
			model.getEmail(),
			model.getPassword(),
			model.getRoles()
		);
		
		return admin;
	}
	public AdminModel mapModel(Admin admin) {
		AdminModel model = new AdminModel(
			admin.getId(),
			admin.getNombre(),
			admin.getApellido(),
			admin.getEmail(),
			admin.getPassword()
		);
		
		return model;
	}
}
