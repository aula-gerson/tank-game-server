package bilac.com.main;

import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Usuario {
  
  private PrintWriter writerText;
  private ObjectOutputStream writerObject;
  private Scanner scanner;
  private Socket scoket;
  private Tanque tanque;
  
  public Usuario(PrintWriter writerText, ObjectOutputStream writerObject, Scanner scanner, Socket scoket,
      Tanque tanque) {
    super();
    this.writerText = writerText;
    this.writerObject = writerObject;
    this.scanner = scanner;
    this.scoket = scoket;
    this.tanque = tanque;
  }

  public PrintWriter getWriterText() {
    return writerText;
  }

  public void setWriterText(PrintWriter writerText) {
    this.writerText = writerText;
  }

  public ObjectOutputStream getWriterObject() {
    return writerObject;
  }

  public void setWriterObject(ObjectOutputStream writerObject) {
    this.writerObject = writerObject;
  }

  public Scanner getScanner() {
    return scanner;
  }

  public void setScanner(Scanner scanner) {
    this.scanner = scanner;
  }

  public Socket getScoket() {
    return scoket;
  }

  public void setScoket(Socket scoket) {
    this.scoket = scoket;
  }

  public Tanque getTanque() {
    return tanque;
  }

  public void setTanque(Tanque tanque) {
    this.tanque = tanque;
  }
    
}
