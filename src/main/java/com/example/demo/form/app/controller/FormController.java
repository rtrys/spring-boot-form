package com.example.demo.form.app.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.app.model.domain.User;

@Controller
public class FormController {

	@GetMapping("/form")
	public String form(Model model) {

		model.addAttribute("title", "Formulariode creacion de usuario");
		
		return "form";
	}

//	@PostMapping("/form")
//	public String processForm(
//			Model model,
//			@RequestParam String username,
//			@RequestParam String password,
//			@RequestParam String email) {
//
//		model.addAttribute("title", "Resultado del formulario");
//		
//		User user = new User();
//		
//		user.setUsername(username);
//		user.setPassword(password);
//		user.setEmail(email);
//
//		model.addAttribute("user", user);
//		
//		return "result";
//	}

//	@PostMapping("/form")
//	public String processForm(User user, Model model) {
//
//		model.addAttribute("title", "Resultado del formulario");
//		model.addAttribute("user", user);
//		
//		return "result";
//	}

	@PostMapping("/form")
	public String processForm(@Valid User user, BindingResult result, Model model) {
		
		model.addAttribute("title", "Resultado del formulario");

		if (result.hasErrors()) {
			Map<String, String> errors = new HashMap<>();

			result.getFieldErrors().forEach(err -> {
				errors.put(err.getField(), "The field ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
			});
			
			model.addAttribute("error", errors);

			return "form";
		}

		model.addAttribute("user", user);
		
		return "result";
	}

}
