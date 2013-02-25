package model.mensagens;

import java.util.GregorianCalendar;

import model.Utilizador;


public class MensagemPessoal extends Mensagem {
	
	// Construtores
	
	public MensagemPessoal(){
		super();
	}
	
	public MensagemPessoal(String pAssunto, String pCorpo, boolean pLida,
			GregorianCalendar pData, Utilizador pDestinatario, 
			Utilizador pRemetente){
		super(pAssunto,pCorpo,pLida,pData,pDestinatario,pRemetente);
	}
	
	public MensagemPessoal(String pAssunto, String pCorpo, boolean pLida,
			Utilizador pDestinatario, Utilizador pRemetente){
		super(pAssunto,pCorpo,pLida,pDestinatario,pRemetente);
	}
	
	public MensagemPessoal(MensagemPessoal pMensagemPessoal){
		super(pMensagemPessoal);
	}
	
	// clone, toString, equals, hashCode
	
	@Override
	public MensagemPessoal clone(){
		return new MensagemPessoal(this);
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