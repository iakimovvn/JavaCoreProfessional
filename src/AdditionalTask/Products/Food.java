package AdditionalTask.Products;

public class Food extends Product {

    public Food(int foodWeight) {
        this.weight = foodWeight;
    }

    @Override
    public String toString() {
        return "Food weight: " + weight ;
    }

    @Override
    public Food clone() {
        return new Food(this.weight);
    }
}
