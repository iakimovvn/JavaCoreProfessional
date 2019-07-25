package AdditionalTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class PortArea extends Semaphore {
    private ArrayList<Port> ports;
    private volatile int portsWithStaff;



    public PortArea(Port... ports) {
        super(ports.length);
        this.ports = new ArrayList<>(Arrays.asList(ports));
        this.portsWithStaff = ports.length;
    }

    public int getPortsWithStaff() {
        return portsWithStaff;
    }

    public void loadingLine(Ship ship){

        if(portsWithStaff > 0) {

            try {
                if (this.availablePermits() == 0) {
                    System.out.println(ship.getName() + " ждет в очереди на погрузку");
                }
                System.out.println("Свободно "+this.availablePermits()+"-"+ship.getName());
                this.acquire();
                System.out.println("Всего "+this.availablePermits()+"-"+ship.getName());

                System.out.println(ship.getName() + " получил разрешение на вход");

                for (Port port : ports) {
                    if (!port.isPortLocked()) {
                        port.loading(ship);
                        if (port.getProductsWeight() == 0) {
                            System.out.println(port.getPortName() + " пуст");
                            this.reducePermits(1);
                            portsWithStaff--;

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
            System.out.println(ship.getName()+"возвращается домой пустой");
        }

    }
}
