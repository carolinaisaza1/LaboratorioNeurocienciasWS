package co.edu.udea.iw.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DispositivoWSDTO {

	// Atributos del Dispositivo

	/**
	 * Tipo de dispositivo
	 */
	private Tipo tipo;
	/**
	 * Referencia unica del dispositivo
	 */
	private String referencia;
	/**
	 * Nombre del dispositivo
	 */
	private String nombre;
	/**
	 * Atributo que indica si el dispositivo esta disponible para su prestamo
	 */
	private boolean disponible;
	/**
	 * Descripcion y caracteristicas del dispositivo
	 */
	private String descripcion;
	/**
	 * Enlace a foto del dispositivo
	 */
	private String foto;

	// Metodos Getters y Setters

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	/**
	 * Metodo para acceder al tipo al que pernetece el Dispositivo
	 * 
	 * @return tipo Objeto de la clase tipo
	 */
	public Tipo getTipo() {
		return tipo;
	}

	/**
	 * Metodo para asignarle un tipo al Dispositivo
	 * 
	 * @param tipo
	 *            Objeto de clase tipo que se quiere asignar
	 */
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	/**
	 * Metodo para obtener la referencia del Dispositivo
	 * 
	 * @return String con la referencia del dispositivo
	 */
	public String getReferencia() {
		return referencia;
	}

	/**
	 * Metodo para asignarle una referencia al dispositivo
	 * 
	 * @param referencia
	 *            String con la referencia a asignar
	 */
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	/**
	 * Metodo para obtener el nombre del dispositivo
	 * 
	 * @return String con el nombre del dispositivo
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Metodo para asignar un nombre al dispositivo
	 * 
	 * @param nombre
	 *            String con el nombre que se quiere asignar
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Metodo para saber si el dispositivo esta disponible para prestamo
	 * 
	 * @return True si esta disponible para prestamo. False, de lo contrario
	 */
	public boolean isDisponible() {
		return disponible;
	}

	/**
	 * Metodo para asignar la disponibilidad o no de un dispositivo para
	 * prestamo
	 * 
	 * @param disponible
	 *            True para asignar disponible.
	 */
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	/**
	 * Metodo para obtener la descripcion y caracteristicas del dispositivo
	 * 
	 * @return String con la descripcion del dispositivo
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Metodo para asignar una descripcion al dispositivo
	 * 
	 * @param descripcion
	 *            String con la descripcion que se desea asignar
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
