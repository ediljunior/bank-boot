package co.edu.usbcali.bank.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.bank.domain.Usuario;
import co.edu.usbcali.bank.dto.UsuarioDTO;
import co.edu.usbcali.bank.mapper.UsuarioMapper;
import co.edu.usbcali.bank.service.UsuarioService;

@RestController
@RequestMapping("/api/usuario/")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	UsuarioMapper usuarioMapper;

	@DeleteMapping("delete/{id}") //http://127.0.0.1:9090/api/usuario/findById/1
	public ResponseEntity<?> delete(@PathVariable("id") String id) {
		try {
			Optional<Usuario> usuarioOptional = usuarioService.findById(id);
			
			if(!usuarioOptional.isPresent()) {
				throw new Exception("El usuario no existe");
			}
			Usuario usuario = usuarioOptional.get();
			usuarioService.delete(usuario);
			UsuarioDTO usuarioDTO = usuarioMapper.usuarioToUsuarioDTO(usuario);
			return ResponseEntity.ok(usuarioDTO);
		}catch(Exception e) {
			return ResponseEntity.badRequest().body(new Respuesta(400, e.getMessage()));
		}
	}
	
	@PutMapping("update")
	public ResponseEntity<?> update(@RequestBody UsuarioDTO usuarioDTO) {
		try {
			Usuario usuario =usuarioMapper.usuarioDTOToUsuario(usuarioDTO);
			usuario = usuarioService.update(usuario);
			usuarioDTO = usuarioMapper.usuarioToUsuarioDTO(usuario);
			return ResponseEntity.ok(usuarioDTO);
		} catch(Exception e) {
			return ResponseEntity.badRequest().body(new Respuesta(400, e.getMessage()));
		}
	}
	
	@PostMapping("save")
	public ResponseEntity<?> save(@RequestBody UsuarioDTO usuarioDTO) {
		try {
			Usuario usuario =usuarioMapper.usuarioDTOToUsuario(usuarioDTO);
			usuario = usuarioService.save(usuario);
			usuarioDTO = usuarioMapper.usuarioToUsuarioDTO(usuario);
			return ResponseEntity.ok().body(usuarioDTO);
		} catch(Exception e) {
			return ResponseEntity.badRequest().body(new Respuesta(400, e.getMessage()));
		}
	}
	
	@GetMapping("findAll")
	public List<UsuarioDTO> findAll() {
		
		List<Usuario> losUsuarios = usuarioService.findAll();
		List<UsuarioDTO> losUsuariosDTO = usuarioMapper.toUsuarioDTOs(losUsuarios);
		return losUsuariosDTO;
		
	}
	
	@GetMapping("findById/{id}") //http://127.0.0.1:9090/api/usuario/findById/1
	public UsuarioDTO findById(@PathVariable("id") String id) {
		Optional<Usuario> usuarioOptional = usuarioService.findById(id);
		
		if(usuarioOptional.isPresent()) {
			Usuario usuario = usuarioOptional.get();
			UsuarioDTO usuarioDTO = usuarioMapper.usuarioToUsuarioDTO(usuario);
			return usuarioDTO;
		}
		
		return null;
	}

}
