# EthereumWalletSimulator
## Sobre o Projeto

Essa aplicação é um simulador de carteira para a criptomoeda Ethereum. Para executar a simulação e fazer transações teste, basta executar a classe Main.

## Padrões de Projeto

Alguns padrões GoF foram utilizados de maneira conjunta nessa aplicação. São esses:

### Singleton

A carteira, fonte de verdade e controladora das transações a serem efetuadas, é única em toda a aplicação e faz uso do padrão Singleton.

### AbstractFactory + ChainOfResponsability

Há dois tipos de carteiras possíveis nessa aplicação. Uma carteira "fria" e uma carteira "quente". A criação dessas carteiras usa o padrão AbstractFactory. Uma carteira quente se conecta com a internet, enquanto a carteira fria é usada para armazenamento offline.

As carteiras se diferem na aplicação pela forma como fazem a validação das transações. Para enviar uma transação em uma ColdWallet, você precisa validar manualmente. As diferentes validações são feitas usando o padrão ChainOfResponsability.

### TemplateMethod + FactoryMethod

Há 3 tipos de transações nessa aplicação: transação de envio, de recebimento e de criação de contrato inteligente. A lógica de alteração no saldo da carteira depende do tipo de transação. Esse controle é feito com os padrões TemplateMethod para definição da lógica e FactoryMethod para criação dos diferentes tipos de transação.

### Builder

Para facilitar a criação de transações, considerando que possuem um alto número de características, foi usado o padrão builder. Isso mantém a legibilidade do código e permite a fácil alteração de características das transações.

### Strategy

O padrão strategy foi definido para escolher as estratégias de gorjeta da carteira. No Ethereum, você pode optar por pagar um valor mais alto para ter mais garantia e velocidade no processamento da transação. Com o padrão Strategy, podemos alterar em estratégias de gorjeta alta, baixa ou inexistente. As gorjetas afetam a transformação do saldo da carteira.

### State + Observer

As transações possuem 3 estados: pendentes, rejeitadas ou aceitas. Conforme passam pelo processo de validação (definida pelo ChainOfResponsability), ela pode ser aceita ou rejeitada. Ao transformar seu estado, a carteira notifica nossos objetos Observers sobre a mudança de estado da transação.

Há dois Observers no momento: Um observer para aplicativo mobile e um observer para logs internos.


## Diagrama de Classes UML

![Diagrama de Classes](./ethereum%20wallet%20class%20diagram.png)
