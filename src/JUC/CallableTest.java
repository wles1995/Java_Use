package JUC;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new Thread().start();
        MyThread myThread = new MyThread();
        FutureTask futureTask = new FutureTask(myThread);
        new Thread(futureTask,"A").start();
        new Thread(futureTask,"C").start();
        new Thread(futureTask,"D").start();
        new Thread(futureTask,"E").start();
        new Thread(futureTask,"B").start();

        int num = (Integer) futureTask.get();
        System.out.println(num);
    }
}

class MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("Thread" + Thread.currentThread().getName() + "  call()");
        TimeUnit.SECONDS.sleep(3);
        return 1024;
    }
}
