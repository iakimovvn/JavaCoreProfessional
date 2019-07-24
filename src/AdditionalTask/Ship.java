package AdditionalTask;


import AdditionalTask.Products.Clothes;
import AdditionalTask.Products.Food;
import AdditionalTask.Products.Fuel;
import AdditionalTask.Products.Product;

public class Ship  implements Runnable {
    private String name;

    private final int MAX_FUEL;
    private final int MAX_CLOTHES;
    private final int MAX_FOOD;

//    private Fuel fuel;
//    private Food food;
//    private Clothes clothes;

    private Product cargo;
    private int cargoMaxWeight;

    private Strait strait;
    private CentralPort centralPort;



    public Ship(String name, int max_fuel, int max_clothes, int max_food, Strait strait, CentralPort centralPort) {
        this.name = name;
        MAX_FUEL = max_fuel;
        MAX_CLOTHES = max_clothes;
        MAX_FOOD = max_food;
        this.strait = strait;
        this.centralPort = centralPort;

//        this.fuel = new Fuel(0);
//        this.food = new Food(0);
//        this.clothes = new Clothes(0);
    }

    public String getName() {
        return name;
    }
//
//    public boolean addFuel (int weight){
//        if((fuel + weight)<= MAX_FUEL){
//            fuel+=weight;
//            return true;
//        }
//        return false;
//    }
//
//    public boolean addFood (int weight){
//        if((food + weight)<= MAX_FOOD){
//            food+=weight;
//            return true;
//        }
//        return false;
//    }
//
//    public boolean addClothes (int weight){
//        if((clothes + weight)<= MAX_CLOTHES){
//            clothes+=weight;
//            return true;
//        }
//        return false;
//    }
//
//    public boolean pickUpFuel(int weight){
//        if(fuel - weight >= 0){
//            fuel-=weight;
//            return true;
//        }
//        return false;
//    }
//
//
//    public boolean pickUpFood(int weight){
//        if(food - weight >= 0){
//            food-=weight;
//            return true;
//        }
//        return false;
//    }
//
//    public boolean pickUpClothes(int weight){
//        if(clothes - weight >= 0){
//            clothes-=weight;
//            System.out.printf("%s pick up %s products");
//            return true;
//        }
//        return false;
//    }



    public boolean addProduct(Product productFromPort){
        boolean isOnBoard = false;
        if(cargo == null){
            cargo = productFromPort;
            identifyLoadingProductForMax(cargo);
            isOnBoard = true;
        }else{
            if(cargo.getWeight()+productFromPort.getWeight() <= cargoMaxWeight) {
                cargo.add(productFromPort.getWeight());
                isOnBoard = true;
            }
        }
        return isOnBoard;
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
        tripBetweenPorts();









        tripBetweenPorts();
        centralPort.unloading(Ship.this);
    }

    private void tripBetweenPorts(){
        String action = (cargo != null)?"разгружаться":"на погрузку";
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
    }
}
