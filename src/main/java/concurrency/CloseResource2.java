package concurrency;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CloseResource2 {

    public static void main(String[] args) throws IOException,
            InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        @SuppressWarnings("resource")
        ServerSocket server = new ServerSocket(8080);
        InputStream in = new Socket("localhost", 8080).getInputStream();
        exec.execute(new IOBlocked3(in));
        exec.execute(new IOBlocked3(System.in));
        TimeUnit.SECONDS.sleep(2);
        exec.shutdownNow();
        System.out.println("will close socket inputstream ...");
        in.close();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("will close system inputstream ...");
        System.in.close();
    }

}
