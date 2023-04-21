import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

public class SoapClient {
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://localhost:9999/ws/hello?wsdl");
        QName qname = new QName("http://soapserver/", "SoapServerImplService");
        Service service = Service.create(url, qname);
        SoapServer hello = service.getPort(SoapServer.class);
        System.out.println(hello.greet("World"));
    }
}
