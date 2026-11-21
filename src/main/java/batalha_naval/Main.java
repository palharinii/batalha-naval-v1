package batalha_naval;

import batalha_naval.jogador.Humano;
import batalha_naval.jogador.Jogador;
import batalha_naval.jogador.Maquina;
import batalha_naval.modelo.Coordenadas;
import batalha_naval.modelo.Navio;
import batalha_naval.modelo.Resultado;
import batalha_naval.modelo.Tabuleiro;

import java.util.Random;

public class Main {
    private static void posicionarFrota(Tabuleiro tabuleiro, Random random) {
        Navio[] frota = {
                new Navio("porta avioes", 5),
                new Navio("encouracado", 4),
                new Navio("cruzador", 3),
                new Navio("submarino", 3),
                new Navio("contra torpedeiro", 2)
        };

        for(Navio navio : frota) {
            boolean posicionado;

            do {
                posicionado = tabuleiro.posicionarNavio(
                        navio,
                        random.nextInt(Tabuleiro.TAMANHO),
                        random.nextInt(Tabuleiro.TAMANHO),
                        random.nextBoolean()
                );
            } while(!posicionado);
        }
    }

    private static String descrever(Resultado resultado) {
        return switch(resultado) {
            case AGUA -> "agua!";
            case ACERTO -> "acertou um navio!";
            case AFUNDOU -> "afundou um navio!";
            case REPETIDO -> "casa ja atacada!";
        };
    }

    public static void main(String[] args) {
        Random random = new Random();

        Tabuleiro tabuleiroHumano = new Tabuleiro();
        Tabuleiro tabuleiroMaquina = new Tabuleiro();

        posicionarFrota(tabuleiroHumano, random);
        posicionarFrota(tabuleiroMaquina, random);

        Jogador jogadorHumano = new Humano();
        Jogador jogadorMaquina = new Maquina();

        System.out.println("=== BATALHA NAVAL ===");
        tabuleiroMaquina.desenhar();

        while(true) {
            Resultado resultado;

            do {
                Coordenadas alvo = jogadorHumano.escolherAlvo();
                resultado = tabuleiroMaquina.atacarNavio(alvo.linha(), alvo.coluna());

                if(resultado == Resultado.REPETIDO) {
                    System.out.println("voce ja atacou essa casa, escolha outra");
                }
            } while(resultado == Resultado.REPETIDO);

            System.out.println("voce: " + descrever(resultado));
            tabuleiroMaquina.desenhar();

            if(tabuleiroMaquina.todosAfundados()) {
                System.out.println("voce venceu!");
                break;
            }

            Coordenadas alvoMaquina = jogadorMaquina.escolherAlvo();
            Resultado resultadoMaquina = tabuleiroHumano.atacarNavio(alvoMaquina.linha(), alvoMaquina.coluna());

            System.out.println("IA: " + descrever(resultadoMaquina));
            tabuleiroHumano.desenhar();

            if(tabuleiroHumano.todosAfundados()) {
                System.out.println("a IA venceu!");
                break;
            }
        }
    }
}