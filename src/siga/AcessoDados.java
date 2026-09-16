package siga;

/**
 * Código INICIAL da atividade — contém os problemas PROPOSITAIS a refatorar.
 *
 * PROBLEMA 1 — mistura de fornecedores (falta Abstract Factory):
 * o método conectar escolhe conexão e comando por condicionais e "new"
 * separados, sem nada que garanta que ambos sejam do MESMO fornecedor. É
 * possível, por engano, abrir uma conexão MySQL e criar um comando PostgreSQL
 * — uma combinação que quebra em tempo de execução.
 *
 * PROBLEMA 2 — construtor telescópico (falta Builder):
 * a configuração de uma consulta é passada por um método com muitos parâmetros
 * opcionais (limite, offset, ordenação, timeout...), ilegível e sujeito a erro
 * de ordem dos argumentos.
 *
 * PROBLEMA 3 — instância não controlada (falta Singleton):
 * nada impede que várias partes do sistema criem seu próprio AcessoDados,
 * quando deveria existir um único ponto de acesso ao banco.
 *
 * Tarefa:
 *   - Etapa 2: criar um Abstract Factory (ex.: FabricaBanco, com FabricaMySQL
 *     e FabricaPostgreSQL) que produza famílias coerentes de Conexao e Comando.
 *   - Etapa 3: criar um Builder para a configuração da consulta (parâmetros
 *     opcionais nomeados e encadeáveis).
 *   - Etapa 4: transformar o AcessoDados em um Singleton.
 */
public class AcessoDados {
    private Conexao conexao;
    private Comando comando;

    // Utiliza a fábrica para garantir a criação de conexão e comando compatíveis
      public void conectar(FabricaBanco factory) {
        // Armazena as instâncias fornecidas pela fábrica
        this.conexao = factory.criarConexao();
        this.comando = factory.criarComando();
        conexao.abrir();
        comando.executar("SELECT * FROM aluno");
    }

    // Fornece uma interface fluente para a criação da consulta, evitando o construtor telescópico
    public MontarConsultaBuilder montarConsultaBuilder(String tabela) {
        return new MontarConsultaBuilder(tabela);
    }
    
    public static class MontarConsultaBuilder {

        private String tabela;
        private String filtro;
        private StringBuilder sb;

        // Inicializa o construtor com a tabela base da consulta
        public MontarConsultaBuilder(String tabela) {
            this.tabela = tabela;
            this.sb = new StringBuilder("SELECT * FROM ").append(this.tabela);
        }

        // Adiciona uma cláusula WHERE com o filtro especificado
        public MontarConsultaBuilder comFiltro(String filtro) {
            this.filtro = filtro;
            sb.append(" WHERE ").append(this.filtro);
            return this;
        }

        // Adiciona a restrição para filtrar apenas registros ativos
        public MontarConsultaBuilder comSomenteAtivos() {
            sb.append(this.filtro != null ? " AND ativo = 1" : " WHERE ativo = 1");
            return this;
        }

        // Adiciona uma ordenação aos resultados da consulta
        public MontarConsultaBuilder comOrdenacao(String ordenacao) {
            sb.append(" ORDER BY ").append(ordenacao);
            return this;
        }

        // Define um limite para a quantidade de registros retornados
        public MontarConsultaBuilder comLimite(int limite) {
            sb.append(" LIMIT ").append(limite);
            return this;
        }

        // Adiciona um deslocamento (offset) para a paginação
        public MontarConsultaBuilder comOffset(int offset) {
            sb.append(" OFFSET ").append(offset);
            return this;
        }

        // Finaliza e retorna a string da consulta SQL montada
        public String montarConsulta() {
            return this.sb.toString();
        }
    }
}