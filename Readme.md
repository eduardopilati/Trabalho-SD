# Localiza&ccedil;&atilde;o de Ve&iacute;culos

## Componentes do grupo:

 * Eduardo P. (l&iacute;der)
 * Giani
 * Guilerme P.
 * Gustavo
 * Jader
 * Jorge
 * Leonardo B.
 * Marciel
 * Tiago P.

## Aplica&ccedil;&otilde;es

| Aplica&ccedil;&atilde;o | Componente | Status |
| ------------------------|------------|-----------|
| Receptor de localiza&ccedil;&atilde;o - UPD | Leonardo B. |  |
| Servidor de acesso ao BD - TCP | Jorge |  |
| Servi&ccedil;o Web Soap | Gustavo |  |
| Servi&ccedil;o Web Rest | Guilherme |  |
| Simulador de localiza&ccedil;&atilde;o do ve&iacute;culo UDP | Eduardo | Finalizado |
| Cliente com interface texto para o servidor TCP | Marciel  |  |
| Cliente com iterface gr&aacute;fica para o servi&ccedil;o Soap |  |  |
| Cliente Web para o servi&ccedil;o Rest | Tiago |  |

## Orienta&ccedil;&otilde;es

 * Vers&atilde;o do Net Beans: 8.2
 * Vers&atilde;o do Java: 1.8

## Comunica&ccedil;&atilde;o do servidor e cliente TCP

### Adicionar:
 * Cliente envia o inteiro 1 ao servidor
 * Cliente envia uma classe data.Veiculo ao servidor
 * Servidor retorna uma mensagem de sucesso ou falha
 
### Listar Tipo:
 * Cliente envia o inteiro 2 ao servidor
 * Cliente envia um inteiro correspondendo ao tipo dos ve&iacute;culos
 * Servidor retorna um List<Veiculo>

### Consultar:
 * Cliente envia o inteiro 3 ao servidor
 * Cliente envia um inteiro correspondendo ao codigo do ve&iacute;culo
 * Servidor retorna uma classe data.Veiculo ao cliente

### Alterar
 * Cliente envia o inteiro 4 ao servidor
 * Cliente envia uma classe data.Veiculo ao servidor
 * Servidor retorna uma mensagem de sucesso ou falha

### Excluir
 * Cliente envia o inteiro 5 ao servidor
 * Cliente envia uma classe data.Veiculo ao servidor
 * Servidor retorna uma mensagem de sucesso ou falha
