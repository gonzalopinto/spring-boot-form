package com.springboot.form.app.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.form.app.services.IRoleService;

@Component
public class RolesEditor extends PropertyEditorSupport {

	@Autowired
	private IRoleService roleService;

	@Override
	public void setAsText(String id) throws IllegalArgumentException {
		if (id != null && !id.isEmpty())
			this.setValue(roleService.getById(Integer.parseInt(id)));
		else
			this.setValue(null);
	}

}
