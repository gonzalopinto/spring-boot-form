package com.springboot.form.app.models;

public class Role {

	private Integer id;

	private String nombre;

	private String role;

	public Role(Integer id, String nombre, String role) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.role = role;
	}

	public Role() {
		super();
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Role))
			return false;
		Role rol = (Role) obj;
		return getId() != null && getId().equals(rol.getId());
	}

}
