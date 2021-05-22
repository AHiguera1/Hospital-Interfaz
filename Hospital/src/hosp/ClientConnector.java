/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hosp;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Semaphore;
/**
 *
 * @author jorge
 */
public class ClientConnector extends UnicastRemoteObject implements InterfazRMI  {
    private ArrayList<Sanitario> descanso;
    private Recepcion r;
    private Map<Integer,Puesto> o;
    private Map<Integer,Puesto> v;
    private Semaphore vacuna;
    Paciente p;
    public ClientConnector(ArrayList<Sanitario> descanso,Semaphore vacuna,Recepcion r,Observacion o,Vacunacion v,Auxiliar1 aux1){
        this.descanso = descanso;
        this.r = r;
        this.o = o.getContainer().getContainer();
        this.v = v.getContainer().getContainer();
        this.p = aux1.getP();
        
    }
   
    

    @Override
    public Recepcion getCola() throws RemoteException {
        return this.r;
    }

    @Override
    public ArrayList<Sanitario> getColaDescanso() throws RemoteException {
        return this.descanso;
    }

    @Override
    public int getVacunas() throws RemoteException {
        return vacuna.availablePermits();
    }

    @Override
    public Paciente getPrimeroCola() throws RemoteException {
        return p;
    }

    @Override
    public Map<Integer, Puesto> getContainerV() throws RemoteException {
       return v;
    }

    @Override
    public Map<Integer, Puesto> getContainerO() throws RemoteException {
        return o;
    }

    
    //public void blockPuesto(int id) throws RemoteException {
    //    v.blockPuesto(id);
    //}

    public ArrayList<Sanitario> getDescanso() {
        return descanso;
    }

    public void setDescanso(ArrayList<Sanitario> descanso) {
        this.descanso = descanso;
    }

    public Recepcion getR() {
        return r;
    }

    public void setR(Recepcion r) {
        this.r = r;
    }

    public Semaphore getVacuna() {
        return vacuna;
    }

    public void setVacuna(Semaphore vacuna) {
        this.vacuna = vacuna;
    }

    public Paciente getP() {
        return p;
    }

    public void setP(Paciente p) {
        this.p = p;
    }

    public void setO(Map<Integer, Puesto> o) {
        this.o = o;
    }

    public void setV(Map<Integer, Puesto> v) {
        this.v = v;
    }

    public Map<Integer, Puesto> getO() {
        return o;
    }

    public Map<Integer, Puesto> getV() {
        return v;
    }

    

    
}
