package ly;

public class Main2 {
    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("子线程不断运行");
                }
            }
        };
        Thread thread = new Thread(r);
        thread.start();
        int i = 0;
        while (i<100) {
            try {
                Thread.currentThread().sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("主线程不断运行"+i);
            if(i==50){
                thread.interrupt();
            }
            i++;
        }

    }
}
