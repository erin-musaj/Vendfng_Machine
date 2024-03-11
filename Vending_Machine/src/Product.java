public class Product {

    private String name;
    private double cost;

    Product(String name, double cost){
        this.name=name;
        this.cost=cost;
    }

    public double getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }
}
