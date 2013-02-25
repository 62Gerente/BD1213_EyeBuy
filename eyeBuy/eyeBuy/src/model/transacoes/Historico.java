package model.transacoes;

import java.util.GregorianCalendar;

import model.Produto;
import model.Utilizador;

public class Historico {
		// Variaveis de Instancia
	
	private int _id;
	private String _descricao;
	private GregorianCalendar _data;
	//private double _precoFinal;
	private Utilizador _comprador;
	//private Utilizador _vendedor;
	private Produto _produto;
	//private boolean avaliado;
	private GregorianCalendar dataAvaliacao;

		// Construtores
	
	public Historico(){
		this._id = 0;
		this._descricao = new String();
		this._data = new GregorianCalendar();
		this._comprador = new Utilizador();
		this._produto = new Produto();
	}
	
	public Historico(int pID, String pDescricao, GregorianCalendar pData, Utilizador pComprador,
			Produto pProduto, GregorianCalendar dataAvaliacao) {
		this._id = pID;
		this._descricao = pDescricao;
		this._data = (GregorianCalendar) pData.clone();
		this._comprador = pComprador.clone();
		this._produto = pProduto.clone();
		this.dataAvaliacao = (GregorianCalendar) dataAvaliacao.clone();
	}
	
	public Historico(int pID, String pDescricao, Utilizador pComprador, Produto pProduto) {
		this._id = pID;
		this._descricao = pDescricao;
		this._data = new GregorianCalendar();
		this._comprador = pComprador.clone();
		this._produto = pProduto.clone();
	}
	
	public Historico(Historico pHistorico){
		this._id = pHistorico.getID();
		this._descricao = pHistorico.getDescricao();
		this._data = pHistorico.getData();
		this._comprador = pHistorico.getComprador();
		this._produto = pHistorico.getProduto();
	}
	
	public void setComprador(Utilizador pComprador) {
		this._comprador = pComprador.clone();
	}

	public Utilizador getComprador() {
		return this._comprador.clone();
	}

	public void setProduto(Produto pProduto) {
		this._produto = pProduto.clone();
	}

	public Produto getProduto() {
		return this._produto.clone();
	}

	public void setId(int pID) {
		this._id = pID;
	}

	public int getID() {
		return this._id;
	}

	public void setDescricao(String pDescricao) {
		this._descricao = pDescricao;
	}

	public String getDescricao() {
		return this._descricao;
	}

	public void setData(GregorianCalendar pData) {
		this._data = (GregorianCalendar) pData.clone();
	}
	
	public void setData() {
		this._data = new GregorianCalendar();
	}

	public GregorianCalendar getData() {
		return (GregorianCalendar) this._data.clone();
	}


	
    // Clone, toString, equals, hashCode
	
	@Override
	public Historico clone(){
		return new Historico(this);
	}
	
	@Override
	public String toString() {
		return "Historico: \nID = " + _id + "\n	Descricão = " + _descricao
				+ "\nData = " + _data 
				+ "\nComprador = " + _comprador.getNomeUtilizador() 
				+ "\nPproduto = " + _produto + "\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == 0) ? 0 : _id);
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
		Historico other = (Historico) obj;
		if (_id == 0) {
			if (other.getID() != 0)
				return false;
		} else if (_id != (other.getID()))
			return false;
		return true;
	}

	public GregorianCalendar getDataAvaliacao() {
		return dataAvaliacao;
	}

	public void setDataAvaliacao(GregorianCalendar dataAvaliacao) {
		this.dataAvaliacao = dataAvaliacao;
	}
	
}