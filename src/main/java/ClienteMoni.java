
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import static java.rmi.server.RemoteServer.getClientHost;
import java.rmi.server.ServerNotActiveException;
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
public class ClienteMoni {
    public ClienteMoni() throws RemoteException
    {
        
        super();
        
    }
    

    public static double id;
    
    
    public double pingMonitor() throws RemoteException, ServerNotActiveException
    {


        return id;
    }
    
    

    //MAIN

    public static void main(String args[]) throws RemoteException, NotBoundException, MalformedURLException, IOException, ServerNotActiveException
    {

        String Opcion;
        Opcion = JOptionPane.showInputDialog("Introduzca el tipo de cliente.\n1)Cliente Comun\n2)Monitor.");
        
      
        String identify; 
        identify = JOptionPane.showInputDialog("Por favor introduzca la ip del Coordinador");
        InterfaceRemota OBJdeInterface = (InterfaceRemota) Naming.lookup("//" + identify + "/Coordinador");
        
      
        int Op = Integer.parseInt(Opcion);
        

        switch(Op) 
        {
            case 1:
               
                 JOptionPane.showMessageDialog(null, "Hola este es el area del cliente");

        
            //Area del cliente       
            String Opcion2;

           
            int numMonitaux = -1;
                                  do
                                        {
                                           Opcion2 = JOptionPane.showInputDialog("Introduzca la accion a realizar en el cliente.\n1)Registrar el cliente y hacer ping.\n2)Obtener la ultima carga del cpu\n.3)Salir");

                                          switch(Integer.parseInt(Opcion2)) 
                                          {

                                              case 1:

                                                      //REGISTRAR CLIENTE 
                                                      
                                                  
                                                      int NumMoni = OBJdeInterface.iniClient();
                                                      numMonitaux = NumMoni;
                                                     JOptionPane.showMessageDialog(null, "Numeros de monitores que estan activos " + NumMoni);
                                                     
                                                  break;


                                              case 2:


                                                        //OBTENER LA CARGA DE LA CPU
                                                        if(numMonitaux<0){
                                                            JOptionPane.showMessageDialog(null, "Debe obtener el numero de monitores primero");

                                                        }
                                                        else if(numMonitaux > 0)
                                                        {
                                                          
                                                            String CargaCPU = OBJdeInterface.getLoadAvg();
                                                            JOptionPane.showMessageDialog(null, "Ultima carga Obtenida : " + CargaCPU);
                                                        }//Cierre del if
                                                        else
                                                        {
                                                             JOptionPane.showMessageDialog(null, "No se encuentran monitores disponibles");

                                                        }
                                                          
                                                  break;


                                          }
                          
                                        }while(Integer.parseInt(Opcion2)!=3);
                break;
        
            case 2:
                
                    ///////////////////////////////////////////////////////
                 //Main del Monitor
                double idaux = OBJdeInterface.iniMonitor();
                id = idaux;
                thread hilo = new thread(OBJdeInterface);
                hilo.start();
                
                break;
            
            
            default:
                
                   JOptionPane.showMessageDialog(null, "Opcion incorrecta ");
      
                break;
        
        }
        
            
        }
}
