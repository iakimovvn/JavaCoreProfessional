package AdditionalTask;

import AdditionalTask.Products.Clothes;
import AdditionalTask.Products.Food;
import AdditionalTask.Products.Fuel;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Shipping {

    private final int SHIP_QUANTITY = 5;

    protected PortArea portArea;
    protected CentralPort centralPort;
    protected Strait strait;
    protected CyclicBarrier cyclicBarrier;

    public Shipping() {
        portArea = new PortArea(new Port("FoodPort",new Food(5900),new Food(100)),
                new Port("FuelPort",new Fuel(8500),new Fuel(100)),
                new Port("ClothesPort",new Clothes(2700),new Clothes(100)));
        centralPort = new CentralPort();
        strait = new Strait();
        cyclicBarrier = new CyclicBarrier(SHIP_QUANTITY,centralPort :: info);


//        ExecutorService pool = Executors.newFixedThreadPool(SHIP_QUANTITY);
//        for (int i = 1; i <=SHIP_QUANTITY; i++) {
//            pool.submit(new Ship("Корабль-"+i,1900,2900,
//                    1800, this));
//        }

        ExecutorService pool = Executors.newFixedThreadPool(SHIP_QUANTITY);
        for (int i = 1; i <=SHIP_QUANTITY; i++) {
            pool.submit(new Ship("Корабль-"+i,100*(int)(Math.random()*7+1),100*(int)(Math.random()*7+1),
                    100*(int)(Math.random()*7+1), this));
        }

        pool.shutdown();
    }
}
