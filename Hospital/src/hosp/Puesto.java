/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hosp;

import java.io.Serializable;

/**
 *
 * @author jorge
 */
public class Puesto implements Serializable {
    int id;
    Paciente p = null;
    Sanitario s = null;
    boolean blocked = false;

    public Puesto(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Paciente getP() {

        return p;
    }

    public void setP(Paciente p) {
        if (!blocked)
            this.p = p;
    }

    public Sanitario getS() {
        return s;
    }

    public void setS(Sanitario s) {
        if (!blocked)
            this.s = s;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

}
