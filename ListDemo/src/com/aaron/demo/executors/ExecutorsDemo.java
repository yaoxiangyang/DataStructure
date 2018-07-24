package com.aaron.demo.executors;

import java.io.IOException;

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
