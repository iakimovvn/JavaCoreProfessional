package AdditionalTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class PortArea extends Semaphore {
    private ArrayList<Port> ports;



    public PortArea(Port... ports) {
        super(ports.length);
        this.ports = new ArrayList<>(Arrays.asList(ports));
    }

    public void loadingLine(Ship ship){

//        if(this.availablePermits() > 0) {

            try {
                if (this.availablePermits() == 0) {
                    System.out.println(ship.getName() + " ждет в очереди на погрузку");
                }
                this.acquire();
                System.out.println(ship.getName() + " получил разрешение на вход");

                for (Port port : ports) {
                    if (!port.reentrantLock.isLocked() && port.getProductsWeight() != 0) {
                        port.loading(ship);
                        if (port.getProductsWeight() == 0) {
                            System.out.println(port.getPortName() + " пуст");
                            this.reducePermits(1);

                        }
                        break;
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        finally {
            System.out.println(ship.getName() + " вышел из погрузки");
            this.release();
        }
//        }else{
//            System.out.println(ship.getName()+"");
//        }

    }
}
