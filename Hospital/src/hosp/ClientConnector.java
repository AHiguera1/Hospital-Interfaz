/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hosp;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
/**
 *
 * @author jorge
 */
public class ClientConnector extends UnicastRemoteObject implements InterfazRMI {
    private Hospital h;
    public ClientConnector(Hospital h){
        this.h = h;
    }
    @Override
    public PuestoContainer getContainerV() throws RemoteException {
       return h.getV().getContainer();
    }

    @Override
    public PuestoContainer getContainerO() throws RemoteException {
       return h.getO().getContainer();
    }

    @Override
    public Recepcion getCola() throws RemoteException {
        return h.getR();
    }

    @Override
    public ArrayList<Sanitario> getColaDescanso() throws RemoteException {
        return h.getDescanso();
    }

    @Override
    public int getVacunas() throws RemoteException {
        return h.getAux2().getVacuna().availablePermits();
    }

    @Override
    public Paciente getPrimeroCola() throws RemoteException {
        return h.getAux1().getP();
    }


    @Override
    public void blockPuesto(int id) throws RemoteException {
        h.getV().blockPuesto(id);
    }
    
}
