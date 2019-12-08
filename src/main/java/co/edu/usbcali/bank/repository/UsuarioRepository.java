package co.edu.usbcali.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.usbcali.bank.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{

	List<Usuario> findByNombre(String nombre);
	
	@Query("SELECT usu FROM Usuario usu WHERE usu.tipoUsuario.tiusId=?1")
	List<Usuario> findByTipoUsuario(Long id);
}
