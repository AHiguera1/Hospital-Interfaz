package hosp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Observacion {
	Random rnd = new Random();
	List<Paciente> pacientes = Collections.synchronizedList(new ArrayList<Paciente>());
	List<Paciente> reaccion = Collections.synchronizedList(new ArrayList<Paciente>());
	Semaphore lleno = new Semaphore(20,true);
	
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

	public void atender(Sanitario s) {
		if(!reaccion.isEmpty()) {
			Paciente p = reaccion.get(0);
			p.setS(s);
			p.getSalir().release();
			reaccion.remove(0);
			System.out.println("Paciente " + p.toString() + " sufre una reaccion y es atendido por " + s.toString());
			Logger.log("Paciente " + p.toString() + " sufre una reaccion y es atendido por " + s.toString());
			try {
				Thread.sleep(2000 + (long)(rnd.nextInt(3000)));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pacientes.remove(p);
			lleno.release();
			
		}
	}
}
