/**
 * 
 */
package co.edu.udea.iw.webServices;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.udea.iw.bl.PrestamoBL;
import co.edu.udea.iw.dto.Prestamo;
import co.edu.udea.iw.dto.PrestamoWSDTO;
import co.edu.udea.iw.exception.MyException;

/**
 * Esta clase contiene los metodos de los servicios web a consumir
 * correspondientes a todo lo que concierne a Prestamos de dispositivos.
 * 
 * @author Sebastian Jimenez
 * @author Carolina Isaza
 * @author Jaime Londono
 *
 */
@Component
@Path("Prestamo")
public class PrestamoWS {

	/**
	 * Objeto autoinyectable de la clase PrestamoBL que permitira acceder a los
	 * metodos en relacion a Prestamos de dispositivos en lo que a la logica del
	 * negocio se refiere.
	 */
	@Autowired
	PrestamoBL prestamoBL;

	/**
	 * Servicio para consultar todos los prestamos registrados en la base de datos.
	 * @return Lista con todos los prestamos encontrados.
	 * @throws RemoteException
	 */
	@Path("/consultarTodos")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public List<PrestamoWSDTO> consultarTodos() throws RemoteException {
		List<PrestamoWSDTO> lista = new ArrayList<PrestamoWSDTO>();
		try {
			for (Prestamo prestamo : prestamoBL.consultarTodos()) {
				PrestamoWSDTO prestamoWS = new PrestamoWSDTO();

				if (prestamo.getAdministrador() != null) {
					prestamoWS.setCorreoAdmin(prestamo.getAdministrador().getEmail());
					prestamoWS.setNombreAdmin(prestamo.getAdministrador().getNombre());
					prestamoWS.setApellidoAdmin(prestamo.getAdministrador().getApellidos());
				}
				prestamoWS.setCedulaUsuario(prestamo.getCedulaUsuario());
				prestamoWS.setCorreoUsuario(prestamo.getCorreoUsuario());
				prestamoWS.setEstado(prestamo.getEstado());
				prestamoWS.setFechaEntrega(prestamo.getFechaEntrega());
				prestamoWS.setFechaFin(prestamo.getFechaFin());
				prestamoWS.setFechaInicio(prestamo.getFechaInicio());
				prestamoWS.setIdPrestamo(prestamo.getIdPrestamo());
				prestamoWS.setNombreUsuario(prestamo.getNombreUsuario());

				lista.add(prestamoWS);
			}
		} catch (MyException e) {
			e.getMessage();
		}

		return (lista);
	}

	/**
	 * Servicio para solicitar un prestamo de dispositivos.
	 * 
	 * @param nombreUsuario Nombre de la persona que solicita el prestamo.
	 * @param cedulaUsuario Cedula o identificacion de la persona que solicita el prestamo. 
	 * @param emailUsuario Correo electronico de la persona que solicita el prestamo.
	 * @param fechaInicio
	 * @param fechaFin
	 * @param dispositivos
	 * @return
	 * @throws RemoteException
	 */
	@Path("/crear")
	@Produces(MediaType.TEXT_PLAIN)
	@POST
	public String crear(@QueryParam("nombreUsuario") String nombreUsuario,
			@QueryParam("cedulaUsuario") String cedulaUsuario, @QueryParam("emailUsuario") String emailUsuario,
			@QueryParam("fechaInicio") String fechaInicio, @QueryParam("fechaFin") String fechaFin,
			@QueryParam("dispositivos") List<String> dispositivos) throws RemoteException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

		try {
			Date fechaIni = sdf.parse(fechaInicio);
			Date fechaEnd = sdf.parse(fechaFin);

			String disp[] = dispositivos.toArray(new String[dispositivos.size()]);
			// String []disp = new String[dispositivos.size()];
			// dispositivos.toArray(disp);
			// System.out.println(disp);

			prestamoBL.crear(nombreUsuario, cedulaUsuario, emailUsuario, fechaIni, fechaEnd, disp);
		} catch (ParseException ex) {
			ex.getMessage();
		} catch (MyException e) {
			return e.getMessage();
		}

		return ("El préstamo ha quedado almacenado");
	}

	/**
	 * Servicio para aprobar o rechazar (modificar el estado) solicitudes de Prestamo.
	 * @param id Identificador del prestamo a revisar.
	 * @param correoAdministrador Correo electronico del administrador que realiza la operacion.
	 * @param estado Nuevo estado a asignar al prestamo.
	 * @return Mensaje confirmado si la operacion fue exitosa. De lo contrario, se retorna la pila de error.
	 */
	@Path("/modificar")
	@Produces(MediaType.TEXT_PLAIN)
	@POST
	public String modificar(@QueryParam("id") int id, @QueryParam("correoAdministrador") String correoAdministrador,
			@QueryParam("estado") int estado) {

		try {
			prestamoBL.modificar(id, correoAdministrador, estado);
		} catch (MyException e) {
			return e.getMessage();
		}

		return ("El estado fue cambiado correctamente");
	}

	/**
	 * Servicio para consultar una solicitud de prestamo especifica.
	 * @param id Identificador de la solicitud que se desea consultar.
	 * @return Toda la informacion de la solicitud especificada.
	 */
	@Path("/consultarUno")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public PrestamoWSDTO consultarUno(@QueryParam("id") int id) {
		PrestamoWSDTO prestamoWS = new PrestamoWSDTO();

		try {
			Prestamo prestamo = prestamoBL.consultarUno(id);

			if (prestamo.getAdministrador() != null) {
				prestamoWS.setCorreoAdmin(prestamo.getAdministrador().getEmail());
				prestamoWS.setNombreAdmin(prestamo.getAdministrador().getNombre());
				prestamoWS.setApellidoAdmin(prestamo.getAdministrador().getApellidos());
			}
			prestamoWS.setCedulaUsuario(prestamo.getCedulaUsuario());
			prestamoWS.setCorreoUsuario(prestamo.getCorreoUsuario());
			prestamoWS.setEstado(prestamo.getEstado());
			prestamoWS.setFechaEntrega(prestamo.getFechaEntrega());
			prestamoWS.setFechaFin(prestamo.getFechaFin());
			prestamoWS.setFechaInicio(prestamo.getFechaInicio());
			prestamoWS.setIdPrestamo(prestamo.getIdPrestamo());
			prestamoWS.setNombreUsuario(prestamo.getNombreUsuario());

		} catch (MyException e) {
			e.getMessage();
		}

		return prestamoWS;
	}

	/**
	 * Servicio para consultar todas las solicitudes de prestamo que aun no han recibido respuesta.
	 * @return Lista de todas las solicitudes encontradas.
	 */
	@Path("/sinRevisar")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public List<PrestamoWSDTO> prestamosSinRevisar() {
		List<PrestamoWSDTO> lista = new ArrayList<PrestamoWSDTO>();
		try {
			for (Prestamo prestamo : prestamoBL.prestamosSinRevisar()) {
				PrestamoWSDTO prestamoWS = new PrestamoWSDTO();
				if (prestamo.getAdministrador() != null) {
					prestamoWS.setCorreoAdmin(prestamo.getAdministrador().getEmail());
					prestamoWS.setNombreAdmin(prestamo.getAdministrador().getNombre());
					prestamoWS.setApellidoAdmin(prestamo.getAdministrador().getApellidos());
				}
				prestamoWS.setCedulaUsuario(prestamo.getCedulaUsuario());
				prestamoWS.setCorreoUsuario(prestamo.getCorreoUsuario());
				prestamoWS.setEstado(prestamo.getEstado());
				prestamoWS.setFechaEntrega(prestamo.getFechaEntrega());
				prestamoWS.setFechaFin(prestamo.getFechaFin());
				prestamoWS.setFechaInicio(prestamo.getFechaInicio());
				prestamoWS.setIdPrestamo(prestamo.getIdPrestamo());
				prestamoWS.setNombreUsuario(prestamo.getNombreUsuario());

				lista.add(prestamoWS);
			}
		} catch (MyException e) {
			e.getMessage();
		}

		return (lista);
	}
}
