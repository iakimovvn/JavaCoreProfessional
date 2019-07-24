package AdditionalTask.Ports;

import AdditionalTask.Ship;

import java.util.concurrent.locks.ReentrantLock;

public abstract class Port {

    protected final int WEIGHT_ONE_LOADING = 100;

    protected int productsWeight;
    protected ReentrantLock reentrantLock;



    public abstract void loading(Ship ship);
}
