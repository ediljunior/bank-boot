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

import co.edu.usbcali.bank.domain.Cliente;

@SpringBootTest
@Rollback(false)
class ClienteRepositoryTest {
	
	@Autowired //Inyeccion de dependencia
	ClienteRepository clienteRepository;
	
	@Autowired //Inyeccion de dependencia
	TipoDocumentoRepository tipoDocumentoRepository;
	
	private final static Logger log=LoggerFactory.getLogger(ClienteRepositoryTest.class);
	private final static Long clieId=8080L;
	
	@Test
	@DisplayName("save")
	void aTest() {
		
		assertNotNull(clienteRepository);
		assertNotNull(tipoDocumentoRepository);
		
		assertFalse(clienteRepository.findById(clieId).isPresent());
		Cliente cliente = new Cliente();
		
		cliente.setActivo("S");
		cliente.setClieId(clieId);
		cliente.setDireccion("Avenida 123");
		cliente.setEmail("homeroj@correo.com");
		cliente.setNombre("Homero J Simpson");
		cliente.setTelefono("555-55-555");
		
		assertTrue(tipoDocumentoRepository.findById(2L).isPresent());
		cliente.setTipoDocumento(tipoDocumentoRepository.findById(2L).get());
		
		clienteRepository.save(cliente);
		
	}

	@Test
	@DisplayName("findById")
	void bTest() {
		
		assertNotNull(clienteRepository);
		assertNotNull(tipoDocumentoRepository);
		
		Optional<Cliente> clienteOptional=clienteRepository.findById(clieId);
		assertTrue(clienteOptional.isPresent());
		
		clienteOptional.ifPresent(cliente->{
			log.info(cliente.getNombre());
		});
		
	}
	
	@Test
	@DisplayName("update")
	void cTest() {
		
		assertNotNull(clienteRepository);
		assertNotNull(tipoDocumentoRepository);
		
		Optional<Cliente> clienteOptional=clienteRepository.findById(clieId);
		assertTrue(clienteOptional.isPresent());
		
		Cliente cliente = clienteOptional.get();
		cliente.setActivo("N");
		
		clienteRepository.save(cliente);
		
	}
	
	@Test
	@DisplayName("delete")
	void dTest() {
		
		assertNotNull(clienteRepository);
		assertNotNull(tipoDocumentoRepository);
		
		Optional<Cliente> clienteOptional=clienteRepository.findById(clieId);
		assertTrue(clienteOptional.isPresent());
		
		Cliente cliente = clienteOptional.get();
		
		clienteRepository.delete(cliente);
		
	}
	
	@Test
	@DisplayName("findAll")
	void eTest() {
		
		assertNotNull(clienteRepository);
		assertNotNull(tipoDocumentoRepository);
		
		List<Cliente> losClientes=clienteRepository.findAll();
		
		assertNotNull(losClientes);
		assertFalse(losClientes.isEmpty());
		
		losClientes.forEach(cliente->{
			log.info("Id: " + cliente.getClieId());
			log.info("Nombre: " + cliente.getNombre());
		});
		
	}
	
	@Test
	@DisplayName("findByNombre")
	void findByNombre() {
		
		assertNotNull(clienteRepository);
		assertNotNull(tipoDocumentoRepository);
		
		List<Cliente> losClientes=clienteRepository.findByNombre("Jerrie Cannell");
		assertNotNull(losClientes);
		assertFalse(losClientes.isEmpty());
		
		losClientes.forEach(cliente->{
			log.info("Id: " + cliente.getClieId());
			log.info("Nombre: " + cliente.getNombre());
		});		
	}
	
	@Test
	@DisplayName("findByTipoDocumento")
	void findByTipoDocumento() {
		
		assertNotNull(clienteRepository);
		assertNotNull(tipoDocumentoRepository);
		
		List<Cliente> losClientes=clienteRepository.findByTipoDocumento(2L);
		assertNotNull(losClientes);
		assertFalse(losClientes.isEmpty());
		
		losClientes.forEach(cliente->{
			log.info("Id: " + cliente.getClieId());
			log.info("Nombre: " + cliente.getNombre());
		});		
	}
}
