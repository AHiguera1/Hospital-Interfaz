	package hosp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;

public class Sanitario extends Thread{
	private int Sid;
	private Random rnd = new  Random();
	private Vacunacion vc;
	private Auxiliar1 aux1;
	private Semaphore vacuna;
	private Semaphore vacunando = new Semaphore(0);
	private Observacion obser;
	private List<Sanitario> descanso;
        private Interfaz it;
        private int cont = 0;

	
	public Sanitario(int id,Vacunacion vc,Semaphore vacuna,Auxiliar1 aux1,Observacion obser,ArrayList<Sanitario> descanso, Interfaz it) {
		this.vc = vc;
                this.it = it;
		this.vacuna = vacuna;
		this.aux1 = aux1;
		this.obser = obser;
		this.Sid = id;
		this.descanso = descanso;
	}
	
	public void run() {
            try {
                descanso.add(this);
                it.getjTextField4().setText(descansando());
                sleep(1000 + (long)(rnd.nextInt(2000)));
                descanso.remove(this);
                it.getjTextField4().setText(descansando());
                while(true) {
                    try {                       
                        vc.getContainer().add(this);
                        while(cont < 15) {
                            vacunando.acquire(); //Espera a que llegue el paciente al puesto
                            vacuna.acquire();
                            it.getjTextField11().setText(Integer.toString(vacuna.availablePermits()));
                            cont++;
                            sleep(3000 + (long)(rnd.nextInt(2000)));
                            vc.getLibre().release();
                            int a = vc.getContainer().getPuesto(this);
                            vc.getContainer().get(a).getP().getVacc().release();
                            aux1.printVacuna(this);
                            vc.getContainer().remove(vc.getContainer().get(a).getP());
                        }
                        cont = 0;
                        Logger.log("Sanitario " + this.toString() + " comienza su descanso");
                        vc.getContainer().remove(this);
                        it.getjTextField4().setText(descansando());
                        //END vacunacion
//================================================================================================================================
                    sleep(5000 + (long)(rnd.nextInt(3000)));
                    obser.atender(this);

                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            } catch (InterruptedException ex) {
                java.util.logging.Logger.getLogger(Sanitario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
	@Override
	public String toString() {
		return "S" + this.Sid;
	}
	public synchronized String  descansando(){
            String str = "";
            for(int i = 0; i < descanso.size(); i++){
                str += descanso.get(i).toString();
                str += " ";
            }
            return str;
        }

    public Auxiliar1 getAux1() {
        return aux1;
    }

    public void setAux1(Auxiliar1 aux1) {
        this.aux1 = aux1;
    }

    public Observacion getObser() {
        return obser;
    }

    public void setObser(Observacion obser) {
        this.obser = obser;
    }

    public List<Sanitario> getDescanso() {
        return descanso;
    }

    public void setDescanso(List<Sanitario> descanso) {
        this.descanso = descanso;
    }

    public Interfaz getIt() {
        return it;
    }

    public void setIt(Interfaz it) {
        this.it = it;
    }

    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
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

	public Semaphore getVacuna() {
		return vacuna;
	}

	public void setVacuna(Semaphore vacuna) {
		this.vacuna = vacuna;
	}


	

}
