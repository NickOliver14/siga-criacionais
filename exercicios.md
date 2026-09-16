                                                                    | **Singleton**        |

## Sua tarefa

Siga as etapas da ficha de atividade prática:

1. **Analisar** o código inicial e identificar a possibilidade de misturar fornecedores e o método de consulta telescópico.
2. **Abstract Factory:** criar uma fábrica abstrata (por exemplo, `FabricaBanco`) com `FabricaMySQL` e `FabricaPostgreSQL`, cada uma produzindo uma `Conexao` e um `Comando` **do mesmo fornecedor**. O `AcessoDados` passa a receber uma fábrica e criar a família coerente a partir dela.
3. **Builder:** criar um `ConsultaBuilder` com métodos nomeados e encadeáveis para os parâmetros opcionais (`comFiltro`, `comOrdenacao`, `comLimite`, etc.) e um `construir()` que devolve a consulta. Substitui o método telescópico.
4. **Singleton:** transformar o `AcessoDados` em um Singleton, com construtor privado, instância estática e método de acesso.
5. **Desenhar** o diagrama de classes da solução (fábrica de banco, produtos, builder e acesso), evidenciando os três padrões.
