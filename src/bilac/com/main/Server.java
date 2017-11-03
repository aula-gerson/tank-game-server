package bilac.com.main;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;

public class Server {
  
  private HashSet<Tanque> tanques;
  private HashSet<ObjectOutputStream> writer;
  
  public Server() {
    this.tanques = new HashSet<Tanque>();
    this.writer = new HashSet<ObjectOutputStream>();
    new Update(this);
    aguardaConexaoUsuario();
  }
  
  public void aguardaConexaoUsuario() {
    try {
      ServerSocket aguardaUsuario = new ServerSocket(4811);
      System.out.println("Aguardando cliente...");
      while(true) {
        Socket usuarioSocket = aguardaUsuario.accept();
        configuraUsuario(usuarioSocket);
        System.out.println("Novo cliente conectado");
      }
    } catch (Exception e) { System.err.println(e.getMessage()); }
  }
  
  private void configuraUsuario(Socket usuarioSocket) {
    try {
      ObjectOutputStream writerObject = new ObjectOutputStream(usuarioSocket.getOutputStream());
      Scanner scanner = new Scanner(usuarioSocket.getInputStream());
      this.tanques.add(new Tanque(400, 50, 80, Color.GREEN));
      this.writer.add(writerObject);
      new EscutaUsuario(scanner);
    } catch (IOException e) { e.printStackTrace(); }
  }
  
  public static void main(String[] args) {
    new Server();
  }

  public HashSet<Tanque> getTanques() {
    return tanques;
  }

  public void setTanques(HashSet<Tanque> usuarios) {
    this.tanques = usuarios;
  }

  public HashSet<ObjectOutputStream> getWriter() {
    return writer;
  }

  public void setWriter(HashSet<ObjectOutputStream> writer) {
    this.writer = writer;
  }
  
}
