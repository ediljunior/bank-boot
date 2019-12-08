package co.edu.usbcali.bank.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.bank.domain.TipoDocumento;
import co.edu.usbcali.bank.dto.TipoDocumentoDTO;
import co.edu.usbcali.bank.mapper.TipoDocumentoMapper;
import co.edu.usbcali.bank.service.TipoDocumentoService;

@RestController
@RequestMapping("/api/tipoDocumento/")
@CrossOrigin("*")
public class TipoDocumentoController {
	
	@Autowired
	TipoDocumentoService tipoDocumentoService;
	
	@Autowired
	TipoDocumentoMapper tipoDocumentoMapper;
	
	
	@GetMapping("findAll")
	public List<TipoDocumentoDTO> findAll() {
		
		List<TipoDocumento> losTipoDocumentos = tipoDocumentoService.findAll();
		List<TipoDocumentoDTO> losTipoDocumentosDTO = tipoDocumentoMapper.toTipoDocumentoDTOs(losTipoDocumentos);
		return losTipoDocumentosDTO;
		
	}
	
	@GetMapping("findById/{id}") 
	public TipoDocumentoDTO findById(@PathVariable("id") Long id) {
		Optional<TipoDocumento> tipoDocumentoOptional = tipoDocumentoService.findById(id);
		
		if(tipoDocumentoOptional.isPresent()) {
			TipoDocumento tipoDocumento = tipoDocumentoOptional.get();
			TipoDocumentoDTO tipoDocumentoDTO = tipoDocumentoMapper.tipoDocumentoToTipoDocumentoDTO(tipoDocumento);
			return tipoDocumentoDTO;
		}
		
		return null;
	}

}
