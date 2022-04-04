package com.gestion.empleados.service;

import java.util.Collection;

import com.gestion.empleados.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gestion.empleados.entity.Empleado;
import com.gestion.empleados.mapper.EmpleadoMapper;
import com.gestion.empleados.model.EmpleadoModel;
import com.gestion.empleados.repository.EmpleadoRepository;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService {
	
	@Autowired
	private EmpleadoRepository repository;

	@Autowired
	private EmpleadoMapper mapper;

	@Override
	public Empleado guardar(EmpleadoModel empleadoModel) {
		
		return repository.save(mapper.mapEntity(empleadoModel));
	}

	@Override
	public Collection<Empleado> listar() {
		return repository.findAll();
	}

	@Override
	public void borrar(Integer id) {
		//Find entity by id
		Empleado empleado = repository.getById(id.longValue());
		
		//Delete entity
		repository.delete(empleado);
	}

	@Override
	public Page<EmpleadoModel> listarPaginado(int page, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
				Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(page - 1, pageSize, sort);

		return mapper.mapPage(repository.findAll(pageable));
	}

	@Override
	public EmpleadoModel obtenerEmpleado(Integer id) {
		Empleado empleado = repository.getById(id.longValue());

		return mapper.mapModel(empleado);
	}
}
