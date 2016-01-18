package co.edu.udea.iw.webServices;

import java.rmi.RemoteException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.udea.iw.bl.AdministradorBL;
import co.edu.udea.iw.exception.MyException;

/**
 * Clase en la que se implementan los servicios Restful necesarios para
 * manipular la información de los administradores
 *
 * @author Carolina Isaza
 * @author Sebastian Jimenez
 * @author Jaime Londono
 *
 */
@Component
@Path("Administrador")
public class AdministradorWS {

	/**
	 * Objeto que se inyecta de la clase AdministradorBL que permite acceder y
	 * regular de acuerdo a las reglas de negocio, las operaciones que pueden
	 * realizarse para gestionar los administradores del sistema
	 */
	@Autowired
	AdministradorBL administradorBL;

	/**
	 * Objeto de la clase Logger que permitir� almacenar los mensajes de error
	 * en el log
	 */
	private static Logger logger = Logger.getLogger(AdministradorWS.class);

	/**
	 * Servicio que permite crear un nuevo administrador en el sistema
	 * 
	 * @param email
	 *            correo del administrador que se desea crear
	 * @param contrasena
	 *            contrasena de acceso al sistema
	 * @param nombre
	 *            nombre del administrador
	 * @param apellido
	 *            apellido del administrador
	 * @return String con el resultado de la operación si fue éxitosa, o la
	 *         pila del error en caso contrario
	 * @throws RemoteException
	 */
	@Path("/crear")
	@Produces(MediaType.TEXT_PLAIN)
	@POST
	public String crearAdministrador(@QueryParam("email") String email, @QueryParam("contrasena") String contrasena,
			@QueryParam("nombre") String nombre, @QueryParam("apellido") String apellido) throws RemoteException {
		try {
			administradorBL.crearAdministrador(email, contrasena, nombre, apellido);
		} catch (MyException e) {
			logger.error(e.getMessage());
			return (e.getMessage());
		}

		return "El administrador fue creado exitosamente";

	}

	/**
	 * Servicio que permite eliminar un administrador físicamente de la base de
	 * datos
	 * 
	 * @param email
	 *            correo del administrador que se desea eliminar
	 * @param contrasena
	 *            contrasena de la cuenta del administrador que se va a eliminar
	 * @return String con el resultado de la operación si fue éxitosa, o la
	 *         pila del error en caso contrario
	 * @throws RemoteException
	 */
	@Path("/eliminar")
	@Produces(MediaType.TEXT_PLAIN)
	@GET
	public String eliminarAdministrador(@QueryParam("email") String email, @QueryParam("contrasena") String contrasena)
			throws RemoteException {

		try {
			administradorBL.eliminarAdministrador(email, contrasena);
		} catch (MyException e) {
			logger.error(e.getMessage());
			return (e.getMessage());
		}

		return "Se elimino correctamente del sistema el administrador deseado";
	}

	/**
	 * Servicio que permite actualizar la contrasena de un administrador
	 * 
	 * @param email
	 *            correo del administrador al cual se le desea actualizar la
	 *            contrasena
	 * @param contrasenaNueva
	 *            contrasena nueva del administrador
	 * @param contrasenaVieja
	 *            contrasena vieja del administrador
	 * @return String con el resultado de la operación si fue éxitosa, o la
	 *         pila del error en caso de que algo falle
	 * @throws RemoteException
	 */
	@Path("/actualizar")
	@Produces(MediaType.TEXT_PLAIN)
	@PUT
	public String actualizarPassAdministrador(@QueryParam("email") String email,
			@QueryParam("contrasenaNueva") String contrasenaNueva,
			@QueryParam("contrasenaVieja") String contrasenaVieja) throws RemoteException {

		try {
			administradorBL.actualizarPassAdministrador(email, contrasenaNueva, contrasenaVieja);
		} catch (MyException e) {
			logger.error(e.getMessage());
			return (e.getMessage());
		}

		return "El administrador fue actualizado correctamente";
	}

	/**
	 * Servicio que permite validar las credenciales de login de un usuario
	 * 
	 * @param email
	 *            correo del usuario que se va a loguear
	 * @param pass
	 *            contrasena de la persona que se va a logear
	 * @return String que indica si la operación de éxitosa fue éxitosa o no,
	 *         en caso de error se retorna el mensaje generado por el mismo.
	 * @throws RemoteException
	 */
	@Path("/login")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String login(@QueryParam("email") String email, @QueryParam("pass") String pass) throws RemoteException {

		Boolean b = Boolean.FALSE;

		try {
			b = administradorBL.login(email, pass);
		} catch (MyException e) {
			logger.error(e.getMessage());
			return (e.getMessage());
		}

		return "Login exitoso: " + b;
	}

}
