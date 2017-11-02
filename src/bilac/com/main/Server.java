package bilac.com.main;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;

public class Server extends Thread {
  
  private HashSet<Usuario> usuarios;
  
  public Server() {
    this.usuarios = new HashSet<Usuario>();
    new Update(this);
  }
  
  @Override 
  public void run() {
    super.run();
    try {
      @SuppressWarnings("resource")
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
      PrintWriter writer = new PrintWriter(usuarioSocket.getOutputStream());
      ObjectOutputStream writerObject = new ObjectOutputStream(usuarioSocket.getOutputStream());
      Scanner scanner = new Scanner(usuarioSocket.getInputStream());
      Tanque tanque = new Tanque(400,50,180,Color.BLUE);
      Usuario novoUsuario = new Usuario(writer, writerObject, scanner, usuarioSocket, tanque);
      this.usuarios.add(novoUsuario);
      new EscutaUsuario(novoUsuario);
    } catch (IOException e) { e.printStackTrace(); }
  }
  
  public static void main(String[] args) {
    new Server().start();
  }

  public HashSet<Usuario> getUsuarios() {
    return usuarios;
  }

  public void setUsuarios(HashSet<Usuario> usuarios) {
    this.usuarios = usuarios;
  }
  
}
