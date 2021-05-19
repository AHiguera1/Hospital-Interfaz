package hosp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;





public class Vacunacion extends Thread {
    Queue<Sanitario> san = new ArrayBlockingQueue<Sanitario>(10);
    private Semaphore libre = new Semaphore(10,true);
    private Interfaz it;

    public Vacunacion(Interfaz it) {
        this.it = it;
    }
    public void run(){
        while(true){
            ArrayList<Sanitario> aux = new ArrayList<>();
            aux.addAll(san);
            for (int i = 0;i < aux.size(); i++){
                switch(i){
                    case 0:
                        it.getjTextField5().setText(puesto(aux.get(i)));
                        break;
                    case 1:
                        it.getjTextField6().setText(puesto(aux.get(i)));
                        break;
                    case 2:
                        it.getjTextField7().setText(puesto(aux.get(i)));
                        break;
                    case 3:
                        it.getjTextField8().setText(puesto(aux.get(i)));
                        break;
                    case 4:
                        it.getjTextField9().setText(puesto(aux.get(i)));
                        break;
                    case 5:
                        it.getjTextField12().setText(puesto(aux.get(i)));
                        break;
                    case 6:
                        it.getjTextField13().setText(puesto(aux.get(i)));
                        break;
                    case 7:
                        it.getjTextField14().setText(puesto(aux.get(i)));
                        break;
                    case 8:
                        it.getjTextField15().setText(puesto(aux.get(i)));
                        break;
                    case 9:
                        it.getjTextField16().setText(puesto(aux.get(i)));
                        break;
                }

            }
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Vacunacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    public String puesto(Sanitario s){
        if(s.getP() == null){
            return s.toString();
        }else{
            return s.toString() + ", " + s.getP().toString();
        }
    }
    public Sanitario getPuesto(int index) {
        return this.san.toArray(new Sanitario[10])[index];
    }


    public int nextPuesto() {
        Iterator<Sanitario> it = san.iterator();
        int aux = 0;
        while(it.hasNext()) {
            Sanitario s = it.next();
            if(s.getP() ==  null) {return aux;}
            aux++;
        }
        return -1;
    }

    public int getPuesto(Sanitario s1) {
        Iterator<Sanitario> it = san.iterator();
        int aux = 0;
        while(it.hasNext()) {
            Sanitario s2 = it.next();
            if(s1 ==  s2) {return aux;}
            aux++;
        }
        return -1;
    }

   
    public Semaphore getLibre() {
        return libre;
    }

    public void setLibre(Semaphore libre) {
        this.libre = libre;
    }

    public Queue<Sanitario> getSan() {
        return san;
    }

    public void setSan(Queue<Sanitario> san) {
        this.san = san;
    }



}
