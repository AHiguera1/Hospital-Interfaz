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
import java.util.concurrent.Semaphore;

/**
 *
 * @author jorge
 */
public class Server extends Thread implements Serializable{
    private ArrayList<Sanitario> descanso;
    private Semaphore vacuna;
    private Recepcion r;
    private Observacion o;
    private Vacunacion v;
    private Auxiliar1 aux1;
    private Auxiliar2 aux2;
    public Server(ArrayList<Sanitario> descanso,Recepcion r,Observacion o,Vacunacion v,Auxiliar1 aux1,Auxiliar2 aux2 ){
        this.descanso = descanso;
        this.r = r;
        this.o = o;
        this.v = v;
        this.aux1 = aux1;
        this.aux2 = aux2;
        
       
    }
    
    public void run(){
        try{
            ClientConnector cc = new ClientConnector(descanso,vacuna,r,o,v,aux1);
            System.out.println(o);
            //Registry reg = LocateRegistry.createRegistry(2087);
            Naming.rebind("//127.0.0.1/ClientConnector",cc);
            System.out.println("RMI Object is online");
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
