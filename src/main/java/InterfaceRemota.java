
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.WriteAbortedException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author milu
 */
public interface InterfaceRemota extends Remote {
    public double iniMonitor () throws RemoteException, IOException,WriteAbortedException, NotSerializableException;
    public void loadMonitor (String VarSectP) throws RemoteException;
    public int iniClient () throws RemoteException,IOException,WriteAbortedException, NotSerializableException,ServerNotActiveException;
    public String getLoadAvg () throws RemoteException;
}