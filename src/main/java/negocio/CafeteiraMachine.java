package negocio;

import com.fasterxml.jackson.databind.ObjectMapper; // ObjectMapper
import javax.swing.JOptionPane; // Import para exibir mensagens em janelas
import java.io.File; //  File
import java.io.IOException; //IOException


public class CafeteiraMachine {

    
    private static final int MAX_COFFEE_BEAN = 100;
    private static final int MAX_WATER = 150;
    private static final int MAX_CHOCOLATE = 100;
    private static final String DATA_FILE_NAME = "cafeteira_data.json";
    private static final File DATA_FILE = new File(System.getProperty("user.dir"), DATA_FILE_NAME);
    private final ObjectMapper objectMapper = new ObjectMapper();

    private int coffeeBean;
    private int water;
    private int chocolate;
    private double balance;


    public CafeteiraMachine() {
        loadData(); 
    }
    
    // ----métodos para o json funcionar----//
    
    private void loadData() {
        File file = new File(DATA_FILE_NAME);
        if (file.exists()) {
            try {
                DadosCafeteira dados = objectMapper.readValue(file, DadosCafeteira.class);
                this.balance = dados.getSaldo();
                this.coffeeBean = dados.getCoffeeBean();
                this.water = dados.getWater();
                this.chocolate = dados.getChocolate();
                System.out.println("Dados da cafeteira carregados com sucesso.");
            } catch (IOException e) {
                System.err.println("Erro ao carregar dados da cafeteira: " + e.getMessage());
                initializeDefaultValues();
            }
        } else {
            System.out.println("Arquivo de dados não encontrado, inicializando com valores padrão.");
            initializeDefaultValues();
        }
    }
    private void initializeDefaultValues() { // pra erro
        this.balance = 0.0;
        this.coffeeBean = 100; 
        this.water = 200;      
        this.chocolate = 60;   
    }
    
    public void saveData() {
        try {
            DadosCafeteira dados = new DadosCafeteira(this.balance, this.coffeeBean, this.water, this.chocolate);
            System.out.println("Tentando salvar dados no arquivo: " + DATA_FILE.getAbsolutePath());
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(DATA_FILE, dados);
            System.out.println("Dados da cafeteira salvos com sucesso em: " + DATA_FILE.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados da cafeteira em " + DATA_FILE.getAbsolutePath() + ": " + e.getMessage());
        }
    }

    // ----Métodos para o Menu do Cliente----

    public void buyCoffee() {
        if (this.coffeeBean >= 10 && this.water >= 10) {
            this.coffeeBean -= 10;
            this.water -= 10;
            this.balance += 2.5;
            JOptionPane.showMessageDialog(null, "Obrigado! Aproveite seu café curto!", "Cafeteira", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Recursos insuficientes para o Café Curto.", "Erro", JOptionPane.ERROR_MESSAGE);
            checkResources();
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
    
    private void checkResources() {
        String msg = "Faltando:\n";
        if (this.coffeeBean < 10) msg += "- Grãos de Café (mín. 10)\n";
        if (this.water < 10) msg += "- Água (mín. 10)\n";
        if (this.chocolate < 5) msg += "- Chocolate (mín. 5)\n";
        JOptionPane.showMessageDialog(null, msg, "Verificar Recursos", JOptionPane.WARNING_MESSAGE);
    }

    // --- Métodos para o Menu do Dono ---


    public void refillCoffeeBean(int amount) {
        if (amount + this.coffeeBean <= MAX_COFFEE_BEAN) {
            this.coffeeBean += amount;
            JOptionPane.showMessageDialog(null, "Grãos de café reabastecidos com sucesso! Atual: " + this.coffeeBean + "g", "Reabastecer", JOptionPane.INFORMATION_MESSAGE);
            saveData();
        } else {
            JOptionPane.showMessageDialog(null, "A quantidade excede a capacidade máxima de grãos de café (" + MAX_COFFEE_BEAN + "g).", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void refillWater(int amount) {
        if (amount + this.water <= MAX_WATER) {
            this.water += amount;
            JOptionPane.showMessageDialog(null, "Água reabastecida com sucesso! Atual: " + this.water + "ml", "Reabastecer", JOptionPane.INFORMATION_MESSAGE);
            saveData();
        } else {
            JOptionPane.showMessageDialog(null, "A quantidade excede a capacidade máxima de água (" + MAX_WATER + "ml).", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void refillChocolate(int amount) {
        if (amount + this.chocolate <= MAX_CHOCOLATE) {
            this.chocolate += amount;
            saveData();
            JOptionPane.showMessageDialog(null, "Chocolate reabastecido com sucesso! Atual: " + this.chocolate + "g", "Reabastecer", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "A quantidade excede a capacidade máxima de chocolate (" + MAX_CHOCOLATE + "g).", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public double getBalance() {
        return this.balance;
    }
    
    public int getCoffeeBean() {
        return coffeeBean;
    }

    public int getWater() {
        return water;
    }

    public int getChocolate() {
        return chocolate;
    }
    
    public String getStatus() {
    return String.format("Saldo: R$%.2f\nGrãos de Café: %dg\nÁgua: %dml\nChocolate: %dg",
            this.balance, this.coffeeBean, this.water, this.chocolate);
}

}