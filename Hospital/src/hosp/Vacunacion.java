package hosp;

import java.io.Serializable;
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





public class Vacunacion implements Serializable{
    private PuestoContainer container;
    private Semaphore libre = new Semaphore(10,true);
    private Interfaz it;
    private Recepcion r;
    int Ids = 0;

    public Vacunacion(Interfaz it,Recepcion r) {
        this.container = new PuestoContainer(it,true);
        this.it = it;
    }
    public void blockPuesto(int id) throws InterruptedException{
        
        Sanitario s = container.get(id).getS();
        it.getjTextField4().setText(s.descansando());
        s.setCont(15);    
        container.blockPuesto(id);
        
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
