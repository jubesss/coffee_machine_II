package br.univates.projeto_cafeteira_poe;
import java.util.Scanner;
/**
 * @author juliano.hammes1
 */
public class main {
    
public static void main(String[] args) {
        // Usa EventQueue.invokeLater para garantir que a interface seja criada na EDT (Event Dispatch Thread)
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // 1. Cria uma ÚNICA instância da sua lógica de cafeteira
                CafeteiraMachine cafeteira = new CafeteiraMachine();

                // 2. Cria e exibe o JFrame principal (interface_cafeteira)
                //    E PASSA a instância da cafeteira para ele
                interface_cafeteira telaPrincipal = new interface_cafeteira(cafeteira); // Chama o construtor correto
                telaPrincipal.setVisible(true);
                telaPrincipal.setLocationRelativeTo(null);
            }
        });
    }
    
}

