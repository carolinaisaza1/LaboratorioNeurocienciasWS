package co.edu.udea.iw.webServices;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.DELETE;
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

import co.edu.udea.iw.bl.DispositivoBL;
import co.edu.udea.iw.dto.Dispositivo;
import co.edu.udea.iw.dto.DispositivoWSDTO;
import co.edu.udea.iw.dto.Tipo;
import co.edu.udea.iw.exception.MyException;

@Component
@Path("Dispositivo")
public class DispositivoWS {

	@Autowired
	DispositivoBL dispositivoBL;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<DispositivoWSDTO> consultarTodos() {
		List<DispositivoWSDTO> lista = new ArrayList<DispositivoWSDTO>();
		try {
			for (Dispositivo dispositivo : dispositivoBL.consultarTodos()) {
				DispositivoWSDTO dispositivoWS = new DispositivoWSDTO();
				dispositivoWS.setTipo(dispositivo.getTipo());
				dispositivoWS.setDescripcion(dispositivo.getDescripcion());
				dispositivoWS.setFoto(dispositivo.getFoto());
				dispositivoWS.setDisponible(dispositivo.isDisponible());
				dispositivoWS.setNombre(dispositivo.getNombre());
				dispositivoWS.setReferencia(dispositivo.getReferencia());
				lista.add(dispositivoWS);
			}
		} catch (MyException e) {
			e.getMessage();
		}
		return lista;
	}

	@GET
	@Path("{referencia}")
	@Produces(MediaType.TEXT_PLAIN)
	public DispositivoWSDTO consultarUno(@PathParam("referencia") String referencia) {
		DispositivoWSDTO dispositivoWS = new DispositivoWSDTO();
		try {

			Dispositivo dispositivo = dispositivoBL.consultarUno(referencia);
			dispositivoWS.setTipo(dispositivo.getTipo());
			dispositivoWS.setDescripcion(dispositivo.getDescripcion());
			dispositivoWS.setFoto(dispositivo.getFoto());
			dispositivoWS.setDisponible(dispositivo.isDisponible());
			dispositivoWS.setNombre(dispositivo.getNombre());
			dispositivoWS.setReferencia(dispositivo.getReferencia());
		} catch (MyException e) {
			System.out.println(e.getMessage());
		}
		return dispositivoWS;
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String crearDispositivo(@QueryParam("referencia") String referencia, @QueryParam("nombre") String nombre,
			@QueryParam("descripcion") String descripcion, @QueryParam("tipo") int tipo,
			@QueryParam("foto") String foto, @QueryParam("emailAdministrador") String emailAdministrador) {
		try {
			dispositivoBL.crearDispositivo(referencia, nombre, descripcion, tipo, foto, emailAdministrador);
		} catch (MyException e) {
			return (e.getMessage());
		}
		return "El dispositivo se ha creado exitosamente";

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{tipo}")
	public List<DispositivoWSDTO> consultarPorTipo(@PathParam("tipo") int tipo) {
		List<DispositivoWSDTO> lista = new ArrayList<DispositivoWSDTO>();
		try {
			for (Dispositivo dispositivo : dispositivoBL.consultarPorTipo(tipo)) {
				DispositivoWSDTO dispositivoWS = new DispositivoWSDTO();
				dispositivoWS.setTipo(dispositivo.getTipo());
				dispositivoWS.setDescripcion(dispositivo.getDescripcion());
				dispositivoWS.setFoto(dispositivo.getFoto());
				dispositivoWS.setDisponible(dispositivo.isDisponible());
				dispositivoWS.setNombre(dispositivo.getNombre());
				dispositivoWS.setReferencia(dispositivo.getReferencia());
				lista.add(dispositivoWS);
			}
		} catch (MyException e) {
			e.getMessage();
		}
		return lista;
	}

	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	public String actualizarDispositivo(@QueryParam("referencia") String referencia,
			@QueryParam("nombre") String nombre, @QueryParam("descripcion") String descripcion,
			@QueryParam("tipo") int tipo, @QueryParam("foto") String foto, @QueryParam("disponible") boolean disponible,
			@QueryParam("emailAdministrador") String emailAdministrador) {
		try {
			dispositivoBL.actualizarDispositivo(referencia, nombre, descripcion, tipo, foto, disponible,
					emailAdministrador);
		} catch (MyException e) {
			return e.getMessage();
		}
		return "El dispositivo se actualizó correctamente";
	}

	@DELETE
	public String eliminarDispositivo(@QueryParam("referencia") String referencia,
			@QueryParam("emailAdministrador") String emailAdministrador) {
		try {
			dispositivoBL.eliminarDispositivo(referencia, emailAdministrador);
		} catch (MyException e) {
			return e.getMessage();
		}
		return "El dispositivo se ha eliminado correctamente";
	}

	@GET
	@Path("{fechaInicio}/{fechaFin}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DispositivoWSDTO> mostrarDispositivosDisponibles(@PathParam("fechaInicio") Date fechaInicio,
			@PathParam("fechaFin") Date fechaFin) {
		List<DispositivoWSDTO> lista = new ArrayList<DispositivoWSDTO>();
		for (Dispositivo dispositivo : dispositivoBL.mostrarDispositivosDisponibles(fechaInicio, fechaFin)) {
			DispositivoWSDTO dispositivoWS = new DispositivoWSDTO();
			dispositivoWS.setTipo(dispositivo.getTipo());
			dispositivoWS.setDescripcion(dispositivo.getDescripcion());
			dispositivoWS.setFoto(dispositivo.getFoto());
			dispositivoWS.setDisponible(dispositivo.isDisponible());
			dispositivoWS.setNombre(dispositivo.getNombre());
			dispositivoWS.setReferencia(dispositivo.getReferencia());
			lista.add(dispositivoWS);
		}
		return lista;

	}

}
