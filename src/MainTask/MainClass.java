package MainTask;

import java.util.concurrent.*;

public class MainClass {

    public static final int CARS_COUNT = 4;
    public static final CyclicBarrier CYCLIC_BARRIER = new CyclicBarrier(CARS_COUNT , MainClass::printStartRace);
    private static boolean isStartRace = false;


    private static void printStartRace(){
        String resWord = (isStartRace)?"закончилась":"началась";
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка "+resWord+"!!!");
        isStartRace = !isStartRace;
    }

    public static void main(String[] args) {

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

        Race race = new Race(new Road(60), new Tunnel(), new Road(40));

        ExecutorService executorService = Executors.newFixedThreadPool(CARS_COUNT);
        for(int i = 0; i < CARS_COUNT ; i++){
            executorService.submit(new Car(race, 20 + (int) (Math.random() * 10)));
        }
        executorService.shutdown();
    }
}
