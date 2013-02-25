package model;

public class Categoria {
		// Variaveis de Instancia
	
	private String _nome;
	
		// Construtores
	
	public Categoria(){
		this._nome = new String();
	}
	
	public Categoria(String pNome){
		this._nome = pNome;
	}
	
	public Categoria(Categoria pCategoria){
		this._nome = pCategoria.getNome();
	}
	
		// Getters e Setters

	public void setNome(String pNome) {
		this._nome = pNome;
	}

	public String getNome() {
		return this._nome;
	}

	    // Clone, toString, equals, hashCode
	
	@Override
	public Categoria clone(){
		return new Categoria(this);
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
		Categoria other = (Categoria) obj;
		if (_nome == null) {
			if (other.getNome() != null)
				return false;
		} else if (!_nome.equals(other.getNome()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Categoria: " + this._nome + "\n";
	}
}