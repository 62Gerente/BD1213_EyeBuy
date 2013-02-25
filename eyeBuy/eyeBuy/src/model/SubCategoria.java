package model;


public class SubCategoria {
		
		//Variaveis de Instancia
	
	private String _nome;
	private Categoria _categoria;

		//Construtores
	
	public SubCategoria(){
		this._nome = new String();
		this._categoria= new Categoria();
	}
	
	public SubCategoria(String pNome, Categoria pCategoria){
		this._nome = pNome;
		this._categoria = pCategoria.clone();
	}
	
	public SubCategoria(SubCategoria pSubCategoria){
		this._nome = pSubCategoria.getNome();
		this._categoria = pSubCategoria.getCategoria();
	}
	
		//Getters e Setters
	
	public void setNome(String pNome) {
		this._nome = pNome;
	}

	public String getNome() {
		return this._nome;
	}
	
	public void setCategoria(Categoria pCategoria){
		this._categoria = pCategoria.clone();
	}
	
	public Categoria getCategoria(){
		return this._categoria.clone();
	}
	
		// clone, toString, equals, hashCode
	
	@Override
	public SubCategoria clone(){
		return new SubCategoria(this);
	}

	@Override
	public String toString() {
		return "SubCategoria:\nNome = " + _nome + "\nCategoria = " + _categoria
				+ "\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((_categoria == null) ? 0 : _categoria.hashCode());
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
		SubCategoria other = (SubCategoria) obj;
		if (_categoria == null) {
			if (other.getCategoria() != null)
				return false;
		} else if (!_categoria.equals(other.getCategoria()))
			return false;
		if (_nome == null) {
			if (other.getNome() != null)
				return false;
		} else if (!_nome.equals(other.getNome()))
			return false;
		return true;
	}
	
	
	
	
}