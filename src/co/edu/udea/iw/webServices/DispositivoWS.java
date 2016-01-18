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

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.udea.iw.bl.DispositivoBL;
import co.edu.udea.iw.dto.Dispositivo;
import co.edu.udea.iw.dto.DispositivoWSDTO;
import co.edu.udea.iw.dto.Tipo;
import co.edu.udea.iw.exception.MyException;

/**
 * Clase en la que se implementan los servicios Restful necesarios para un
 * dispositivo
 * 
 * @author Carolina Isaza
 * @author Sebastian Jimenez
 * @author Jaime Londono
 *
 */
@Component
@Path("Dispositivo")
public class DispositivoWS {
	/**
	 * Objeto de la clase Dispositivo de la logica del negocio.
	 */
	@Autowired
	DispositivoBL dispositivoBL;

	/**
	 * Objeto de la clase Logger que permitirá almacenar los mensajes de error
	 * en el log
	 */
	private static Logger logger = Logger.getLogger(DispositivoWS.class);

	/**
	 * Servicio de tipo GET en el que se consultaran todos los dispositivos
	 * dentro de la base de datos. Para acceder a este servicio se deberá
	 * agregar a la url el path /consultarTodos
	 * 
	 * @return lista de dispositivos encontrados
	 */
	@GET
	@Path("/consultarTodos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DispositivoWSDTO> consultarTodos() {
		List<DispositivoWSDTO> lista = new ArrayList<DispositivoWSDTO>();
		try {
			// For que recorre cada uno de los dispositivos almacenados en la
			// base de datos
			for (Dispositivo dispositivo : dispositivoBL.consultarTodos()) {
				// Crea un objeto del tipo DispositivoWSDTO
				DispositivoWSDTO dispositivoWS = new DispositivoWSDTO();
				// Almacena cada dispositivo recuperado tras la consulta en el
				// objeto de tipo dispositivoWSDTO
				dispositivoWS.setTipo(dispositivo.getTipo());
				dispositivoWS.setDescripcion(dispositivo.getDescripcion());
				dispositivoWS.setFoto(dispositivo.getFoto());
				dispositivoWS.setDisponible(dispositivo.isDisponible());
				dispositivoWS.setNombre(dispositivo.getNombre());
				dispositivoWS.setReferencia(dispositivo.getReferencia());
				// Anade el dispositivo a la lista de los dispositivos que se
				// mostrarán en el servicio
				lista.add(dispositivoWS);
			}
		} catch (MyException e) {
			logger.error(e.getMessage());
		}
		return lista;
	}

	/**
	 * Servicio de tipo GET en el que se consultará un dispositivo identificado
	 * con una referencia ingresada en la url.
	 * 
	 * @param referencia
	 *            String con la referencia del dispositivo que se quiere buscar.
	 * @return Dispositivo encontrado tras la consulta
	 */
	@Path("/consultarUno")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public DispositivoWSDTO consultarUno(@QueryParam("referencia") String referencia) {
		DispositivoWSDTO dispositivoWS = new DispositivoWSDTO();
		try {
			// Se almacena en la variable dispositivo el dispositivo retornado
			// del metodo consultar uno de la logica de negocio
			Dispositivo dispositivo = dispositivoBL.consultarUno(referencia);
			// Se asignan al objeto del tipo DispositivoWSDTO los valores
			// definidos para mostrar en el servicio

			dispositivoWS.setTipo(dispositivo.getTipo());
			dispositivoWS.setDescripcion(dispositivo.getDescripcion());
			dispositivoWS.setFoto(dispositivo.getFoto());
			dispositivoWS.setDisponible(dispositivo.isDisponible());
			dispositivoWS.setNombre(dispositivo.getNombre());
			dispositivoWS.setReferencia(dispositivo.getReferencia());

		} catch (MyException e) {
			logger.error(e.getMessage());
			return null;
		}
		return dispositivoWS;
	}

	/**
	 * Servicio de tipo POST en el que se crea un nuevo dispositivo
	 * 
	 * @param referencia
	 *            String con el identificador del dispositivo a crear.
	 * @param nombre
	 *            Nombre del dispositivo.
	 * @param descripcion
	 *            Descripcion y caracteristicas tecnicas del dispositivo a
	 *            crear.
	 * @param tipo
	 *            Describe el tipo de dispositivo (proyector, ordenador, movil,
	 *            etc).
	 * @param foto
	 *            Enlace a internet con una foto del dispositivo.
	 * @param emailAdministrador
	 *            Correo electronico del administrador que agrega el
	 *            dispositivo.
	 * @return String con el estado de la operacion.
	 */
	@POST
	@Path("/crear")
	@Produces(MediaType.TEXT_PLAIN)
	public String crearDispositivo(@QueryParam("referencia") String referencia, @QueryParam("nombre") String nombre,
			@QueryParam("descripcion") String descripcion, @QueryParam("tipo") int tipo,
			@QueryParam("foto") String foto, @QueryParam("emailAdministrador") String emailAdministrador) {
		try {
			// Se invoca el metodo de crearDispositivo definido en la logica del
			// negocio con los parametros ingresados en la url
			dispositivoBL.crearDispositivo(referencia, nombre, descripcion, tipo, foto, emailAdministrador);
		} catch (MyException e) {
			logger.error(e.getMessage());
			return (e.getMessage());
		}
		return "El dispositivo se ha creado exitosamente";

	}

	/**
	 * Solicitud de tipo GET en la que se consultarán los dispositivos según un
	 * tipo especificado.
	 * 
	 * @param tipo
	 *            Entero que especifica el tipo de dispositivos que se quiere
	 *            encontrar.
	 * @return Lista de los dispositivos que corresponden al tipo especificado.
	 *         El formato en que se retorna la consulta es en JSON
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/consultarPorTipo")
	public List<DispositivoWSDTO> consultarPorTipo(@QueryParam("tipo") int tipo) {
		List<DispositivoWSDTO> lista = new ArrayList<DispositivoWSDTO>();
		try {
			// For que recorre cada uno de los dispositivos retornados por el
			// metodo consultarPorDispositivo definido en la logica del negocio
			for (Dispositivo dispositivo : dispositivoBL.consultarPorTipo(tipo)) {
				// Se crea un objeto del tipo DispositivoWSDTO
				DispositivoWSDTO dispositivoWS = new DispositivoWSDTO();
				// Se asignan al objeto del tipo DispositivoWSDTO los valores
				// definidos para mostrar en el servicio
				dispositivoWS.setTipo(dispositivo.getTipo());
				dispositivoWS.setDescripcion(dispositivo.getDescripcion());
				dispositivoWS.setFoto(dispositivo.getFoto());
				dispositivoWS.setDisponible(dispositivo.isDisponible());
				dispositivoWS.setNombre(dispositivo.getNombre());
				dispositivoWS.setReferencia(dispositivo.getReferencia());
				// Se anade a la lista el dispositivo
				lista.add(dispositivoWS);
			}
		} catch (MyException e) {
			logger.error(e.getMessage());
		}
		return lista;
	}

	/**
	 * Servicio del tipo PUT en el que se actualizan los datos correspondientes
	 * a un dispositivo especifico
	 * 
	 * @param referencia
	 *            String con el identificador del dispositivo a actualizar.
	 * @param nombre
	 *            Nuevo nombre a asignar al dispositivo.
	 * @param descripcion
	 *            Descripcion y caracteristicas nuevos del dispositivo.
	 * @param tipo
	 *            Tipo del dispositivo que se quiere actualizar.
	 * @param foto
	 *            Nuevo enlace a la foto del dispositivo.
	 * @param disponible
	 *            Boolean especificando si el dispositivo esta disponible a
	 *            prestamo.
	 * @param emailAdministrador
	 *            Correo electronico del administrador que realiza la
	 *            actualizacion de datos.
	 * @return String con el mensaje de estado de la operacion, ya sea de exito
	 *         o de error
	 */
	@PUT
	@Path("/actualizar")
	@Produces(MediaType.TEXT_PLAIN)
	public String actualizarDispositivo(@QueryParam("referencia") String referencia,
			@QueryParam("nombre") String nombre, @QueryParam("descripcion") String descripcion,
			@QueryParam("tipo") int tipo, @QueryParam("foto") String foto, @QueryParam("disponible") boolean disponible,
			@QueryParam("emailAdministrador") String emailAdministrador) {
		try {
			// Invoca el metodo actualizarDispositivo de la logica del negocio
			// con los parametros ingresados desde la url
			dispositivoBL.actualizarDispositivo(referencia, nombre, descripcion, tipo, foto, disponible,
					emailAdministrador);
		} catch (MyException e) {
			logger.error(e.getMessage());
			return e.getMessage();
		}
		return "El dispositivo se actualizó correctamente";
	}

	/**
	 * Servicio del tipo GET en el que se consultan los dispositivos que están
	 * disponibles dentro de una intervalo de fecha especifica
	 * 
	 * @param fechaInicio
	 *            Fecha inicial en la que se desea hacer la consulta de
	 *            disponibilidad de dispositivos.
	 * @param fechaFin
	 *            Fecha final en la que se desea hacer la consulta de
	 *            disponibilidad de dispositivos.
	 * @return Lista de los dispositivos que se encuentran disponibles en las
	 *         fechas seleccionadas
	 */
	@GET
	@Path("/mostrarDispositivosDisponibles")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DispositivoWSDTO> mostrarDispositivosDisponibles(@QueryParam("fechaInicio") String fechaInicio,
			@QueryParam("fechaFin") String fechaFin) {
		List<DispositivoWSDTO> lista = new ArrayList<DispositivoWSDTO>();
		// Define el formato en el que se recibe la fecha
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

		try {
			// Pasa las fechas ingresadas como String al tipo Date
			Date fechaIni = sdf.parse(fechaInicio);
			Date fechaEnd = sdf.parse(fechaFin);
			// Recorre la lista de dispositivos que se encuentran disponibles
			// dentro del rango de la fechas ingresadas
			for (Dispositivo dispositivo : dispositivoBL.mostrarDispositivosDisponibles(fechaIni, fechaEnd)) {
				// Crea un objeto del tipo DispositivoWSDTO
				DispositivoWSDTO dispositivoWS = new DispositivoWSDTO();
				// Se asignan al objeto del tipo DispositivoWSDTO los valores
				// definidos para mostrar en el servicio
				dispositivoWS.setTipo(dispositivo.getTipo());
				dispositivoWS.setDescripcion(dispositivo.getDescripcion());
				dispositivoWS.setFoto(dispositivo.getFoto());
				dispositivoWS.setDisponible(dispositivo.isDisponible());
				dispositivoWS.setNombre(dispositivo.getNombre());
				dispositivoWS.setReferencia(dispositivo.getReferencia());
				// anade el dispositivo a la lista que se retornara
				lista.add(dispositivoWS);
			}
		} catch (ParseException e) {
			logger.error(e.getMessage());
			return null;
		}
		return lista;

	}

}
