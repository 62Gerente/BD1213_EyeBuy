package model.mensagens;

import java.util.GregorianCalendar;

import model.Utilizador;


public class MensagemSistema extends Mensagem {
	
	// Construtores
	
	public MensagemSistema(){
		super();
	}
	
	public MensagemSistema(String pAssunto, String pCorpo, boolean pLida,
			GregorianCalendar pData, Utilizador pDestinatario, 
			Utilizador pRemetente){
		super(pAssunto,pCorpo,pLida,pData,pDestinatario,pRemetente);
	}
	
	public MensagemSistema(String pAssunto, String pCorpo, boolean pLida,
			Utilizador pDestinatario){
		super(pAssunto,pCorpo,pLida,pDestinatario,null);
	}
	
	public MensagemSistema(MensagemSistema pMensagemSistema){
		super(pMensagemSistema);
	}
	
	// clone, toString, equals, hashCode
	
	@Override
	public MensagemSistema clone(){
		return new MensagemSistema(this);
	}
	
	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}	
}