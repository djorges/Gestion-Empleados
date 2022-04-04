package com.gestion.empleados.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.gestion.empleados.entity.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gestion.empleados.entity.Admin;
import com.gestion.empleados.mapper.AdminMapper;
import com.gestion.empleados.model.AdminModel;
import com.gestion.empleados.repository.AdminRepository;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class AdminServiceImpl implements IAdminService{
	public static final String USER_NOT_FOUND_MESSAGE = "Usuario o password no validos";

	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	public AdminServiceImpl(@Lazy BCryptPasswordEncoder encoder){
		this.passwordEncoder = encoder;
	}

	@Autowired
	private AdminRepository repository;

	@Autowired
	private AdminMapper mapper;

	@Override
	public Admin guardar(AdminModel model) {
		Admin admin = mapper.mapEntity(model);
		admin.setPassword(passwordEncoder.encode(model.getPassword()));
		admin.setRoles(
				List.of(new Rol("ROLE_USER"))
		);
		return repository.save(admin);
	}
	
	@Override
	public AdminModel obtenerAdmin(Integer id) {
		Admin admin = repository.getById(id.longValue());

		return mapper.mapModel(admin);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin admin = repository.findByEmail(username);
		if (admin == null) {
			throw new UsernameNotFoundException(USER_NOT_FOUND_MESSAGE);
		}
		return new User(admin.getEmail(), admin.getPassword(), mapAuthority(admin.getRoles()));
	}
	private Collection<? extends GrantedAuthority> mapAuthority(Collection<Rol> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
	}
}
