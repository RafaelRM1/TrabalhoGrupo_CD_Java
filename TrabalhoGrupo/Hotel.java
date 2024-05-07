import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Hotel {
    // Representa o numero maximo de hospedes por quarto
    public static final int NUMERO_MAXIMO_HOSPEDES = 4;
    // lista de quartos do hotel
    private List<Quarto> quartos;
    // Fila de espera para grupo de hospedes
    private BlockingQueue<List<Hospede>> filaEspera;
    static Lock lock = new ReentrantLock(); // Locks

    public Hotel(int numQuartos) {
        // Inicia a lista de quartos
        quartos = new ArrayList<>();
        // Cria os quartos e adiciona a lista
        for (int i = 1; i <= numQuartos; i++) {
            Quarto quarto = new Quarto(i);
            quartos.add(quarto);
            System.out.println(
                    "\n-------------------------------------------------\nQuarto número " + i + " foi criado.");
        }
        // Inicializa a fila de espera com capacidade para 10 grupos de hospedes
        filaEspera = new ArrayBlockingQueue<>(10);
    }
    public synchronized Quarto alocaQuarto(List<Hospede> grupoHospedes) throws InterruptedException {
        lock.lock();
        try {
            // Metodo para alocar um quarto para um grupo de hospedes
            for (Quarto quarto : quartos) {
                // Verifica se o quarto esta vago
                if (quarto.estaVago()) {
                    // Adiciona o grupo de hospedes ao quarto
                    quarto.adicionarHospedes(grupoHospedes);
                    return quarto;
                }
            }
            // Se todos os quartos estiverem ocupados, adiciona o grupo a fila de espera
            System.out.println(
                    "\n-------------------------------------------------\nTodos os quartos estão ocupados. O grupo "
                            + grupoHospedes.get(0).getNumeroGrupo() +
                            " será colocado na fila de espera.");
            filaEspera.put(grupoHospedes);
            return null; // ou lançar uma excecao indicando que não ha quartos disponiveis
        } finally {
            lock.unlock();
        }
    }

    // Metodo para liberar um quarto ocupado por um grupo de hospedes
    public synchronized void liberarQuarto(List<Hospede> grupoHospedes) {
        lock.lock();
        try {
            for (Quarto quarto : quartos) {
                // verifica se o quarto contem todos os hospedes do grupo
                if (quarto.containsAllHospedes(grupoHospedes)) {
                    // Remove o grupo de hospedes do quarto
                    quarto.removerHospedes(grupoHospedes);
                    // Se o quarto ficar vago, verifica se ha um grupo esperando para ocupar
                    if (quarto.estaVago()) {
                        List<Hospede> proximoGrupo = filaEspera.poll();
                        if (proximoGrupo != null) {
                            try {
                                alocaQuarto(proximoGrupo); // Aloca o quarto para o proximo grupo na fila de espera
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    return;
                }
            }
        } finally {
            lock.unlock();
        }
    }

    // Metodo para obter a lista de quartos do hotel
    public synchronized List<Quarto> getQuartos() {
        return quartos;
    }

    // Metodo para obter o proximo grupo da fila de espera
    public synchronized List<Hospede> getProximoGrupoFilaEspera() {
        return filaEspera.poll();
    }
}
