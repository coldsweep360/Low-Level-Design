package lld.ActorPatternForConcurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

record EmailMessage(String to, String subject, String body) {}

abstract class Actor<M> implements Runnable {
    private final BlockingQueue<M> mailbox = new LinkedBlockingQueue<>();

    public void send(M message) {
        mailbox.offer(message);
    }

    @Override
    public final void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                M message = mailbox.take();
                onReceive(message);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    protected abstract void onReceive(M message);
}

class EmailActor extends Actor<EmailMessage> {
    @Override
    protected void onReceive(EmailMessage email) {
        System.out.println("Sending email to " + email.to());
        System.out.println("Subject: " + email.subject());
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        EmailActor emailActor = new EmailActor();
        Thread actorThread = new Thread(emailActor, "email-actor");
        actorThread.start();

        emailActor.send(new EmailMessage(
                "ada@example.com",
                "Welcome!",
                "Thanks for signing up."
        ));

        Thread.sleep(100);
        actorThread.interrupt();
        actorThread.join();
    }
}