package co.edu.udea.iw.webServices;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.udea.iw.bl.DispositivoBL;
import co.edu.udea.iw.dto.Dispositivo;
import co.edu.udea.iw.dto.DispositivoWSDTO;
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
}
