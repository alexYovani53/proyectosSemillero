package com.mapeo.restjpa2.implementacion;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.mapeo.restjpa2.dto.UsuarioDto;
import com.mapeo.restjpa2.entity.Siniestros;
import com.mapeo.restjpa2.entity.Usuario;
import com.mapeo.restjpa2.repository.UsuarioRepository;
import com.mapeo.restjpa2.ws.UsuarioInterface;

@Component
public class UsuarioImplementacion implements UsuarioInterface {

	private static final Log LOG  = LogFactory.getLog(UsuarioImplementacion.class);
	
	@Autowired
	UsuarioRepository repositorioUsuario;
	
	@Override
	public List<Usuario> getAll() {
		return repositorioUsuario.findAll();
	}
	

	@Override
	public List<Usuario> findByCorreoPassword(String correo, String password) {
		return repositorioUsuario.findByCorreoAndPassword(correo, password);
	}
	

	@Override
	public ResponseEntity<Integer> pedirFallo() {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(0);
	}

	@Override
	public ResponseEntity<Usuario> guardarUsuario(UsuarioDto nuevoUsuario) {
		
		Usuario usuario =  convertirUsuarioDtoAUsuario(nuevoUsuario);		
		
		try {
			return ResponseEntity.ok(repositorioUsuario.save(usuario));
		} catch (Exception e) {
			LOG.error("Error encontrado en GUARDAR USUARIO "+ e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	private Usuario convertirUsuarioDtoAUsuario(UsuarioDto usuarioNuevo) {
		
		ModelMapper map =  new ModelMapper();
		return map.map(usuarioNuevo, Usuario.class);
	}

}
