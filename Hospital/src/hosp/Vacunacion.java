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





public class Vacunacion{
    private PuestoContainer container;
    private Semaphore libre = new Semaphore(10,true);
    private Interfaz it;
    private Recepcion r;
    int Ids = 0;

    public Vacunacion(Interfaz it,Recepcion r) {
        this.container = new PuestoContainer(it,true);
        this.it = it;
    }
    public void blockPuesto(int id){
        switch(id){
                    case 0:
                        it.tf.get(4).setText("");                   
                        it.tf.get(4).setText("{Cerrado}");                   
                        break;
                    case 1:
                        it.tf.get(5).setText("");
                        it.tf.get(5).setText("{Cerrado}");
                        break;
                    case 2:
                        it.tf.get(6).setText("");
                        it.tf.get(6).setText("{Cerrado}");
                        break;
                    case 3:
                        it.tf.get(7).setText("");
                        it.tf.get(7).setText("{Cerrado}");
                        break;
                    case 4:
                        it.tf.get(8).setText("");
                        it.tf.get(8).setText("{Cerrado}");
                        break;
                    case 5:
                        it.tf.get(11).setText("");
                        it.tf.get(11).setText("{Cerrado}");
                        break;
                    case 6:
                        it.tf.get(12).setText("");
                        it.tf.get(12).setText("{Cerrado}");
                        break;
                    case 7:
                        it.tf.get(13).setText("");
                        it.tf.get(13).setText("{Cerrado}");
                        break;
                    case 8:
                        it.tf.get(14).setText("");
                        it.tf.get(14).setText("{Cerrado}");
                        break;
                    case 9:
                        it.tf.get(15).setText("");
                        it.tf.get(15).setText("{Cerrado}");
                        break;
                    
        }
        Sanitario s = container.get(id).getS();
        s.setCont(15);
        Paciente p = container.get(id).getP();
        container.remove(s);
        container.blockPuesto(id);
        container.get(id).setBlocked(false);
        
        
     
        
    
    }
    
    public Semaphore getLibre() {
        return libre;
    }

    public void setLibre(Semaphore libre) {
        this.libre = libre;
    }
    
    public PuestoContainer getContainer() {
        return container;
    }

    public void setContainer(PuestoContainer container) {
        this.container = container;
    }

    public Interfaz getIt() {
        return it;
    }

    public void setIt(Interfaz it) {
        this.it = it;
    }



}
