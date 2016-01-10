/**
 * 
 */
package co.edu.udea.iw.dto;


import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Esta clase contiene los atributos de un prestamo de dispositivos en el
 * Laboratorio de Neurociencias y sus respectivos metodos de acceso (getters and
 * setters).
 * 
 * @author Carolina Isaza
 * @author Sebastian Jimenez
 * @author Jaime Londono
 *
 */
@XmlRootElement
public class PrestamoWSDTO implements Serializable {

	/**
	 * Identificador del prestamo
	 */
	private int idPrestamo;
	/**
	 * Nombre del usuario que realiza el prestamo
	 */
	private String nombreUsuario;
	/**
	 * Cedula del usuario que realiza el prestamo
	 */
	private String cedulaUsuario;
	/**
	 * Fecha en la que se da inicio al prestamo
	 */
	private Date fechaInicio;
	/**
	 * Fecha en la que termina el prestamo
	 */
	private Date fechaFin;
	/**
	 * Fecha en la que se entregaron el o los dispositivos prestados
	 */
	private Date fechaEntrega;
	/**
	 * Administrador que se ha hecho cargo de autorizar el prestamo
	 */
	private Administrador administrador;
	/**
	 * Estado del prestamo
	 */
	private int estado;
	/**
	 * Correo electronico del usuario que realiza el prestamo
	 */
	private String correoUsuario;

	/**
	 * Metodo para obtener el estado en que se encuentra el prestamo
	 * 
	 * @return Entero que identifica el estado del prestamo
	 */
	public int getEstado() {
		return estado;
	}

	/**
	 * Metodo para asignar el estado en que se encuentra el prestamo
	 * 
	 * @param estado
	 *            Entero que identifica el estado a asignar
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}

	/**
	 * Metodo para obtener el correo electronico del usuario que realiza el
	 * prestamo
	 * 
	 * @return String con el correo del usuario que realiza el prestamo
	 */
	public String getCorreoUsuario() {
		return correoUsuario;
	}

	/**
	 * Metodo para asignar el correo electronico del usuario que realiza el
	 * prestamo
	 * 
	 * @param correoUsuario
	 *            String con el correo a asignar
	 */
	public void setCorreoUsuario(String correoUsuario) {
		this.correoUsuario = correoUsuario;
	}

	/**
	 * Metodo para obtener la fecha en que se entregaron los dispositivos
	 * prestados
	 * 
	 * @return Objeto tipo Date con la fecha en que se entregaron los objetos
	 *         prestados
	 */
	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	/**
	 * Metodo para asignar la fecha en que se entregaron los dispositivos
	 * prestados
	 * 
	 * @param fechaEntrega
	 *            Objeto tipo Date con la fecha de entrega
	 */
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	/**
	 * Metodo para obtener el identificador del prestamo
	 * 
	 * @return Entero con identificador del prestamo
	 */
	public int getIdPrestamo() {
		return idPrestamo;
	}

	/**
	 * Metodo para asignar el identificador del prestamo
	 * 
	 * @param idPrestamo
	 *            Entero con identificador a asignar al prestamo
	 */
	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}

	/**
	 * Metodo para obtener el nombre del usuario que realiza el prestamo
	 * 
	 * @return String con el nombre del usuario que realiza el prestamo
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	/**
	 * Metodo para obtener el nombre del usuario que realiza el prestamo
	 * 
	 * @param nombreUsuario
	 *            String con el nombre que se desea asignar a quien realiza el
	 *            prestamo
	 */
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	/**
	 * Metodo para obtener la cedula del usuario que realiza el prestamo
	 * 
	 * @return String con la cedula de quien realiza el prestamo
	 */
	public String getCedulaUsuario() {
		return cedulaUsuario;
	}

	/**
	 * Metodo para asignar la cedula del usuario que realiza el prestamo
	 * 
	 * @param cedulaUsuario
	 *            String con la cedula que se asignara
	 */
	public void setCedulaUsuario(String cedulaUsuario) {
		this.cedulaUsuario = cedulaUsuario;
	}

	/**
	 * Metodo para obtener la fecha en que el prestamo inicia
	 * 
	 * @return fechaInicio Objeto tipo Date con la fecha en que inicia el prestamo
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * Metodo para fijar la fecha en que el prestamo inicia
	 * 
	 * @param fechaInicio
	 *            Objeto tipo Date con la fecha a fijar en que inicia el
	 *            prestamo
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * Metodo para obtener la fecha en que el prestamo finaliza
	 * 
	 * @return Objeto tipo Date con la fecha en que finaliza el prestamo
	 */
	public Date getFechaFin() {
		return fechaFin;
	}

	/**
	 * Metodo para fijar la fecha en que el prestamo finaliza
	 * 
	 * @param fechaFin
	 *            Objeto tipo Date con la fecha a fijar en que finaliza el
	 *            prestamo
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * Metodo para obtener el Administrador encargado de aprobar el prestamo
	 * 
	 * @return Objeto tipo Administrador que representa la persona encargarda de
	 *         aprobar el prestamo
	 */
	public Administrador getAdministrador() {
		return administrador;
	}

	/**
	 * Metodo para obtener el Administrador encargado de aprobar el prestamo
	 * 
	 * @param administrador
	 *            Objeto del administrador que se designa para aprobar el
	 *            prestamo
	 */
	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

}
