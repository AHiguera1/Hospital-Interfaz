/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hosp;
import java.rmi.*;
import java.rmi.registry.*;
import java.net.*;

/**
 *
 * @author jorge
 */
public class Server extends Thread{
    private Hospital h;
    public Server(Hospital h){
        this.h = h;
    }
    
    public void run(){
        try{
            ClientConnector cc = new ClientConnector(h);
            Registry reg = LocateRegistry.createRegistry(1008);
            Naming.rebind("//127.0.0.1/ClientConnector",cc);
            System.out.println("RMI Object is online");
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
