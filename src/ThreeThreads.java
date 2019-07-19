public class ThreeThreads {

    private char symbol = 'C';

    public ThreeThreads(){

       Thread t1 = new Thread(new Runnable() {
           @Override
           public void run() {

               for (int i = 0; i < 5; i++) {
                   printSymbol('A', 'C');
               }
           }
       });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    printSymbol('B','A');
                }

            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    printSymbol('C', 'B');

                }

            }
        });

        t1.start();
        t2.start();
        t3.start();
    }


    private synchronized void printSymbol (char symbol, char symbolBefore){

        while(this.symbol != symbolBefore){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.symbol = symbol;
        System.out.print(symbol);
        notifyAll();
    }



}


