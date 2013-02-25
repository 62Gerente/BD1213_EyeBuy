package model.transacoes;

import java.util.GregorianCalendar;

import model.Produto;
import model.Utilizador;

public class Proposta {
  	// Variaveis de Instancia
	
	private int _id;
	private GregorianCalendar _data;
	private double _preco;
	private Utilizador _comprador;
	private Produto _produto;

		// Construtores
	
	public Proposta(){
		this._id = 0;
		this._preco = 0;
		
		this._data = new GregorianCalendar();
		this._comprador = new Utilizador();
		this._produto = new Produto();
	}
	
	public Proposta(int pID,GregorianCalendar pData,
			double pPreco, Utilizador pComprador,
			Produto pProduto) {
		this._id = pID;
		this._preco = pPreco;
		
		this._data = (GregorianCalendar) pData.clone();
		this._comprador = pComprador.clone();
		this._produto = pProduto.clone();
	}
	
	public Proposta(int pID, double pPreco, 
			Utilizador pComprador, Produto pProduto) {
		this._id = pID;
		this._preco = pPreco;
		
		this._data = new GregorianCalendar();
		
		this._comprador = pComprador.clone();
		this._produto = pProduto.clone();
	}
	
	public Proposta(Proposta pProposta){
		this._id = pProposta.getID();
		this._data = pProposta.getData();
		this._preco = pProposta.getPrecoFinal();
		this._comprador = pProposta.getComprador();
		this._produto = pProposta.getProduto();
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

	public void setData(GregorianCalendar pData) {
		this._data = (GregorianCalendar) pData.clone();
	}
	
	public void setData() {
		this._data = new GregorianCalendar();
	}

	public GregorianCalendar getData() {
		return (GregorianCalendar) this._data.clone();
	}

	public void setPrecoFinal(double pPreco) {
		this._preco = pPreco;
	}

	public double getPrecoFinal() {
		return this._preco;
	}


	
    // Clone, toString, equals, hashCode
	
	@Override
	public Proposta clone(){
		return new Proposta(this);
	}
	
	@Override
	public String toString() {
		return "Proposta: \nID = " + _id
				+ "\nData = " + _data + "\nPreço Final = " + _preco
				+ "\nComprador = " + _comprador.getNomeUtilizador() + "\nVendedor = "
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
		Proposta other = (Proposta) obj;
		if (_id == 0) {
			if (other.getID() != 0)
				return false;
		} else if (_id != (other.getID()))
			return false;
		return true;
	}
}
