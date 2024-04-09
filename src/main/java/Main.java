import org.example.atom.exceptions.UnexpectedItemException;
import org.example.atom.model.Message;
import org.example.atom.model.User;
import org.example.atom.responseSender.AtomRequestManager;

public class Main {
    public static void main(String[] args) throws UnexpectedItemException {
        AtomRequestManager manager = new AtomRequestManager();
        Message message = new Message();
//        manager.register("alex", "123");
//        manager.getUserByUsername("alex");
        message.setAuthor("alw");
        message.setText("bye");
        manager.createTopic("from idea", message);
//        manager.addMessage(1, message);
//        manager.register("alex", "123");
    }

//    public static void deleteMessage()
}
