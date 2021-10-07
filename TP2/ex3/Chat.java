

public class Chat {
    public static void main(String[] args) {
        ReceiveThread threadReceive = new ReceiveThread();
        SendThread threadSend = new SendThread();
        threadReceive.run();
        threadSend.run();
    }
}