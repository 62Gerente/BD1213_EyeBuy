package model.mensagens;

import java.util.GregorianCalendar;

import model.Utilizador;


public class MensagemAdministrador extends Mensagem {
	
	// Construtores
	
	public MensagemAdministrador(){
		super();
	}
	
	public MensagemAdministrador(String pAssunto, String pCorpo, boolean pLida,
			GregorianCalendar pData, Utilizador pDestinatario, 
			Utilizador pRemetente){
		super(pAssunto,pCorpo,pLida,pData,pDestinatario,pRemetente);
	}
	
	public MensagemAdministrador(String pAssunto, String pCorpo, boolean pLida,
			Utilizador pDestinatario, Utilizador pRemetente){
		super(pAssunto,pCorpo,pLida,pDestinatario,pRemetente);
	}
	
	public MensagemAdministrador(MensagemAdministrador pMensagemAdministrador){
		super(pMensagemAdministrador);
	}
	
	// clone, toString, equals, hashCode
	
	@Override
	public MensagemAdministrador clone(){
		return new MensagemAdministrador(this);
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