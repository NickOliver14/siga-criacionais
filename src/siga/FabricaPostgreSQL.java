package siga;

// Fábrica concreta responsável por criar a conexão e os comandos específicos para PostgreSQL
public class FabricaPostgreSQL implements FabricaBanco {

    @Override
    public Conexao criarConexao() {
        return new ConexaoPostgreSQL();
    }

    @Override
    public Comando criarComando() {
        return new ComandoPostgreSQL();
    }
}
