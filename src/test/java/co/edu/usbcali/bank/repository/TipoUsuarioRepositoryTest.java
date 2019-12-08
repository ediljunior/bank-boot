package co.edu.usbcali.bank.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import co.edu.usbcali.bank.domain.TipoUsuario;

@SpringBootTest
@Rollback(false)
class TipoUsuarioRepositoryTest {

	@Autowired
	TipoUsuarioRepository tipoUsuarioRepository;
	
	private final static Logger log=LoggerFactory.getLogger(TipoUsuarioRepositoryTest.class);
	private final static Long tiusId=99L;
	
	@Test
	@DisplayName("save")
	void aTest() {
		assertNotNull(tipoUsuarioRepository);
		
		assertFalse(tipoUsuarioRepository.findById(tiusId).isPresent());
		TipoUsuario tipoUsuario = new TipoUsuario();
		
		tipoUsuario.setActivo("S");
		tipoUsuario.setTiusId(tiusId);
		tipoUsuario.setNombre("Asesor Auxiliar");
		
		tipoUsuarioRepository.save(tipoUsuario);
		
	}

	@Test
	@DisplayName("findById")
	void bTest() {
		assertNotNull(tipoUsuarioRepository);

		Optional<TipoUsuario> tipoUsuarioOptional=tipoUsuarioRepository.findById(tiusId);
		assertTrue(tipoUsuarioOptional.isPresent());
		
		tipoUsuarioOptional.ifPresent(tipoUsuario->{
			log.info(tipoUsuario.getNombre());
		});
		
	}
	
	@Test
	@DisplayName("update")
	void cTest() {
		assertNotNull(tipoUsuarioRepository);
		
		Optional<TipoUsuario> tipoUsuarioOptional = tipoUsuarioRepository.findById(tiusId);
		assertTrue(tipoUsuarioOptional.isPresent());
		
		TipoUsuario tipoUsuario = tipoUsuarioOptional.get();
		
		tipoUsuario.setActivo("N");
		
		tipoUsuarioRepository.save(tipoUsuario);
	}
	
	@Test
	@DisplayName("delete")
	void dTest() {
		assertNotNull(tipoUsuarioRepository);
		
		Optional<TipoUsuario> tipoUsuarioOptional = tipoUsuarioRepository.findById(tiusId);
		assertTrue(tipoUsuarioOptional.isPresent());
		
		TipoUsuario tipoUsuario = tipoUsuarioOptional.get();
		
		tipoUsuarioRepository.delete(tipoUsuario);
	}
	
	@Test
	@DisplayName("findAll")
	void eTest() {
		assertNotNull(tipoUsuarioRepository);
		
		List<TipoUsuario> losTiposUsuario = tipoUsuarioRepository.findAll();
		assertNotNull(losTiposUsuario);
		assertFalse(losTiposUsuario.isEmpty());
		
		losTiposUsuario.forEach(tipoUsuario->{
			log.info("Id: " + tipoUsuario.getTiusId());
			log.info("Nombre: " + tipoUsuario.getNombre());
		});
	}
	
	@Test
	@DisplayName("findByNombre")
	void findByNombreTest() {
		assertNotNull(tipoUsuarioRepository);
		
		List<TipoUsuario> losTiposUsuario = tipoUsuarioRepository.findByNombre("CAJERO");
		assertNotNull(losTiposUsuario);
		assertFalse(losTiposUsuario.isEmpty());
		
		losTiposUsuario.forEach(tipoUsuario->{
			log.info("Id: " + tipoUsuario.getTiusId());
			log.info("Nombre: " + tipoUsuario.getNombre());
		});
	}
	
	@Test
	@DisplayName("findByNombreAndActivo")
	void findByNombreAndActivoTest()
	{
		assertNotNull(tipoUsuarioRepository);
		
		List<TipoUsuario> losTiposUsuario = tipoUsuarioRepository.findByNombreAndActivo("ASESOR COMERCIAL","S");
		assertNotNull(losTiposUsuario);
		assertFalse(losTiposUsuario.isEmpty());
		
		losTiposUsuario.forEach(tipoUsuario->{
			log.info("Id: " + tipoUsuario.getTiusId());
			log.info("Nombre: " + tipoUsuario.getNombre());
		});
	}

}
