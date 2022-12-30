package com.springboot.form.app.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.form.app.models.Role;

@Service
public class RoleServiceImpl implements IRoleService {

	private List<Role> listaRoles;

	public RoleServiceImpl() {
		super();
		listaRoles = Arrays.asList(new Role(1, "Administrador", "ROLE_ADMIN"), new Role(2, "User", "ROLE_USER"),
				new Role(3, "Moderador", "ROLE_MODERADOR"));
	}

	@Override
	public List<Role> listarRoles() {
		return listaRoles;
	}

	@Override
	public Role getById(Integer id) {
		return listaRoles.stream().filter(r -> id.equals(r.getId())).findFirst().orElse(null);
	}

}
