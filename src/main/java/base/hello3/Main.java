package base.hello3;

class LyRun implements Runnable {
    private int from;
    private int to;
    private Bank bank;

    public LyRun(Bank bank, int from, int to) {
        this.bank = bank;
        this.from = from;
        this.to = to;
    }

    @Override
    public void run() {
        while (true) {
            bank.optMoney(160, from, to);
        }
    }
}

public class Main {
    public static void main(String[] args) {

        Bank bank = new Bank(100);
        Thread thread1 = new Thread(new LyRun(bank, 0, 1));
        Thread thread2 = new Thread(new LyRun(bank, 1, 0));
        thread1.start();
        thread2.start();
    }
}
