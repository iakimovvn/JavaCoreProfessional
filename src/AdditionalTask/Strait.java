package AdditionalTask;

import java.util.concurrent.Semaphore;

public class Strait {

    private Semaphore smp;

    public Strait() {
        this.smp =new Semaphore(2);
    }

    public void go(Ship ship){
        try {
            try {
                if(smp.availablePermits() == 0) {
                    System.out.println(ship.getName() + " ждет пока появится возможность пройти пролив ");
                }
                smp.acquire();
                System.out.println(ship.getName() + " вошёл в пролив");
                Thread.sleep( 2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(ship.getName() + " вышел из пролива");
                smp.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
