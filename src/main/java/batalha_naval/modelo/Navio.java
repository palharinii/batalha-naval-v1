package batalha_naval.modelo;

public final class Navio {
    private final String nome;
    private final int tamanho;
    private int acertos;

    public Navio(String nome, int tamanho) {
        this.nome = nome;
        this.tamanho = tamanho;
    }

    public boolean isAfundado() {
        return acertos == tamanho;
    }

    public void registrarAcerto() {
        acertos++;
    }

    public String getNome() {
        return nome;
    }

    public int getTamanho() {
        return tamanho;
    }

    public int getAcertos() {
        return acertos;
    }
}