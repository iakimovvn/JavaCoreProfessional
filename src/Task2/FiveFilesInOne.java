package Task2;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

public class FiveFilesInOne {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<FileInputStream> inputStreamArrayList = new ArrayList<>();
        inputStreamArrayList.add(new FileInputStream("src/Task2/1.txt"));
        inputStreamArrayList.add(new FileInputStream("src/Task2/2.txt"));
        inputStreamArrayList.add(new FileInputStream("src/Task2/3.txt"));
        inputStreamArrayList.add(new FileInputStream("src/Task2/4.txt"));
        inputStreamArrayList.add(new FileInputStream("src/Task2/5.txt"));
        Enumeration<FileInputStream> enumeration = Collections.enumeration(inputStreamArrayList);

        try(SequenceInputStream sequenceInputStream = new SequenceInputStream(enumeration);
        FileOutputStream out = new FileOutputStream("src/Task2/Tell_me_my_talisman.txt")){
            int x;
            while((x = sequenceInputStream.read())!=-1){
                out.write(x);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
