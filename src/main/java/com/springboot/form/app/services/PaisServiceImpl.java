package com.springboot.form.app.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.form.app.models.Pais;

@Service
public class PaisServiceImpl implements IPaisService {

	private List<Pais> listaPaises;

	public PaisServiceImpl(List<Pais> listaPaises) {
		super();
		this.setListaPaises(
				Arrays.asList(new Pais(1, "ES", "España"), new Pais(2, "IT", "Italia"), new Pais(3, "FR", "Francia"),
						new Pais(4, "DE", "Alemania"), new Pais(5, "GR", "Grecia"), new Pais(6, "TK", "Turquía")));
	}

	@Override
	public List<Pais> listar() {
		return listaPaises;
	}

	@Override
	public Pais getById(Integer id) {
		return listaPaises.stream().filter(p -> id.equals(p.getId())).findFirst().orElse(null);
	}

//	public List<Pais> getListaPaises() {
//		return listaPaises;
//	}
//
	public void setListaPaises(List<Pais> listaPaises) {
		this.listaPaises = listaPaises;
	}

}
