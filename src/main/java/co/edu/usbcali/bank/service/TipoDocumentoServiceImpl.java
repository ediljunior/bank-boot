package co.edu.usbcali.bank.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.bank.domain.TipoDocumento;
import co.edu.usbcali.bank.repository.TipoDocumentoRepository;

@Service
@Scope("singleton")
public class TipoDocumentoServiceImpl implements TipoDocumentoService{

	@Autowired
	TipoDocumentoRepository tipoDocumentoRepository;
	
	@Autowired
	private Validator validator;
		
	public void validar(TipoDocumento tipoDocumento) throws Exception {
		if(tipoDocumento == null) {
			throw new Exception("El cliente es nulo");
		}
	    try {	    	
	        Set<ConstraintViolation<TipoDocumento>> constraintViolations = validator.validate(tipoDocumento);

	        if (constraintViolations.size() > 0) {
	            StringBuilder strMessage = new StringBuilder();

	            for (ConstraintViolation<TipoDocumento> constraintViolation : constraintViolations) {
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
	@Transactional(readOnly = true)
	public Optional<TipoDocumento> findById(Long id) {
		return tipoDocumentoRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TipoDocumento> findAll() {
		return tipoDocumentoRepository.findAll();
	}
	
	@Override
	public TipoDocumento save(TipoDocumento entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TipoDocumento update(TipoDocumento entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(TipoDocumento entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long id) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
