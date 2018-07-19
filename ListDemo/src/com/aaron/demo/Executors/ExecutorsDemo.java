package com.aaron.demo.Executors;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorsDemo {

    public static void main(String args[]){
        try {
            NetworkService service = new NetworkService(9501, 2);
            service.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
