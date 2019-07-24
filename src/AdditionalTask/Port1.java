package AdditionalTask;

import AdditionalTask.Products.Product;

import java.util.concurrent.locks.ReentrantLock;

public class Port1 <T extends Product>{
    private final T WEIGHT_ONE_LOADING;

    private String portName;

    private T product;
    ReentrantLock reentrantLock;

    public Port1(String portName,T product, T weightOneLoading) {
        this.portName = portName;
        this.product = product;
        this.reentrantLock = new ReentrantLock();
        this.WEIGHT_ONE_LOADING = weightOneLoading;
    }

    public void loading(Ship ship){
        reentrantLock.lock();
        System.out.println(ship.getName()+" начал погрузку в "+portName);
        while(product.getWeight()>=WEIGHT_ONE_LOADING.getWeight() && !ship.addProduct(WEIGHT_ONE_LOADING)){
            product.subtract(WEIGHT_ONE_LOADING.getWeight());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(ship.getName()+" закончил погрузку в "+portName);

        reentrantLock.unlock();
    }
}
