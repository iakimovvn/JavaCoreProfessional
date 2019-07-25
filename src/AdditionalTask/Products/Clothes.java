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
}
