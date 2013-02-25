package model.mensagens;

import java.util.GregorianCalendar;
import java.util.Observable;

import model.Utilizador;


public abstract class Mensagem extends Observable{
	
		// Variaveis de Instancia
	private int id;
	private String _assunto;
	private String _corpo;
	private boolean _lida;
	private GregorianCalendar _data;
	public Utilizador _destinatario;
	private Utilizador _remetente;
	private boolean apagada;

		// Construtores
	
	public Mensagem(){
		this._assunto = new String();
		this._corpo = new String();
		this._lida = true;
		this._data = new GregorianCalendar();
		this._destinatario = new Utilizador();
		this._remetente = new Utilizador();	
	}
	
	public Mensagem(String pAssunto, String pCorpo, boolean pLida,
			GregorianCalendar pData, Utilizador pDestinatario, 
			Utilizador pRemetente){
		this._assunto = pAssunto;
		this._corpo = pCorpo;
		this._lida = pLida;
		
		this._data = (GregorianCalendar) pData.clone();
		
		this._destinatario = pDestinatario.clone();
		this._remetente = pRemetente.clone();
		
	}
	
	public Mensagem(String pAssunto, String pCorpo, boolean pLida,
			Utilizador pDestinatario, Utilizador pRemetente){
		this._assunto = pAssunto;
		this._corpo = pCorpo;
		this._lida = pLida;
		
		this._data = new GregorianCalendar();
		
		this._destinatario = pDestinatario.clone();
		this._remetente = pRemetente.clone();
		
	}
	
	public Mensagem(Mensagem pMensagem){
		this._assunto = pMensagem.getAssunto();
		this._corpo = pMensagem.getCorpo();
		this._lida = pMensagem.getLida();
		
		this._data = pMensagem.getData();
		
		this._destinatario = pMensagem.getDestinatario();
		this._remetente = pMensagem.getRemetente();
		
	}
	
		// Getters e Setters
	
	public void setRemetente(Utilizador pRemetente) {
		this._remetente = pRemetente.clone();
	}

	public Utilizador getRemetente() {
		return this._remetente.clone();
	}
	
	public void setDestinatario(Utilizador pDestinatario) {
		this._destinatario = pDestinatario.clone();
	}

	public Utilizador getDestinatario() {
		return this._destinatario.clone();
	}

	public void setAssunto(String pAssunto) {
		this._assunto = pAssunto;
	}

	public String getAssunto() {
		return this._assunto;
	}

	public void setCorpo(String pCorpo) {
		this._corpo = pCorpo;
	}

	public String getCorpo() {
		return this._corpo;
	}

	public void setLida(boolean pLida) {
		this._lida = pLida;
	}

	public boolean getLida() {
		return this._lida;
	}

	public void setData(GregorianCalendar pData) {
		this._data = (GregorianCalendar) pData.clone();
	}

	public GregorianCalendar getData() {
		return (GregorianCalendar) this._data.clone();
	}


		// clone, toString, equals, hashCode
	
	@Override
	public abstract Mensagem clone();
	
	@Override
	public String toString() {
		return "Mensagem:\nAssunto = " + _assunto + "\nCorpo = " + _corpo
				+ "\nData = " + _data + "\nDestinatario = " + _destinatario.getNomeUtilizador()
				+ "\nRemetente = " + _remetente.getNomeUtilizador() + "\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((_assunto == null) ? 0 : _assunto.hashCode());
		result = prime * result + ((_corpo == null) ? 0 : _corpo.hashCode());
		result = prime * result + ((_data == null) ? 0 : _data.hashCode());
		result = prime * result
				+ ((_destinatario == null) ? 0 : _destinatario.hashCode());
		result = prime * result + (_lida ? 1231 : 1237);
		result = prime * result
				+ ((_remetente == null) ? 0 : _remetente.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mensagem other = (Mensagem) obj;
		if (_assunto == null) {
			if (other.getAssunto() != null)
				return false;
		} else if (!_assunto.equals(other.getAssunto()))
			return false;
		if (_corpo == null) {
			if (other.getCorpo() != null)
				return false;
		} else if (!_corpo.equals(other.getCorpo()))
			return false;
		if (_data == null) {
			if (other.getData() != null)
				return false;
		} else if (!_data.equals(other.getData()))
			return false;
		if (_destinatario == null) {
			if (other.getDestinatario() != null)
				return false;
		} else if (!_destinatario.equals(other.getDestinatario()))
			return false;
		if (_lida != other._lida)
			return false;
		if (_remetente == null) {
			if (other.getRemetente() != null)
				return false;
		} else if (!_remetente.equals(other.getRemetente()))
			return false;
		return true;
	}

	public boolean isApagada() {
		return apagada;
	}

	public void setApagada(boolean apagada) {
		this.apagada = apagada;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
