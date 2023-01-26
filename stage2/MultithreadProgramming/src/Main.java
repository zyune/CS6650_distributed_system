public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread threadB = new Thread(new MyRunnable(), "Thread B");
        threadB.start();

        Thread threadA = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " has started execution");
            try {
                threadB.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " has finished execution");
        }, "Thread A");
        threadA.start();
    }
}

class MyRunnable implements Runnable {
    public void run() {
        System.out.println(Thread.currentThread().getName() + " has started execution");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " has finished execution");
    }
}
// Here is an example of Thread A calling the join() method on Thread B, causing
// Thread A to wait until Thread B completes before continuing its own
// execution:

// In this example, Thread B is created and started first, then Thread A is
// created and started. Within the Thread A's runnable, it calls the join()
// method on Thread B, causing it to wait for Thread B to finish executing
// before printing the message "Thread A has finished execution".

// It's important to note that the join() method should be called from the
// thread which wants to wait for other thread to complete, not the other way
// around.