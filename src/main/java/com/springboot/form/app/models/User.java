package com.springboot.form.app.models;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.springboot.form.app.validations.IdRegex;
import com.springboot.form.app.validations.Requerido;

public class User {

	// @Pattern(regexp = "[0-9]{2}[.,][\\d]{3}[.,][\\d]{3}[-][A-Z]{1}")
	@IdRegex
	private String id;

	@NotBlank
	@Size(min = 3, max = 10)
	private String username;

	@NotEmpty
	@NotBlank
	private String password;

	@NotEmpty
	@Email
	private String email;

	// @NotEmpty(message = "el nombre no puede ser vacio - G")
	private String nombre;

//	@NotBlank
//	@NotEmpty
	@Requerido
	private String apellidos;

	@NotNull
	@Min(5)
	@Max(5000)
	private Integer cuenta;

	@NotNull
	// @DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past
	private Date fechaNacimiento;

	@NotNull
	@Future
	private Date fechaProximoCumple;

	@NotNull
	private Pais pais;

	@NotEmpty
	private List<Role> roles;

	private Boolean habilitar;

	@NotEmpty
	private String genero;
	
	private String valorSecreto;

	public User() {
		super();
	}

	public User(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public Pais getPais() {
		return this.pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Integer getCuenta() {
		return this.cuenta;
	}

	public void setCuenta(Integer cuenta) {
		this.cuenta = cuenta;
	}

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Date getFechaProximoCumple() {
		return this.fechaProximoCumple;
	}

	public void setFechaProximoCumple(Date fechaProximoCumple) {
		this.fechaProximoCumple = fechaProximoCumple;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Boolean getHabilitar() {
		return this.habilitar;
	}

	public void setHabilitar(Boolean habilitar) {
		this.habilitar = habilitar;
	}

	public String getGenero() {
		return this.genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getValorSecreto() {
		return valorSecreto;
	}

	public void setValorSecreto(String valorSecreto) {
		this.valorSecreto = valorSecreto;
	}

}
