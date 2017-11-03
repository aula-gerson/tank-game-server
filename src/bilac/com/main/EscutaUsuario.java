package bilac.com.main;

import java.io.PrintWriter;
import java.util.Scanner;

public class EscutaUsuario extends Thread {
  
  private Scanner scanner;
  
  public EscutaUsuario(Scanner scanner) {
    super();
    this.scanner = scanner;
    this.start();
  }
  
  @Override
  public void run() {
    super.run();
    try {
      String msg;
      while((msg = this.scanner.nextLine()) != null) {
          System.out.println(msg);
      }
    } catch (Exception e) { System.out.println(e.getMessage()); }
  }
  
}
