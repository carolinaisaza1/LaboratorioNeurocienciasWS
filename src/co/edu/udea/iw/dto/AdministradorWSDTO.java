package co.edu.udea.iw.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Esta clase contiene los atributos de un Administrador del Laboratorio de
 * Neurociencias y sus respectivos metodos de acceso (getters and setters).
 * 
 * @author Carolina Isaza
 * @author Sebastian Jimenez
 * @author Jaime Londono
 *
 */

@XmlRootElement
public class AdministradorWSDTO {

	// Atributos de tipo privado definidos para el Administrador
	/**
	 * Nombre del administrador
	 */
	private String nombre;
	/**
	 * Apellido del administrador
	 */
	private String apellidos;
	/**
	 * Contrasena del administrador
	 */
	private String contrasena;
	/**
	 * Correo electronico del administrador
	 */
	private String email;

	// Metodos Getters y Setters

	/**
	 * Metodo para obtener el nombre del administrador
	 * 
	 * @return nombre del objeto administrador
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Metodo para asignar un valor al atributo nombre
	 * 
	 * @param nombre
	 *            Nombre a asignar al objeto administrador
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Metodo para obtener el apellido del administrador
	 * 
	 * @return Apellido del objeto administrador existente
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * Metodo para asignar un valor al atributo apellido
	 * 
	 * @param apellidos
	 *            Apellido a asignar al objeto administrador
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * Metodo para obtener la contrasena del administrador
	 * 
	 * @return Contrasena cifrada del objeto administrador
	 */
	public String getContrasena() {
		return contrasena;
	}

	/**
	 * Metodo para asignar un valor al atributo contrasena
	 * 
	 * @param contrasena
	 *            Contrasena cifrada que se asignara al administrador
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	/**
	 * Metodo para obtener el correo electronico del administrador
	 * 
	 * @return Email asociado al objeto administrador
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Metodo para asignar un valor al atributo email
	 * 
	 * @param email
	 *            Email a asignar al administrador
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}

