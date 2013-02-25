          package model;

import java.sql.Blob;
import java.sql.SQLException;

public class Imagem {
	
	
	
	private String _descricao;
	private Blob _imagem;
	private int id;
	
		// Construtores
	
	public Imagem() throws SQLException{
		this._descricao = new String();
		this._imagem = null;
	}
	
	public Imagem(String pDescricao, Blob pImagem){
		this._descricao = pDescricao;
		this._imagem = pImagem;
	}
	
	public Imagem(Imagem pImagem){
		this._descricao = pImagem.getDescricao();
		this._imagem = pImagem.getImagem();
	}
	
		// Getters e Setters
	
	public void setDescricao(String pDescricao) {
		this._descricao = pDescricao;
	}

	public String getDescricao() {
		return this._descricao;
	}

	public void setImagem(Blob pImagem) {
		this._imagem = pImagem;
	}

	public Blob getImagem() {
		return this._imagem;
	}
	
		// clone, toString, equals, hashCode
	
	@Override
	public Imagem clone(){
		return new Imagem(this);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((_descricao == null) ? 0 : _descricao.hashCode());
		result = prime * result + ((_imagem == null) ? 0 : _imagem.hashCode());
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
		Imagem other = (Imagem) obj;
		if (_descricao == null) {
			if (other._descricao != null)
				return false;
		} else if (!_descricao.equals(other._descricao))
			return false;
		if (_imagem == null) {
			if (other._imagem != null)
				return false;
		} else if (!_imagem.equals(other._imagem))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Imagem:\nDescricao = " + _descricao + "\nImagem = " + _imagem
				+ "\n\n";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
