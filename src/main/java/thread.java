
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class thread extends Thread
{
    
    InterfaceRemota OBJdeInterface;
    public double id;
    thread(InterfaceRemota OBJdeInterface)
    {
        this.OBJdeInterface = OBJdeInterface;
    }
    
    @Override
    public void run()
    {
           
            float Valor_de_rango2 = 0;
                float Valor_de_rango = Float.parseFloat(JOptionPane.showInputDialog("Por favor introduzca el rango de medición del computador expresado en minutos"));        
                Valor_de_rango2 = (Valor_de_rango * 60000);
                

            while (true) 
            {
                                try {

                                        Process Retorno = Runtime.getRuntime().exec("cat /proc/loadavg");
                                        BufferedReader Entrada = new BufferedReader(new InputStreamReader(Retorno.getInputStream()));
                                        StringBuilder Regresa = new StringBuilder(80);
                                        String VarString = null;
                                        while ((VarString = Entrada.readLine()) != null)
                                        {
                                            Regresa.append(VarString);
                                        }
                                        JOptionPane.showMessageDialog(null, Regresa);
                                        final String VarTimeCpu = Regresa.toString();
                                        OBJdeInterface.loadMonitor(VarTimeCpu); 
                                        Thread.sleep((long) Valor_de_rango2);
                                } 
                                catch (IOException ValorExcepcion)
                                {
                                } catch (InterruptedException ex) { 
                    Logger.getLogger(thread.class.getName()).log(Level.SEVERE, null, ex);
                } 
          }
    }
    
}
