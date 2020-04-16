package br.com.caelum.estoque.ws.exception;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class InfoFault {
	
	private Date dataErro;
	private String mensagem;
	
	public InfoFault(Date dataErro, String mensagem) {
		this.dataErro = dataErro;
		this.mensagem = mensagem;
	}

	public InfoFault() {
	}

	public Date getDataErro() {
		return dataErro;
	}

	public void setDataErro(Date dataErro) {
		this.dataErro = dataErro;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
