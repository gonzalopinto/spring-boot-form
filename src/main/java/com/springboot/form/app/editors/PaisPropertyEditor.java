package com.springboot.form.app.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.form.app.services.IPaisService;

@Component
public class PaisPropertyEditor extends PropertyEditorSupport {

	@Autowired
	private IPaisService paisService;

	@Override
	public void setAsText(String id) throws IllegalArgumentException {
		if (id != null && !id.isEmpty())
			this.setValue(paisService.getById(Integer.parseInt(id)));
		else
			this.setValue(null);
	}

}
