package hosp;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;

public class Paciente extends Thread{
	private int Pid;
	private Recepcion recepcion;
	private Semaphore vacc = new Semaphore(0);
	private Semaphore salir = new Semaphore(0);
	private Observacion obser;
	private Sanitario s = null;
	
	public Paciente(Recepcion recepcion, int id,Observacion obser) {
		this.recepcion = recepcion;
		this.Pid = id;
		this.obser = obser;
 	}
	
	@Override
	public String toString() {
		return "P" + this.Pid;
	}
	
	public void run() {		
		this.recepcion.entraCola(this);
		try {
			vacc.acquire();
			if(obser.observar(this)) {
				salir.acquire();
			}
                        if(obser.getPacientes().contains(this)) obser.getPacientes().remove(this);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String msg = "Paciente " + this.toString() + " ha salido del hospital";
		Logger.log(msg);
		
	}
        public void dispose(){
            try{
                recepcion = null;
                vacc = null;
                salir = null;
                obser = null;
                s = null;
                try {
                    this.stop();
                    this.finalize();                    
                } catch (Throwable ex) {
                    java.util.logging.Logger.getLogger(Paciente.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.gc();
            }catch(Exception e){e.printStackTrace();}
           
        }

	public Semaphore getSalir() {
		return salir;
	}

	public void setSalir(Semaphore salir) {
		this.salir = salir;
	}

	public Observacion getObser() {
		return obser;
	}

	public void setObser(Observacion obser) {
		this.obser = obser;
	}

	public int getPid() {
		return Pid;
	}

	public void setPid(int id) {
		this.Pid = id;
	}

	public Recepcion getRecepcion() {
		return recepcion;
	}

	public void setRecepcion(Recepcion recepcion) {
		this.recepcion = recepcion;
	}

	public Semaphore getVacc() {
		return vacc;
	}

	public void setVacc(Semaphore vacc) {
		this.vacc = vacc;
	}

	public Sanitario getS() {
		return s;
	}

	public void setS(Sanitario s) {
		this.s = s;
	}
	
}

