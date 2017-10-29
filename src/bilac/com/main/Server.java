package bilac.com.main;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server extends Thread {
  
  @Override 
  public void run() {
    super.run();
    try {
      @SuppressWarnings("resource")
      ServerSocket aguardaCliente = new ServerSocket(4811);
      while(true) {
        Socket cliente = aguardaCliente.accept();
        Scanner scanner = new Scanner(cliente.getInputStream());
        PrintWriter writer = new PrintWriter(cliente.getOutputStream());
        System.out.println("Novo cliente conectado");
      }
    } catch (Exception e) { System.err.println(e.getMessage()); }
  }
  
  public static void main(String[] args) {
    new Server().start();
  }
  
}
