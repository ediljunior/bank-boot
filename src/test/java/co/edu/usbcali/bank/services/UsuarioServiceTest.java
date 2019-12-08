package co.edu.usbcali.bank.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import co.edu.usbcali.bank.domain.Usuario;
import co.edu.usbcali.bank.repository.TipoUsuarioRepository;
import co.edu.usbcali.bank.service.UsuarioService;

@SpringBootTest
@Rollback(false)
class UsuarioServiceTest {

	@Autowired //Inyeccion de dependencia
	UsuarioService usuarioService;
	
	@Autowired //Inyeccion de dependencia
	TipoUsuarioRepository tipoUsuarioRepository;
	
	private final static Logger log=LoggerFactory.getLogger(UsuarioServiceTest.class);
	private final static String usuUsuario="pepitoperez" ;
	
	@Test
	@DisplayName("save")
	void aTest() {
		assertNotNull(usuarioService);
		assertNotNull(tipoUsuarioRepository);
		
		Usuario usuario = new Usuario();
		
		usuario.setActivo("S");
		usuario.setUsuUsuario(usuUsuario);
		usuario.setNombre("Pepito Perez");
		usuario.setClave("ju123");
		usuario.setIdentificacion(new BigDecimal(12345123));
		
		assertTrue(tipoUsuarioRepository.findById(2L).isPresent());
		usuario.setTipoUsuario(tipoUsuarioRepository.findById(2L).get());
		
		try {
			usuario = usuarioService.save(usuario);
			assertNotNull(usuario,"No retornó el usuario");
		} catch (Exception e) {
			assertNull(e, e.getMessage());
		}
		
	}
	
	@Test
	@DisplayName("update")
	void bTest() {
		assertNotNull(usuarioService);
		assertNotNull(tipoUsuarioRepository);
		
		Optional<Usuario> optionalUsuario = usuarioService.findById(usuUsuario);
		assertTrue(optionalUsuario.isPresent(), "El usuario no existe");
		
		Usuario usuario = optionalUsuario.get();
		
		usuario.setActivo("N");
		
		try {
			usuario = usuarioService.update(usuario);
			assertNotNull(usuario,"No retornó el usuario");
		} catch (Exception e) {
			assertNull(e, e.getMessage());
		}
		
	}

	@Test
	@DisplayName("findById")
	void cTest() {
		assertNotNull(usuarioService);
		assertNotNull(tipoUsuarioRepository);
		assertNotNull(usuarioService.findById(usuUsuario).get(), "El usuario no existe");
	}
	
	@Test
	@DisplayName("delete")
	void dTest() {
		assertNotNull(usuarioService);
		assertNotNull(tipoUsuarioRepository);		
		
		Optional<Usuario> optionalUsuario = usuarioService.findById(usuUsuario);
		assertTrue(optionalUsuario.isPresent(), "El usuario no existe");
		
		Usuario usuario = optionalUsuario.get();
		
		try {
			usuarioService.delete(usuario);
		} catch (Exception e) {
			assertNull(e, e.getMessage());
		}
		
	}
	
	@Test
	@DisplayName("findAll")
	void eTest() {
		assertNotNull(usuarioService);
		List<Usuario> losUsuarios=usuarioService.findAll();
		assertNotNull(losUsuarios, "La lista es nula");
		assertFalse(losUsuarios.isEmpty(), "No Existen usuarios la lista esta vacia");
	}
	
}
