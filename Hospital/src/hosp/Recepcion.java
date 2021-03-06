package hosp;

import java.io.Serializable;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Recepcion implements Serializable {

	private Queue<Paciente> cola = new ConcurrentLinkedQueue<Paciente>();

	public void entraCola(Paciente p) {
		cola.add(p);
	}

	public Queue<Paciente> getCola() {
		return cola;
	}

	public void setCola(Queue<Paciente> cola) {
		this.cola = cola;
	}

}
