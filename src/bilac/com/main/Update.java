package bilac.com.main;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashSet;

public class Update extends Thread {
  
  private Server server;
  
  public Update(Server server) {
    this.server = server;
    this.start();
  }
  
  @Override 
  public void run() {
    super.run();
      try {
        while(true) {
          updateTanques();
          sleep(100);
        }
      } catch (InterruptedException e) {e.printStackTrace();}
  }
  
  private void updateTanques() {
    for (Tanque tanque : this.server.getTanques()) {
      tanque.verificarColisaoComOsTanques(this.server.getTanques());
      tanque.mover();
    }
    for (ObjectOutputStream writer : this.server.getWriter()) {
      try {
        writer.writeObject(this.server.getTanques());
        writer.reset();
        writer.flush();
      } catch (IOException e) { e.printStackTrace(); }
    }
  }
  
}
  