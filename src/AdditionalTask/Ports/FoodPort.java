package AdditionalTask.Ports;

import AdditionalTask.Ship;

import java.util.concurrent.locks.ReentrantLock;


public class FoodPort extends Port {


    public FoodPort (int foodWeight){
        this.productsWeight = foodWeight;
        this.reentrantLock = new ReentrantLock();

    }



    @Override
    public void loading(Ship ship) {

        reentrantLock.lock();

        while(!ship.addFood(WEIGHT_ONE_LOADING) &&  productsWeight>=WEIGHT_ONE_LOADING){
            productsWeight-=WEIGHT_ONE_LOADING;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        reentrantLock.unlock();
    }
}
