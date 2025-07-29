package br.univates.projeto_cafeteira_poe;

import javax.swing.JOptionPane; // Import para exibir mensagens em janelas

public class CafeteiraMachine {

    // Capacidade máxima de grãos de café e água (melhor como final se for fixo)
    private static final int MAX_COFFEE_BEAN = 100;
    private static final int MAX_WATER = 150;
    private static final int MAX_CHOCOLATE = 100;
    private static final int OWNER_PIN = 1010; // Senha do dono

    // Variáveis de estado da cafeteira (agora são atributos da instância)
    private int coffeeBean;
    private int water;
    private int chocolate;
    private double balance;

    // Construtor da classe: inicializa a cafeteira
    public CafeteiraMachine() {
        this.coffeeBean = 0; // Começa com 0 ou o valor que desejar
        this.water = 200; // O seu valor inicial
        this.chocolate = 80; // O seu valor inicial
        this.balance = 0.0;
    }

    // --- Métodos para o Menu do Cliente ---

    public void buyCoffee() {
        if (this.coffeeBean >= 10 && this.water >= 10) {
            this.coffeeBean -= 10;
            this.water -= 10;
            this.balance += 2.5;
            JOptionPane.showMessageDialog(null, "Obrigado! Aproveite seu café curto!", "Cafeteira", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Recursos insuficientes para o Café Curto.", "Erro", JOptionPane.ERROR_MESSAGE);
            checkResources(); // Chama um método para verificar e informar o que falta
        }
    }

    public void buyLargeCoffee() {
        if (this.coffeeBean >= 10 && this.water >= 15) {
            this.coffeeBean -= 10;
            this.water -= 15;
            this.balance += 3.0;
            JOptionPane.showMessageDialog(null, "Obrigado! Aproveite seu café longo!", "Cafeteira", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Recursos insuficientes para o Café Longo.", "Erro", JOptionPane.ERROR_MESSAGE);
            checkResources();
        }
    }

    public void buyCappuccino() {
        if (this.coffeeBean >= 10 && this.water >= 10 && this.chocolate >= 5) {
            this.coffeeBean -= 10;
            this.water -= 10;
            this.chocolate -= 5;
            this.balance += 4.0;
            JOptionPane.showMessageDialog(null, "Obrigado! Aproveite seu cappuccino!", "Cafeteira", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Recursos insuficientes para o Cappuccino.", "Erro", JOptionPane.ERROR_MESSAGE);
            checkResources();
        }
    }
    
    // Método auxiliar para informar ao usuário o que está faltando
    private void checkResources() {
        String msg = "Faltando:\n";
        if (this.coffeeBean < 10) msg += "- Grãos de Café (mín. 10)\n";
        if (this.water < 10) msg += "- Água (mín. 10)\n";
        if (this.chocolate < 5) msg += "- Chocolate (mín. 5)\n";
        JOptionPane.showMessageDialog(null, msg, "Verificar Recursos", JOptionPane.WARNING_MESSAGE);
    }

    // --- Métodos para o Menu do Dono ---

    public boolean checkPin(int pinAttempt) {
        return pinAttempt == OWNER_PIN;
    }

    public void refillCoffeeBean(int amount) {
        if (amount + this.coffeeBean <= MAX_COFFEE_BEAN) {
            this.coffeeBean += amount;
            JOptionPane.showMessageDialog(null, "Grãos de café reabastecidos com sucesso! Atual: " + this.coffeeBean + "g", "Reabastecer", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "A quantidade excede a capacidade máxima de grãos de café (" + MAX_COFFEE_BEAN + "g).", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void refillWater(int amount) {
        if (amount + this.water <= MAX_WATER) {
            this.water += amount;
            JOptionPane.showMessageDialog(null, "Água reabastecida com sucesso! Atual: " + this.water + "ml", "Reabastecer", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "A quantidade excede a capacidade máxima de água (" + MAX_WATER + "ml).", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void refillChocolate(int amount) {
        if (amount + this.chocolate <= MAX_CHOCOLATE) {
            this.chocolate += amount;
            JOptionPane.showMessageDialog(null, "Chocolate reabastecido com sucesso! Atual: " + this.chocolate + "g", "Reabastecer", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "A quantidade excede a capacidade máxima de chocolate (" + MAX_CHOCOLATE + "g).", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public double getBalance() {
        return this.balance;
    }
    
    // Métodos para obter os níveis atuais (para exibir na GUI, se necessário)
    public int getCoffeeBean() {
        return coffeeBean;
    }

    public int getWater() {
        return water;
    }

    public int getChocolate() {
        return chocolate;
    }
}