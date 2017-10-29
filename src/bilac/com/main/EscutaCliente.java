package bilac.com.main;

import java.io.PrintWriter;
import java.util.Scanner;

public class EscutaCliente extends Thread {
  
  private Scanner scanner;
  private PrintWriter writer;
  
  public EscutaCliente(Scanner scanner, PrintWriter writer) {
    super();
    this.scanner = scanner;
    this.writer = writer;
    this.start();
  }

  @Override public void run() {
    super.run();
    try {
      String msg;
      while((msg = this.scanner.nextLine()) != null) {
          System.out.println(msg);
      }
    } catch (Exception e) { System.out.println(e.getMessage()); }
  }
  
}
