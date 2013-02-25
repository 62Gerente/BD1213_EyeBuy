package model;

public class Pesquisa {
	
		// Variaveis de Instancia
	
	private String _pesquisa;
	private int _numeroPesquisas = 0;

		// Construtores
	
	public Pesquisa(){
		this._pesquisa = new String();
		this._numeroPesquisas = 0;
	}
	
	public Pesquisa(String pPesquisa, int pNumeroPesquisas){
		this._pesquisa = pPesquisa;
		this._numeroPesquisas = pNumeroPesquisas;
	}
	
	public Pesquisa(Pesquisa pPesquisa){
		this._pesquisa = pPesquisa.getPesquisa();
		this._numeroPesquisas = pPesquisa.getNumeroPesquisas();
	}
	
		// Getters e Setters

	public void setPesquisa(String pPesquisa) {
		this._pesquisa = pPesquisa;
	}

	public String getPesquisa() {
		return this._pesquisa;
	}

	public void setNumeroPesquisas(int pNumeroPesquisas) {
		this._numeroPesquisas = pNumeroPesquisas;
	}

	public int getNumeroPesquisas() {
		return this._numeroPesquisas;
	}
	
		// clone, toString, equals, hashCode
	
	public Pesquisa clone(){
		return new Pesquisa(this);
	}

	@Override
	public String toString() {
		return "Pesquisa = " + _pesquisa + "\nNumero Pesquisas = "
				+ _numeroPesquisas + "\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((_pesquisa == null) ? 0 : _pesquisa.hashCode());
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
		Pesquisa other = (Pesquisa) obj;
		if (_pesquisa == null) {
			if (other.getPesquisa() != null)
				return false;
		} else if (!_pesquisa.equals(other.getPesquisa()))
			return false;
		return true;
	}
	
	
}