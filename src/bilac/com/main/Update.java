package bilac.com.main;

import java.io.IOException;
import java.util.HashSet;

public class Update extends Thread {
  
  Server server;
  
  public Update(Server server) {
    this.server = server;
    this.start();
  }
  
  @Override 
  public void run() {
    super.run();
    while(true) {
      try {
        criarTanqueParaONovoUsuario();
        sleep(100);
      } catch (InterruptedException e) {e.printStackTrace();}
    }
  }
  
  private void criarTanqueParaONovoUsuario() {
    HashSet<Tanque> tanques = new HashSet<Tanque>();
    for (Usuario usuario : this.server.getUsuarios()) {
      tanques.add(usuario.getTanque());
    }
    for (Usuario usuario : this.server.getUsuarios()) {
      try {
        usuario.getWriterObject().writeObject(tanques);
        usuario.getWriterObject().flush();
      } catch (IOException e) { e.printStackTrace(); };
    }
  }

  
}
