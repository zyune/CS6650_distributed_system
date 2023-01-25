
class MyThread2 implements Runnable {
    public void run() {
        System.out.println("Yune is the very best snowboarder, this thread is running");

    }

}

public class ThreadEx2 {
    public static void main(String[] args) {
        Thread mt = new Thread(new MyThread2());
        mt.start();
    }
}

/**
 * MyThread implements Runnable
 */
