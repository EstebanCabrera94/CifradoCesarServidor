/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cesarservidor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Esteban
 */
public class CesarServidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        final int PUERTO = 40080;
        try {
            ServerSocket serverSocket = new ServerSocket(PUERTO);
            System.out.println("Esperando a que se conecte el cliente");
            Socket socket = serverSocket.accept();
            System.out.println("El cliente se conectó");
            
            
            enviarYrecibir(socket);
            
        } catch (IOException ex) {
            Logger.getLogger(CesarServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static void enviarYrecibir(Socket socket){
        try {
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            
            Scanner scanner = new Scanner(System.in);
            String mensaje;
            String abc = "abcdefghijklmnñopqrstuvwxyzabc";
            String letra;
            int posicion;
            while (true) {
 
                //Recibir mensaje del cliente
                  System.out.println("Esperando mensaje del cliente");
                  mensaje = br.readLine();
                  System.out.println("Cliente: " +  mensaje);
                  //mensaje = mensaje.substring(0,1);
                  //int fleje = abc.indexOf(mensaje)+3;
                  //String codigo = abc.substring(fleje,fleje+1);
                  String codigo = "";
                  for (int i = 0; i < mensaje.length(); i++) {
                      
                    letra = mensaje.substring(i,i+1);
                    if(" ".equals(letra)){
                         codigo+=" "; 
                      }else{
                        if(-1 != abc.indexOf(letra.toLowerCase())){
                            posicion = abc.indexOf(letra.toLowerCase())+3;
                            codigo+=abc.substring(posicion,posicion+1);
                        }else{
                            bw.write("El texto introducido no fue aceptado");
                            bw.newLine();
                            bw.flush();
                            
                        }
                            
                        
                    }
                }
                  
                    bw.write(codigo);
                    bw.newLine();
                    bw.flush();
                  
                  //Enviar mensaje al cliente
                  
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(CesarServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
