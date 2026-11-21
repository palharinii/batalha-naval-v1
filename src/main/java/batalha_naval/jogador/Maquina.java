package batalha_naval.jogador;

import batalha_naval.modelo.Coordenadas;
import batalha_naval.modelo.Tabuleiro;

import java.util.Random;

public class Maquina implements Jogador {
    private final Random random = new Random();
    private final boolean[][] jaAtirou = new boolean[Tabuleiro.TAMANHO][Tabuleiro.TAMANHO];

    @Override
    public Coordenadas escolherAlvo() {
        int linha;
        int coluna;

        do {
            linha = random.nextInt(Tabuleiro.TAMANHO); // 0 a 9
            coluna = random.nextInt(Tabuleiro.TAMANHO); // 0 a 9
        } while(jaAtirou[linha][coluna]);

        System.out.println("IA jogou: " + (char) (coluna + 'A') + (linha + 1));

        jaAtirou[linha][coluna] = true;
        return new Coordenadas(linha, coluna);
    }
}