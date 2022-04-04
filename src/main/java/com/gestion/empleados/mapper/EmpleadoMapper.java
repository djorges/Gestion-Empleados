package com.gestion.empleados.mapper;

import com.gestion.empleados.entity.Empleado;
import com.gestion.empleados.entity.Rol;
import com.gestion.empleados.model.EmpleadoModel;
import org.springframework.data.domain.Page;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class EmpleadoMapper {

	public Empleado mapEntity(EmpleadoModel model) {

		return new Empleado(model.getNombre(), model.getApellido(), model.getDni(), model.getPassword());
	}
	public EmpleadoModel mapModel(Empleado empleado) {

		return new EmpleadoModel(empleado.getId(), empleado.getNombre(), empleado.getApellido(), empleado.getDni(), empleado.getPassword());
	}

	public Page<EmpleadoModel> mapPage(Page<Empleado> pages){
		return pages.map( empleado -> new EmpleadoModel(
				empleado.getId(),
				empleado.getNombre(),
				empleado.getApellido(),
				empleado.getDni(),
				empleado.getPassword()
		));
	}
}
