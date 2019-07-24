package AdditionalTask;

import AdditionalTask.Products.Clothes;
import AdditionalTask.Products.Food;
import AdditionalTask.Products.Fuel;

public class CentralPort {

    protected final int WEIGHT_ONE_LOADING = 100;

    private Food food ;
    private Fuel fuel ;
    private Clothes clothes;

    public CentralPort() {
        this.food = new Food(0);
        this.fuel = new Fuel(0);
        this.clothes = new Clothes(0);
    }

    public synchronized void unloading (Ship ship){
        if(ship.cargo == null){
            System.out.println(ship.getName()+" вернулся в порт пустым");
        }else {
            System.out.println(ship.getName()+" начал разгрузку");
            if (ship.cargo instanceof Food) {
                unloadFood(ship);
            } else if (ship.cargo instanceof Fuel) {
                unloadFuel(ship);
            } else {
                unloadClothes(ship);
            }
            ship.washHolds();
            System.out.println(ship.getName()+" закончил разгрузку");

        }
    }

    private void unloadFood(Ship ship){
        while(ship.isUnLoadOneUnit(WEIGHT_ONE_LOADING)){
            food.add(WEIGHT_ONE_LOADING);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void unloadFuel(Ship ship){
        while(ship.isUnLoadOneUnit(WEIGHT_ONE_LOADING)){
            fuel.add(WEIGHT_ONE_LOADING);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void unloadClothes(Ship ship){
        while(ship.isUnLoadOneUnit(WEIGHT_ONE_LOADING)){
            clothes.add(WEIGHT_ONE_LOADING);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    public void info(){
        System.out.println("Сейчас в Центральном порту: ");
        System.out.println(food);
        System.out.println(fuel);
        System.out.println(clothes);
    }


}
