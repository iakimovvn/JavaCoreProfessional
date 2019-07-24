package AdditionalTask.Products;


public abstract class Product  {
    protected int weight;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void add(int weight) {
        this.weight+=weight;
    }

    public void subtract(int weight) {
        this.weight-=weight;

    }
}
