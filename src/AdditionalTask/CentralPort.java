package AdditionalTask;

public class CentralPort {

    protected final int WEIGHT_ONE_LOADING = 100;

    private int food = 0;
    private int fuel = 0;
    private int clothes = 0;

    public synchronized void unloading (Ship ship){
        while(!ship.pickUpFood(WEIGHT_ONE_LOADING)){
            food+=WEIGHT_ONE_LOADING;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while(!ship.pickUpFuel(WEIGHT_ONE_LOADING)){
            fuel+=WEIGHT_ONE_LOADING;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        while(!ship.pickUpClothes(WEIGHT_ONE_LOADING)){
            fuel+=WEIGHT_ONE_LOADING;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void info(){
        System.out.println("Сейчас в Центральном порту: ");
        System.out.println("Food: "+food);
        System.out.println("Fuel: "+fuel);
        System.out.println("Clothes:"+clothes);
    }


}
