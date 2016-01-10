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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.udea.iw.bl.AdministradorBL;
import co.edu.udea.iw.exception.MyException;

@Component
@Path("Administrador")
public class AdministradorWS {
	
	
	@Autowired
	AdministradorBL administradorBL;

	@Produces(MediaType.TEXT_PLAIN)
	@POST
	public String crearAdministrador(@QueryParam("email") String email, @QueryParam("contrasena") String contrasena,
			@QueryParam("nombre") String nombre, @QueryParam("apellido") String apellido) throws RemoteException {	
		try {
			administradorBL.crearAdministrador(email, contrasena, nombre, apellido);
		} catch (MyException e) {
			return(e.getMessage());
		}
		
		return "El administrador fue creado exitosamente";
		
	}
	
	@Produces(MediaType.TEXT_PLAIN)
	@GET
	public String eliminarAdministrador(@QueryParam("email") String email,
			@QueryParam("contrasena") String contrasena) throws RemoteException {
		
		try {
			administradorBL.eliminarAdministrador(email, contrasena);
		} catch (MyException e) {
			return(e.getMessage());
		}
		
		return "Se eliminó correctamente del sistema el administrador deseado";
	}
	
	@Produces(MediaType.TEXT_PLAIN)
	@PUT
	public String actualizarPassAdministrador(@QueryParam("email") String email,
			@QueryParam("contrasenaNueva") String contrasenaNueva, 
			@QueryParam("contrasenaVieja") String contrasenaVieja) throws RemoteException {
		
		try {
			administradorBL.actualizarPassAdministrador(email, contrasenaNueva, contrasenaVieja);
		} catch (MyException e) {
			return(e.getMessage());
		}
		
		return "El administrador fue actualizado correctamente";	
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("{email}/{pass}")
	public String login(@PathParam("email") String email,
			@PathParam("pass") String pass) throws RemoteException {
		
		Boolean b = Boolean.FALSE;
		
		try {
			b = administradorBL.login(email, pass);
		} catch (MyException e) {
			return(e.getMessage());
		}
		
		return "Login exitoso: " + b;
	}
	
}
