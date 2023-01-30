import java.rmi.Naming;

public class HelloClient {
    public static void main(String[] args) {
        try {
            Hello hello = (Hello) Naming.lookup("rmi://10.17.4.102:1099/Hello");
            System.out.println(hello.sayHello());
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
