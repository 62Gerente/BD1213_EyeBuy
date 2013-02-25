package model.transacoes;

public abstract class MetodoVenda {
	
	// Construtores
	
	private int idProduto;
	
	public MetodoVenda(){
	}
	
	public MetodoVenda(int pidProduto){
		idProduto = pidProduto;
	}
	
	public MetodoVenda(MetodoVenda pMetodoVenda){
		idProduto = pMetodoVenda.getIDProduto();
	}
	
	// clone, toString, equals, hashCode
	
	public abstract MetodoVenda clone();

	@Override
	public String toString() {
		return "MetodoVenda: " + getClass() + "\n";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		else
			return true;
	}

	public int getIDProduto() {
		return idProduto;
	}

	public void setIDProduto(int idProduto) {
		this.idProduto = idProduto;
	}
}