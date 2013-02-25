package model;


import java.util.GregorianCalendar;
import java.util.Observable;

public class Produto extends Observable{
	
		//Variaveis de Instancia
	
	private int _id;
	private String _nome;
	private double _preco;
	private String _descricao;
	private String _estado;
	private int _quantidade;
	private boolean _vendido;
	private int _nrImagens = 0;
	private String _metodoVenda;
	private GregorianCalendar _dataColocacao;
	private SubCategoria _subcategoria;
	private Categoria _categoria;
	private Utilizador _utilizador;
	private Localidade _localidade;
	private GregorianCalendar _dataVenda;
	private GregorianCalendar _dataAvaliacao;
	private boolean _avaliado;
	private boolean apagado;
	
		//Construtores
	
	public Produto(){
		this._id = 0;
		this._nome = new String();
		this._preco = 0;
		this._descricao = new String();
		this._estado = new String();
		this._quantidade = 0;
		this._vendido = true;
		this._metodoVenda = new String();
		this._dataColocacao = new GregorianCalendar();
		this._subcategoria = new SubCategoria();
		this._categoria = new Categoria();
		this._utilizador = new Utilizador();
		this._localidade = new Localidade();
		this._dataVenda = null;
		this._dataAvaliacao = null;
		this._avaliado = false;
		this.apagado = false;
	}

	
	public Produto(String pNome, double pPreco,
			String pDescricao, String pEstado, int pQuantidade,
			int pNrImagens, String pMetodoVenda, 
			SubCategoria pSubcategoria, Categoria pCategoria, 
			Utilizador pUtilizador, Localidade pLocalidade) {
		this._id = 0;
		this._nome = pNome;
		this._preco = pPreco;
		this._descricao = pDescricao;
		this._estado = pEstado;
		this._quantidade = pQuantidade;
		this._vendido = false;
		this._nrImagens = pNrImagens;
		this._metodoVenda = pMetodoVenda;
		this._dataColocacao = new GregorianCalendar();
		this._subcategoria = pSubcategoria;
		this._categoria = pCategoria;
		this._utilizador = pUtilizador;
		this._localidade = pLocalidade;
		this._dataVenda = null;
		this._dataAvaliacao = null;
		this._avaliado = false;
		this.apagado = false;
	}
	
	public Produto(int pID, String pNome, double pPreco,
				String pDescricao, String pEstado, int pQuantidade,
				boolean pVendido, int pNrImagens, String pMetodoVenda,
				GregorianCalendar pDataColocacao, SubCategoria pSubcategoria,
				Categoria pCategoria, Utilizador pUtilizador,
				Localidade pLocalidade, GregorianCalendar pDataVenda,
				GregorianCalendar pDataAvaliacao, boolean pAvaliado, boolean apagado) {
			this._id = pID;
			this._nome = pNome;
			this._preco = pPreco;
			this._descricao = pDescricao;
			this._estado = pEstado;
			this._quantidade = pQuantidade;
			this._vendido = pVendido;
			this._nrImagens = pNrImagens;
			this._metodoVenda = pMetodoVenda;
			this._dataColocacao = pDataColocacao;
			this._subcategoria = pSubcategoria;
			this._categoria = pCategoria;
			this._utilizador = pUtilizador;
			this._localidade = pLocalidade;
			this._dataVenda = pDataVenda;
			this._dataAvaliacao = pDataAvaliacao;
			this._avaliado = pAvaliado;
			this.apagado = apagado;
		}
	
	public Produto(int pID, String pNome, double pPreco,
			String pDescricao, String pEstado, int pQuantidade,
			boolean pVendido, int pNrImagens, String pMetodoVenda, 
			SubCategoria pSubcategoria, Categoria pCategoria, 
			Utilizador pUtilizador, Localidade pLocalidade) {
		this._id = pID;
		this._nome = pNome;
		this._preco = pPreco;
		this._descricao = pDescricao;
		this._estado = pEstado;
		this._quantidade = pQuantidade;
		this._vendido = pVendido;
		this._nrImagens = pNrImagens;
		this._metodoVenda = pMetodoVenda;
		this._dataColocacao = new GregorianCalendar();
		this._subcategoria = pSubcategoria;
		this._categoria = pCategoria;
		this._utilizador = pUtilizador;
		this._localidade = pLocalidade;
		this._dataVenda = null;
		this._dataAvaliacao = null;
		this._avaliado = false;
		this.apagado = false;
	}
	
	public Produto(Produto pProduto) {
		this._id = pProduto.getId();
		this._nome = pProduto.getNome();
		this._preco = pProduto.getPreco();
		this._descricao = pProduto.getDescricao();
		this._estado = pProduto.getEstado();
		this._quantidade = pProduto.getQuantidade();
		this._vendido = pProduto.getVendido();
		this._nrImagens = pProduto.getNrImagens();
		this._metodoVenda = pProduto.getMetodoVenda();
		this._dataColocacao = pProduto.getDataColocacao();
		this._subcategoria = pProduto.getSubCategoria();
		this._categoria = pProduto.getCategoria();
		this._utilizador = pProduto.getUtilizador();
		this._localidade = pProduto.getLocalidade();
		this._dataVenda = pProduto.getDataVenda();
		this._dataAvaliacao = pProduto.getDataAvaliacao();
		this._avaliado = pProduto.getAvaliado();
		this.apagado = pProduto.isApagado();
	}

		// Getters e Setters
	



	public GregorianCalendar getDataAvaliacao(){
		return this._dataAvaliacao;
	}
	
	public boolean getAvaliado(){
		return this._avaliado;
	}
	
	public void setDataAvaliacao(GregorianCalendar pDataAvaliacao){
		this._dataAvaliacao = (GregorianCalendar) pDataAvaliacao.clone();
	}
	
	public void setAvaliado(boolean pAvaliado){
		this._avaliado = pAvaliado;
	}
	
	public void setUtilizador(Utilizador pUtilizador) {
		this._utilizador = pUtilizador.clone();
	}

	public Utilizador getUtilizador() {
		return this._utilizador.clone();
	}
	
	public void setCategoria(Categoria pCategoria) {
		this._categoria = pCategoria.clone();
	}

	public Categoria getCategoria() {
		return this._categoria.clone();
	}
	
	public void setSubCategoria(SubCategoria pSubCategoria) {
		this._subcategoria = pSubCategoria.clone();
	}

	public SubCategoria getSubCategoria() {
		return this._subcategoria.clone();
	}

	public void setLocalidade(Localidade pLocalidade) {
		this._localidade = pLocalidade.clone();
	}

	public Localidade getLocalidade() {
		return this._localidade.clone();
	}

	public void setId(int pId) {
		this._id = pId;
	}

	public int getId() {
		return this._id;
	}

	public void setNome(String pNome) {
		this._nome = pNome;
	}

	public String getNome() {
		return this._nome;
	}

	public void setPreco(double pPreco) {
		this._preco = pPreco;
	}

	public double getPreco() {
		return this._preco;
	}

	public void setDescricao(String pDescricao) {
		this._descricao = pDescricao;
	}

	public String getDescricao() {
		return this._descricao;
	}

	public void setEstado(String pEstado) {
		this._estado = pEstado;
	}

	public String getEstado() {
		return this._estado;
	}

	public void setMetodoVenda(String pMetodoVenda) {
		this._metodoVenda = pMetodoVenda;
	}

	public String getMetodoVenda() {
		return this._metodoVenda;
	}
	
	public void setQuantidade(int pQuantidade) {
		this._quantidade = pQuantidade;
	}

	public int getQuantidade() {
		return this._quantidade;
	}

	public void setVendido(boolean pVendido) {
		this._vendido = pVendido;
	}

	public boolean getVendido() {
		return this._vendido;
	}

	public void setNrImagens(int pNrImagens) {
		this._nrImagens = pNrImagens;
	}

	public int getNrImagens() {
		return this._nrImagens;
	}

	public void setDataColocacao(GregorianCalendar pDataColocacao) {
		this._dataColocacao =  (GregorianCalendar) pDataColocacao.clone();
	}

	public GregorianCalendar getDataColocacao() {
		return (GregorianCalendar) this._dataColocacao.clone();
	}
	
	public void setDataVenda(GregorianCalendar pDataVenda) {
		this._dataVenda =  (GregorianCalendar) pDataVenda.clone();
	}

	public GregorianCalendar getDataVenda() {
		return this._dataVenda;
	}
	
		// clone, toString, equals, hashCode
	
	@Override
	public Produto clone(){
		return new Produto(this);
	}

	@Override
	public String toString() {
		return "Produto:\nID = " + _id + "\nNome = " + _nome + "\nPreco = "
				+ _preco + "\nDescricao = " + _descricao + "\nEstado = "
				+ _estado + "\nQuantidade = " + _quantidade + "\nVendido = "
				+ _vendido + "\nNr Imagens=" + _nrImagens + "\nMetodo Venda = "
				+ _metodoVenda + "\nData Colocacao = " + _dataColocacao
				+ "\nSub Categoria = " + _subcategoria + "\nCategoria = "
				+ _categoria + "\nUtilizador = " + _utilizador
				+ "\nLocalidade = " + _localidade + "\nData Venda = " + _dataVenda + "\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id != 0) ? 0 : _id);
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
		Produto other = (Produto) obj;
		if (_id == 0) {
			if (other.getId() != 0)
				return false;
		} else if (_id != other.getId())
			return false;
		return true;
	}


	public boolean isApagado() {
		return apagado;
	}


	public void setApagado(boolean apagado) {
		this.apagado = apagado;
	}
	
	
}