package co.edu.usbcali.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.usbcali.bank.domain.TipoUsuario;
import co.edu.usbcali.bank.domain.Usuario;

public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Long>{

	List<TipoUsuario> findByNombre(String nombre);
	
	List<TipoUsuario> findByNombreAndActivo(String nombre, String activo);
}
