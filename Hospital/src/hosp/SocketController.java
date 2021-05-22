/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hosp;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
public class ClientConnector implements Runnable {
    private Socket s;
    private Hospital h;

    public ClientConnector(Hospital h, Socket s) {
        this.h = h;
        this.s = s;
    }

    public void run() {
        ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
        if (((int) ois.readObject()) == 1) {
            ArrayList<String> obj = new ArrayList();
        String str = "";
        h.getR().getCola().forEach(p -> {
            str += p.toString() + " ";
        });
        obj.add(str);
        str = "";
        obj.add(h.getAux1().getP().toString());
        obj.add("A1");
        h.getDescanso().forEach(s -> {
            str += s.toString() + " ";
        });
        obj.add(str);
        str = "";
        // Parte de los containers
        // Funcion propia de container que devuelva Array de String con la peña q tiene
        obj.add(Integer.toString(h.getVacuna().availablePermits()));
        // Segundo container
        ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
        oos.writeObject(obj);
        } else {
            int a = (int) ois.readObject();
            h.getV().blockPuesto(a);
        }
        

    }
}
