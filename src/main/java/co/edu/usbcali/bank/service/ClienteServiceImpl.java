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

import co.edu.usbcali.bank.domain.Cliente;
import co.edu.usbcali.bank.domain.TipoDocumento;
import co.edu.usbcali.bank.repository.ClienteRepository;
import co.edu.usbcali.bank.repository.TipoDocumentoRepository;

@Service
@Scope("singleton")
public class ClienteServiceImpl implements ClienteService{
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	TipoDocumentoRepository tipoDocumentoRepository;
	
	@Autowired
	private Validator validator;

	public void validar(Cliente cliente) throws Exception {
		if(cliente == null) {
			throw new Exception("El cliente es nulo");
		}
	    try {	    	
	        Set<ConstraintViolation<Cliente>> constraintViolations = validator.validate(cliente);

	        if (constraintViolations.size() > 0) {
	            StringBuilder strMessage = new StringBuilder();

	            for (ConstraintViolation<Cliente> constraintViolation : constraintViolations) {
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
	public Cliente save(Cliente entity) throws Exception {
		validar(entity);
		
		Optional<Cliente> clienteOptional = clienteRepository.findById(entity.getClieId());
		
		if(clienteOptional.isPresent()) {
			throw new Exception("El cliente con id: " + entity.getClieId() + " Ya existe");
		}		
		
		Optional<TipoDocumento> tipoDocumentoOptional = tipoDocumentoRepository.findById(entity.getTipoDocumento().getTdocId()); 
		if(!tipoDocumentoOptional.isPresent()) {
			throw new Exception("El tipo de documento con id: " + entity.getTipoDocumento().getTdocId() + " No Existe.");
		}
		entity.setTipoDocumento(tipoDocumentoOptional.get());
				
		return clienteRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Cliente update(Cliente entity) throws Exception {
		validar(entity);
		
		Optional<Cliente> clienteOptional = clienteRepository.findById(entity.getClieId());
		
		if(!clienteOptional.isPresent()) {
			throw new Exception("El cliente con id: " + entity.getClieId() + " no existe");
		}		
		
		Optional<TipoDocumento> tipoDocumentoOptional = tipoDocumentoRepository.findById(entity.getTipoDocumento().getTdocId()); 
		if(!tipoDocumentoOptional.isPresent()) {
			throw new Exception("El tipo de documento con id: " + entity.getTipoDocumento().getTdocId() + " No Existe.");
		}
		entity.setTipoDocumento(tipoDocumentoOptional.get());
				
		return clienteRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Cliente entity) throws Exception {
		if(entity==null || entity.getClieId() == null) {
			throw new Exception("El cliente es nulo");
		}
		Optional<Cliente> clienteOptional = clienteRepository.findById(entity.getClieId());
		
		if(!clienteOptional.isPresent()) {
			throw new Exception("El cliente con id: " + entity.getClieId() + " no existe");
		}
		Cliente cliente = clienteOptional.get();
		
		if(cliente.getCuentaRegistradas().size()>0) {
			throw new Exception("El cliente tiene cuentas registradas");
		}
		
		if(cliente.getCuentas().size()>0) {
			throw new Exception("El cliente tiene cuentas");
		}
		
		clienteRepository.delete(clienteOptional.get());
		
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Long id) throws Exception {
		// TODO Auto-generated method stub
		if(id == null || id < 1) {
			throw new Exception("El cliente es nulo o menor que uno");
		}
		Optional<Cliente> clienteOptional = clienteRepository.findById(id);
		
		if(!clienteOptional.isPresent()) {
			throw new Exception("El cliente con id: " + id + " no existe");
		}
				
		delete(clienteOptional.get());
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Cliente> findById(Long id) {		
		return clienteRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

}
