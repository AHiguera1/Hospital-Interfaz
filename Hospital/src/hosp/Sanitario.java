package hosp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Sanitario extends Thread{
	private int Sid;
	private Random rnd = new  Random();
	private Vacunacion vc;
	private Paciente p;
	private Auxiliar1 aux1;
	private Semaphore vacuna;
	private Semaphore vacunando = new Semaphore(0);
	private Observacion obser;
	List<Sanitario> descanso;

	
	public Sanitario(int id,Vacunacion vc,Semaphore vacuna,Auxiliar1 aux1,Observacion obser,ArrayList<Sanitario> descanso) {
		this.vc = vc;
		this.vacuna = vacuna;
		this.aux1 = aux1;
		this.obser = obser;
		this.Sid = id;
		this.descanso = descanso;
	}
	
	public void run() {
		while(true) {
			try {
				descanso.add(this);
				sleep(1000 + (long)(rnd.nextInt(2000)));
				descanso.remove(this);
				vc.getSan().add(this);
				int cont = 0;
				while(cont != 15) {	
					vacunando.acquire();
					vacuna.acquire();
					cont++;
					sleep(3000 + (long)(rnd.nextInt(2000)));
					vc.getLibre().release();
					p.getVacc().release();
					aux1.printVacuna(this);
					p = null;
				}
				System.out.println("Sanitario" + this.toString() + " comienza su descanso");
				Logger.log("Sanitario" + this.toString() + " comienza su descanso");
				vc.getSan().remove(this);
				sleep(5000 + (long)(rnd.nextInt(3000)));
				obser.atender(this);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();	
			}
		}
		
		
		
	}
	@Override
	public String toString() {
		return "S" + this.Sid;
	}
	

	public Semaphore getVacunando() {
		return vacunando;
	}

	public void setVacunando(Semaphore vacunando) {
		this.vacunando = vacunando;
	}

	public int getSid() {
		return Sid;
	}

	public void setSid(int id) {
		this.Sid = id;
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

	public Paciente getP() {
		return p;
	}

	public void setP(Paciente p) {
		this.p = p;
	}

	public Semaphore getVacuna() {
		return vacuna;
	}

	public void setVacuna(Semaphore vacuna) {
		this.vacuna = vacuna;
	}


	

}
