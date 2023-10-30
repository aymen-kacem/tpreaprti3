package ServerurmtPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerMT extends Thread{
private int nbClient;
	public static void main(String[] args) {
		new ServerMT().start();

	}
	public void run() {
		try {
			System.out.println("demarer le serveur");
			ServerSocket ss=new ServerSocket(1234);
			while(true) {
				Socket s=ss.accept();
				new ClientProcess(s,++nbClient).start();
				System.out.println("le client num"+nbClient+"est connecte");
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	


public class ClientProcess extends Thread{
  private int numClient;
  private Socket s;
	public ClientProcess(Socket s, int i) {
	this.s=s;
	
	this.numClient=i;
	}
	public void run() {
		try {
			new PrintWriter(s.getOutputStream(),true).println("bienvenue cous ete le client num"+numClient);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Opertion op=(Operation)new ObjectInputStream(s.getInputStream()).readObject();
	}
	
}
public class Client{
	private static final int port=1234;
	private int numClient;
		public void main(String[]args) {
		System.out.println("je suis un client");
		Socket s=new Socket("localhost",port);
		try {
			int op1,op2;
			String oper;
			System.out.println(new BufferedReader(new InputStreamReader(s.getInputStream())).readLine());
			Scanner ss=new Scanner(System.in);
			op1=ss.nextInt();
			op2=ss.nextInt();
			oper=ss.nextLine();
			Operation op=new Opertaion(op1,op2,oper);
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(op);
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	 
	}
}

}
