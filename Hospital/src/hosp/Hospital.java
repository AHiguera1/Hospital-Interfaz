package hosp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Hospital extends Thread implements Serializable{

    private Interfaz it;
    private ArrayList<Sanitario> descanso;
    private Semaphore vacuna;
    private Random rnd;
    private Recepcion r;
    private Observacion o;
    private Vacunacion v;
    private Auxiliar1 aux1;
    private Auxiliar2 aux2;
    private Server s;
	
    public Hospital (Interfaz it)
    {
        Logger.createLogger();
        this.it = it;
        descanso = new ArrayList<Sanitario>();
        vacuna = new Semaphore(0);
        rnd = new Random();
        r = new Recepcion();
        o = new Observacion(it);
        v = new Vacunacion(it,r);
        aux1 = new Auxiliar1(v,r,it);
        aux2 = new Auxiliar2(v,vacuna,it);
        s = new Server(descanso,r,o,v,aux1,aux2);
        s.start();
            
    }
        
        public void run(){
                o.start();
		aux1.start();
		aux2.start();
		for(int i = 0; i < 10; i++) {
			new Sanitario(i,v,vacuna,aux1,o,descanso,it).start();
		}
		for(int i = 0; i < 2000; i++) {
			new Paciente(r,i,o).start();
			try {
                            //Por esto al pintar la cola de recepcion no pinta nadie, porque llegan con retardo
				//Thread.sleep(1000 + (long)(rnd.nextInt(2000)));
                                Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}

	}

    public Interfaz getIt() {
        return it;
    }

    public void setIt(Interfaz it) {
        this.it = it;
    }

    public ArrayList<Sanitario> getDescanso() {
        return descanso;
    }

    public void setDescanso(ArrayList<Sanitario> descanso) {
        this.descanso = descanso;
    }

    public Semaphore getVacuna() {
        return vacuna;
    }

    public void setVacuna(Semaphore vacuna) {
        this.vacuna = vacuna;
    }

    public Random getRnd() {
        return rnd;
    }

    public void setRnd(Random rnd) {
        this.rnd = rnd;
    }

    public Recepcion getR() {
        return r;
    }

    public void setR(Recepcion r) {
        this.r = r;
    }

    public Observacion getO() {
        return o;
    }

    public void setO(Observacion o) {
        this.o = o;
    }

    public Vacunacion getV() {
        return v;
    }

    public void setV(Vacunacion v) {
        this.v = v;
    }

    public Auxiliar1 getAux1() {
        return aux1;
    }

    public void setAux1(Auxiliar1 aux1) {
        this.aux1 = aux1;
    }

    public Auxiliar2 getAux2() {
        return aux2;
    }

    public void setAux2(Auxiliar2 aux2) {
        this.aux2 = aux2;
    }
        
        

}
