package equipa4.rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import equipa4.Menu;
import equipa4.MenuService;


@Path("/menu")
public class MenuRESTService {
	private MenuService menuService;
	
	public MenuRESTService(EntityManager em) {
        this.menuService = new MenuService(em);
    }
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello(Request p) {
		return "REST Server: Bem vindo ao restaurante";
	}
	@GET
	@Path("/getMenus")
	public Response getProdutos() {
		List<Menu> menus = menuService.findAllMenus();	
		return Response.status(Response.Status.OK)
				.entity(menus)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
	@GET
	@Path("/getMenus/{id}")
	public Response getId(@PathParam("id") int id) {
		Menu menuResponse = menuService.findMenu(id);
		
		return Response.status(Response.Status.OK)
				.entity(menuResponse)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
	@POST
	@Path("/addProduto")
	public Response updateMenu(int idM, String nomeM, String descricaoM, String grupoM, int infNM, String alergenicosM, float precoM) {
		Menu produtoResponse = menuService.updateMenu(idM, nomeM, descricaoM, grupoM, infNM, alergenicosM, precoM);	
		return Response.status(Response.Status.CREATED)
				.entity(produtoResponse)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
	
	@DELETE
	@Path("/removeProduto/{id}")
	public Response removeProduto(@PathParam("idM") int idM) {
		Menu menuRemoved = menuService.removeMenu(idM);
		
		return Response.status(Response.Status.OK)
				.entity(menuRemoved)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
}
