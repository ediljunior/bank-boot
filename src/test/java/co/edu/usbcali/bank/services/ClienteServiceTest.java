package co.edu.usbcali.bank.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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
import co.edu.usbcali.bank.repository.TipoDocumentoRepository;
import co.edu.usbcali.bank.service.ClienteService;

@SpringBootTest
@Rollback(false)
class ClienteServiceTest {

	@Autowired //Inyeccion de dependencia
	ClienteService clienteService;
	
	@Autowired //Inyeccion de dependencia
	TipoDocumentoRepository tipoDocumentoRepository;
	
	private final static Logger log=LoggerFactory.getLogger(ClienteServiceTest.class);
	private final static Long clieId=8080L;
	
	@Test
	@DisplayName("save")
	void aTest() {
		assertNotNull(clienteService);
		assertNotNull(tipoDocumentoRepository);
		
		Cliente cliente = new Cliente();
		
		cliente.setActivo("S");
		cliente.setClieId(clieId);
		cliente.setDireccion("Avenida 123");
		cliente.setEmail("homeroj@correo.com");
		cliente.setNombre("Homero J Simpson");
		cliente.setTelefono("555-55-555");
		
		assertTrue(tipoDocumentoRepository.findById(2L).isPresent());
		cliente.setTipoDocumento(tipoDocumentoRepository.findById(2L).get());
		
		try {
			cliente = clienteService.save(cliente);
			assertNotNull(cliente,"No retornó el cliente");
		} catch (Exception e) {
			assertNull(e, e.getMessage());
		}
		
	}
	
	@Test
	@DisplayName("update")
	void bTest() {
		assertNotNull(clienteService);
		assertNotNull(tipoDocumentoRepository);
		
		Optional<Cliente> optionalCliente = clienteService.findById(clieId);
		assertTrue(optionalCliente.isPresent(), "El cliente no existe");
		
		Cliente cliente = optionalCliente.get();
		
		cliente.setActivo("N");
		
		try {
			cliente = clienteService.update(cliente);
			assertNotNull(cliente,"No retornó el cliente");
		} catch (Exception e) {
			assertNull(e, e.getMessage());
		}
		
	}

	@Test
	@DisplayName("findById")
	void cTest() {
		assertNotNull(clienteService);
		assertNotNull(tipoDocumentoRepository);
		assertNotNull(clienteService.findById(clieId).get(), "El cliente no existe");
	}
	
	@Test
	@DisplayName("delete")
	void dTest() {
		assertNotNull(clienteService);
		assertNotNull(tipoDocumentoRepository);		
		
		Optional<Cliente> optionalCliente = clienteService.findById(clieId);
		assertTrue(optionalCliente.isPresent(), "El cliente no existe");
		
		Cliente cliente = optionalCliente.get();
		
		try {
			clienteService.delete(cliente);
		} catch (Exception e) {
			assertNull(e, e.getMessage());
		}
		
	}
	
	@Test
	@DisplayName("findAll")
	void eTest() {
		assertNotNull(clienteService);
		List<Cliente> losClientes=clienteService.findAll();
		assertNotNull(losClientes, "La lista es nula");
		assertFalse(losClientes.isEmpty(), "No Existen clientes la lista esta vacia");
	}
}
