package sn.isi.metier;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread {
	
	private Socket client;
    private BufferedReader fromServeur;
    private PrintStream toServeur;
    private Scanner scanner = new Scanner(System.in);
	
    
    public Client() {
    	
    	 try{

             this.start();
         }catch (Exception ex) {
             ex.printStackTrace();
         }
	
	}
    
    @Override
    public void run() {
    	try {
            client = new Socket("127.0.0.1", 8889);
            toServeur = new PrintStream(client.getOutputStream());
            fromServeur = new BufferedReader(
                    new InputStreamReader(client.getInputStream()));
            
            while (true){
                System.out.println("Client, votre message :");
                toServeur.println(scanner.nextLine());

                String message = fromServeur.readLine();
                System.out.println("Message  du serveur : " + message);
            }
    	}catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            try {
                client.close();
            }catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    
    
    

}
