package co.edu.udea.iw.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Esta clase contiene los atributos de tipo de dispositivos en el Laboratorio
 * de Neurociencias y sus respectivos metodos de acceso (getters and setters).
 * 
 * @author Carolina Isaza
 * @author Sebastian Jimenez
 * @author Jaime Londono
 *
 */

@XmlRootElement
public class TipoWSDTO implements Serializable {

	/**
	 * Identificador del tipo de dispositivo
	 */
	private int id;
	/**
	 * Nombre del tipo de dispositivo
	 */
	private String nombre;

	/**
	 * Metodo para obtener el identificador del tipo de dispositivo
	 * 
	 * @return Identificador del tipo de dispositivo
	 */
	public int getId() {
		return id;
	}

	/**
	 * Metodo para asignar el identificador al tipo de dispositivo
	 * 
	 * @param id
	 *            Identificador que se desea asignar al tipo de dispositivo
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Metodo para obtener el nombre del tipo de dispositivo
	 * 
	 * @return String que contiene el nombre del tipo de dispositivo
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Metodo para asignar el nombre al tipo de dispositivo
	 * 
	 * @param nombre
	 *            String con el nombre a asignar al tipo de dispositivo
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
