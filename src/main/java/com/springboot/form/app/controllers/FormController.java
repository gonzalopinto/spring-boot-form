package com.springboot.form.app.controllers;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.springboot.form.app.editors.NombreMayusculaEditor;
import com.springboot.form.app.editors.PaisPropertyEditor;
import com.springboot.form.app.editors.RolesEditor;
import com.springboot.form.app.models.Pais;
import com.springboot.form.app.models.Role;
import com.springboot.form.app.models.User;
import com.springboot.form.app.services.IPaisService;
import com.springboot.form.app.services.IRoleService;
import com.springboot.form.app.validations.UserValidator;

@Controller
//mismo nombre que se quieren mantener en sesion, en este caso el user
@SessionAttributes("user")
public class FormController {

	@Autowired
	private UserValidator validator;

	@Autowired
	private IPaisService paisService;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private PaisPropertyEditor paisEditor;

	@Autowired
	private RolesEditor roleEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Con el set se establece el validador y se pierden la validacion de las
		// anotaciones
		// binder.setValidator(validator);

		// Con el add añadimos el validador y no se pierden las anotaciones
		binder.addValidators(validator);

		// El datepicket siempre es con guion, aunque aparezca con /
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// establece que sea estricto el formato de la fecha, no laxo
		format.setLenient(false);
		// este afecta a todos los dates del formulario
		// binder.registerCustomEditor(Date.class, new CustomDateEditor(format, false));
		// este SOLO afecta al campo "fechaNacimiento" del formulario
		binder.registerCustomEditor(Date.class, "fechaNacimiento", new CustomDateEditor(format, false));

		binder.registerCustomEditor(String.class, "nombre", new NombreMayusculaEditor());
		binder.registerCustomEditor(String.class, "apellidos", new NombreMayusculaEditor());

		binder.registerCustomEditor(Pais.class, paisEditor);
		binder.registerCustomEditor(Role.class, roleEditor);
	}

	@ModelAttribute("listaPaises")
	private List<Pais> listaPaises() {
		return paisService.listar();
	}

	@ModelAttribute("paises")
	private List<String> paises() {
		return Arrays.asList("España", "Italia", "Francia", "Alemania", "Grecia", "Turquía");
	}

	@SuppressWarnings("serial")
	@ModelAttribute("paisesMap")
	private Map<String, String> paisesMap() {
		return new HashMap<>() {
			{
				put("ES", "España");
				put("IT", "Italia");
				put("FR", "Francia");
				put("DE", "Alemania");
				put("GR", "Grecia");
				put("TK", "Turquía");
			}
		};
	}

	@ModelAttribute("listaRoles")
	private List<Role> roles() {
		return roleService.listarRoles();
	}

	@ModelAttribute("rolesStr")
	private List<String> rolesStr() {
		return Arrays.asList("ROLE_ADMIN", "ROLE_USER", "ROLE_MODERATOR");
	}

	@SuppressWarnings("serial")
	@ModelAttribute("rolesMap")
	private Map<String, String> rolesMap() {
		return new HashMap<>() {
			{
				put("ROLE_ADMIN", "Administrador");
				put("ROLE_USER", "Usuario");
				put("ROLE_MODERATOR", "Moderador");
			}
		};
	}

	@ModelAttribute("genero")
	private List<String> genero() {
		return Arrays.asList("Hombre", "Mujer");
	}

	@GetMapping("/form")
	public String form(Model model) {
		model.addAttribute("titulo", "Formulario");
		User user = new User();
		user.setNombre("John");
		user.setApellidos("Doe");
		user.setId("12.567.222-K");
		user.setUsername("Gon");
		user.setCuenta(6);
		user.setEmail("gonzalo.pinto@gmail.com");
		user.setHabilitar(true);
		user.setValorSecreto("Algun valor secreto");
		user.setPais(new Pais(5, "GR", "Grecia"));
		user.setRoles(Arrays.asList(new Role(2, "User", "ROLE_USER"), new Role(3, "Moderador", "ROLE_MODERADOR")));
		model.addAttribute("user", user);
		return "form";
	}

	// @ModelAttribute se pone el nombre que habrá en la vista para usarlo
	@PostMapping("/form")
	public String procesar(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
		// , SessionStatus status) {
		// , @RequestParam String username, @RequestParam String password,
		// @RequestParam String email) {
		// User user = new User(username, password, email);

		// userValidator.validate(user, result);

		// model.addAttribute("titulo", "Resultado form");
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Resultado form");
//			Map<String, String> errores = new HashMap<>();
//			result.getFieldErrors().forEach(err -> {
//				errores.put(err.getField(), err.getDefaultMessage());
//			});
//			model.addAttribute("errores", errores);		

			return "form";
		}
		model.addAttribute("user", user);
		// para que se limpie el objeto user al terminar el formulario
		// status.setComplete();

		return "redirect:/detalle";
	}

	@GetMapping("/detalle")
	public String detalle(@SessionAttribute(name = "user", required = false) User user, Model model,
			SessionStatus status) {
		if (user == null)
			return "redirect:/form";
		
		model.addAttribute("titulo", "Resultado form");

		status.setComplete();
		return "resultado";
	}

}
