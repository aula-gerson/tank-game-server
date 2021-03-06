package bilac.com.main;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashSet;

import bilac.com.encryption.Descrypt;
import bilac.com.encryption.Encrypt;
import bilac.com.entidades.Tanque;

public class Update extends Thread {
  
  private Server server;
  private boolean emEstadoDeCriptografia;
  
  public Update(Server server) {
    this.server = server;
    this.emEstadoDeCriptografia = false;
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
      tanque.setContador(tanque.getContador()+1);
      if(tanque.getContador() > 0) tanque.setEstaVivo(true);
      tanque.verificarColisaoComOsTanques(this.server.getTanques());
      tanque.mover();
      tanque.getTiro().mover();
      tanque.getTiro().verificarColisaoComOsTanques(this.server.getTanques());
      if(!tanque.isEstaVivo()) tanque.setCor(Color.BLACK);
      else tanque.setCor(Color.RED);
    }
    this.emEstadoDeCriptografia = true;
    this.server.setTanques(Encrypt.listaDeTanques(this.server.getTanques(), 124));
    for (ObjectOutputStream writer : this.server.getWriter()) {
      try {
        writer.writeObject(this.server.getTanques());
        writer.reset();
        writer.flush();
      } catch (IOException e) { e.printStackTrace(); }
    }
    this.server.setTanques(Descrypt.listaDeTanques(this.server.getTanques(), 124));
    this.emEstadoDeCriptografia = false;
  }

  public boolean isEmEstadoDeCriptografia() {
    return emEstadoDeCriptografia;
  }

  public void setEmEstadoDeCriptografia(boolean emEstadoDeCriptografia) {
    this.emEstadoDeCriptografia = emEstadoDeCriptografia;
  }
  
}
  