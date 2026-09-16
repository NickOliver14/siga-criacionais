package siga;

// Define a interface da fábrica abstrata, estabelecendo o contrato 
// para a criação dos objetos da família de bancos de dados.
public interface FabricaBanco {

    Conexao criarConexao();

    Comando criarComando();
}
