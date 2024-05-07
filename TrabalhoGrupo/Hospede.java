public class Hospede extends Thread { // Classe que representa um hspede em um hotel

    private int numeroGrupo; // Numero do grupo ao qual o hóspede pertence
    private boolean jaPasseou = false; // indica se o hospede ja andou pela cidade

    public Hospede(int numeroGrupo) {
        this.numeroGrupo = numeroGrupo;
    }

    // Metodo para simular o hospede passeando pela cidade
    public void passearPelaCidade() {
        // Verifica se o hospede ainda nao passeou
        if (!jaPasseou) {
            try {
                jaPasseou = true;
                // Simula o hospede passeando pela cidade por 3 segundos
                Thread.sleep(3000);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }
    }

    // Metodo para conseguir o status de se o hospede ja passeou pela cidade
    public boolean getJaPasseou() {
        return jaPasseou;
    }

    // Metodo para conseguir o numero do grupo ao qual o hospede pertence
    public int getNumeroGrupo() {
        return numeroGrupo;
    }

    // Metodo estatico para indicar que um grupo voltou ao quarto
    public static void retornarGrupoParaQuarto(int numeroGrupo) {
        // Mostra uma mensagem indicando que o grupo retornou para o quarto
        System.out.println("\n-------------------------------------------------\nGrupo " + numeroGrupo + " retornou.");
    }

    public void retornarParaQuarto() {
        try {
            Thread.sleep(2000); // Aguarda 2 segundos antes de retornar o grupo para o quarto
            retornarGrupoParaQuarto(numeroGrupo); // Chamada ao novo método estático
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
