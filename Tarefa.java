public class Tarefa {
    // Atributos (propriedades da tarefa)
    private String titulo;
    private String descricao;
    private boolean concluida;

    // Construtor (como criar uma nova tarefa)
    public Tarefa(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.concluida = false; // Toda nova tarefa começa pendente
    }

    // Método para marcar como concluída
    public void marcarComoConcluida() {
        this.concluida = true;
    }

    // Método para verificar se está concluída
    public boolean isConcluida() {
        return concluida;
    }

    // Método para mostrar a tarefa na tela
    @Override
    public String toString() {
        String status = concluida ? "[X]" : "[ ]";
        return status + " " + titulo + " - " + descricao;
    }

    // Getters (para acessar os atributos de fora)
    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }
}