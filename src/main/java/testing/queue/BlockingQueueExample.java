package testing.queue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by IntelliJ IDEA.
 * User: mateusz
 * Date: 14.04.12
 * Time: 19:41
 */
class Producer implements Runnable {
    private final BlockingQueue<String> queue;
    String dupa = new String("a");

    Producer(BlockingQueue q) {
        queue = q;
    }

    public void run() {
        try {
            int i = 10 ;
            Random r = new Random();
            while (i-- > 0) {
                Thread.sleep(r.nextInt(5));
                queue.put(produce());
            }
        } catch (InterruptedException ex) {
        }
    }

    String produce() {
         dupa = dupa + "a";
         return dupa;
    }
}

class Consumer implements Runnable {
    private final BlockingQueue<String> queue;
    private String consumerName;

    Consumer(BlockingQueue q, String consumerName) {
        queue = q;
        this.consumerName = consumerName;
    }

    public void run() {
        try {
            while (true) {
                consume(queue.take());
            }
        } catch (InterruptedException ex) {
        }
    }

    void consume(String x) {
        System.out.println(consumerName + x);
    }
}

public class BlockingQueueExample {
    public static void main(String[] args) {
        BlockingQueue<String> q = new LinkedBlockingQueue<String>();
        Producer p = new Producer(q);
        Consumer c1 = new Consumer(q, "First Consumer  ");
        Consumer c2 = new Consumer(q, "Second Consumer ");
        new Thread(p).start();
        new Thread(c1).start();
        new Thread(c2).start();
    }
}