# Batalha Naval

Jogo de Batalha Naval via CLI, feito em Java, onde você enfrenta a IA em um tabuleiro 10x10. Vence quem afundar primeiro toda a frota do adversário.

## Sobre o projeto

O jogo roda inteiramente no console. As frotas de ambos os lados são posicionadas aleatoriamente no início da partida, e os jogadores se alternam atirando em coordenadas do tabuleiro inimigo até que uma das frotas seja destruída.

O projeto foi estruturado com orientação a objetos, separando as responsabilidades em classes e usando recursos modernos do Java, como `record`, `enum` e `switch` expressions.

## Funcionalidades

- Tabuleiro 10x10 com colunas `A–J` e linhas `1–10`
- Posicionamento aleatório da frota (horizontal ou vertical, sem sobreposição)
- Modo **Jogador vs IA**
- Validação de entrada: coordenadas inválidas são recusadas e pedidas novamente
- Tiros repetidos não consomem o turno: o jogo pede outra coordenada
- Feedback a cada jogada: água, acerto ou navio afundado
- A máquina escolhe alvos aleatórios e nunca repete uma casa

## Como executar

### Requisitos

- **JDK 21** (o projeto usa `record` e `switch` com expressões)

### Compilando e rodando

Os arquivos pertencem ao pacote `batalha_naval`, então devem ficar dentro de uma pasta com esse nome. A partir da pasta que **contém** `batalha_naval/`, execute:

```bash
javac batalha_naval/*.java
java batalha_naval.Main
```

Ou abra o projeto em uma IDE (IntelliJ, Eclipse, VS Code) e execute a classe `Main`.

## 🎮 Como jogar

1. Ao iniciar, o tabuleiro inimigo é exibido (sem mostrar os navios).
2. No seu turno, digite a coordenada do alvo: uma **letra de A a J** seguida de um **número de 1 a 10**. Exemplos: `A4`, `j10`, `C7` (maiúsculas e minúsculas são aceitas).
3. O resultado do seu tiro é mostrado, e em seguida a máquina atira no seu tabuleiro.
4. O jogo termina quando todos os navios de um dos lados forem afundados.

### Legenda do tabuleiro

| Símbolo | Significado                |
|:-------:|----------------------------|
|   `~`   | Água ainda não atacada     |
|   `O`   | Tiro na água (errou)       |
|   `X`   | Tiro que acertou um navio  |

### Frota

| Navio              | Tamanho |
|--------------------|:-------:|
| Porta-aviões       |    5    |
| Encouraçado        |    4    |
| Cruzador           |    3    |
| Submarino          |    3    |
| Contra-torpedeiro  |    2    |

### Exemplo de execução

```
=== BATALHA NAVAL ===
   A  B  C  D  E  F  G  H  I  J
 1 ~  ~  ~  ~  ~  ~  ~  ~  ~  ~
 2 ~  ~  ~  ~  ~  ~  ~  ~  ~  ~
...
seu turno: B3
voce: acertou um navio!
```

## Estrutura do código

```
batalha_naval/
├── Main.java          # Fluxo principal do jogo e posicionamento da frota
├── Tabuleiro.java     # Grade 10x10, ataques, posicionamento e exibição
├── Navio.java         # Nome, tamanho e controle de acertos de cada navio
├── Jogador.java       # Interface com o método escolherAlvo()
├── Humano.java        # Jogador que lê as coordenadas pelo teclado
├── Maquina.java       # Jogador controlado pelo computador (alvos aleatórios)
├── Coordenadas.java   # Record com linha e coluna
└── Resultado.java     # Enum: AGUA, ACERTO, AFUNDOU, REPETIDO
```

### Decisões de design

- **`Jogador` como interface:** `Humano` e `Maquina` a implementam, então é simples criar novos tipos de jogador (por exemplo, uma IA mais inteligente) sem alterar o laço principal.
- **`Resultado` como enum:** representa de forma clara e segura os possíveis desfechos de um ataque.
- **`Tabuleiro` com duas matrizes:** uma guarda a posição real dos navios e outra guarda o que é visível ao jogador, evitando revelar a frota.
