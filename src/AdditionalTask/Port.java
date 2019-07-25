package AdditionalTask;

import AdditionalTask.Products.Product;

import java.util.concurrent.locks.ReentrantLock;

public class Port{
    private final Product WEIGHT_ONE_LOADING;

    private String portName;

    private Product product;
    private ReentrantLock reentrantLock;

    public Port(String portName, Product product, Product weightOneLoading) {
        this.portName = portName;
        this.product = product;
        this.reentrantLock = new ReentrantLock();
        this.WEIGHT_ONE_LOADING = weightOneLoading;
    }

    public void loading(Ship ship){
        reentrantLock.lock();
        System.out.println(ship.getName()+" начал погрузку в "+portName);
        while(product.getWeight()>=WEIGHT_ONE_LOADING.getWeight() && ship.isLoadOneUnit(getWEIGHT_ONE_LOADING())){
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

    public int getProductsWeight(){
        return product.getWeight();
    }

    public Product getWEIGHT_ONE_LOADING() {
        return WEIGHT_ONE_LOADING.clone();
    }

    public String getPortName() {
        return portName;
    }

    public synchronized boolean isPortLocked(){
        return reentrantLock.isLocked() && getProductsWeight()!=0;
    }

}
