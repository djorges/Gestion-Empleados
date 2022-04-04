package com.gestion.empleados.service;

import java.util.Collection;

import com.gestion.empleados.entity.Empleado;
import com.gestion.empleados.model.EmpleadoModel;
import org.springframework.data.domain.Page;

public interface IEmpleadoService {
	Empleado guardar(EmpleadoModel model);
	Collection<Empleado> listar();
	EmpleadoModel obtenerEmpleado(Integer id);
	void borrar(Integer id);
	Page<EmpleadoModel> listarPaginado(int page, int pageSize, String sortField, String sorDirection);
}
