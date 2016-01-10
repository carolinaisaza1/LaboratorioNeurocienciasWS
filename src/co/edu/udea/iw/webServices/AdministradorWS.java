package co.edu.udea.iw.webServices;

import java.rmi.RemoteException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
	
}
