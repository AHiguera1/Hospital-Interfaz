package hosp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Auxiliar1 extends Thread implements Serializable {
    private Random rnd = new Random();
    private Vacunacion vc;
    private Recepcion recepcion;
    private Interfaz it;
    private Paciente p;

    public Auxiliar1(Vacunacion vc, Recepcion recepcion, Interfaz it) {
        this.vc = vc;
        this.recepcion = recepcion;
        this.it = it;

    }

    public void run() {
        int cont = 0;
        it.getjTextField3().setText("A1");
        while (true) {
            if (!recepcion.getCola().isEmpty()) {
                p = recepcion.getCola().poll();
                it.getjTextField2().setText(p.toString());
                String str = "";
                ArrayList<Paciente> aux = new ArrayList<>();
                aux.addAll(recepcion.getCola());
                for (Paciente e : aux) {
                    str += e.toString();
                    str += " ";
                }
                it.getjTextField1().setText(str);
                if (checkCita()) {
                    try {
                        vc.getLibre().acquire();
                        int a = vc.getContainer().add(p);
                        while (a == -1)
                            a = vc.getContainer().add(p);
                        boolean b = vc.getContainer().get(a).getS() == null;
                        while (b)
                            b = vc.getContainer().get(a).getS() == null;
                        vc.getContainer().get(a).getS().getVacunando().release(); // Empieza a vacunar
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    Logger.log("Paciente " + p.toString() + " ha acudido sin cita");
                    String msg = "Paciente " + p.toString() + " ha salido del hospital";
                    p.dispose();
                    Logger.log(msg);
                }
                cont++;
            }
            if (cont == 10) {
                cont = 0;
                Logger.log("Auxiliar A1 comienza su descanso");
                it.getjTextField2().setText("(Descanso)");
                try {
                    sleep(3000 + (long) (rnd.nextInt(2000)));
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
            }
        }
    }

    public void printVacuna(Sanitario s) {

        Logger.log("Paciente " + vc.getContainer().get(vc.getContainer().getPuesto(s)).getP().toString()
                + " vacunado en el puesto " + Integer.toString(vc.getContainer().getPuesto(s)) + " por "
                + s.toString());
    }

    public boolean checkCita() {
        try {
            sleep(500 + (long) (rnd.nextInt(501)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int a = rnd.nextInt(100);
        if (a > 0) {
            return true;
        } else {
            return false;
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

    public Recepcion getRecepcion() {
        return recepcion;
    }

    public void setRecepcion(Recepcion recepcion) {
        this.recepcion = recepcion;
    }

    public Interfaz getIt() {
        return it;
    }

    public void setIt(Interfaz it) {
        this.it = it;
    }

    public Paciente getP() {
        return p;
    }

    public void setP(Paciente p) {
        this.p = p;
    }

}
