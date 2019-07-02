package BigTask;

import java.util.ArrayList;
import java.util.Iterator;

public class Box <T extends Fruit> {
    private ArrayList<T> arrayList;

    public Box(T ... fruits) {
        this.arrayList = new ArrayList<>();
        for (T fruit: fruits) {
            arrayList.add(fruit);
        }
    }

    public float getWeight(){
        Iterator<T> iterator= arrayList.iterator();
        float weight = 0;

        while (iterator.hasNext()){
            weight+=iterator.next().getWeight();
        }
        return weight;
    }

    public boolean compere (Box<? extends Fruit> box){
        return this.getWeight() == box.getWeight();
    }

    public void pourInBox (Box<T> boxTo, int howMany){
        Iterator <T>iterator = arrayList.iterator();
        for (int i = 1; i <=howMany ; i++) {
            if (iterator.hasNext()) {
                T fruit = iterator.next();
                iterator.remove();
                boxTo.putFruitInBox(fruit);
            } else {
                System.out.println("Пересыпано "+(i-1)+" не хватило "+(howMany-i+1));
                break;
            }
        }
        if (arrayList.size()==0){
            System.out.println("Коробка путса");
        }

    }
    public void putFruitInBox(T fruit){
        arrayList.add(fruit);
    }
}
