package MainTask;

import java.util.concurrent.*;

public class MainClass {

    public static final int CARS_COUNT = 4;
    public static final CyclicBarrier CYCLIC_BARRIER = new CyclicBarrier(CARS_COUNT , MainClass::printStartRace);
    private boolean isStartRase = false;
    public static volatile boolean isWinner = false;

    public static void printStartRace(){
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
    }

    public static void main(String[] args) {

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        ExecutorService executorService = Executors.newFixedThreadPool(CARS_COUNT);
        for(int i = 0; i < CARS_COUNT ; i++){
            executorService.submit(new Car(race, 20 + (int) (Math.random() * 10)));
        }
        executorService.shutdown();


        while (!executorService.isTerminated()){
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}
