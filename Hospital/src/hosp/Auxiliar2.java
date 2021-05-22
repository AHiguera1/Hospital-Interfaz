package hosp;

import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Auxiliar2 extends Thread implements Serializable{
	private Random rnd = new Random();
	private Vacunacion vc;
	private Semaphore vacuna;
        private Interfaz it;
	
	public Auxiliar2(Vacunacion vc, Semaphore vacuna,Interfaz it) {
		this.vc = vc;
		this.vacuna = vacuna;
                this.it = it;
                it.getjTextField10().setText("A2");
	}
	
	public void run() {
		try {
			int cont = 0;
			while(true) {
				if(cont == 20) {
                                        Logger.log("Auxiliar A2 comienza su descanso");
					sleep(1000 + (long)(rnd.nextInt(3000)));
					cont = 0;				
				}else {
					sleep(500 + (long)(rnd.nextInt(500)));
					vacuna.release();
                                        it.getjTextField11().setText(Integer.toString(vacuna.availablePermits()));
					Logger.log("Auxiliar A2 prepara una vacuna");
					cont++;
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	

	
	public Random getRnd() {
		return rnd;
	}
	
	public void setRnd(Random rnd) {
		this.rnd = rnd;
	}

    public Vacunacion getVc() {
        return vc;
    }

    public void setVc(Vacunacion vc) {
        this.vc = vc;
    }

    public Semaphore getVacuna() {
        return vacuna;
    }

    public void setVacuna(Semaphore vacuna) {
        this.vacuna = vacuna;
    }

    public Interfaz getIt() {
        return it;
    }

    public void setIt(Interfaz it) {
        this.it = it;
    }
}

