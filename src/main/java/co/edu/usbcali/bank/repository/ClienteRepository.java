package co.edu.usbcali.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.usbcali.bank.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	List<Cliente> findByNombre(String nombre);
	
	@Query("SELECT cli FROM Cliente cli WHERE cli.tipoDocumento.tdocId=?1")
	List<Cliente> findByTipoDocumento(Long id);
}
