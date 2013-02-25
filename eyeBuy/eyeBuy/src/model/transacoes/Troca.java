package model.transacoes;

import model.Produto;
import model.Utilizador;


public class Troca {
	
		// Variaveis de Instancia
	
	private double _novoPreco;
	private Produto _produtoInteressado;
	private Produto _produtoParaTroca;
	private Utilizador _interessado;
	private Utilizador _vendedor;
	private int id;

		// Construtores
	
	public Troca(){
		this.id = 0;
		this._novoPreco = 0;
		this._produtoInteressado = new Produto();
		this._produtoParaTroca = new Produto();
		this._interessado = new Utilizador();
		this._vendedor = new Utilizador();
	}
	
	public Troca(int pId,double pNovoPreco, Produto pProdutoInteressado,
			Produto pProdutoParaTroca,Utilizador pInteressado,
			Utilizador pVendedor){
		this.id = pId; 
		this._novoPreco = pNovoPreco;
		this._produtoInteressado = pProdutoInteressado.clone();
		this._produtoParaTroca = pProdutoParaTroca.clone();
		this._interessado = pInteressado.clone();
		this._vendedor = pVendedor.clone();
	}
	
	public Troca(Troca pTroca){
		this.id = pTroca.getId(); 
		this._novoPreco = pTroca.getNovoPreco();
		this._produtoInteressado = pTroca.getProdutoInteressado();
		this._produtoParaTroca = pTroca.getProdutoParaTroca();
		this._interessado = pTroca.getInteressado();
		this._vendedor = pTroca.getVendedor();
	}
	
		// Getters e Setters
	
	public void setProdutoInteressado(Produto pProdutoInteressado) {
		this._produtoInteressado = pProdutoInteressado;
	}

	public Produto getProdutoInteressado() {
		return this._produtoInteressado;
	}

	public void setInteressado(Utilizador pInteressado) {
		this._interessado = pInteressado;
	}

	public Utilizador getVendedor() {
		return this._vendedor;
	}

	public void setVendedor(Utilizador pVendedor) {
		this._vendedor = pVendedor;
	}

	public Utilizador getInteressado() {
		return this._interessado;
	}
	
	public void setProdutoParaTroca(Produto pProdutoParaTroca) {
		this._produtoParaTroca = pProdutoParaTroca.clone();
	}

	public Produto getProdutoParaTroca() {
		return this._produtoParaTroca.clone();
	}

	public void setNovoPreco(double pNovoPreco) {
		this._novoPreco = pNovoPreco;
	}

	public double getNovoPreco() {
		return this._novoPreco;
	}
	
		// clone, equals, toString, hashCode
	
	@Override
	public Troca clone(){
		return new Troca(this);
	}

	@Override
	public String toString() {
		return "Troca:\nNovo Preco = " + _novoPreco + "\nProduto Interessado = "
				+ _produtoInteressado + "\nProduto Para Troca = "
				+ _produtoParaTroca + "\nInteressado = " + _interessado
				+ "\nVendedor = " + _vendedor + "\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((_interessado == null) ? 0 : _interessado.hashCode());
		result = prime
				* result
				+ ((_produtoInteressado == null) ? 0 : _produtoInteressado
						.hashCode());
		result = prime
				* result
				+ ((_produtoParaTroca == null) ? 0 : _produtoParaTroca
						.hashCode());
		result = prime * result
				+ ((_vendedor == null) ? 0 : _vendedor.hashCode());
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
		Troca other = (Troca) obj;
		if (_interessado == null) {
			if (other.getInteressado() != null)
				return false;
		} else if (!_interessado.equals(other.getInteressado()))
			return false;
		if (_produtoInteressado == null) {
			if (other.getProdutoInteressado() != null)
				return false;
		} else if (!_produtoInteressado.equals(other.getProdutoInteressado()))
			return false;
		if (_produtoParaTroca == null) {
			if (other.getProdutoParaTroca() != null)
				return false;
		} else if (!_produtoParaTroca.equals(other.getProdutoParaTroca()))
			return false;
		if (_vendedor == null) {
			if (other.getVendedor() != null)
				return false;
		} else if (!_vendedor.equals(other.getVendedor()))
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}