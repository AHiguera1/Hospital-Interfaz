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
    private PuestoContainer container = new PuestoContainer(true);
    private Semaphore libre = new Semaphore(10,true);
    private Interfaz it;

    public Vacunacion(Interfaz it) {
        this.it = it;
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
