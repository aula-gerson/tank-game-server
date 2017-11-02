package bilac.com.main;

import java.io.PrintWriter;
import java.util.Scanner;

public class EscutaUsuario extends Thread {
  
  Usuario usuario;
  
  public EscutaUsuario(Usuario usuario) {
    super();
    this.usuario = usuario;
    this.start();
  }
  
  @Override
  public void run() {
    super.run();
    try {
      String msg;
      while((msg = this.usuario.getScanner().nextLine()) != null) {
          System.out.println(msg);
      }
    } catch (Exception e) { System.out.println(e.getMessage()); }
  }
  
}
