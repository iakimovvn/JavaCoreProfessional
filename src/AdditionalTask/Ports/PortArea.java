package AdditionalTask.Ports;

import AdditionalTask.Port1;
import AdditionalTask.Ship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class PortArea extends Semaphore {
    private ArrayList<Port1> ports;


    public PortArea(Port1 ... ports) {
        super(ports.length);
        this.ports = new ArrayList<>(Arrays.asList(ports));
    }

    public void loadingLine(Ship ship){

        try {
            if(this.availablePermits() == 0){
                System.out.println(ship.getName()+" ждет в очереди на погрузку");
            }
            this.acquire();
            System.out.println(ship.getName()+"дождался очереди");

            for (Port1 port: ports) {
                if(!port..isLocked() && port.productsWeight!=0){
                    port.loading(ship);
                    if(port.productsWeight == 0){
                        this.reducePermits(1);
                    }
                    break;
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
