package co.edu.usbcali.bank.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the cliente database table.
 * 
 */
@Entity
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="clie_id")
	@NonNull
	@Min(1)
	private Long clieId;

	@NonNull
	@Size(min = 1, max = 1, message = "Solo se puede escribir N o S")
	private String activo;

	@NonNull
	@Size(min = 5, max = 250)
	private String direccion;

	@NonNull
	@Email
	private String email;	

	@NonNull
	@Size(min = 3, max = 100)
	private String nombre;

	@NonNull
	@Size(min = 2, max = 50)
	private String telefono;

	//bi-directional many-to-one association to TipoDocumento
	@ManyToOne
	@JoinColumn(name="tdoc_id")
	@NonNull
	private TipoDocumento tipoDocumento;
	
	@Column(name="fecha_creacion")
	private Timestamp fechaCreacion;

	@Column(name="fecha_modificacion")
	private Timestamp fechaModificacion;
	
	@Column(name="usu_creador")
	private String usuCreador;

	@Column(name="usu_modificador")
	private String usuModificador;

	//bi-directional many-to-one association to Cuenta
	@OneToMany(mappedBy="cliente")
	private List<Cuenta> cuentas;

	//bi-directional many-to-one association to CuentaRegistrada
	@OneToMany(mappedBy="cliente")
	private List<CuentaRegistrada> cuentaRegistradas;

	public Cliente() {
	}

	public Long getClieId() {
		return this.clieId;
	}

	public void setClieId(Long clieId) {
		this.clieId = clieId;
	}

	public String getActivo() {
		return this.activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Timestamp getFechaModificacion() {
		return this.fechaModificacion;
	}

	public void setFechaModificacion(Timestamp fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getUsuCreador() {
		return this.usuCreador;
	}

	public void setUsuCreador(String usuCreador) {
		this.usuCreador = usuCreador;
	}

	public String getUsuModificador() {
		return this.usuModificador;
	}

	public void setUsuModificador(String usuModificador) {
		this.usuModificador = usuModificador;
	}

	public TipoDocumento getTipoDocumento() {
		return this.tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public List<Cuenta> getCuentas() {
		return this.cuentas;
	}

	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	public Cuenta addCuenta(Cuenta cuenta) {
		getCuentas().add(cuenta);
		cuenta.setCliente(this);

		return cuenta;
	}

	public Cuenta removeCuenta(Cuenta cuenta) {
		getCuentas().remove(cuenta);
		cuenta.setCliente(null);

		return cuenta;
	}

	public List<CuentaRegistrada> getCuentaRegistradas() {
		return this.cuentaRegistradas;
	}

	public void setCuentaRegistradas(List<CuentaRegistrada> cuentaRegistradas) {
		this.cuentaRegistradas = cuentaRegistradas;
	}

	public CuentaRegistrada addCuentaRegistrada(CuentaRegistrada cuentaRegistrada) {
		getCuentaRegistradas().add(cuentaRegistrada);
		cuentaRegistrada.setCliente(this);

		return cuentaRegistrada;
	}

	public CuentaRegistrada removeCuentaRegistrada(CuentaRegistrada cuentaRegistrada) {
		getCuentaRegistradas().remove(cuentaRegistrada);
		cuentaRegistrada.setCliente(null);

		return cuentaRegistrada;
	}

}