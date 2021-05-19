package hosp;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Observacion extends Thread{
	private Random rnd = new Random();
        //Pacientes no puede ser un ArrayList, pq entonces hay q añadirle puestos
        //tiene q ser un array estatico Puesto[20], y que los accesos sean siempre a 
        //una posicion concreta, mira los metodos addP() y removeP()
	private Puesto[] pacientes = new Puesto[20];
	List<Paciente> reaccion = Collections.synchronizedList(new ArrayList<>());
	Semaphore lleno = new Semaphore(20,true);
        private Interfaz it;
        
        public class Puesto{
            Paciente p = null;
            public Puesto(){
                
            }
            
            public Paciente getP(){
                return this.p;
            }
            public void setP(Paciente p){
                this.p = p;
            }
        }
        public Observacion(Interfaz it){
            this.it = it;
            for(int i = 0; i < 20;i++){
                pacientes[i] = new Puesto();
            }
        }
        
        public String puesto(Puesto p){
            if(p.getP() != null){ 
                if(p.getP().getS() == null){
                return p.getP().toString();
            }else{
                return p.getP().toString() + ", " + p.getP().getS().toString();
            }
        }
            return "\t";
    }
        @Override
        public void run(){
        while(true){
            //System.out.println(puesto(pacientes.get(0)));
            for (int i = 0;i < 20; i++){
                switch(i){
                    case 0:
                        it.getjTextField17().setText(puesto(pacientes[i]));
                   
                        break;
                    case 1:
                        it.getjTextField18().setText(puesto(pacientes[i]));
                        break;
                    case 2:
                        it.getjTextField19().setText(puesto(pacientes[i]));
                        break;
                    case 3:
                        it.getjTextField20().setText(puesto(pacientes[i]));
                        break;
                    case 4:
                        it.getjTextField21().setText(puesto(pacientes[i]));
                        break;
                    case 5:
                        it.getjTextField22().setText(puesto(pacientes[i]));
                        break;
                    case 6:
                        it.getjTextField23().setText(puesto(pacientes[i]));
                        break;
                    case 7:
                        it.getjTextField24().setText(puesto(pacientes[i]));
                        break;
                    case 8:
                        it.getjTextField25().setText(puesto(pacientes[i]));
                        break;
                    case 9:
                        it.getjTextField26().setText(puesto(pacientes[i]));
                        break;
                    case 10:
                        it.getjTextField28().setText(puesto(pacientes[i]));
                        break;
                    case 11:
                        it.getjTextField29().setText(puesto(pacientes[i]));
                        break;
                    case 12:
                        it.getjTextField30().setText(puesto(pacientes[i]));
                        break;
                    case 13:
                        it.getjTextField31().setText(puesto(pacientes[i]));
                        break;
                    case 14:
                        it.getjTextField32().setText(puesto(pacientes[i]));
                        break;
                    case 15:
                        it.getjTextField33().setText(puesto(pacientes[i]));
                        break;
                    case 16:
                        it.getjTextField34().setText(puesto(pacientes[i]));
                        break;
                    case 17:
                        it.getjTextField35().setText(puesto(pacientes[i]));
                        break;
                    case 18:
                        it.getjTextField36().setText(puesto(pacientes[i]));
                        break;
                    case 19:
                        it.getjTextField27().setText(puesto(pacientes[i]));
                        break;
                }

            }
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

    }
    public boolean addP(Paciente p){
        
        for(Puesto a : pacientes){
            if(a.getP() == null){
                a.setP(p);
                return true;
            }
        }
        return false;
    }
    
    public void removeP(Paciente p){
        for(Puesto a : pacientes){
            if(a.getP() == p){
                a.setP(null);
            }
        }
        
    }
    
       
	public boolean observar(Paciente p) {
		try {
			lleno.acquire();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(!addP(p)){}
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int a = rnd.nextInt(20);
		if(a > 0) {	
			return false;
		}else {
			reaccion.add(p);
			return true;
		}
		
	}

	public boolean atender(Sanitario s) {
		if(!reaccion.isEmpty()) {
			Paciente p = reaccion.get(0);
			p.setS(s);
			p.getSalir().release();
			reaccion.remove(0);
			
			try {Logger.log("Paciente " + p.toString() + " sufre una reaccion y es atendido por " + s.toString());
				Thread.sleep(2000 + (long)(rnd.nextInt(3000)));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			removeP(p);
			lleno.release();
			return true;
		}
                return false;
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
