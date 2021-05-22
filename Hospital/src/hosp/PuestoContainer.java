/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hosp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author jorge
 */
public class PuestoContainer implements Serializable {
    private Map<Integer, Puesto> container = new ConcurrentHashMap<>();
    private Interfaz it;
    private final boolean mode;

    public void initialize10() {
        for (int i = 1; i < 11; i++) {
            this.container.put(i, new Puesto(i));
        }
    }

    public void initialize20() {
        for (int i = 1; i < 21; i++) {
            this.container.put(i, new Puesto(i));
        }
    }

    public PuestoContainer(Interfaz it, boolean mode) {
        this.it = it;
        if (mode)
            initialize10();
        else
            initialize20();
        this.mode = mode;

    }

    public String puestoStatus(Puesto p) {
        String str = "";
        if (p.isBlocked())
            str = "{Cerrado}";
        else {
            boolean a = p.getP() == null;
            boolean b = p.getS() == null;
            if (a) {
                if (!b)
                    str += p.getS().toString();
            } else {
                if (b)
                    str += p.getP().toString();
                else
                    str += p.getS().toString() + ", " + p.getP().toString();
            }

        }
        return str;
    }

    public void printContainer20() {
        for (int i = 0; i < 20; i++) {
            switch (i) {
                case 0:
                    it.tf.get(16).setText("");
                    it.tf.get(16).setText(puestoStatus(container.get(i + 1)));
                    break;
                case 1:
                    it.tf.get(17).setText("");
                    it.tf.get(17).setText(puestoStatus(container.get(i + 1)));
                    break;
                case 2:
                    it.tf.get(18).setText("");
                    it.tf.get(18).setText(puestoStatus(container.get(i + 1)));
                    break;
                case 3:
                    it.tf.get(19).setText("");
                    it.tf.get(19).setText(puestoStatus(container.get(i + 1)));
                    break;
                case 4:
                    it.tf.get(20).setText("");
                    it.tf.get(20).setText(puestoStatus(container.get(i + 1)));
                    break;
                case 5:
                    it.tf.get(21).setText("");
                    it.tf.get(21).setText(puestoStatus(container.get(i + 1)));
                    break;
                case 6:
                    it.tf.get(22).setText(puestoStatus(container.get(i + 1)));
                    break;
                case 7:
                    it.tf.get(23).setText("");
                    it.tf.get(23).setText(puestoStatus(container.get(i + 1)));
                    break;
                case 8:
                    it.tf.get(24).setText("");
                    it.tf.get(24).setText(puestoStatus(container.get(i + 1)));
                    break;
                case 9:
                    it.tf.get(25).setText("");
                    it.tf.get(25).setText(puestoStatus(container.get(i + 1)));
                    break;
                case 10:
                    it.tf.get(27).setText("");
                    it.tf.get(27).setText(puestoStatus(container.get(i + 1)));
                    break;
                case 11:
                    it.tf.get(28).setText("");
                    it.tf.get(28).setText(puestoStatus(container.get(i + 1)));
                    break;
                case 12:
                    it.tf.get(29).setText("");
                    it.tf.get(29).setText(puestoStatus(container.get(i + 1)));
                    break;
                case 13:
                    it.tf.get(30).setText("");
                    it.tf.get(30).setText(puestoStatus(container.get(i + 1)));
                    break;
                case 14:
                    it.tf.get(31).setText("");
                    it.tf.get(31).setText(puestoStatus(container.get(i + 1)));
                    break;
                case 15:
                    it.tf.get(32).setText("");
                    it.tf.get(32).setText(puestoStatus(container.get(i + 1)));
                    break;
                case 16:
                    it.tf.get(33).setText("");
                    it.tf.get(33).setText(puestoStatus(container.get(i + 1)));
                    break;
                case 17:
                    it.tf.get(34).setText("");
                    it.tf.get(34).setText(puestoStatus(container.get(i + 1)));
                    break;
                case 18:
                    it.tf.get(35).setText("");
                    it.tf.get(35).setText(puestoStatus(container.get(i + 1)));
                    break;
                case 19:
                    it.tf.get(26).setText("");
                    it.tf.get(26).setText(puestoStatus(container.get(i + 1)));
                    break;
            }

        }

    }

    public void printContainer10() {
        for (int i = 0; i < 10; i++) {
            switch (i) {
                case 0:
                    it.tf.get(4).setText("");
                    it.tf.get(4).setText(puestoStatus(container.get(i + 1)));
                    break;
                case 1:
                    it.tf.get(5).setText("");
                    it.tf.get(5).setText(puestoStatus(container.get(i + 1)));
                    break;
                case 2:
                    it.tf.get(6).setText("");
                    it.tf.get(6).setText(puestoStatus(container.get(i + 1)));
                    break;
                case 3:
                    it.tf.get(7).setText("");
                    it.tf.get(7).setText(puestoStatus(container.get(i + 1)));
                    break;
                case 4:
                    it.tf.get(8).setText("");
                    it.tf.get(8).setText(puestoStatus(container.get(i + 1)));
                    break;
                case 5:
                    it.tf.get(11).setText("");
                    it.tf.get(11).setText(puestoStatus(container.get(i + 1)));
                    break;
                case 6:
                    it.tf.get(12).setText("");
                    it.tf.get(12).setText(puestoStatus(container.get(i + 1)));
                    break;
                case 7:
                    it.tf.get(13).setText("");
                    it.tf.get(13).setText(puestoStatus(container.get(i + 1)));
                    break;
                case 8:
                    it.tf.get(14).setText("");
                    it.tf.get(14).setText(puestoStatus(container.get(i + 1)));
                    break;
                case 9:
                    it.tf.get(15).setText("");
                    it.tf.get(15).setText(puestoStatus(container.get(i + 1)));
                    break;

            }

        }

    }

    public void addS(int id, Sanitario s) {
        this.container.get(id).setS(s);
        if (mode)
            printContainer10();
        else
            printContainer20();
    }

    public int add(Sanitario s) {
        ArrayList<Puesto> aux = new ArrayList<>(container.values());
        if (mode) {
            for (Puesto p : aux) {
                if (p.getS() == null) {
                    p.setS(s);
                    printContainer10();
                    return p.getId();
                }
            }
        } else {
            for (Puesto p : aux) {
                if (p.getS() == null) {
                    p.setS(s);
                    printContainer20();
                    return p.getId();
                }
            }
        }
        return -1;
    }

    public int add(Paciente pa) {
        ArrayList<Puesto> aux = new ArrayList<>(container.values());
        if (mode) {
            for (Puesto p : aux) {
                if (p.getP() == null && p.getS() != null) {
                    p.setP(pa);
                    printContainer10();
                    return p.getId();
                }
            }
        } else {
            for (Puesto p : aux) {
                if (p.getP() == null) {
                    p.setP(pa);
                    printContainer20();
                    return p.getId();
                }
            }
        }
        return -1;
    }

    public void remove(Sanitario s) {
        ArrayList<Puesto> aux = new ArrayList<>(container.values());
        if (mode) {
            for (Puesto p : aux) {
                if (p.getS() == s) {
                    p.setBlocked(false);
                    p.setS(null);
                    printContainer10();
                    return;
                }
            }
        } else {
            for (Puesto p : aux) {
                if (p.getS() == s) {
                    p.setS(null);
                    printContainer20();
                    return;
                }
            }
        }
    }

    public void remove(Paciente pa) {
        ArrayList<Puesto> aux = new ArrayList<>(container.values());
        if (mode) {
            for (Puesto p : aux) {
                if (p.getP() == pa) {
                    p.setP(null);
                    printContainer10();
                    return;
                }
            }
        } else {
            for (Puesto p : aux) {
                if (p.getP() == pa) {
                    p.setP(null);
                    printContainer20();
                    return;
                }
            }
        }
    }

    public Puesto get(int id) {
        return this.container.get(id);
    }

    public void blockPuesto(int id) {
        this.container.get(id).setBlocked(true);
        this.container.get(id).setS(null);
        this.container.get(id).setP(null);
    }

    public int getPuesto(Sanitario s) {
        ArrayList<Puesto> aux = new ArrayList<>(container.values());
        for (Puesto p : aux) {
            if (p.getS() == s)
                return p.getId();
        }
        return -1;
    }

    public int getPuesto(Paciente pa) {
        ArrayList<Puesto> aux = new ArrayList<>(container.values());
        for (Puesto p : aux) {
            if (p.getP() == pa)
                return p.getId();
        }
        return -1;
    }

    public Map<Integer, Puesto> getContainer() {
        return container;
    }

    public void setContainer(Map<Integer, Puesto> container) {
        this.container = container;
    }

    public Interfaz getIt() {
        return it;
    }

    public void setIt(Interfaz it) {
        this.it = it;
    }

}
