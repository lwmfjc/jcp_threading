package base.hello1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    private final double[] accounts;
    private Lock lock = new ReentrantLock();
    //使用cond1.await时该线程进去了该条件(cond1)的等待集
    //使用cond1.signalAll时激活该条件等待集中所有线程，并成为可运行
    //如果竞争成功，调度器调用了它，那就且此时能获得该锁(没人其他线程在用锁)，
    // 那么就从await返回并继续判断，条件满足则进入下一条语句

    //有个注意的地方：线程在等待集的时候时处于等待状态。被唤醒时如果有其他线程拿着锁，那
    //它也是不能够运行的
    private Condition cond1=lock.newCondition();

    public Bank(int n, double initialBalance) {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
    }

    public void transfer(int from, int to,
                         double amount) throws InterruptedException {
        //Thread.sleep(20);
        lock.lock();
        System.out.println(Thread.currentThread().getName()+"获取了锁");
        //注意这块代码,被唤醒了和继续等待肯定是连在一起的,因为
        //被唤醒时如果锁可用就拿到锁，其他线程不能访问，然后继续判断
        while (accounts[from] - amount < 0) {
            System.out.println(Thread.currentThread().getName()+"进入等待" +
                    String.format(":金额%.2f 取款%.2f",accounts[from],amount));
            cond1.await(); //这里这个线程已经失去锁了,被唤醒的时候会再次检测锁是否可用
            System.out.println(Thread.currentThread().getName()+"被唤醒了");
        }
        //Thread.sleep(1000);
        System.out.print(Thread.currentThread());
        accounts[from] -= amount;
        System.out.printf("当前用户余额[%.2f] ", accounts[from]);
                /*if(accounts[from]<=900){
                    System.err.println("----------------");
                }*/
        System.out.printf("%10.2f from %d to [%d]", amount, from, to);
        accounts[to] += amount;

        System.out.printf("Total Balance:%10.2f%n", getTotalBalance());
        cond1.signalAll();
        lock.unlock();
    }

    public double getTotalBalance() {
        double sum = 0;
        for (double a : accounts) {
            sum += a;
        }
        return sum;
    }

    public int size() {
        return accounts.length;
    }
}
