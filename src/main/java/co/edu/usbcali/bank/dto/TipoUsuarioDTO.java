package co.edu.usbcali.bank.dto;

import java.sql.Timestamp;

public class TipoUsuarioDTO {
	private Long tiusId;
	private String activo;
	private Timestamp fechaCreacion;
	private Timestamp fechaModificacion;
	private String nombre;
	private String usuCreador;
	private String usuModificador;
	public Long getTiusId() {
		return tiusId;
	}
	public void setTiusId(Long tiusId) {
		this.tiusId = tiusId;
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
