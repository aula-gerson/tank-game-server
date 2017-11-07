package bilac.com.main;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import bilac.com.entidades.Tanque;

public class Server {
  
  private List<Tanque> tanques;
  private HashSet<ObjectOutputStream> writer;
  private Update update;
  
  public Server() {
    this.tanques = new ArrayList<Tanque>();
    this.writer = new HashSet<ObjectOutputStream>();
    this.update = new Update(this);
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
      this.tanques.add(new Tanque(400, 50, 160, Color.RED));
      this.writer.add(writerObject);
      new EscutaUsuario(scanner, this.tanques.get(this.tanques.size()-1), update);
    } catch (IOException e) { e.printStackTrace(); }
  }
  
  public static void main(String[] args) {
    new Server();
  }

  public List<Tanque> getTanques() {
    return tanques;
  }

  public void setTanques(List<Tanque> tanques) {
    this.tanques = tanques;
  }

  public HashSet<ObjectOutputStream> getWriter() {
    return writer;
  }

  public void setWriter(HashSet<ObjectOutputStream> writer) {
    this.writer = writer;
  }

  public Update getUpdate() {
    return update;
  }

  public void setUpdate(Update update) {
    this.update = update;
  }
  
}
