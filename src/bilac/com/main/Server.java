package bilac.com.main;

import java.awt.Color;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;

public class Server extends Thread {
  
  private HashSet<Tanque> tanques;
  
  public Server() {
    this.tanques = new HashSet<Tanque>();
  }
  
  @Override 
  public void run() {
    super.run();
    try {
      @SuppressWarnings("resource")
      ServerSocket aguardaUsuario = new ServerSocket(4811);
      System.out.println("Aguardando cliente...");
      while(true) {
        Socket usuario = aguardaUsuario.accept();
        configuraComunicacaoComUsuario(usuario);
        System.out.println("Novo cliente conectado");
      }
    } catch (Exception e) { System.err.println(e.getMessage()); }
  }
  
  private void configuraComunicacaoComUsuario(Socket usuario) {
    try {
      Scanner scanner = new Scanner(usuario.getInputStream());
      PrintWriter writer = new PrintWriter(usuario.getOutputStream());
      new EscutaCliente(scanner, writer);
    } catch (IOException e) { e.printStackTrace(); }
  }
  
  public static void main(String[] args) {
    new Server().start();
  }
  
}
