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

/**
 * Esta clase contiene los metodos de los servicios web a consumir
 * correspondientes a todo lo que concierne a Fallos de dispositivos.
 * 
 * @author Sebastian Jimenez
 * @author Carolina Isaza
 * @author Jaime Londono
 *
 */
@Component
@Path("Fallo")
public class FalloWS {

	/**
	 * Objeto autoinyectable de la clase FalloBL que permitira acceder a los
	 * metodos en relacion a Fallo de dispositivos en lo que a la logica del
	 * negocio se refiere.
	 */
	@Autowired
	FalloBL falloBL;

	/**
	 * Servicio para consultar todos los fallos registrados en la base de datos.
	 * 
	 * @return Lista con todos los fallos encontrados.
	 */
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

	/**
	 * Servicio para reportar un nuevo fallo
	 * 
	 * @param idFallo
	 *            Identificador del fallo.
	 * @param error
	 *            Descripcion del fallo y fecha de deteccion.
	 * @param referenciaDispositivo
	 *            Referencia del dispositivo en el cual fue encontrado el fallo.
	 * @return Mensaje confirmado si la operacion fue exitosa. De lo contrario,
	 *         se retorna la pila de error.
	 */
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

	/**
	 * Servicio para cambiar el estado de un fallo
	 * 
	 * @param idFallo
	 *            Identificador del fallo a actualizar.
	 * @param solucionado
	 *            Boolean para especificar si el fallo ha sido solucionado o no.
	 * @return Mensaje confirmado si la operacion fue exitosa. De lo contrario,
	 *         se retorna la pila de error.
	 */
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

	/**
	 * Servicio que permite consultar un fallo en especifico
	 * 
	 * @param referenciaDispositivo
	 *            Referencia del dispositivo del que se quieren conocer los
	 *            fallos.
	 * @return Lista de los fallos asociados a un dispositivo
	 */
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

	/**
	 * Servicio para consultar los danos que no han sido solucionados
	 * 
	 * @return Lista con los danos que no han sido solucionados
	 */
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

	/**
	 * Servicio para consultar un fallo en especifico
	 * 
	 * @param id
	 *            Identificador del fallo a consultar.
	 * @return Informacion correspondiente al fallo consultado
	 */
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
