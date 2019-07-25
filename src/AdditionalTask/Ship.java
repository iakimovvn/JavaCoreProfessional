package AdditionalTask;


import AdditionalTask.Products.Food;
import AdditionalTask.Products.Fuel;
import AdditionalTask.Products.Product;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Ship  implements Runnable {
    private String name;

    private final int MAX_FUEL;
    private final int MAX_CLOTHES;
    private final int MAX_FOOD;


    protected Product cargo;
    private int cargoMaxWeight;
    private PortArea portArea;
    private CyclicBarrier cyclicBarrier;

    private boolean isTripToHome = false;

    private Strait strait;
    private CentralPort centralPort;



    public Ship(String name, int max_fuel, int max_clothes, int max_food, Shipping shipping) {
        this.name = name;
        MAX_FUEL = max_fuel;
        MAX_CLOTHES = max_clothes;
        MAX_FOOD = max_food;
        this.strait = shipping.strait;
        this.centralPort = shipping.centralPort;
        this.portArea = shipping.portArea;
        this.cyclicBarrier= shipping.cyclicBarrier;
    }

    public String getName() {
        return name;
    }


    public boolean isLoadOneUnit(Product productFromPort){
        boolean isOnBoard = false;
        if(cargo == null){
            cargo = productFromPort;
            identifyLoadingProductForMax(cargo);
            System.out.println(this.getName()+ " погрузил 100 единиц "+cargo.getTitle());
            isOnBoard = true;
        }else{
            if(cargo.getWeight()+productFromPort.getWeight() <= cargoMaxWeight) {
                cargo.add(productFromPort.getWeight());
                System.out.println(this.getName()+ " погрузил 100 единиц "+cargo.getTitle());
                isOnBoard = true;
            }
        }
        return isOnBoard;
    }

    public boolean isUnLoadOneUnit(int oneUnitWeight){
        if(cargo.getWeight() - oneUnitWeight >= 0) {
            cargo.subtract(oneUnitWeight);
            System.out.println(this.getName()+ " разгрузил 100 единиц "+cargo.getTitle());

            return true;
        }
        return false;
    }

    public void washHolds(){
        cargo = null;
    }




    private void identifyLoadingProductForMax(Product product){
        if(product instanceof Fuel){
            cargoMaxWeight = MAX_FUEL;
        }if(product instanceof Food){
            cargoMaxWeight = MAX_FOOD;
        }else{
            cargoMaxWeight = MAX_CLOTHES;
        }
    }



    @Override
    public void run() {

        while (portArea.getPortsWithStaff() > 0) {
            tripBetweenPorts();

            portArea.loadingLine(Ship.this);

            tripBetweenPorts();

            centralPort.unloading(Ship.this);
        }
        try {
            cyclicBarrier.await();
        } catch (InterruptedException |BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    private void tripBetweenPorts(){
        String action = (isTripToHome)?"разгружаться":"на погрузку";
        System.out.println(name+" пошёл "+action);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name+" подошёл к проливу");
        strait.go(Ship.this);
        System.out.println(name+" пошёл к прорту "+action);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        isTripToHome = !isTripToHome;
    }

}
