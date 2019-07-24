package AdditionalTask.Products;

public class Clothes extends Product {
    public Clothes(int clothesWeight){
        this.weight = clothesWeight;
    }

    @Override
    public String toString() {
        return "Clothes weight: " + weight;
    }

    @Override
    public Clothes clone() {
        return new Clothes(this.weight);
    }

//    @Override
//    public Clothes clone() {
//        final Clothes clone;
//        try {
//            clone = (Clothes) super.clone();
//        }
//        catch (CloneNotSupportedException ex) {
//            throw new RuntimeException("superclass messed up", ex);
//        }
//        clone.weight = this.weight;
//        return clone;
//    }
}
