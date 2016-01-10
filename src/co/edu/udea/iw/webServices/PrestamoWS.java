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
 * @author Sebastian
 *
 */
@Component
@Path("Prestamo")
public class PrestamoWS {

	@Autowired
	PrestamoBL prestamoBL;

	@Path("/consultarTodos")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public List<PrestamoWSDTO> consultarTodos() throws RemoteException {
		List<PrestamoWSDTO> lista = new ArrayList<PrestamoWSDTO>();
		try {
			for (Prestamo prestamo : prestamoBL.consultarTodos()) {
				PrestamoWSDTO prestamoWS = new PrestamoWSDTO();

				prestamoWS.setAdministrador(prestamo.getAdministrador());
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

	@Path("/crear")
	@Produces(MediaType.TEXT_PLAIN)
	@POST
	public String crear(@QueryParam("nombreUsuario") String nombreUsuario,
			@QueryParam("cedulaUsuario") String cedulaUsuario, @QueryParam("emailUsuario") String emailUsuario,
			@QueryParam("emailAdmin") String emailAdmin, @QueryParam("fechaInicio") String fechaInicio,
			@QueryParam("fechaFin") String fechaFin, @QueryParam("dispositivos") List<String> dispositivos)
					throws RemoteException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

		try {
			Date fechaIni = sdf.parse(fechaInicio);
			Date fechaEnd = sdf.parse(fechaFin);

			String disp[] = dispositivos.toArray(new String[dispositivos.size()]);
			// String []disp = new String[dispositivos.size()];
			// dispositivos.toArray(disp);
			// System.out.println(disp);

			prestamoBL.crear(nombreUsuario, cedulaUsuario, emailUsuario, emailAdmin, fechaIni, fechaEnd, disp);
		} catch (ParseException ex) {
			ex.getMessage();
		} catch (MyException e) {
			e.getMessage();
		}

		return ("El préstamo ha quedado almacenado");
	}

	@Path("/modificar")
	@Produces(MediaType.TEXT_PLAIN)
	@POST
	public String modificar(@QueryParam("id") int id, @QueryParam("correoAdministrador") String correoAdministrador,
			@QueryParam("estado") int estado) {

		try {
			prestamoBL.modificar(id, correoAdministrador, estado);
		} catch(MyException e) {
			e.getMessage();
		}
		
		return("El estado fue cambiado correctamente");
	}
	
	@Path("/consultarUno")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public PrestamoWSDTO consultarUno(@QueryParam("id") int id) {
		PrestamoWSDTO prestamoWS = new PrestamoWSDTO();
		
		try {
			Prestamo prestamo = prestamoBL.consultarUno(id);
			
			prestamoWS.setAdministrador(prestamo.getAdministrador());
			prestamoWS.setCedulaUsuario(prestamo.getCedulaUsuario());
			prestamoWS.setCorreoUsuario(prestamo.getCorreoUsuario());
			prestamoWS.setEstado(prestamo.getEstado());
			prestamoWS.setFechaEntrega(prestamo.getFechaEntrega());
			prestamoWS.setFechaFin(prestamo.getFechaFin());
			prestamoWS.setFechaInicio(prestamo.getFechaInicio());
			prestamoWS.setIdPrestamo(prestamo.getIdPrestamo());
			prestamoWS.setNombreUsuario(prestamo.getNombreUsuario());
			
		} catch(MyException e) {
			e.getMessage();
		}
		
		return prestamoWS;
	}
	
	@Path("/sinRevisar")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public List<PrestamoWSDTO> prestamosSinRevisar() {
		List<PrestamoWSDTO> lista = new ArrayList<PrestamoWSDTO>();
		try {
			for (Prestamo prestamo : prestamoBL.prestamosSinRevisar()) {
				PrestamoWSDTO prestamoWS = new PrestamoWSDTO();

				prestamoWS.setAdministrador(prestamo.getAdministrador());
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
