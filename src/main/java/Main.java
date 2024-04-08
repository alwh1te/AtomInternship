import org.example.atom.model.Message;
import org.example.atom.responseSender.TopicRequestManager;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        TopicRequestManager manager = new TopicRequestManager();
        Message message = new Message();
        message.setAuthor("alw");
//        message.setDate(new Date(System.currentTimeMillis()));
        message.setText("bye");
//        Topic topic = manager.createTopic("4th topic");
        message.setTopicId(1);
        manager.sendPutRequest("/1/message", message);
//        List<String> lst = manager.readMessages("4th topic");
//        System.out.println(TopicServiceImpl.TOPIC_NAME_ID_MAP.values());
//        for (String s : lst) {
//            System.out.println(s);
//        }
    }
}
