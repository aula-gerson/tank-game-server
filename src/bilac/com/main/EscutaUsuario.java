package bilac.com.main;

import java.io.PrintWriter;
import java.util.Scanner;

public class EscutaUsuario extends Thread {
  
  private Scanner scanner;
  private Tanque tanque;
  
  public EscutaUsuario(Scanner scanner, Tanque tanque) {
    super();
    this.scanner = scanner;
    this.tanque = tanque;
    this.start();
  }
  
  @Override
  public void run() {
    super.run();
    try {
      String msg;
      while((msg = this.scanner.nextLine()) != null) {
        switch (msg) {
          case "GIRAR_ANTI_HORARIO":
            tanque.girar(-10);
            System.out.println("1");
            break;
          case "GIRAR_HORARIO":
            tanque.girar(10);
            System.out.println("2");
            break;
          case "AUMENTAR_VELOCIDADE":
            tanque.aumentarVelocidade();
            System.out.println("3");
            break;
          case "DIMINUIR_VELOCIDADE":
            tanque.diminuirVelocidade();
            System.out.println("4");
            break;
          default:
            System.out.println("5");
            break;
        }
      }
    } catch (Exception e) { System.out.println(e.getMessage()); }
  }
  
}
