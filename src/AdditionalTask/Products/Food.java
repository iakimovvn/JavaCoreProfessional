package AdditionalTask.Products;

public class Food extends Product {

    public Food(int foodWeight) {
        this.weight = foodWeight;
    }

    @Override
    public String toString() {
        return "Food weight: " + weight ;
    }
}
