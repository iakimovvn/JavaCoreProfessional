package Task1;

import java.io.*;
import java.util.Arrays;

public class Task1 {
    public static void main(String[] args) {

        byte[] outArr = new byte[50];
        for (int i = 0; i < outArr.length; i++) {
            outArr[i] = 43;
        }
        System.out.println(Arrays.toString(outArr));


        File file = new File("src/Task1/task1.file");

        try(FileOutputStream out = new FileOutputStream(file)) {
            out.write(outArr);
        } catch (IOException e) {
            e.printStackTrace();
        }


        try(FileInputStream in = new FileInputStream(file)){
            int count;
            byte[]inArr = new byte[50];
            while((count=in.read(inArr))>0){
                System.out.println(Arrays.toString(inArr));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
