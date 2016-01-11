package co.edu.udea.iw.webServices;

import java.util.ArrayList;
import java.util.List;

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

import co.edu.udea.iw.bl.FalloBL;
import co.edu.udea.iw.dto.Dispositivo;
import co.edu.udea.iw.dto.DispositivoWSDTO;
import co.edu.udea.iw.dto.Fallo;
import co.edu.udea.iw.dto.FalloWSDTO;
import co.edu.udea.iw.exception.MyException;

@Component
@Path("Fallo")
public class FalloWS {

	@Autowired
	FalloBL falloBL;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/consultarTodos")
	public List<FalloWSDTO> consultarTodos() {
		List<FalloWSDTO> lista = new ArrayList<FalloWSDTO>();
		try {
			for (Fallo fallo : falloBL.consultarTodos()) {
				FalloWSDTO falloWS = new FalloWSDTO();
				falloWS.setDispositivo(fallo.getDispositivo());
				falloWS.setError(fallo.getError());
				falloWS.setFechaDeteccion(fallo.getFechaDeteccion());
				falloWS.setIdFallo(fallo.getIdFallo());
				falloWS.setSolucionado(fallo.isSolucionado());
				lista.add(falloWS);
			}
		} catch (MyException e) {
			e.getMessage();
		}
		return lista;
	}

	@POST
	@Path("/crear")
	public String crearFallo(@QueryParam("idFallo") int idFallo, @QueryParam("error") String error,
			@QueryParam("referencia") String referenciaDispositivo) {
		try {
			falloBL.crearFallo(idFallo, error, referenciaDispositivo);
		} catch (MyException e) {
			return e.getMessage();
		}
		return "El fallo se ha creado correctamente";
	}

	@PUT
	@Path("/actualizar")
	public String actualizarFallo(@QueryParam("idFallo") int idFallo, @QueryParam("solucionado") boolean solucionado) {
		try {
			falloBL.actualizarFallo(idFallo, solucionado);
		} catch (MyException e) {
			return e.getMessage();
		}
		return "El fallo ha sido actualizado";
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/consultarPorDispositivo")
	public List<FalloWSDTO> consultarPorDispositivo(@QueryParam("referencia") String referenciaDispositivo) {
		List<FalloWSDTO> lista = new ArrayList<FalloWSDTO>();
		try {
			for (Fallo fallo : falloBL.consultarFalloPorDispositivo(referenciaDispositivo)) {
				FalloWSDTO falloWS = new FalloWSDTO();
				falloWS.setDispositivo(fallo.getDispositivo());
				falloWS.setError(fallo.getError());
				falloWS.setFechaDeteccion(fallo.getFechaDeteccion());
				falloWS.setIdFallo(fallo.getIdFallo());
				falloWS.setSolucionado(fallo.isSolucionado());
				lista.add(falloWS);
			}
		} catch (MyException e) {
			e.getMessage();
		}
		return lista;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/DanosSinSolucion")
	public List<FalloWSDTO> consultarDanoSinSolucion() {
		List<FalloWSDTO> lista = new ArrayList<FalloWSDTO>();
		try {
			for (Fallo fallo : falloBL.consultarDanosSinSolucion()) {
				FalloWSDTO falloWS = new FalloWSDTO();
				falloWS.setDispositivo(fallo.getDispositivo());
				falloWS.setError(fallo.getError());
				falloWS.setFechaDeteccion(fallo.getFechaDeteccion());
				falloWS.setIdFallo(fallo.getIdFallo());
				falloWS.setSolucionado(fallo.isSolucionado());
				lista.add(falloWS);
			}
		} catch (MyException e) {
			e.getMessage();
		}
		return lista;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/ConsultarUno")
	public FalloWSDTO consultarUno(@QueryParam("id") int id) {
		FalloWSDTO falloWS = new FalloWSDTO();
		try {
			Fallo fallo = falloBL.consultarUno(id);
			falloWS.setDispositivo(fallo.getDispositivo());
			falloWS.setError(fallo.getError());
			falloWS.setFechaDeteccion(fallo.getFechaDeteccion());
			falloWS.setIdFallo(fallo.getIdFallo());
			falloWS.setSolucionado(fallo.isSolucionado());
		} catch (MyException e) {
			e.getMessage();
		}
		return falloWS;
	}
}
