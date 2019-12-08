package co.edu.usbcali.bank.dto;

import java.sql.Timestamp;

public class TipoDocumentoDTO {

	private Long tdocId;
	private String activo;
	private Timestamp fechaCreacion;
	private Timestamp fechaModificacion;
	private String nombre;
	private String usuCreador;
	private String usuModificador;
	
	public Long getTdocId() {
		return tdocId;
	}
	public void setTdocId(Long tdocId) {
		this.tdocId = tdocId;
	}
	public String getActivo() {
		return activo;
	}
	public void setActivo(String activo) {
		this.activo = activo;
	}
	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Timestamp getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(Timestamp fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getUsuCreador() {
		return usuCreador;
	}
	public void setUsuCreador(String usuCreador) {
		this.usuCreador = usuCreador;
	}
	public String getUsuModificador() {
		return usuModificador;
	}
	public void setUsuModificador(String usuModificador) {
		this.usuModificador = usuModificador;
	}	
}
