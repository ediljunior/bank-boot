package co.edu.usbcali.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import co.edu.usbcali.bank.domain.TipoUsuario;
import co.edu.usbcali.bank.repository.TipoUsuarioRepository;

@Service
@Scope("singleton")
public class TipoUsuarioServiceImpl implements TipoUsuarioService{
	
	@Autowired
	TipoUsuarioRepository tipoUsuarioRepository;

	@Override
	public TipoUsuario save(TipoUsuario entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TipoUsuario update(TipoUsuario entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(TipoUsuario entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<TipoUsuario> findById(Long id) {
		// TODO Auto-generated method stub
		return tipoUsuarioRepository.findById(id);
	}

	@Override
	public List<TipoUsuario> findAll() {
		// TODO Auto-generated method stub
		return tipoUsuarioRepository.findAll();
	}

}
