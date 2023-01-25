/**
 * Mythread extends Thread, this is one category of java multi threading uisng
 * extend
 */
class Mythread extends Thread {
    public void run() {
        System.out.println("Yune  is the best, this thread is runnning");

    }

}

public class ThreadEx1 {
    public static void main(String[] args) {
        Mythread t = new Mythread();
        t.start();
    }

}
