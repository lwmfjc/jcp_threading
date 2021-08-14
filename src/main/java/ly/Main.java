package ly;

import com.sun.media.jfxmedia.logging.Logger;

public class Main {
    private static int STEPS = 10;
    private static int DELAY = 500;

    public static void main(String[] args) throws InterruptedException {
        // System.out.println("Hello concurrency");
        Thread.currentThread().setName("[主线程]");
        for (int i = 1; i <= STEPS; i++) {
            System.out.println(Thread.currentThread().getName()
                    + "移动第" + i + "步");
            //用于暂停当前线程的活动
            Thread.sleep(DELAY);
        }
    }
}
