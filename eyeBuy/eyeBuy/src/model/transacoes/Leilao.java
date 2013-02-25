package model.transacoes;

import java.util.GregorianCalendar;

public class Leilao extends MetodoVenda {
	
		// Variaveis de Instancia
	
	private GregorianCalendar _dataFim;
	private double _precoActual;
	
		// Construtores
	
	public Leilao(){
		super();
		this._dataFim = new GregorianCalendar();
		this._precoActual = 0;
	}
	
	public Leilao(int idProduto, GregorianCalendar pDataFim, double pPrecoActual){
		super(idProduto);
		this._dataFim = (GregorianCalendar) pDataFim.clone();
		this._precoActual = pPrecoActual;
	}
	
	public Leilao(Leilao pLeilao){
		super(pLeilao);
		this._dataFim = pLeilao.getDatafim();
		this._precoActual = pLeilao.getPrecoActual();
	}
	
		// Getters e Setters
	
	public void setDatafim(GregorianCalendar pDatafim) {
		this._dataFim = (GregorianCalendar) pDatafim.clone();
	}
	
	public void setDatafim() {
		this._dataFim = new GregorianCalendar();
	}
	
	public GregorianCalendar getDatafim() {
		return (GregorianCalendar) this._dataFim.clone();
	}
	
	public void setPrecoActual(double pPrecoActual){
		this._precoActual = pPrecoActual;
	}
	
	public double getPrecoActual(){
		return this._precoActual;
	}
	
		// clone, toString, hashCode, equals
	
	@Override
	public Leilao clone(){
		return new Leilao(this);
	}

	@Override
	public String toString() {
		return super.toString() + "Data Fim = " + _dataFim + "\nPreço Actual = "
				+ _precoActual + "\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((_dataFim == null) ? 0 : _dataFim.hashCode());
		long temp;
		temp = Double.doubleToLongBits(_precoActual);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
/*
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Leilao other = (Leilao) obj;
		if (_dataFim == null) {
			if (other.getDatafim() != null)
				return false;
		} else if (!_dataFim.equals(other.getDatafim()))
			return false;
		if (Double.doubleToLongBits(_precoActual) != Double
				.doubleToLongBits(other.getPrecoActual()))
			return false;
		return true;
	}
*/
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
}