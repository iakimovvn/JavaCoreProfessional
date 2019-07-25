package AdditionalTask.Products;

public class Fuel extends Product {
    public Fuel(int fuelWeight){
        this.weight = fuelWeight;
    }

    @Override
    public String toString() {
        return "Fuel weight:" + weight;
    }

    @Override
    public Fuel clone() {
        return new Fuel(this.weight);
    }

    @Override
    public String getTitle() {
        return "Fuel";
    }


}
