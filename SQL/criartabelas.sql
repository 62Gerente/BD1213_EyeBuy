/*____________________________________   ELIMINAR TABELAS  ____________________________________*/

DROP TABLE Leiloes CASCADE CONSTRAINTS;
DROP TABLE Administradores CASCADE CONSTRAINTS;
DROP TABLE Trocas CASCADE CONSTRAINTS;
DROP TABLE Pesquisas CASCADE CONSTRAINTS;
DROP TABLE Localidades CASCADE CONSTRAINTS;
DROP TABLE Subcategorias CASCADE CONSTRAINTS;
DROP TABLE Categorias CASCADE CONSTRAINTS;
DROP TABLE Mensagens CASCADE CONSTRAINTS;
DROP TABLE Fotos CASCADE CONSTRAINTS;
DROP TABLE Historico CASCADE CONSTRAINTS;
DROP TABLE CarrinhoCompras CASCADE CONSTRAINTS;
DROP TABLE ProdutosSeguidos CASCADE CONSTRAINTS;
DROP TABLE ProdutosDesejados CASCADE CONSTRAINTS;
DROP TABLE MetodosPagamento CASCADE CONSTRAINTS;
DROP TABLE Produtos CASCADE CONSTRAINTS;
DROP TABLE Utilizadores CASCADE CONSTRAINTS;
DROP TABLE Propostas CASCADE CONSTRAINTS;
DROP TABLE Restricoes CASCADE CONSTRAINTS;

/*____________________________________   CRIAR TABELAS   ____________________________________*/
CREATE TABLE Restricoes(
  palavra       varchar2(20) NOT NULL,
  PRIMARY KEY (palavra)
);

CREATE TABLE Produtos (
  id             number(7) NOT NULL,
  nome           varchar2(100) NOT NULL, 
  preco          number(7,2) NOT NULL,
  descricao      varchar2(300) NOT NULL, 
  estado         varchar2(20) NOT NULL, 
  quantidade     number(4) DEFAULT 1 NOT NULL, 
  nrImagens      number(2) DEFAULT 0 NOT NULL, 
  dataColocacao  timestamp(7) DEFAULT CURRENT_TIMESTAMP NOT NULL, 
  metodoVenda    varchar2(20) NOT NULL, 
  localidade     varchar2(50) NOT NULL, 
  categoria      varchar2(50) NOT NULL, 
  subCategoria   varchar2(50) NOT NULL, 
  nomeUtilizador varchar2(50) NOT NULL, 
  dataVenda      timestamp(7),  
  dataAvaliacao  timestamp(7),
  apagado        number(1) DEFAULT 0 NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE ProdutosDesejados (
  nomeUtilizador varchar2(50) NOT NULL, 
  idProduto      number(7) NOT NULL, 
  PRIMARY KEY (nomeUtilizador, 
  idProduto));

CREATE TABLE Mensagens (
  id      number(7) NOT NULL, 
  origem  varchar2(50), 
  destino varchar2(50), 
  tipo    varchar2(30) NOT NULL, 
  assunto varchar2(200) NOT NULL, 
  corpo   varchar2(1000) NOT NULL, 
  data    timestamp(7) NOT NULL, 
  lida  number(1) DEFAULT 0 NOT NULL, 
  apagadaor number(1) DEFAULT 0 NOT NULL,
  apagadadt number(1) DEFAULT 0 NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE Historico (
  idProduto  number(7) NOT NULL, 
  comprador  varchar2(50) NOT NULL, 
  descricao  varchar2(20),
  data       timestamp(7) DEFAULT CURRENT_TIMESTAMP NOT NULL, 
  dataAvaliado timestamp(7),
  PRIMARY KEY (idProduto));

CREATE TABLE CarrinhoCompras (
  nomeUtilizador varchar2(50) NOT NULL, 
  idProduto      number(7) NOT NULL, 
  PRIMARY KEY (nomeUtilizador, 
  idProduto));

CREATE TABLE ProdutosSeguidos (
  nomeUtilizador varchar2(50) NOT NULL, 
  idProduto      number(7) NOT NULL, 
  PRIMARY KEY (nomeUtilizador, 
  idProduto));

CREATE TABLE Fotos (
  idFoto number(7) NOT NULL,
  idProduto number(7) NOT NULL, 
  foto      BLOB, 
  descricao varchar2(300) NOT NULL, 
  PRIMARY KEY (idFoto));

CREATE TABLE MetodosPagamento (
  idProduto  number(7) NOT NULL, 
  nomeMetodo varchar2(20) NOT NULL, 
  PRIMARY KEY (idProduto, 
  nomeMetodo));

CREATE TABLE Utilizadores (
  nomeUtilizador       varchar2(50) NOT NULL, 
  nome                 varchar2(200) NOT NULL, 
  email                varchar2(70) NOT NULL UNIQUE, 
  password             varchar2(50) NOT NULL, 
  morada               varchar2(100), 
  telemovel            varchar2(15) UNIQUE, 
  localidade           varchar2(50) NOT NULL, 
  codigoPostal         varchar2(15), 
  dataNascimento       timestamp(7) NOT NULL, 
  contaPaypal          varchar2(70), 
  contaMBNet           varchar2(70), 
  dataRegisto          timestamp(7) DEFAULT CURRENT_TIMESTAMP NOT NULL, 
  avaliacaoComprador   number(3) DEFAULT 100 NOT NULL, 
  imagem               BLOB, 
  descricao            varchar2(300), 
  nrAvaliacoes         number(4) DEFAULT 0 NOT NULL, 
  nrVendas             number(4) DEFAULT 0 NOT NULL, 
  nrCompras            number(4) DEFAULT 0 NOT NULL, 
  totalGanho           number(7,2) DEFAULT 0 NOT NULL, 
  totalGasto           number(7,2) DEFAULT 0 NOT NULL, 
  totalAVender         number(4) DEFAULT 0 NOT NULL, 
  avaliacoesPositivas  number(4) DEFAULT 0 NOT NULL, 
  avaliacoesNegativas  number(4) DEFAULT 0 NOT NULL, 
  avaliacaoVendedor    number(3) DEFAULT 100 NOT NULL, 
  nrAvaliacoesVendaPos number(4) DEFAULT 0 NOT NULL, 
  nrAvaliacoesVendaNeg number(4) DEFAULT 0 NOT NULL, 
  dadosCompletos       number(1) DEFAULT 0 NOT NULL, 
  apagado              number(1) DEFAULT 0 NOT NULL,
  PRIMARY KEY (nomeUtilizador));

CREATE TABLE Categorias (
  nome varchar2(50) NOT NULL, 
  PRIMARY KEY (nome));

CREATE TABLE Subcategorias (
  nome      varchar2(50) NOT NULL, 
  categoria varchar2(50) NOT NULL, 
  PRIMARY KEY (nome, categoria));

CREATE TABLE Localidades (
  nome varchar2(50) NOT NULL, 
  PRIMARY KEY (nome));

CREATE TABLE Pesquisas (
  pesquisa        varchar2(100) NOT NULL, 
  nomeUtilizador  varchar2(50) NOT NULL, 
  numeroPesquisas number(4) DEFAULT 0 NOT NULL, 
  PRIMARY KEY (pesquisa, 
  nomeUtilizador));

CREATE TABLE Trocas (
  id                   number(7) NOT NULL,
  interessado          varchar2(50) NOT NULL, 
  vendedor             varchar2(50) NOT NULL, 
  idProdutoInteressado number(7) NOT NULL, 
  idProdutoParaTroca   number(7) NOT NULL, 
  novoPreco            number(7,2) NOT NULL, 
  PRIMARY KEY (id));

CREATE TABLE Administradores (
  login    varchar2(50) NOT NULL, 
  password varchar2(70) NOT NULL, 
  PRIMARY KEY (login));

CREATE TABLE Leiloes (
  idProduto   number(7) NOT NULL, 
  dataFim     timestamp(7) NOT NULL, 
  precoactual number(7,2) NOT NULL, 
  PRIMARY KEY (idProduto));

CREATE TABLE Propostas(
  id            number(7) NOT NULL,
  idProduto     number(7) NOT NULL,
  idUtilizador  varchar2(50) NOT NULL,
  valor         number(7,2) NOT NULL,
  data          timestamp(7) NOT NULL,
  PRIMARY KEY (id));

/*____________________________________   ADICIONAR CHAVES ESTRANGEIRAS   ____________________________________*/

ALTER TABLE CarrinhoCompras ADD CONSTRAINT FKCarrinhoCo721169 FOREIGN KEY (idProduto) REFERENCES Produtos (id);
ALTER TABLE CarrinhoCompras ADD CONSTRAINT FKCarrinhoCo329760 FOREIGN KEY (nomeUtilizador) REFERENCES Utilizadores (nomeUtilizador);
ALTER TABLE Fotos ADD CONSTRAINT FKFotos634962 FOREIGN KEY (idProduto) REFERENCES Produtos (id);
ALTER TABLE Leiloes ADD CONSTRAINT FKLeiloes57290 FOREIGN KEY (idProduto) REFERENCES Produtos (id);
ALTER TABLE Mensagens ADD CONSTRAINT FKMensagens46787 FOREIGN KEY (origem) REFERENCES Utilizadores (nomeUtilizador);
ALTER TABLE Mensagens ADD CONSTRAINT FKMensagens939873 FOREIGN KEY (destino) REFERENCES Utilizadores (nomeUtilizador);
ALTER TABLE MetodosPagamento ADD CONSTRAINT FKMetodosPag607420 FOREIGN KEY (idProduto) REFERENCES Produtos (id);
ALTER TABLE Pesquisas ADD CONSTRAINT FKPesquisas938924 FOREIGN KEY (nomeUtilizador) REFERENCES Utilizadores (nomeUtilizador);
ALTER TABLE Produtos ADD CONSTRAINT FKProdutos665268 FOREIGN KEY (localidade) REFERENCES Localidades (nome);
ALTER TABLE Produtos ADD CONSTRAINT FKProdutos519750 FOREIGN KEY (subCategoria, categoria) REFERENCES Subcategorias (nome, categoria);
ALTER TABLE Produtos ADD CONSTRAINT FKProdutos884609 FOREIGN KEY (nomeUtilizador) REFERENCES Utilizadores (nomeUtilizador);
ALTER TABLE ProdutosDesejados ADD CONSTRAINT FKProdutosDe782820 FOREIGN KEY (nomeUtilizador) REFERENCES Utilizadores (nomeUtilizador);
ALTER TABLE ProdutosDesejados ADD CONSTRAINT FKProdutosDe731890 FOREIGN KEY (idProduto) REFERENCES Produtos (id);
ALTER TABLE ProdutosSeguidos ADD CONSTRAINT FKProdutosSe646956 FOREIGN KEY (idProduto) REFERENCES Produtos (id);
ALTER TABLE ProdutosSeguidos ADD CONSTRAINT FKProdutosSe697886 FOREIGN KEY (nomeUtilizador) REFERENCES Utilizadores (nomeUtilizador);
ALTER TABLE Propostas ADD CONSTRAINT FKPropostasProduto FOREIGN KEY (idProduto) REFERENCES Produtos (id);
ALTER TABLE Propostas ADD CONSTRAINT FKPropostasUtilizador FOREIGN KEY (idUtilizador) REFERENCES Utilizadores (nomeUtilizador);
ALTER TABLE Subcategorias ADD CONSTRAINT FKSubcategor351797 FOREIGN KEY (categoria) REFERENCES Categorias (nome);
ALTER TABLE Utilizadores ADD CONSTRAINT FKUtilizador529092 FOREIGN KEY (localidade) REFERENCES Localidades (nome);
ALTER TABLE Trocas ADD CONSTRAINT FKTrocas903040 FOREIGN KEY (interessado) REFERENCES Utilizadores (nomeUtilizador);
ALTER TABLE Trocas ADD CONSTRAINT FKTrocas847532 FOREIGN KEY (vendedor) REFERENCES Utilizadores (nomeUtilizador);
ALTER TABLE Trocas ADD CONSTRAINT FKTrocas153908 FOREIGN KEY (idProdutoInteressado) REFERENCES Produtos (id);
ALTER TABLE Trocas ADD CONSTRAINT FKTrocas295653 FOREIGN KEY (idProdutoParaTroca) REFERENCES Produtos (id);
ALTER TABLE Historico ADD CONSTRAINT FKHistorico8683 FOREIGN KEY (comprador) REFERENCES Utilizadores (nomeUtilizador);
ALTER TABLE Historico ADD CONSTRAINT FKHistorico662393 FOREIGN KEY (idProduto) REFERENCES Produtos (id);


