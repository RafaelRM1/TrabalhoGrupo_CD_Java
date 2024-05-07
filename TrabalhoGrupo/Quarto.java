import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Classe que representa um quarto no hotel
public class Quarto {
    private int numero;
    // Lista de hospedes hospedados no quarto
    private List<Hospede> hospedes;
    // Indica se o quarto esta pronto para ser limpo
    private boolean prontoParaLimpeza = false;
    private Lock lock; // Locks

    // Construtor do quarto
    public Quarto(int numero) {
        this.numero = numero;
        this.hospedes = new ArrayList<>();
        this.lock = new ReentrantLock();

    }

    // Metodo para devolver a chave do quarto na recepcao
    public void devolverChave() {
        // Imprime uma mensagem indicando que os hóspedes devolveram a chave na recepção
        System.out.println("\n--------------------------------\nHóspedes do quarto " + numero
                + " devolveram a chave na recepção e foram passear.");
        prontoParaLimpeza = true;
        if (prontoParaLimpeza == false) {
            System.out.println("\n---------------------------\nHóspedes do quarto " + numero
                    + " pegaram a chave na recepção e voltaram pro quarto.");
        }
    }

    public void pegarChave() {

        if (prontoParaLimpeza == false) {
            System.out.println("\n--------------------------------\nHóspedes do quarto " + numero
                    + " pegaram a chave na recepção e voltaram pro quarto.");
        }
    }

    // Metodo para verifica se o quarto esta pronto para ser limpo
    public boolean estaProntoParaLimpeza() {
        return prontoParaLimpeza;
    }

    // Metodo para limpar o quarto
    public void limpar() {
        // Imprime uma mensagem indicando que o quarto foi limpo
        System.out.println("\n-------------------------------\nO quarto " + numero + " foi limpo.");
        prontoParaLimpeza = false;
    }

    // Metodo para obter o numero do quarto
    public int getNumero() {
        return numero;
    }

    // Metodo para obter a lista de hospedes do quarto
    public List<Hospede> getHospedes() {
        return hospedes;
    }

    // Metodo para verificar se o quarto esta vago
    public boolean estaVago() {
        return hospedes.isEmpty();
    }

    // Metodo para adicionar novos hospedes ao quarto
    public void adicionarHospedes(List<Hospede> novosHospedes) {
        hospedes.addAll(novosHospedes);
    }

    // Metodo para remover hospedes do quarto
    public void removerHospedes(List<Hospede> hospedesARemover) {
        hospedes.removeAll(hospedesARemover);
    }

    // Metodo para verificar se o quarto contem todos os hospedes especificados
    public boolean containsAllHospedes(List<Hospede> hospedes) {
        List<Hospede> quartoHospedes = getHospedes();
        return quartoHospedes.containsAll(hospedes);
    }

    // Metodo para pegar Lock
    public Lock getLock() {
        return lock;
    }
}

