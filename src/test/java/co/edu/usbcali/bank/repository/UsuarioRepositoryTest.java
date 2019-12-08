package co.edu.usbcali.bank.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

@SpringBootTest
@Rollback(false)
class UsuarioRepositoryTest {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	TipoUsuarioRepository tipoUsuarioRepository;
	
	private final static Logger log=LoggerFactory.getLogger(ClienteRepositoryTest.class);
	private final static String usuUsuario="Edy";
	
	@Test
	@DisplayName("save")
	void aTest() {
		assertNotNull(usuarioRepository);
		assertNotNull(tipoUsuarioRepository);
		
		assertFalse(usuarioRepository.findById(usuUsuario).isPresent());
		Usuario usuario = new Usuario();
		
		usuario.setActivo("S");
		usuario.setUsuUsuario(usuUsuario);
		usuario.setClave("123456");
		usuario.setIdentificacion(new BigDecimal(1113516813));
		usuario.setNombre("Edilberto Ramos");
		
		assertTrue(tipoUsuarioRepository.findById(3L).isPresent());
		usuario.setTipoUsuario(tipoUsuarioRepository.findById(3L).get());
		
		usuarioRepository.save(usuario);
		
	}

	@Test
	@DisplayName("findById")
	void bTest() {
		assertNotNull(usuarioRepository);
		assertNotNull(tipoUsuarioRepository);

		Optional<Usuario> usuarioOptional=usuarioRepository.findById(usuUsuario);
		assertTrue(usuarioOptional.isPresent());
		
		usuarioOptional.ifPresent(usuario->{
			log.info(usuario.getNombre());
		});
		
	}
	
	@Test
	@DisplayName("update")
	void cTest() {
		assertNotNull(usuarioRepository);
		assertNotNull(tipoUsuarioRepository);
		
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuUsuario);
		assertTrue(usuarioOptional.isPresent());
		
		Usuario usuario = usuarioOptional.get();
		
		usuario.setActivo("N");
		
		usuarioRepository.save(usuario);
	}
	
	@Test
	@DisplayName("delete")
	void dTest() {
		assertNotNull(usuarioRepository);
		assertNotNull(tipoUsuarioRepository);
		
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuUsuario);
		assertTrue(usuarioOptional.isPresent());
		
		Usuario usuario = usuarioOptional.get();
		
		usuarioRepository.delete(usuario);
	}
	
	@Test
	@DisplayName("findAll")
	void eTest() {
		assertNotNull(usuarioRepository);
		assertNotNull(tipoUsuarioRepository);
		
		List<Usuario> losUsuarios = usuarioRepository.findAll();
		assertNotNull(losUsuarios);
		assertFalse(losUsuarios.isEmpty());
		
		losUsuarios.forEach(usuario->{
			log.info("Identificación: " + usuario.getIdentificacion());
			log.info("Nombre: " + usuario.getNombre());
		});
	}
	
	@Test
	@DisplayName("findByNombre")
	void findByNombreTest() {
		assertNotNull(usuarioRepository);
		assertNotNull(tipoUsuarioRepository);
		
		List<Usuario> losUsuarios = usuarioRepository.findByNombre("Carmela Allbrook");
		assertNotNull(losUsuarios);
		assertFalse(losUsuarios.isEmpty());
		
		losUsuarios.forEach(usuario->{
			log.info("Identificación: " + usuario.getIdentificacion());
			log.info("Nombre: " + usuario.getNombre());
		});
	}
	
	@Test
	@DisplayName("findByTipoUsuario")
	void findByTipoUsuarioTest()
	{
		assertNotNull(usuarioRepository);
		assertNotNull(tipoUsuarioRepository);
		
		List<Usuario> losUsuarios = usuarioRepository.findByTipoUsuario(2L);
		
		assertNotNull(losUsuarios);
		assertFalse(losUsuarios.isEmpty());
		
		losUsuarios.forEach(usuario->{
			log.info("Identificación: " + usuario.getIdentificacion());
			log.info("Nombre: " + usuario.getNombre());
		});
	}
}
