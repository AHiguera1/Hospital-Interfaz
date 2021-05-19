package hosp;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;

public class Observacion extends Thread{
	private Random rnd = new Random();
	List<Paciente> pacientes = Collections.synchronizedList(new ArrayList<Paciente>());
	List<Paciente> reaccion = Collections.synchronizedList(new ArrayList<Paciente>());
	Semaphore lleno = new Semaphore(20,true);
        private Interfaz it;
        public Observacion(Interfaz it){
            this.it = it;
        }
        
        public String puesto(Paciente p){
        if(p.getS() == null){
            return p.toString();
        }else{
            return p.toString() + ", " + p.getS().toString();
        }
    }
        public void run(){
        while(true){
            for (int i = 0;i < pacientes.size(); i++){
                switch(i){
                    case 0:
                        it.getjTextField17().setText(puesto(pacientes.get(i)));
                        break;
                    case 1:
                        it.getjTextField18().setText(puesto(pacientes.get(i)));
                        break;
                    case 2:
                        it.getjTextField19().setText(puesto(pacientes.get(i)));
                        break;
                    case 3:
                        it.getjTextField20().setText(puesto(pacientes.get(i)));
                        break;
                    case 4:
                        it.getjTextField21().setText(puesto(pacientes.get(i)));
                        break;
                    case 5:
                        it.getjTextField22().setText(puesto(pacientes.get(i)));
                        break;
                    case 6:
                        it.getjTextField23().setText(puesto(pacientes.get(i)));
                        break;
                    case 7:
                        it.getjTextField24().setText(puesto(pacientes.get(i)));
                        break;
                    case 8:
                        it.getjTextField25().setText(puesto(pacientes.get(i)));
                        break;
                    case 9:
                        it.getjTextField26().setText(puesto(pacientes.get(i)));
                        break;
                    case 10:
                        it.getjTextField28().setText(puesto(pacientes.get(i)));
                        break;
                    case 11:
                        it.getjTextField29().setText(puesto(pacientes.get(i)));
                        break;
                    case 12:
                        it.getjTextField30().setText(puesto(pacientes.get(i)));
                        break;
                    case 13:
                        it.getjTextField31().setText(puesto(pacientes.get(i)));
                        break;
                    case 14:
                        it.getjTextField32().setText(puesto(pacientes.get(i)));
                        break;
                    case 15:
                        it.getjTextField33().setText(puesto(pacientes.get(i)));
                        break;
                    case 16:
                        it.getjTextField34().setText(puesto(pacientes.get(i)));
                        break;
                    case 17:
                        it.getjTextField35().setText(puesto(pacientes.get(i)));
                        break;
                    case 18:
                        it.getjTextField36().setText(puesto(pacientes.get(i)));
                        break;
                    case 19:
                        it.getjTextField27().setText(puesto(pacientes.get(i)));
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
       
	public boolean observar(Paciente p) {
		try {
			lleno.acquire();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		pacientes.add(p);
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
			Logger.log("Paciente " + p.toString() + " sufre una reaccion y es atendido por " + s.toString());
			try {
				Thread.sleep(2000 + (long)(rnd.nextInt(3000)));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pacientes.remove(p);
			lleno.release();
			return true;
		}
                return false;
	}
}
