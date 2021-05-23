/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hosp;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 *
 * @author jorge
 */
public class SocketController implements Runnable {
    private Socket s;
    private Hospital h;

    public SocketController(Hospital h, Socket s) {
        this.h = h;
        this.s = s;
    }

    public void run() {
        try{
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            if (ois.readInt() == 1) {
                ArrayList<String> obj = new ArrayList();
                String str = "";
                for(Paciente p : h.getR().getCola()){
                    str += p.toString() + " ";
                }
                obj.add(str);
                str = "";
                obj.add(h.getAux1().getP().toString());
                obj.add("A1");
                for(Sanitario s : h.getDescanso()){
                    str += s.toString() + " ";
                }
                obj.add(str);
                str = "";
                // Parte de los containers
                int a = 0;
                for(String s: h.getV().getContainer().getStringArray()){
                    if(a == 5){
                        obj.add("A2");
                        obj.add(Integer.toString(h.getVacuna().availablePermits()));
                    }
                    obj.add(s);
                }
                // Segundo container
                for(String s: h.getO().getContainer().getStringArray()){
                    obj.add(s);
                }
                ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                oos.writeObject(obj);
            } else {
                int a = (int) ois.readObject();
                h.getV().blockPuesto(a-50);
            }
        }catch(Exception e){}
        
        

    }
    
    public void addContent(Paciente p, String s){
        s += p.toString() + " ";
    }
}
