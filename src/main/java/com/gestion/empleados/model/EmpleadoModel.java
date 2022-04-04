package com.gestion.empleados.model;


import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmpleadoModel {
	private Long id;

	@NotBlank(message = "Campo Requerido")
	@Size(min=3, message = "Valor minimo: 3 caracteres")
	private String nombre;

	@NotBlank(message = "Campo Requerido")
	@Size(min=3, message = "Valor minimo: 3 caracteres")
	private String apellido;

	@NotNull(message = "Campo Requerido")
	private Integer dni;

	@NotBlank(message = "Campo Requerido")
	@Range(min=6, max = 10, message = "Valor de 6 a 10 caracteres")
	private String password;

	public EmpleadoModel() {
		super();
	}
	public EmpleadoModel(Long id, String nombre, String apellido, Integer dni, String password) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.password = password;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Integer getDni() {
		return dni;
	}
	public void setDni(Integer dni) {
		this.dni = dni;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
