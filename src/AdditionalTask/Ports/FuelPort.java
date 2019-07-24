package AdditionalTask.Ports;

import AdditionalTask.Ship;

import java.util.concurrent.locks.ReentrantLock;

public class FuelPort extends Port {

    public FuelPort(int fuelWeight) {
        this.productsWeight = fuelWeight;
        this.reentrantLock = new ReentrantLock();    }


    @Override
    public void loading(Ship ship) {
        if(!ship.addFuel(WEIGHT_ONE_LOADING) && productsWeight >= WEIGHT_ONE_LOADING){
            productsWeight-=WEIGHT_ONE_LOADING;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
