package hosp;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Auxiliar2 extends Thread{
	private Random rnd = new Random();
	private Vacunacion vc;
	private Semaphore vacuna;
	
	private int id = 2;
	
	public Auxiliar2(Vacunacion vc, Semaphore vacuna) {
		this.vc = vc;
		this.vacuna = vacuna;
	}
	
	public void run() {
		try {
			int cont = 0;
			while(true) {
				if(cont == 20) {
					System.out.println("Auxiliar A2 comienza su descanso");
					Logger.log("Auxiliar A2 comienza su descanso");
					sleep(1000 + (long)(rnd.nextInt(3000)));
					cont = 0;				
				}else {
					sleep(500 + (long)(rnd.nextInt(500)));
					vacuna.release();
					System.out.println("Auxiliar A2 prepara una vacuna");
					Logger.log("Auxiliar A2 prepara una vacuna");
					cont++;
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	
	
	@Override
	public String toString() {
		return "A" + this.id;
	}
	
	public Random getRnd() {
		return rnd;
	}
	
	public void setRnd(Random rnd) {
		this.rnd = rnd;
	}
}

