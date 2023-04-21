import javax.jms.*;
import javax.naming.*;

public class FireAlarmJMS {
    public void raise() {
        Context ctx = new InitialContext();
        TopicConnectionFactory topicFactory = (TopicConnectionFactory) ctx.lookup("TopicConnectionFactory");
        Topic topic = (Topic) ctx.lookup("Alarms");
        TopicConnection topicConn = topicConnectionFactory.createTopicConnection();
    }
}