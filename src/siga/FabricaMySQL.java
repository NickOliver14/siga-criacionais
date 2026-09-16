package siga;

// Fábrica concreta responsável por criar a conexão e os comandos específicos para MySQL
public class FabricaMySQL implements FabricaBanco {

    @Override
    public Conexao criarConexao() {
        return new ConexaoMySQL();
    }

    @Override
    public Comando criarComando() {
        return new ComandoMySQL();
    }
}
