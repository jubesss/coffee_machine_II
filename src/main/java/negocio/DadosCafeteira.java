package negocio;

/**
 * @author juliano.hammes1
 */
public class DadosCafeteira {

private double saldo;
    private int coffeeBean;
    private int water;
    private int chocolate;

    // Construtor vazio para Jackson
    public DadosCafeteira() {
    }

    public DadosCafeteira(double saldo, int coffeeBean, int water, int chocolate) {
        this.saldo = saldo;
        this.coffeeBean = coffeeBean;
        this.water = water;
        this.chocolate = chocolate;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getCoffeeBean() {
        return coffeeBean;
    }

    public void setCoffeeBean(int coffeeBean) {
        this.coffeeBean = coffeeBean;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public int getChocolate() {
        return chocolate;
    }

    public void setChocolate(int chocolate) {
        this.chocolate = chocolate;
    }
}
