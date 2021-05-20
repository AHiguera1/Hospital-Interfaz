/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hosp;
import java.rmi.*;
import java.util.ArrayList;

/**
 *
 * @author jorge
 */
public interface InterfazRMI extends Remote {
    PuestoContainer getContainerV() throws RemoteException;
    PuestoContainer getContainerO() throws RemoteException;
    Recepcion getCola() throws RemoteException;
    ArrayList<Sanitario> getColaDescanso() throws RemoteException;
    int getVacunas() throws RemoteException;
    Paciente getPrimeroCola() throws RemoteException;
    void blockPuesto(int id) throws RemoteException;
    
    
    
}
