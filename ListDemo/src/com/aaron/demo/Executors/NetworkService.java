package com.aaron.demo.Executors;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class NetworkService {
    private final ServerSocket serverSocket;
    private final ExecutorService pool;
    private boolean flag = true;

    public NetworkService(int port, int poolSize) throws IOException {
        serverSocket = new ServerSocket(port);
        pool = Executors.newFixedThreadPool(poolSize);
    }

    public void run(){
        try{
            for(;;){
                pool.execute(new Handler(serverSocket.accept()));
            }
        }catch (Exception e){
            pool.shutdown();
        }

        //pool.execute(new Handler(null));
        //shutdownAndAwaitTermination();
    }

    class Handler implements Runnable{
        private final Socket socket;

        Handler(Socket socket){
            this.socket = socket;
        }


        @Override
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


    public  void shutdownAndAwaitTermination(){
        pool.shutdown();
        try{
            if(!pool.awaitTermination(10, TimeUnit.SECONDS)){
                pool.shutdownNow();
                System.out.println("Pool shutdown now");
                if(!pool.awaitTermination(3, TimeUnit.SECONDS)){
                    System.err.println("Pool did not terminate");
                }
            }
            System.out.println("shutdown");
        }catch (Exception e){
            pool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
