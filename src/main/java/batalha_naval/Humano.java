package batalha_naval;

import java.util.Scanner;

public class Humano implements Jogador {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public Coordenadas escolherAlvo() {
        do {
            System.out.print("seu turno: ");
            String coordenadas = scanner.nextLine().toUpperCase().strip();

            if(coordenadas.matches("[A-J](10|[1-9])")) {
                // ex: A5
                int linha = Integer.parseInt(coordenadas.substring(1)) - 1; // 5 - 1 = indice 4
                int coluna = coordenadas.charAt(0) - 'A'; // A - A = indice 0

                return new Coordenadas(linha, coluna);
            }

            System.out.println("entrada invalida, tente novamente!");
            System.out.println("para escolher o alvo, use uma letra entre A-J e um numero entre 1-10");
            System.out.println("ex: A4");
        } while(true);
    }
}