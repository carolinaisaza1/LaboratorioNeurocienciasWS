package co.edu.udea.iw.webServices;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	@Path("/consultarTodos")
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

	@Path("/consultarUno")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public DispositivoWSDTO consultarUno(@QueryParam("referencia") String referencia) {
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
			e.getMessage();
		}
		return dispositivoWS;
	}

	@POST
	@Path("/crear")
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
	@Path("/consultarPorTipo")
	public List<DispositivoWSDTO> consultarPorTipo(@QueryParam("tipo") int tipo) {
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
	@Path("/actualizar")
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

	@GET
	@Path("/mostrarDispositivosDisponibles")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DispositivoWSDTO> mostrarDispositivosDisponibles(@QueryParam("fechaInicio") String fechaInicio,
			@QueryParam("fechaFin") String fechaFin) {
		List<DispositivoWSDTO> lista = new ArrayList<DispositivoWSDTO>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

		try {
			Date fechaIni = sdf.parse(fechaInicio);
			Date fechaEnd = sdf.parse(fechaFin);
			for (Dispositivo dispositivo : dispositivoBL.mostrarDispositivosDisponibles(fechaIni, fechaEnd)) {
				DispositivoWSDTO dispositivoWS = new DispositivoWSDTO();
				dispositivoWS.setTipo(dispositivo.getTipo());
				dispositivoWS.setDescripcion(dispositivo.getDescripcion());
				dispositivoWS.setFoto(dispositivo.getFoto());
				dispositivoWS.setDisponible(dispositivo.isDisponible());
				dispositivoWS.setNombre(dispositivo.getNombre());
				dispositivoWS.setReferencia(dispositivo.getReferencia());
				lista.add(dispositivoWS);
			}
		} catch (ParseException e) {
			e.getMessage();
		}
		return lista;

	}

}
