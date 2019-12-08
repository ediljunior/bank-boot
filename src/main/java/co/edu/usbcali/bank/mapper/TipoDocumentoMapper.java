package co.edu.usbcali.bank.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import co.edu.usbcali.bank.domain.TipoDocumento;
import co.edu.usbcali.bank.dto.TipoDocumentoDTO;

@Mapper
public interface TipoDocumentoMapper {
	
	TipoDocumentoDTO tipoDocumentoToTipoDocumentoDTO(TipoDocumento tipoDocumento);
	
	TipoDocumento tipoDocumentoDTOTotipoDocumento(TipoDocumentoDTO tipoDocumentoDTO);
	
	List<TipoDocumentoDTO> toTipoDocumentoDTOs(List<TipoDocumento> clientes);
	
	List<TipoDocumento> totipoDocumento(List<TipoDocumentoDTO> clientesDTOs);
}
