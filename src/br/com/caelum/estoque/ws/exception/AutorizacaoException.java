package br.com.caelum.estoque.ws.exception;

import java.util.Date;

import javax.xml.ws.WebFault;

@WebFault(name = "AutorizacaoFault")
public class AutorizacaoException extends Exception {
	private static final long serialVersionUID = 3808665396670957168L;
	
	public AutorizacaoException(String mensagem) {
		super(mensagem);
	}
	
	public InfoFault getFaultInfo() {
		return new InfoFault(new Date(), "Token invalido");
	}

}
