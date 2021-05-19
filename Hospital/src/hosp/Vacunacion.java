package hosp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Semaphore;





public class Vacunacion {
	private Map<Paciente,Sanitario> vacc = new HashMap<Paciente,Sanitario>();
	Queue<Sanitario> san = new ArrayBlockingQueue<Sanitario>(10);
	private Semaphore libre = new Semaphore(10,true);
	
	public Vacunacion() {
	
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

	public Map<Paciente, Sanitario> getVacc() {
		return vacc;
	}

	public void setVacc(Map<Paciente, Sanitario> vacc) {
		this.vacc = vacc;
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
