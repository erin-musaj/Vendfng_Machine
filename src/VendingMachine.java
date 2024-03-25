import java.util.ArrayList;

public class VendingMachine {
    private double credit;
    private ArrayList<Product> products;

    public VendingMachine(double credit){
        this.products = new ArrayList<Product>();
        this.credit = credit;
    }

    public double getCredit() {
        return credit;
    }

    public Product getProduct(int i) {
        return products.get(i);
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void editCredit(double addCred) throws Exception {
        if(addCred<0 && this.credit<Math.abs(addCred)){
            throw new Exception("Gino");
        }
        this.credit+=addCred;
    }

    public void buyProduct(int i) throws Exception {
        if(credit<products.get(i).getCost()){
            throw new Exception("I'm gonna touch you");
        }
        this.credit-=products.get(i).getCost();
    }

    public int productsLength(){
        return this.products.size();
    }
}
