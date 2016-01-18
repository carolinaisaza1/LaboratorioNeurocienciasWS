package co.edu.udea.iw.webServices;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.udea.iw.bl.TipoBL;
import co.edu.udea.iw.dto.Tipo;
import co.edu.udea.iw.dto.TipoWSDTO;
import co.edu.udea.iw.exception.MyException;

/**
 * Clase en la que se implementan los servicios Restful necesarios para crear y
 * consultar tipos de dispositivos
 *
 * @author Carolina Isaza
 * @author Sebastian Jimenez
 * @author Jaime Londono
 *
 */

@Component
@Path("Tipo")
public class TipoWS {

	/**
	 * Objeto que se inyecta de la clase tipoBL que permite acceder y regular de
	 * acuerdo a las reglas de negocio, las operaciones de los diferentes tipos
	 * de dispositivos
	 */
	@Autowired
	TipoBL tipoBL;

	/**
	 * Objeto de la clase Logger que permitir� almacenar los mensajes de error
	 * en el log
	 */
	private static Logger logger = Logger.getLogger(TipoWS.class);

	/**
	 * Servicio que permite retornar todos los tipos de dispositivos almacenados
	 * en la base de datos
	 * 
	 * @return lista con todos los tipos de dispositivos almacenados en la base
	 *         de datos
	 */
	@Path("/consultarTodos")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<TipoWSDTO> consultarTodos() {
		List<TipoWSDTO> lista = new ArrayList<TipoWSDTO>();
		try {
			for (Tipo tipo : tipoBL.consultarTodos()) {
				TipoWSDTO tipoWS = new TipoWSDTO();
				tipoWS.setId(tipo.getId());
				tipoWS.setNombre(tipo.getNombre());

				lista.add(tipoWS);
			}
		} catch (MyException e) {
			logger.error(e.getMessage());
		}
		return lista;
	}

	/**
	 * Servicio que permite ingresar a la base de datos un nuevo tipo de
	 * dispositivo
	 * 
	 * @param nombre
	 *            nombre del tipo de dispositivo a crear
	 * @return mensaje confirmando si la operación fue exitosa, o si hubo
	 *         algún error se retorna la pila del mismo.
	 * @throws RemoteException
	 */
	@Path("/crear")
	@Produces(MediaType.TEXT_PLAIN)
	@POST
	public String crear(@QueryParam("nombre") String nombre) throws RemoteException {
		try {
			tipoBL.crear(nombre);
		} catch (MyException e) {
			logger.error(e.getMessage());
			return (e.getMessage());
		}

		return "El tipo de dispositivo fue guardado exitosamente";

	}

	/**
	 * Servicio que permite consultar los tipos de dispositivos individualmente
	 * dado el id de alguno de ellos.
	 * 
	 * @param id
	 *            identificación única de cada tipo de dispositivo
	 * @return dispositivo correspondiente al id dado, si no se encuentra un
	 *         dispositivo con el id se retorna el objeto vacío.
	 */
	@Path("/consultarUno")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public TipoWSDTO consultarUno(@QueryParam("id") Integer id) {

		TipoWSDTO tipoWS = new TipoWSDTO();

		try {
			Tipo tipo = tipoBL.consultarUno(id);
			tipoWS.setId(tipo.getId());
			tipoWS.setNombre(tipo.getNombre());

		} catch (MyException e) {
			logger.error(e.getMessage());
			return null;
		}

		return tipoWS;
	}

}
