package bilac.com.main;

import java.io.PrintWriter;
import java.util.Scanner;

import bilac.com.encryption.Descrypt;
import bilac.com.entidades.Tanque;

public class EscutaUsuario extends Thread {
  
  private Scanner scanner;
  private Tanque tanque;
  private Update update;
  
  public EscutaUsuario(Scanner scanner, Tanque tanque, Update update) {
    super();
    this.scanner = scanner;
    this.tanque = tanque;
    this.update = update;
    this.start();
  }
  
  @Override
  public void run() {
    super.run();
    try {
      String msg;
      while((msg = this.scanner.nextLine()) != null) {
        if(!this.update.isEmEstadoDeCriptografia()) {
          msg = Descrypt.text(msg, 124);
          switch (msg) {
          case "GIRAR_ANTI_HORARIO":
            tanque.girar(-10);
            break;
          case "GIRAR_HORARIO":
            tanque.girar(10);
            break;
          case "AUMENTAR_VELOCIDADE":
            tanque.aumentarVelocidade();
            break;
          case "DIMINUIR_VELOCIDADE":
            tanque.diminuirVelocidade();
            break;
          case "ATIRAR":
            tanque.atirar();
            break;
          }
        }
      }
    } catch (Exception e) { System.out.println(e.getMessage()); }
  }
  
}
