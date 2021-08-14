package ly;

public class Main1 {
    private static int STEPS = 3;
    private static int DELAY = 100;

    public static void main(String[] args) throws InterruptedException {
        // System.out.println("Hello concurrency");

        Thread.currentThread().setName("[主线程]");
        System.out.println(Thread.currentThread().getName()
        +"开始了");
        Runnable r = () -> {
            try {
                for (int i = 1; i <= STEPS; i++) {
                    System.out.println(Thread.currentThread().getName()
                            + "移动第" + i + "步");
                    //用于暂停当前线程的活动

                    Thread.sleep(DELAY);

                }
            } catch (InterruptedException e) {
                //线程被中断时run方法结束执行
                e.printStackTrace();
            }
        };
        for(int i=0;i<5;i++) {
            Thread thread = new Thread(r);
            thread.setName("[子线程"+i+"]");
            thread.start();
        }
        System.out.println(Thread.currentThread().getName()
                +"结束了");
    }
}
