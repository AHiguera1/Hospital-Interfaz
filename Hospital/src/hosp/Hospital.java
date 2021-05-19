package hosp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Hospital extends Thread{

    private Interfaz it;
    private ArrayList<Sanitario> descanso;
    private Semaphore vacuna;
    private Random rnd;
    private Recepcion r;
    private Observacion o;
    private Vacunacion v;
    private Auxiliar1 aux1;
    private Auxiliar2 aux2;
	
    public Hospital (Interfaz it)
    {
        Logger.createLogger();
        this.it = it;
        descanso = new ArrayList<Sanitario>();
        vacuna = new Semaphore(0);
        rnd = new Random();
        r = new Recepcion();
        o = new Observacion();
        v = new Vacunacion();
        aux1 = new Auxiliar1(v,r,it);
        aux2 = new Auxiliar2(v,vacuna);
            
    }
        
        public void run(){
		
		aux1.start();
		aux2.start();
		for(int i = 0; i < 10; i++) {
			new Sanitario(i,v,vacuna,aux1,o,descanso).start();
		}
		for(int i = 0; i < 50; i++) {
			new Paciente(r,i,o).start();
			try {
				Thread.sleep(1000 + (long)(rnd.nextInt(2000)));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
        
        

}
