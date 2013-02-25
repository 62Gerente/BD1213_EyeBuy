package model;

public class Localidade {
		
		// Variaveis de Instancia
	
	private String _nome;

		// Construtores
	
	public Localidade(){
		this._nome = new String();
	}
	
	public Localidade(String pNome){
		this._nome = pNome;
	}
	
	public Localidade(Localidade pLocalidade){
		this._nome = pLocalidade.getNome();
	}
	
		// Setters e Getters
	
	public void setNome(String pNome) {
		this._nome = pNome;
	}

	public String getNome() {
		return this._nome;
	}
	
		// clone, toString, equals, hashCode
	
	@Override
	public Localidade clone(){
		return new Localidade(this);
	}

	@Override
	public String toString() {
		return "Localidade:\nNome = " + _nome + "\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_nome == null) ? 0 : _nome.hashCode());
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
		Localidade other = (Localidade) obj;
		if (_nome == null) {
			if (other.getNome() != null)
				return false;
		} else if (!_nome.equals(other.getNome()))
			return false;
		return true;
	}
	
}