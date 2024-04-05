import org.example.atom.responseSender.TopicRequestManager;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        TopicRequestManager manager = new TopicRequestManager();
        manager.createTopic("4th topic", List.of("bye"));
//        List<String> lst = manager.readTopics();
//        for (String s : lst) {
//            System.out.println(s);
//        }
    }
}
