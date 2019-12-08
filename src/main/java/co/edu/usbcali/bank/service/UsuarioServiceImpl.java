package co.edu.usbcali.bank.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.bank.domain.TipoUsuario;
import co.edu.usbcali.bank.domain.Usuario;
import co.edu.usbcali.bank.repository.TipoUsuarioRepository;
import co.edu.usbcali.bank.repository.UsuarioRepository;

@Service
@Scope("singleton")
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	TipoUsuarioRepository tipoUsuarioRepository;
	
	@Autowired
	private Validator validator;

	public void validar(Usuario usuario) throws Exception {
		if(usuario == null) {
			throw new Exception("El usuario es nulo");
		}
	    try {	    	
	        Set<ConstraintViolation<Usuario>> constraintViolations = validator.validate(usuario);

	        if (constraintViolations.size() > 0) {
	            StringBuilder strMessage = new StringBuilder();

	            for (ConstraintViolation<Usuario> constraintViolation : constraintViolations) {
	                strMessage.append(constraintViolation.getPropertyPath()
	                                                     .toString());
	                strMessage.append(" - ");
	                strMessage.append(constraintViolation.getMessage());
	                strMessage.append(". \n");
	            }

	            throw new Exception(strMessage.toString());
	        }
	    } catch (Exception e) {
	        throw e;
	    }
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)	
	public Usuario save(Usuario entity) throws Exception {
		validar(entity);
		
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(entity.getUsuUsuario());
		
		if(usuarioOptional.isPresent()) {
			throw new Exception("El usuario con id: " + entity.getUsuUsuario() + " Ya existe");
		}		
		
		Optional<TipoUsuario> tipoUsuarioOptional = tipoUsuarioRepository.findById(entity.getTipoUsuario().getTiusId()); 
		if(!tipoUsuarioOptional.isPresent()) {
			throw new Exception("El tipo de usuario con id: " + entity.getTipoUsuario().getTiusId() + " No Existe.");
		}
		entity.setTipoUsuario(tipoUsuarioOptional.get());
				
		return usuarioRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Usuario update(Usuario entity) throws Exception {
		validar(entity);
		
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(entity.getUsuUsuario());
		
		if(!usuarioOptional.isPresent()) {
			throw new Exception("El usuario con id: " + entity.getUsuUsuario() + " No existe");
		}		
		
		Optional<TipoUsuario> tipoUsuarioOptional = tipoUsuarioRepository.findById(entity.getTipoUsuario().getTiusId()); 
		if(!tipoUsuarioOptional.isPresent()) {
			throw new Exception("El tipo de usuario con id: " + entity.getTipoUsuario().getTiusId() + " No Existe.");
		}
		entity.setTipoUsuario(tipoUsuarioOptional.get());
				
		return usuarioRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)	
	public void delete(Usuario entity) throws Exception {
		if(entity == null || entity.getUsuUsuario() == null || entity.getUsuUsuario().isEmpty()) {
			throw new Exception("El usuario es nulo");
		}
		
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(entity.getUsuUsuario());
		
		if(!usuarioOptional.isPresent()) {
			throw new Exception("El usuario con id: " + entity.getUsuUsuario() + " No existe");
		}		
		
		if(usuarioOptional.get().getTransaccions().size() > 0) {
			throw new Exception("El usuario tiene transacciones");
		}
				
		usuarioRepository.delete(usuarioOptional.get());
		
	}

	@Override
	public void deleteById(String id) throws Exception {

		if(id == null || id.isEmpty()) {
			throw new Exception("El id del usuario es nulo");
		}

		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
		
		if(!usuarioOptional.isPresent()) {
			throw new Exception("El usuario con id: " + id + " No existe");
		}		
				
		delete(usuarioOptional.get());
		
	}

	@Override
	public Optional<Usuario> findById(String id) {
		return usuarioRepository.findById(id);
	}

	@Override
	public List<Usuario> findAll() {		
		return usuarioRepository.findAll();
	}

}
