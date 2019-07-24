package AdditionalTask;

import AdditionalTask.Products.Clothes;
import AdditionalTask.Products.Food;
import AdditionalTask.Products.Fuel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Shipping {

    private final int SHIP_QUANTITY = 4;

    protected PortArea portArea;
    protected CentralPort centralPort;
    protected Strait strait;

    public Shipping() {
        portArea = new PortArea(new Port("FoodPort",new Food(5900),new Food(100)),
                new Port("FuelPort",new Fuel(8500),new Fuel(100)),
                new Port("ClothesPort",new Clothes(2700),new Clothes(100)));
        centralPort = new CentralPort();
        strait = new Strait();

        ExecutorService pool = Executors.newFixedThreadPool(SHIP_QUANTITY);
        for (int i = 1; portArea.availablePermits() != 0 ; i++) {
            pool.submit(new Ship("Корабль-"+i,100*(int)(Math.random()*10),100*(int)(Math.random()*10),
                    100*(int)(Math.random()*10), this));
        }

        pool.shutdown();

        centralPort.info();
    }
}
