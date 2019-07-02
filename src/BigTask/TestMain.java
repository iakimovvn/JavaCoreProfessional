package BigTask;

public class TestMain {
    public static void main(String[] args) {
        Box <Orange> boxOrange = new Box<>(new Orange(1.5f), new Orange(1.5f));
        Box <Apple> boxApple = new Box<>(new Apple(1.0f),new Apple(1.0f),new Apple(1.0f));
        Box <Apple> boxAppleTwo = new Box<>(new Apple(1.0f));

        System.out.println(boxApple.getWeight());
        System.out.println(boxOrange.getWeight());
        System.out.println(boxAppleTwo.getWeight());

        System.out.println(boxOrange.compere(boxApple));

        boxAppleTwo.pourInBox(boxApple,2);

        System.out.println(boxApple.getWeight());
        System.out.println(boxOrange.getWeight());
        System.out.println(boxAppleTwo.getWeight());

        System.out.println(boxOrange.compere(boxApple));



    }
}
