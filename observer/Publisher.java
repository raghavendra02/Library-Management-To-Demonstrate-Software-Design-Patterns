package observer;

public interface Publisher {
    public void subscribe(notifListener e);
    public void unsubscribe(notifListener e);
    public void notifySubs();
}