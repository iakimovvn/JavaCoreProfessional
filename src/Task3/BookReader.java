package Task3;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class BookReader {
    private File textFile;


    public BookReader() {

        textFile = inputFile();

        long t = System.currentTimeMillis();
        showAllFile();
        System.out.println(System.currentTimeMillis() - t);


        int page = inputPage();
        if(page >= 0 ){
            t = System.currentTimeMillis();
            showPage(page);
            System.out.println("\n\n\nTime is: "+(System.currentTimeMillis() - t));
        }
    }

    public static File inputFile(){
        File file;
        JFileChooser fileChooser = new JFileChooser();
        while (true) {
            int ref = fileChooser.showDialog(null, "Выберите файл");

            if (ref == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
                if (file.getName().endsWith(".txt")) {
                    break;
                }
            }else{
                System.exit(0);
            }
        }
        return file;

    }

    public static int inputPage(){
        Scanner inputPage = new Scanner(System.in);
        System.out.print("Input page ");
        return inputPage.nextInt();
    }



    public void showAllFile(){
        try(BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(textFile))){
            int x;
            while((x = bufferedInputStream.read()) != -1){
                System.out.print((char)x);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void showPage(int page){
        int symbolsInPage = 1800;
        try(RandomAccessFile raf = new RandomAccessFile(textFile, "r")) {
            raf.seek(page * symbolsInPage);
            for(int i = 0; i <symbolsInPage; i++){
                System.out.print((char)raf.read());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
