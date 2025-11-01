import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // Lista global para guardar todas as tarefas
    private static ArrayList<Tarefa> tarefas = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("🚀 Bem-vindo à MINHA TO-DO LIST AVANÇADA!");
        System.out.println("Feito por @Lilithcurious\n");

        // Loop principal do programa
        while (true) {
            exibirMenu();
            int opcao = lerOpcao();

            switch (opcao) {
                case 1 -> adicionarTarefa();
                case 2 -> listarTodasTarefas();
                case 3 -> listarTarefasPendentes();
                case 4 -> marcarTarefaConcluida();
                case 5 -> {
                    System.out.println("\n👋 Tchau! Obrigado por usar minha To-Do List!");
                    scanner.close();
                    return; // sai do programa
                }
                default -> System.out.println("❌ Opção inválida! Tente novamente.");
            }

            System.out.println("\n" + "─".repeat(40) + "\n");
        }
    }

    // Método para mostrar o menu
    private static void exibirMenu() {
        System.out.println("📋 === MINHA TO-DO LIST AVANÇADA ===");
        System.out.println("1. ➕ Adicionar nova tarefa");
        System.out.println("2. 📄 Listar todas as tarefas");
        System.out.println("3. ⏳ Listar tarefas pendentes");
        System.out.println("4. ✅ Marcar tarefa como concluída");
        System.out.println("5. 🚪 Sair");
        System.out.println("═".repeat(35));
        System.out.print("Escolha uma opção: ");
    }

    // Método para ler a opção do usuário
    private static int lerOpcao() {
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            System.out.println("❌ Digite apenas números!");
            scanner.nextLine(); // limpa o buffer
            return 0;
        }
    }

    // Método 1: Adicionar nova tarefa
    private static void adicionarTarefa() {
        System.out.println("\n➕ === ADICIONAR TAREFA ===");
        
        System.out.print("📝 Título da tarefa: ");
        scanner.nextLine(); // limpa buffer
        String titulo = scanner.nextLine();

        if (titulo.trim().isEmpty()) {
            System.out.println("❌ Título não pode estar vazio!");
            return;
        }

        System.out.print("📄 Descrição (opcional): ");
        String descricao = scanner.nextLine();

        // Cria a nova tarefa
        Tarefa novaTarefa = new Tarefa(titulo, descricao);
        tarefas.add(novaTarefa);

        System.out.println("✅ Tarefa '" + titulo + "' adicionada com sucesso!");
        System.out.println("📊 Total de tarefas: " + tarefas.size());
    }

    // Método 2: Listar todas as tarefas
    private static void listarTodasTarefas() {
        System.out.println("\n📄 === TODAS AS TAREFAS (" + tarefas.size() + ") ===");
        
        if (tarefas.isEmpty()) {
            System.out.println("📭 Nenhuma tarefa cadastrada ainda.");
            return;
        }

        // Separa pendentes de concluídas
        int pendentes = 0;
        int concluidas = 0;

        for (int i = 0; i < tarefas.size(); i++) {
            Tarefa t = tarefas.get(i);
            System.out.println((i + 1) + ". " + t);
            
            if (t.isConcluida()) {
                concluidas++;
            } else {
                pendentes++;
            }
        }

        System.out.println("─".repeat(35));
        System.out.println("📊 Pendentes: " + pendentes + " | Concluídas: " + concluidas);
    }

    // Método 3: Listar só pendentes
    private static void listarTarefasPendentes() {
        System.out.println("\n⏳ === TAREFAS PENDENTES ===");
        
        ArrayList<Tarefa> pendentes = new ArrayList<>();
        for (Tarefa t : tarefas) {
            if (!t.isConcluida()) {
                pendentes.add(t);
            }
        }

        if (pendentes.isEmpty()) {
            System.out.println("🎉 Todas as tarefas estão concluídas! Parabéns!");
            return;
        }

        for (int i = 0; i < pendentes.size(); i++) {
            System.out.println((i + 1) + ". " + pendentes.get(i));
        }

        System.out.println("📊 Total pendente: " + pendentes.size());
    }

    // Método 4: Marcar como concluída
    private static void marcarTarefaConcluida() {
        if (tarefas.isEmpty()) {
            System.out.println("❌ Nenhuma tarefa para marcar!");
            return;
        }

        System.out.println("\n✅ === MARCAR TAREFA CONCLUÍDA ===");
        listarTarefasPendentes();

        System.out.print("Digite o número da tarefa (1-" + tarefas.size() + "): ");
        try {
            int numero = scanner.nextInt() - 1;
            scanner.nextLine(); // limpa buffer

            if (numero >= 0 && numero < tarefas.size()) {
                Tarefa tarefaEscolhida = tarefas.get(numero);
                
                if (tarefaEscolhida.isConcluida()) {
                    System.out.println("⚠️ Esta tarefa já está concluída!");
                } else {
                    tarefaEscolhida.marcarComoConcluida();
                    System.out.println("🎉 Tarefa '" + tarefaEscolhida.getTitulo() + "' marcada como CONCLUÍDA! ✅");
                }
            } else {
                System.out.println("❌ Número inválido!");
            }
        } catch (Exception e) {
            System.out.println("❌ Digite apenas números!");
            scanner.nextLine();
        }
    }
}
