package model.pagamentos;

public abstract class MetodoPagamento {
	
	// Construtores
	
	public MetodoPagamento(){
	}
	
	// clone, toString, equals, hashCode
	
	public abstract MetodoPagamento clone();

	@Override
	public String toString() {
		return "MetodoPagamento: " + getClass() + "\n";
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
}