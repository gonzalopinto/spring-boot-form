package com.springboot.form.app.services;

import java.util.List;

import com.springboot.form.app.models.Role;

public interface IRoleService {
	
	List<Role> listarRoles();
	
	Role getById(Integer id);

}
