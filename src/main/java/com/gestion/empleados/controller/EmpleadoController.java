package com.gestion.empleados.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.gestion.empleados.GestionEmpleadosApplication;
import com.gestion.empleados.entity.Empleado;
import com.gestion.empleados.model.EmpleadoModel;
import com.gestion.empleados.service.IEmpleadoService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {
	private final Logger log = LoggerFactory.getLogger(GestionEmpleadosApplication.class);
	private final int PAGE_SIZE = 5;

	@Autowired
	private IEmpleadoService service;

	@GetMapping("/")
	public String listar(Model model) {
		return listarPaginado(1, "nombre","asc",model);
	}
	
	@GetMapping("/registro")
	public String registro() {
		return "registro_empleado";
	}

	@PostMapping("/registro")
	public String registro(
		@Valid @ModelAttribute("empleado") EmpleadoModel empleadoModel,
		BindingResult resultadoValidacion
	) {
		log.info("Objeto, {}", empleadoModel);

		if(resultadoValidacion.hasErrors()){
			return "registro_empleado";
		}else{
			service.guardar(empleadoModel);
			return "redirect:/empleados/";
		}
	}
	
	@GetMapping("/editar/{id}")
	public String edit(@PathVariable Integer id, Model model) { 

		EmpleadoModel empleado = service.obtenerEmpleado(id);
		log.info("Objeto recuperado {}", empleado);

		model.addAttribute("empleado", empleado);

		return "editar_empleado";
	}

	@GetMapping("/borrar/{id}")
	public String borrar(@PathVariable Integer id, Model model) {
		service.borrar(id);
		
		return "redirect:/empleados/";
	}

	@GetMapping("/{page}")
	public String listarPaginado(
		@PathVariable("page") int page,
		@RequestParam(value = "sortField") String sortField,
		@RequestParam(value = "orderBy") String sortDir,
		Model model
	){
		Page<EmpleadoModel> pageable = service.listarPaginado(page, PAGE_SIZE, sortField, sortDir);

		//Add model attributes
		model.addAttribute("empleados", pageable.getContent());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", pageable.getTotalPages());
		model.addAttribute("totalItems",pageable.getTotalElements());

		model.addAttribute("sortField",sortField);
		model.addAttribute("orderBy", sortDir);
		model.addAttribute("reverseOrderBy", sortDir.equals("asc") ? "desc": "asc");
		return "listar_empleados_paginado";
	}

	@ModelAttribute("empleado")
	public EmpleadoModel getEmpleadoModel() {
		return new EmpleadoModel();
	}
}
