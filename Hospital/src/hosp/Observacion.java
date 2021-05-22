package hosp;

import java.io.Serializable;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Observacion extends Thread implements Serializable{
	private Random rnd = new Random();
        //Pacientes no puede ser un ArrayList, pq entonces hay q añadirle puestos
        //tiene q ser un array estatico Puesto[20], y que los accesos sean siempre a 
        //una posicion concreta, mira los metodos addP() y removeP()
	private PuestoContainer container;
	List<Paciente> reaccion = Collections.synchronizedList(new ArrayList<>());
	Semaphore lleno = new Semaphore(20,true);
        private Interfaz it;
        
        
        public Observacion(Interfaz it){
            this.it = it;
            this.container = new PuestoContainer(it,false);
            
        }
        public boolean observar(Paciente p) {
		try {
			lleno.acquire(); //Semaforo para control de acceso maximo
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
                int a = this.container.add(p);
                while(a == -1) a = this.container.add(p);

		try {
			Thread.sleep(10000);
                        
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		a = rnd.nextInt(20);
		if(a > 0) {	
                        lleno.release();
			return false;                        
		}else {
			reaccion.add(p);
			return true;
		}
		
	}

	public boolean atender(Sanitario s) {
		if(!reaccion.isEmpty()) {
			Paciente p = reaccion.get(0);
                        int a = container.getPuesto(p);
                        while(a == -1) a = container.getPuesto(p);
                        container.addS(a,s);                            
			p.getSalir().release();
			reaccion.remove(0);			
			try {                               
                            Thread.sleep(2000 + (long)(rnd.nextInt(3000)));
                            Logger.log("Paciente " + p.toString() + " sufre una reaccion y es atendido por " + s.toString());
			} catch (InterruptedException e) {
                            e.printStackTrace();
			}
                        container.remove(s);
			container.remove(p);                        
			lleno.release();
			return true;
		}
                return false;
	}

    public PuestoContainer getContainer() {
        return container;
    }

    public void setContainer(PuestoContainer container) {
        this.container = container;
    }
        
       
    public Random getRnd() {
        return rnd;
    }

    public void setRnd(Random rnd) {
        this.rnd = rnd;
    }

 
    public List<Paciente> getReaccion() {
        return reaccion;
    }

    public void setReaccion(List<Paciente> reaccion) {
        this.reaccion = reaccion;
    }

    public Semaphore getLleno() {
        return lleno;
    }

    public void setLleno(Semaphore lleno) {
        this.lleno = lleno;
    }

    public Interfaz getIt() {
        return it;
    }

    public void setIt(Interfaz it) {
        this.it = it;
    }
        
}
