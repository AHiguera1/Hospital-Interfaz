package hosp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Auxiliar1 extends Thread {
	private Random rnd = new Random();
	private Vacunacion vc;
	private Recepcion recepcion;
	private int id = 1;
        private Interfaz it;
	
	public Auxiliar1(Vacunacion vc, Recepcion recepcion, Interfaz it) {
		this.vc = vc;
		this.recepcion = recepcion;
                this.it = it;
	
	}
	
	public void run() {
		int cont = 0;
                it.getjTextField3().setText("A1");
		while(true) {
			if(!recepcion.getCola().isEmpty()) {
				Paciente p = recepcion.getCola().poll();
                                it.getjTextField2().setText(p.toString());
                                String str = "";
                                ArrayList<Paciente> aux = new ArrayList<>();
                                aux.addAll(recepcion.getCola());
                            for (Paciente e : aux){                                
                                str += e.toString();      
                                str += " ";
                                
                            }
                                           
                            it.getjTextField1().setText(str);
				if(checkCita()) {
					try {
						vc.getLibre().acquire();
						int next = vc.nextPuesto();
						while(next == -1) { next = vc.nextPuesto();}
						vc.getPuesto(next).setP(p);
						vc.getPuesto(next).getVacunando().release();
						
						
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
					
					
					
				}else {
					
					
					Logger.log("Paciente " + p.toString() + " ha acudido sin cita");
					String msg = "Paciente " + p.toString() + " ha salido del hospital";
					p.dispose();
					Logger.log(msg);
				}
				cont++;
                                //System.out.println("A1 " + cont);
			
			}
			if(cont == 10) {
                            cont = 0;
                            System.out.println("Auxiliar A1 comienza su descanso");
                            Logger.log("Auxiliar A1 comienza su descanso");
                            it.getjTextField2().setText("(Descanso)");
                            try {
				sleep(3000 + (long)(rnd.nextInt(2000)));
                            } catch (InterruptedException e) {
					
				e.printStackTrace();
                            }
			}
		}
	}
	
	public void printVacuna(Sanitario s) {
		
		Logger.log("Paciente " + s.getP().toString() + " vacunado en el puesto " + Integer.toString(vc.getPuesto(s)) + " por " + s.toString());
	}
	
	
	public boolean checkCita() {
		try {
			sleep(500 + (long)(rnd.nextInt(501)));
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
		
		int a = rnd.nextInt(100);
		if(a > 0) {
			return true;
		}else {return false;}
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
