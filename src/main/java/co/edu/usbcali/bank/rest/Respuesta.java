package co.edu.usbcali.bank.rest;

public class Respuesta {
	private Integer codigo;
	private String mensaje;
	
	public Respuesta() {
		super();
	}
	
	public Respuesta(Integer codigo, String mensaje) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getMensaje() {
		return mensaje;
	}	
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}	
}
