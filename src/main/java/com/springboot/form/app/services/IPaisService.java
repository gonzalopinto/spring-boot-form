package com.springboot.form.app.services;

import java.util.List;

import com.springboot.form.app.models.Pais;

public interface IPaisService {

	public List<Pais> listar();
	
	public Pais getById(Integer id);
}
