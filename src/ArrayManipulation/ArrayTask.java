package ArrayManipulation;

import java.util.ArrayList;

public class ArrayTask {


    private static <T>void swappingArrayElements (T[] arr, int firstElement, int secondElement){
        T element = arr[firstElement];
        arr[firstElement] = arr[secondElement];
        arr[secondElement] = element;
    }

    private static void showArr(Object[] arr) {
        for (Object o : arr) {
            System.out.print( o.toString()+"\t");
        }
        System.out.println();
    }

    private static<T>ArrayList<T> arrToArrayList(T[] arr){
        ArrayList<T> arrayList = new ArrayList<>();
        for (T element: arr) {
           arrayList.add(element);
        }
        return arrayList;
    }

    public static void main(String[] args) {
        String[] arrStr = new String[]{"Привет","участникам","соревнований"};
        Integer[] arrInt = new Integer[]{1,2,3,4,5};
        showArr(arrStr);
        showArr(arrInt);
        swappingArrayElements(arrStr,1,2);
        swappingArrayElements(arrInt,3,4);
        showArr(arrStr);
        showArr(arrInt);


        ArrayList<String > strList = arrToArrayList(arrStr);
        ArrayList<Integer> intList = arrToArrayList(arrInt);
        System.out.println(strList.toString());
        System.out.println(intList.toString());
    }
}
