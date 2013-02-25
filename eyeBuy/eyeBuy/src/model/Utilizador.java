package model;

import java.sql.Blob;
import java.util.GregorianCalendar;
import java.util.Observable;

public class Utilizador extends Observable {
	
		//Variaveis de Instancia
	
	private String _nomeUtilizador;
	private String _nome;
	private String _email;
	private String _password;
	private String _morada;
	private String _telemovel;
	private String _codigoPostal;
	private GregorianCalendar _dataNascimento;
	private String _contaPaypal;
	private String _contaMBNet;
	private double _avaliacaoComprador = 100;
	private Blob _imagem;
	private String _descricao;
	private int _nrVendas = 0;
	private int _nrCompras = 0;
	private double _totalGasto = 0;
	private double _totalGanho = 0;
	private int _totalAVender = 0;
	private int _avaliacoesPositivas = 0;
	private int _avaliacoesNegativas = 0;
	private GregorianCalendar _dataRegisto;
	private Localidade _localidade;
	private double _avaliacaoVendedor = 100;
	private int _nrAvaliacoesVendaNeg = 0;
	private int _nrAvaliacoesVendaPos = 0;
	private boolean _perfilcompleto;
	private boolean apagado;

		// Construtores
	
	public Utilizador(){
		this._nomeUtilizador = new String();
		this._nome = new String();
		this._email = new String();
		this._password = new String();
		this._morada = new String();
		this._telemovel = new String();
		this._codigoPostal = new String();
		this._dataNascimento = new GregorianCalendar();
		this._contaPaypal = new String();
		this._contaMBNet = new String();
		this._imagem = null;
		this._descricao = new String();
		this._dataRegisto = new GregorianCalendar();
		this._localidade = new Localidade();
		this._perfilcompleto = false;
		this.apagado = false;
	}

	public Utilizador(String pNomeUtilizador, String pNome, String pEmail,
				String pPassword, String pMorada, String pTelemovel,
				String pCodigoPostal, GregorianCalendar pDataNascimento,
				String pContaPaypal, String pContaMBNet,
				double pAvaliacaoComprador, Blob pImagem, String pDescricao,
				int pNrVendas, int pNrCompras,
				double pTotalGasto, double pTotalGanho, int pTotalAVender,
				int pAvaliacoesPositivas, int pAvaliacoesNegativas,
				GregorianCalendar pDataRegisto, Localidade pLocalidade,
				double pAvaliacaoVendedor,
				int pNrAvaliacoesVendaNeg, int pNrAvaliacoesVendaPos, boolean pPerfilCompleto, boolean apagado) {
			this._nomeUtilizador = pNomeUtilizador;
			this._nome = pNome;
			this._email = pEmail;
			this._password = pPassword;
			this._morada = pMorada;
			this._telemovel = pTelemovel;
			this._codigoPostal = pCodigoPostal;
			this.apagado = apagado;
			this._dataNascimento = (GregorianCalendar) pDataNascimento.clone();
			this._contaPaypal = pContaPaypal;
			this._contaMBNet = pContaMBNet;
			this._avaliacaoComprador = pAvaliacaoComprador;
			this._imagem = pImagem;
			this._descricao = pDescricao;
			this._nrVendas = pNrVendas;
			this._nrCompras = pNrCompras;
			this._totalGasto = pTotalGasto;
			this._totalGanho = pTotalGanho;
			this._totalAVender = pTotalAVender;
			this._avaliacoesPositivas = pAvaliacoesPositivas;
			this._avaliacoesNegativas = pAvaliacoesNegativas;
			this._dataRegisto = (GregorianCalendar) pDataRegisto.clone();
			this._localidade = pLocalidade.clone();
			this._avaliacaoVendedor = pAvaliacaoVendedor;
			this._nrAvaliacoesVendaNeg = pNrAvaliacoesVendaNeg;
			this._nrAvaliacoesVendaPos = pNrAvaliacoesVendaPos;
			this._perfilcompleto = pPerfilCompleto;
		}

	public Utilizador(String pNomeUtilizador, String pNome, String pEmail,
			String pPassword, String pMorada, String pTelemovel,
			String pCodigoPostal, GregorianCalendar pDataNascimento,
			String pContaPaypal, String pContaMBNet,
			double pAvaliacaoComprador, Blob pImagem, String pDescricao,
			int pNrVendas, int pNrCompras,
			double pTotalGasto, double pTotalGanho, int pTotalAVender,
			int pAvaliacoesPositivas, int pAvaliacoesNegativas,
			Localidade pLocalidade, double pAvaliacaoVendedor,
			int pNrAvaliacoesVendaNeg, int pNrAvaliacoesVendaPos, boolean pPerfilCompleto, boolean apagado) {
		this._nomeUtilizador = pNomeUtilizador;
		this._nome = pNome;
		this._email = pEmail;
		this._password = pPassword;
		this._morada = pMorada;
		this._telemovel = pTelemovel;
		this._codigoPostal = pCodigoPostal;
		this.apagado = apagado;
		this._dataNascimento = (GregorianCalendar) pDataNascimento.clone();
		this._contaPaypal = pContaPaypal;
		this._contaMBNet = pContaMBNet;
		this._avaliacaoComprador = pAvaliacaoComprador;
		this._imagem = pImagem;
		this._descricao = pDescricao;
		this._nrVendas = pNrVendas;
		this._nrCompras = pNrCompras;
		this._totalGasto = pTotalGasto;
		this._totalGanho = pTotalGanho;
		this._totalAVender = pTotalAVender;
		this._avaliacoesPositivas = pAvaliacoesPositivas;
		this._avaliacoesNegativas = pAvaliacoesNegativas;
		this._dataRegisto = new GregorianCalendar();
		this._localidade = pLocalidade.clone();
		this._avaliacaoVendedor = pAvaliacaoVendedor;
		this._nrAvaliacoesVendaNeg = pNrAvaliacoesVendaNeg;
		this._nrAvaliacoesVendaPos = pNrAvaliacoesVendaPos;
		this._perfilcompleto = pPerfilCompleto;
	}
	
	public Utilizador(String pNomeUtilizador, String pNome, String pEmail,
			String pPassword, GregorianCalendar pDataNascimento,
			Localidade pLocalidade) {
		this._nomeUtilizador = pNomeUtilizador;
		this._nome = pNome;
		this._email = pEmail;
		this._password = pPassword;
		this._morada = new String();
		this._telemovel = new String();
		this._codigoPostal = new String();
		this._dataNascimento = (GregorianCalendar) pDataNascimento.clone();
		this._contaPaypal = new String();
		this._contaMBNet = new String();
		this._imagem = null;
		this._descricao = new String();
		this._dataRegisto = new GregorianCalendar();
		this._localidade = pLocalidade.clone();
		this._perfilcompleto = false;
		this.apagado = false;
	}
	
	public Utilizador(Utilizador pUtilizador) {
		this._nomeUtilizador = pUtilizador.getNomeUtilizador();
		this._nome = pUtilizador.getNome();
		this._email = pUtilizador.getEmail();
		this._password = pUtilizador.getPassword();
		this._morada = pUtilizador.getMorada();
		this._telemovel = pUtilizador.getTelemovel();
		this._codigoPostal = pUtilizador.getCodigoPostal();
		this._dataNascimento = pUtilizador.getDataNascimento();
		this._contaPaypal = pUtilizador.getContaPaypal();
		this._contaMBNet = pUtilizador.getContaMBNet();
		this._avaliacaoComprador = pUtilizador.getAvaliacaoComprador();
		this._imagem = pUtilizador.getImagem();
		this._descricao = pUtilizador.getDescricao();
		this._nrVendas = pUtilizador.getNrVendas();
		this._nrCompras = pUtilizador.getNrCompras();
		this._totalGasto = pUtilizador.getTotalGasto();
		this._totalGanho = pUtilizador.getTotalGanho();
		this._totalAVender = pUtilizador.getTotalAVender();
		this._avaliacoesPositivas = pUtilizador.getAvaliacoesPositivas();
		this._avaliacoesNegativas = pUtilizador.getAvaliacoesNegativas();
		this._dataRegisto = pUtilizador.getDataRegisto();
		this._localidade = pUtilizador.getLocalidade();
		this._avaliacaoVendedor = pUtilizador.getAvaliacaoVendedor();
		this._nrAvaliacoesVendaNeg = pUtilizador.getNrAvaliacoesVendaNeg();
		this._nrAvaliacoesVendaPos = pUtilizador.getNrAvaliacoesVendaPos();
		this._perfilcompleto = pUtilizador.getPerfilCompleto();
		this.apagado = pUtilizador.isApagado();
	}
	
		// Getters e Setters
	

	public void setPerfilCompleto(boolean pPerfilCompleto) {
		this._perfilcompleto = pPerfilCompleto;
	}

	public boolean getPerfilCompleto() {
		return this._perfilcompleto;
	}
	
	public void setAvaliacaoVendedor(double pAvaliacaoVendedor) {
		this._avaliacaoVendedor = pAvaliacaoVendedor;
	}

	public double getAvaliacaoVendedor() {
		return this._avaliacaoVendedor;
	}

	public void setNrAvaliacoesVendaNeg(int pNrAvaliacoesVendaNeg) {
		this._nrAvaliacoesVendaNeg = pNrAvaliacoesVendaNeg;
	}

	public int getNrAvaliacoesVendaNeg() {
		return this._nrAvaliacoesVendaNeg;
	}

	public void setNrAvaliacoesVendaPos(int pNrAvaliacoesVendaPos) {
		this._nrAvaliacoesVendaPos = pNrAvaliacoesVendaPos;
	}

	public int getNrAvaliacoesVendaPos() {
		return this._nrAvaliacoesVendaPos;
	}

	public void setLocalidade(Localidade pLocalidade) {
		this._localidade = pLocalidade.clone();
	}

	public Localidade getLocalidade() {
		return this._localidade.clone();
	}

	public void setNomeUtilizador(String pNomeUtilizador) {
		this._nomeUtilizador = pNomeUtilizador;
	}

	public String getNomeUtilizador() {
		return this._nomeUtilizador;
	}

	public void setNome(String pNome) {
		this._nome = pNome;
	}

	public String getNome() {
		return this._nome;
	}

	public void setEmail(String pEmail) {
		this._email = pEmail;
	}

	public String getEmail() {
		return this._email;
	}

	public void setPassword(String pPassword) {
		this._password = pPassword;
	}

	public String getPassword() {
		return this._password;
	}

	public void setMorada(String pMorada) {
		this._morada = pMorada;
	}

	public String getMorada() {
		return this._morada;
	}

	public void setTelemovel(String pTelemovel) {
		this._telemovel = pTelemovel;
	}

	public String getTelemovel() {
		return this._telemovel;
	}

	public void setCodigoPostal(String pCodigoPostal) {
		this._codigoPostal = pCodigoPostal;
	}

	public String getCodigoPostal() {
		return this._codigoPostal;
	}

	public void setDataNascimento(GregorianCalendar pDataNascimento) {
		this._dataNascimento = (GregorianCalendar) pDataNascimento.clone();
	}

	public GregorianCalendar getDataNascimento() {
		return (GregorianCalendar) this._dataNascimento.clone();
	}

	public void setContaPaypal(String pContaPaypal) {
		this._contaPaypal = pContaPaypal;
	}

	public String getContaPaypal() {
		return this._contaPaypal;
	}
	
	public String getContaMBNet() {
		return this._contaMBNet;
	}

	public void setContaMBNet(String pContaMBNet) {
		this._contaMBNet = pContaMBNet;
	}

	public void setAvaliacaoComprador(double pAvaliacaoComprador) {
		this._avaliacaoComprador = pAvaliacaoComprador;
	}

	public double getAvaliacaoComprador() {
		return this._avaliacaoComprador;
	}

	public void setImagem(Blob pImagem) {
		this._imagem = pImagem;
	}

	public Blob getImagem() {
		return this._imagem;
	}

	public void setDescricao(String pDescricao) {
		this._descricao = pDescricao;
	}

	public String getDescricao() {
		return this._descricao;
	}


	public void setNrVendas(int pNrVendas) {
		this._nrVendas = pNrVendas;
	}

	public int getNrVendas() {
		return this._nrVendas;
	}

	public void setNrCompras(int pNrCompras) {
		this._nrCompras = pNrCompras;
	}

	public int getNrCompras() {
		return this._nrCompras;
	}

	public void setTotalGasto(double pTotalGasto) {
		this._totalGasto = pTotalGasto;
	}

	public double getTotalGasto() {
		return this._totalGasto;
	}

	public void setTotalGanho(double pTotalGanho) {
		this._totalGanho = pTotalGanho;
	}

	public double getTotalGanho() {
		return this._totalGanho;
	}

	public void setTotalAVender(int pTotalAVender) {
		this._totalAVender = pTotalAVender;
	}

	public int getTotalAVender() {
		return this._totalAVender;
	}

	public void setAvaliacoesPositivas(int pAvaliacoesPositivas) {
		this._avaliacoesPositivas = pAvaliacoesPositivas;
	}

	public int getAvaliacoesPositivas() {
		return this._avaliacoesPositivas;
	}

	public void setAvaliacoesNegativas(int pAvaliacoesNegativas) {
		this._avaliacoesNegativas = pAvaliacoesNegativas;
	}

	public int getAvaliacoesNegativas() {
		return this._avaliacoesNegativas;
	}

	public void setDataRegisto(GregorianCalendar pDataRegisto) {
		this._dataRegisto = (GregorianCalendar) pDataRegisto.clone();
	}

	public GregorianCalendar getDataRegisto() {
		return (GregorianCalendar) this._dataRegisto.clone();
	}
	
		// clone, toString, hashCode, equals
	
	@Override
	public Utilizador clone(){
		return new Utilizador(this);
	}

	@Override
	public String toString() {
		return "Utilizador:\nNome Utilizador = " + _nomeUtilizador + "\nNome = "
				+ _nome + "\n Email = " + _email + "\n Password = " + _password
				+ "\nMorada = " + _morada + "\nTelemovel = " + _telemovel
				+ "\nCodigo Postal = " + _codigoPostal + "\nData Nascimento = "
				+ _dataNascimento + "\nConta Paypal = " + _contaPaypal
				+ "\nConta MBNet = " + _contaMBNet + "\nAvaliacao Comprador = "
				+ _avaliacaoComprador + "\nImagem = " + _imagem
				+ "Descricao = " + _descricao + "\nNr Vendas = " + _nrVendas + "\nNr Compras = "
				+ _nrCompras + "\nTotal Gasto = " + _totalGasto
				+ "\nTotal Ganho = " + _totalGanho + "\nTotal a Vender = "
				+ _totalAVender + "\nAvaliacoes Positivas = "
				+ _avaliacoesPositivas + "\nAvaliacoes Negativas = "
				+ _avaliacoesNegativas + "\nData Registo = " + _dataRegisto
				+ "\nLocalidade = " + _localidade + "\nAvaliacao Vendedor = "
				+ _avaliacaoVendedor + "\nNr Avaliacoes Venda Neg = "
				+ _nrAvaliacoesVendaNeg + "\nNr Avaliacoes Venda Pos = "
				+ _nrAvaliacoesVendaPos + "\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((_nomeUtilizador == null) ? 0 : _nomeUtilizador.hashCode());
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
		Utilizador other = (Utilizador) obj;
		if (_nomeUtilizador == null) {
			if (other.getNomeUtilizador() != null)
				return false;
		} else if (!_nomeUtilizador.equals(other.getNomeUtilizador()))
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