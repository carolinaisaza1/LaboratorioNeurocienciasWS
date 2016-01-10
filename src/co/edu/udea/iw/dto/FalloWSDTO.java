package co.edu.udea.iw.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FalloWSDTO {
	/**
	 * Identificador del fallo de dispositivo.
	 */
	private int idFallo;
	/**
	 * Descripcion del fallo.
	 */
	private String error;
	/**
	 * Fecha en la que fue detectado el fallo.
	 */
	private Date fechaDeteccion;
	/**
	 * Atributo que indica si el fallo ha sido solucionado o no.
	 */
	private boolean solucionado;
	/**
	 * Dispositivo al cual esta asociado el fallo.
	 */
	private Dispositivo dispositivo;

	/**
	 * Metodo para obtener el identificador del fallo.
	 * 
	 * @return String con el Identificador del fallo
	 */
	public int getIdFallo() {
		return idFallo;
	}

	/**
	 * Metodo para asignar un identificador al fallo.
	 * 
	 * @param idFallo
	 *            String con identificador que se desea asignar.
	 */
	public void setIdFallo(int idFallo) {
		this.idFallo = idFallo;
	}

	/**
	 * Metodo para obtener la descripcion del fallo.
	 * 
	 * @return String con la descripcion del fallo.
	 */
	public String getError() {
		return error;
	}

	/**
	 * Metodo para asignar una descripcion al fallo.
	 * 
	 * @param error
	 *            String con la descripcion que se desea asignar
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * Metodo para obtener la fecha en la que fue detectado el fallo
	 * 
	 * @return Objeto tipo Date con la fecha de deteccion.
	 */
	public Date getFechaDeteccion() {
		return fechaDeteccion;
	}

	/**
	 * Metodo para asingar la fecha en la que fue detectado el fallo
	 * 
	 * @param fechaDeteccion
	 *            Objeto tipo Date con la fecha en que se detecto el fallo.
	 */
	public void setFechaDeteccion(Date fechaDeteccion) {
		this.fechaDeteccion = fechaDeteccion;
	}

	/**
	 * Metodo para saber si el fallo ya fue solucionado
	 * 
	 * @return True si el fallo ha sido solucionado. False, de lo contrario
	 */
	public boolean isSolucionado() {
		return solucionado;
	}

	/**
	 * Metodo para fijar si el fallo fue solucionado.
	 * 
	 * @param solucionado
	 *            True si el fallo ha sido solucionado.
	 */
	public void setSolucionado(boolean solucionado) {
		this.solucionado = solucionado;
	}

	/**
	 * Metodo para obtener el objeto Dispositivo al cual esta asociado el fallo
	 * 
	 * @return Objeto tipo Dispositivo al cual esta asociado el fallo
	 */
	public Dispositivo getDispositivo() {
		return dispositivo;
	}

	/**
	 * Metodo para asociar un Dispositivo al fallo en cuestion
	 * 
	 * @param dispositivo
	 *            Objeto tipo Dispositivo que tendra asociado el fallo.
	 */
	public void setDispositivo(Dispositivo dispositivo) {
		this.dispositivo = dispositivo;
	}

}
