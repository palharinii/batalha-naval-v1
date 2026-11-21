package batalha_naval.modelo;

import java.util.ArrayList;
import java.util.List;

public final class Tabuleiro {
    public static final int TAMANHO = 10;

    private final Navio[][] mapa;
    private final String[][] visao;
    private final List<Navio> navios = new ArrayList<>();

    public Tabuleiro() {
        this.mapa = new Navio[TAMANHO][TAMANHO];
        this.visao = new String[TAMANHO][TAMANHO];

        for(int i = 0; i < TAMANHO; i++) {
            for(int j = 0; j < TAMANHO; j++) {
                visao[i][j] = "~";
            }
        }
    }

    public void desenhar() {
        System.out.print("  ");
        for(int i = 0; i < TAMANHO; i++) {
            System.out.print(" " + (char) ('A' + i) + " ");
        }

        System.out.println();

        for(int i = 0; i < TAMANHO; i++) {
            String numero = (i < TAMANHO - 1) ? " " + (i + 1) : "" + (i + 1);
            System.out.print(numero);

            for(int j = 0; j < TAMANHO; j++) {
                System.out.print(" " + visao[i][j] + " ");
            }

            System.out.println();
        }
    }

    public Resultado atacarNavio(int linha, int coluna) {
        if(!visao[linha][coluna].equals("~")) {
            return Resultado.REPETIDO;
        }

        Navio alvo = mapa[linha][coluna];

        if(alvo == null) {
            visao[linha][coluna] = "O";
            return Resultado.AGUA;
        }

        alvo.registrarAcerto();
        visao[linha][coluna] = "X";

        return (alvo.isAfundado()) ? Resultado.AFUNDOU : Resultado.ACERTO;
    }

    public boolean posicionarNavio(Navio navio, int linha, int coluna, boolean horizontal) {
        for(int i = 0; i < navio.getTamanho(); i++) {
            int l = (horizontal) ? linha : linha + i;
            int c = (horizontal) ? coluna + i : coluna;

            if(l >= TAMANHO || c >= TAMANHO || mapa[l][c] != null) {
                return false;
            }
        }

        for(int i = 0; i < navio.getTamanho(); i++) {
            int l = (horizontal) ? linha : linha + i;
            int c = (horizontal) ? coluna + i : coluna;

            mapa[l][c] = navio;
        }

        navios.add(navio);
        return true;
    }

    public boolean todosAfundados() {
        for(Navio navio : navios) {
            if(!navio.isAfundado()) {
                return false;
            }
        }

        return true;
    }
}