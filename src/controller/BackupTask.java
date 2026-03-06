package controller;

public class BackupTask implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                wait(30000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
