package AdditionalTask;

public class MainClass {


    public static void main(String[] args) {
        long t = System.currentTimeMillis();
        new Shipping();
        System.out.println("Время выполнения: "+(System.currentTimeMillis()-t));
    }
}
