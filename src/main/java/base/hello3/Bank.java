package base.hello3;

import java.util.Locale;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    //银行有固定的钱
    private int[] nums = new int[2];
    private Lock lock = new ReentrantLock();

    public Bank(int num) {
        for (int i = 0; i < this.nums.length; i++) {
            nums[i] = num;
        }
    }


    //取钱
    public void optMoney(int num, int i1, int i2) {
        lock.lock();
        nums[i1] += num;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        nums[i2] -= num;
        System.out.printf(" nums[i2]=%d  ",   nums[i2]);
        System.out.printf("nums[i1]+nums[i2]=%d\n", nums[i1] + nums[i2]);
        lock.unlock();
    }
}
