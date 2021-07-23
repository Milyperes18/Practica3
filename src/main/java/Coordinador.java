import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.io.WriteAbortedException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author milu
 */
public class Coordinador extends UnicastRemoteObject implements InterfaceRemota, Serializable 
{

    public List<Integer> ListNumeMon = new ArrayList<>();

    public List<Double> ListSectPrinc = new ArrayList<>();

    public List<Double> ListTemp = new ArrayList<>();
    
    public String VarString;

    //Constructor
   public Coordinador() throws RemoteException
   {
       super();
   }

    @Override
    public double iniMonitor() throws RemoteException, IOException, WriteAbortedException, NotSerializableException 
    {
    
        double valorDado;
        valorDado = Math.floor(Math.random()*6666+1);
        ListSectPrinc.add(valorDado);

        return valorDado;
        
    }   
    
    @Override
    public void loadMonitor(String VarSectP) throws RemoteException
    {
        this.VarString = VarSectP;  
        
    }
    
    
    @Override
    public int iniClient() throws RemoteException, IOException, WriteAbortedException, NotSerializableException, ServerNotActiveException
        {
        
        ClienteMoni ObjMon = new ClienteMoni();
        double Dispositivo = 0;        

        if(!(this.VarString == null)){
            
             Dispositivo = ObjMon.pingMonitor();
            }else{
            
                if(this.ListNumeMon.size()> 0){
               
                    JOptionPane.showMessageDialog(null, ListNumeMon.size());
                    this.ListNumeMon.add(0, (ListTemp.size()-1));
                }
                
            }
            
            if(!(Dispositivo == 0)){
             
                if(!(ListTemp.contains(Dispositivo))){
                ListTemp.add(Dispositivo);      
                this.ListNumeMon.add(0, ListTemp.size());
                }
             }

        if (this.ListNumeMon.size() <= 0) {
            ListSectPrinc.removeAll(ListSectPrinc);            
        } else {

            for(int Conta=0; Conta < ListSectPrinc.size(); Conta++){
                if(!(ListTemp.contains(ListSectPrinc.get(Conta)))){
                ListSectPrinc.remove(Conta);
                break;
                }

            }
        }
        
        if(ListSectPrinc.size()<=0){
        
            if(!(this.ListNumeMon.isEmpty())){
            this.VarString = "";
            this.ListNumeMon.remove(0);
            }
        }

        int ValordeAuxilio = 1;
        
        if(this.ListNumeMon.size()>0){
            
            ValordeAuxilio = this.ListNumeMon.get(0);
        
        }
        return ValordeAuxilio;
        
    }

    /**
     *
     * @return
     * @throws RemoteException
     */
    @Override
     public String getLoadAvg() throws RemoteException {
        
        return this.VarString;


    }    

     
    public static void main(String args[]) throws RemoteException 
    {
        try 
        {           //variable
            Registry VarRegistro = LocateRegistry.createRegistry(1099);
            VarRegistro.rebind("Coordinador", new Coordinador());
            JOptionPane.showMessageDialog(null, "En ejecucion!!!");
        }
        catch(RemoteException ValorExcepcion)
        {
                        System.out.println("");
        }
        
    }
    
}
