package co.edu.udea.iw.webServices;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.udea.iw.bl.TipoBL;
import co.edu.udea.iw.dto.Tipo;
import co.edu.udea.iw.dto.TipoWSDTO;
import co.edu.udea.iw.exception.MyException;

@Component
@Path("Tipo")
public class TipoWS {
	
	@Autowired
	TipoBL tipoBL;
	
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
			e.getMessage();
		}
		return lista;
	}

}
