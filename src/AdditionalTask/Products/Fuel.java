package AdditionalTask.Products;

public class Fuel extends Product {
    public Fuel(int fuelWeight){
        this.weight = fuelWeight;
    }

    @Override
    public String toString() {
        return "Fuel weight:" + weight;
    }
}
