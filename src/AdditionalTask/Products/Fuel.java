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

//    @Override
//    public Fuel clone() {
//        final Fuel clone;
//        try {
//            clone = (Fuel) super.clone();
//        }
//        catch (CloneNotSupportedException ex) {
//            throw new RuntimeException("superclass messed up", ex);
//        }
//        clone.weight = this.weight;
//        return clone;
//    }
}
