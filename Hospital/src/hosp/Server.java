/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hosp;

import java.io.Serializable;
import java.rmi.*;
import java.rmi.registry.*;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 *
 * @author jorge
 */
public class Server extends Thread implements Serializable {
    private Hospital h;
    private ServerSocket ss = new ServerSocket(7777);
    ExecutorService es = Executors.newCachedThreadPool();

    public Server(Hospital h) {
        this.h = h;

    }

    public void run() {
        try {
            while (true) {
                Socket s = ss.accept();
                SocketController sc = new SocketController(h, s);
                es.execute(sc);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
