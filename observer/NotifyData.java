package observer;

import java.util.ArrayList;
import java.util.List;

import dao.UserDAO;
import model.User;

public class NotifyData implements Publisher {
    private static List<notifListener> subs;
    UserDAO userDao = new UserDAO();

    public NotifyData(){
        subs = new ArrayList<>();
    }

    // Observer

    public void subscribe(notifListener e) {
        subs.add(e);
        System.out.println("User successfully subscribed!");
    }

    public void unsubscribe(notifListener e) {
        subs.remove(e);
        System.out.println("User successfully unsubscribed!");
    }

    public void notifySubs(){
        System.out.println(subs);
        List<User> subs = userDao.FetchSubs();
        System.out.printf("+--------+------------------+----------------------+------------------------+\n");
        System.out.printf("| %6s | %16s | %20s | %22s |\n","ID","Name","Email","Phone Number");
        System.out.printf("+--------+------------------+----------------------+------------------------+\n");
        for(User user : subs){
            user.update();
            System.out.printf("| %6d | %16s | %20s | %22s |\n",
                    user.getID(), user.getName(), user.getEmail(), user.getPhoneNumber());
        }
        System.out.printf("+--------+------------------+----------------------+------------------------+\n");
        System.out.println("Users successfully notified!");
    };

    public void displayNotif(){
        System.out.println("Showing notifications");
        notifySubs();
    }
}
