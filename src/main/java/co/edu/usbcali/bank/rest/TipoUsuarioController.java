package co.edu.usbcali.bank.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.bank.domain.TipoUsuario;
import co.edu.usbcali.bank.dto.TipoUsuarioDTO;
import co.edu.usbcali.bank.mapper.TipoUsuarioMapper;
import co.edu.usbcali.bank.service.TipoUsuarioService;

@RestController
@RequestMapping("/api/tipoUsuario/")
public class TipoUsuarioController {

	@Autowired
	TipoUsuarioService tipoUsuarioService;
	
	@Autowired
	TipoUsuarioMapper tipoUsuarioMapper;
	
	@GetMapping("findAll")
	public List<TipoUsuarioDTO> findAll(){
		List<TipoUsuario> losTipoUsuarios = tipoUsuarioService.findAll();
		List<TipoUsuarioDTO> losTipoUsuarioDTOs = tipoUsuarioMapper.toTipoUsuarioDTOs(losTipoUsuarios);
		
		return losTipoUsuarioDTOs;
	}
	

	@GetMapping("findById/{id}")
	public TipoUsuarioDTO findById(@PathVariable("id") Long id)  {
		Optional<TipoUsuario> tipoUsuarioOptional = tipoUsuarioService.findById(id);
		
		if(tipoUsuarioOptional.isPresent()) {
			TipoUsuario tipoUsuario = tipoUsuarioOptional.get();
			TipoUsuarioDTO tipoUsuarioDTO = tipoUsuarioMapper.tipoUsuarioToTipoUsuarioDTO(tipoUsuario);
			return tipoUsuarioDTO;
		}
		return null;
	}
}
