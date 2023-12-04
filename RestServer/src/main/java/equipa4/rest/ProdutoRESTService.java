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

import equipa4.Produto;
import equipa4.ProdutoService;


@Path("/produto")
public class ProdutoRESTService {
	private ProdutoService produtoService;
	
	public ProdutoRESTService(EntityManager em) {
        this.produtoService = new ProdutoService(em);
    }
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello(Request p) {
		return "REST Server: Bem vindo ao restaurante";
	}
	@GET
	@Path("/getProdutos")
	public Response getProdutos() {
		List<Produto> produtos = produtoService.findAllProduto();	
		return Response.status(Response.Status.OK)
				.entity(produtos)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
	@GET
	@Path("/getProduto/{id}")
	public Response getNome(@PathParam("id") int id) {
		Produto produtoResponse = produtoService.findProduto(id);
		
		return Response.status(Response.Status.OK)
				.entity(produtoResponse)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
	@POST
	@Path("/addProduto")
	public Response updateProduto(int id, String nome, String descricao, String grupo, String ingredientes, int infNutricional, String alergenios, float preco) {
		Produto produtoResponse = produtoService.updateProduto(id, nome, descricao, grupo, ingredientes, infNutricional, alergenios, preco );	
		return Response.status(Response.Status.CREATED)
				.entity(produtoResponse)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
	
	@DELETE
	@Path("/removeProduto/{id}")
	public Response removeProduto(@PathParam("id") int id) {
		Produto produtoRemoved = produtoService.removeProduto(id);
		
		return Response.status(Response.Status.OK)
				.entity(produtoRemoved)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
}