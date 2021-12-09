package com.holamundo.holamundo.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/miServicio")
public class misServicios {
	
	
	@GetMapping(path="/holaMundo/{nombre}")	
	public String holaMundo(@PathVariable("nombre") String nombre) {
		return "Bienvenido! " + nombre + ". Este es mi primer paso con SpringBoot";
	}
	
	
}
