package br.com.caelum.estoque.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import br.com.caelum.estoque.modelo.item.Filtro;
import br.com.caelum.estoque.modelo.item.Filtros;
import br.com.caelum.estoque.modelo.item.Item;
import br.com.caelum.estoque.modelo.item.ItemDao;
import br.com.caelum.estoque.modelo.item.ItemValidador;
import br.com.caelum.estoque.modelo.usuario.TokenDao;
import br.com.caelum.estoque.modelo.usuario.TokenUsuario;
import br.com.caelum.estoque.ws.exception.AutorizacaoException;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL, 
parameterStyle = ParameterStyle.WRAPPED) // ParameterStyle definindo com embrulhamento ou sem embrulhamento
public class EstoqueWs {
	
	private ItemDao dao = new ItemDao();
	
	@WebMethod(operationName = "todosOsItens")
	@WebResult(name = "item")
	@ResponseWrapper(localName="itens")
	@RequestWrapper(localName = "listaItens")
	public List<Item> getItens(@WebParam(name="filtros")Filtros filtros){
		System.out.println("Chamando getItens");
		 List<Filtro> lista = filtros.getLista();
		List<Item> itens = dao.todosItens(lista);
		return itens;
	}
	
	@WebMethod(operationName = "cadastrarItem")
	@WebResult(name = "item")
	public Item cadastrarItem(
			@WebParam(name="tokenUsuario",header = true)TokenUsuario tokenUsuario,
			@WebParam(name="item") Item item) throws AutorizacaoException {
		
		System.out.println("Cadastrando Item " + item + " Token: " + tokenUsuario);
		
		boolean valido = new TokenDao().ehValido(tokenUsuario);
		
		if(!valido) {
			throw new AutorizacaoException("Autorização falhou.");
		}
		
		new ItemValidador(item).validate();
		
		this.dao.cadastrar(item);
		
		return item;
	}

}
