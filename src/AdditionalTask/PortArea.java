package AdditionalTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class PortArea extends Semaphore {
    private ArrayList<Port> ports;
    private volatile AtomicInteger portsWithStaff;



    public PortArea(Port... ports) {
        super(ports.length);
        this.ports = new ArrayList<>(Arrays.asList(ports));
        this.portsWithStaff = new AtomicInteger(ports.length);
    }

    public int getPortsWithStaff() {
        return portsWithStaff.get();
    }

    public void loadingLine(Ship ship){

        if(portsWithStaff.get() > 0) {

            try {
                if (this.availablePermits() == 0) {
                    System.out.println(ship.getName() + " ждет в очереди на погрузку");
                }
                System.out.println("Свободно "+this.availablePermits()+"-"+ship.getName());
                this.acquire();
                System.out.println("Всего "+this.availablePermits()+"-"+ship.getName());

                System.out.println(ship.getName() + " получил разрешение на вход");

                for (Port port : ports) {
                    if (port.isPortLocked()) {
                        port.loading(ship);
                        if (port.getProductsWeight() == 0) {
                            System.out.println(port.getPortName() + " пуст");

                            portsWithStaff.decrementAndGet();

                            if(portsWithStaff.get() != 0){
                                this.reducePermits(1);
                            }

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
        }else{
            System.err.println(ship.getName()+"возвращается домой пустой, он слишком долго плыл");
        }

    }
}
