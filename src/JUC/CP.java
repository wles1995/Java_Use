package JUC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CP {
    public static void main(String[] args) {
        Data1 data = new Data1();
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

    }
}

class Data1{
    private int number = 0;

    public synchronized void increment() throws InterruptedException {
        while (number != 0){
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + "=>" + number);
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        while (number == 0){
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "=>" + number);
        this.notifyAll();
    }
}
class Data2{
    private int number = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {
        lock.lock();
        while (number != 0){
            condition.await();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + "=>" + number);
        condition.signalAll();
        lock.unlock();
    }

    public void decrement() throws InterruptedException {
        lock.lock();
        while (number == 0){
            condition.await();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "=>" + number);
        condition.signalAll();
        lock.unlock();
    }
}
