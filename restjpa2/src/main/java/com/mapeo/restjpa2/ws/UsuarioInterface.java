package com.mapeo.restjpa2.ws;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mapeo.restjpa2.dto.UsuarioDto;
import com.mapeo.restjpa2.entity.Usuario;

@RestController
@RequestMapping("/usuario")
@CrossOrigin
public interface UsuarioInterface {

	
	@GetMapping("/getall")
	public List<Usuario> getAll();
	
	@GetMapping("/login")
	public List<Usuario> findByCorreoPassword(@RequestParam("correo") String correo, @RequestParam("password") String password);
	
	
	@PostMapping("/post")
	public ResponseEntity<Usuario> guardarUsuario(@RequestBody UsuarioDto nuevoUsuario);
}
