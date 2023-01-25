Here is an example of the producer-consumer problem using shared memory in Java:

This code uses a buffer as a shared memory represented by the class Buffer, where the producer thread generates numbers and stores them in the buffer, and the consumer thread takes numbers from the buffer and consumes them. The producer and consumer threads communicate with each other using the wait() and notify() methods for synchronizing access to the buffer. The buffer has a fixed size of 10 and the producer and consumer threads run for a fixed number of iterations of 10.

```java
import java.util.LinkedList;

public class SharedMemory {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();

        Thread producer = new Thread(new Producer(buffer));
        Thread consumer = new Thread(new Consumer(buffer));

        producer.start();
        consumer.start();
    }
}

class Buffer {
    private LinkedList<Integer> list = new LinkedList<>();
    private int capacity;

    public Buffer() {
        this.capacity = 10;
    }

    public void produce(int item) {
        while (list.size() == capacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        list.add(item);
        System.out.println("Produced item: " + item);
        notify();
    }

    public int consume() {
        while (list.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int item = list.remove();
        System.out.println("Consumed item: " + item);
        notify();
        return item;
    }
}

class Producer implements Runnable {
    private Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            buffer.produce(i);
        }
    }
}

class Consumer implements Runnable {
    private Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            buffer.consume();
        }
    }
}
```
