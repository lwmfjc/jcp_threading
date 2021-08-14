package base.hello1;

public class UnsynchBankTest {
    //100个账户
    public static final int NACCOUNTS = 100;
    //每个账户1000块
    public static final double INITAIL_BALANCE = 1000;
    public static final double MAX_AMOUNT = 1000;
    public static final int DELAY = 10;

    public static void main(String[] args) {
        Bank bank = new Bank(NACCOUNTS, INITAIL_BALANCE);
        for (int i = 0; i < NACCOUNTS; i++) {
            //这里假设NACCOUNTS个线程从1账户取钱存钱
            int fromAccount = i;
            int finalI = i;
            Runnable r = () -> {
                Thread.currentThread().setName("线程["+ finalI+"]");
                try {
                    while (true) {
                        int toAccount = (int) ((int)
                                (bank.size()) * Math.random());
                        double amount = MAX_AMOUNT * Math.random();
                        bank.transfer(fromAccount, toAccount, amount);
                        Thread.sleep((int) (DELAY * Math.random()));
                    }
                } catch (InterruptedException e) {

                }
            };
            Thread t = new Thread(r);
            t.start();
        }
    }

}
