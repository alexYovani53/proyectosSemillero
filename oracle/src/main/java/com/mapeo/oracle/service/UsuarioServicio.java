package com.mapeo.oracle.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapeo.oracle.entity.Usuario;
import com.mapeo.oracle.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
@CrossOrigin
public class UsuarioServicio {

	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@GetMapping(path= "/buscar")
	public List<Usuario> getUsuarios(){
		return usuarioRepository.findAll();
	}
	
	@GetMapping(path= "/buscar/por/email/{email}")
	public List<Usuario> getUsuarios(@PathVariable("email") String email){
		return usuarioRepository.findByEmail(email);
	}
	
	
	@PostMapping(path="/guardar")
	public Usuario guardar(@RequestBody Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	@DeleteMapping(path="/eliminar/{idusuario}")
	public void eliminar(@PathVariable("idusuario") int idusuario) {
		Optional<Usuario> usuario = usuarioRepository.findById(idusuario);
		if(usuario.isPresent()) {
			usuarioRepository.delete(usuario.get());
		}
	}
	
}
