import java.io.*;
import java.net.*;
import java.lang.*;

public class ServeurTourelle{

  private ServerSocket serveur;

  public ServeurTourelle() throws Exception{
    this.serveur = new ServerSocket(0, 1, InetAddress.getByName(Inet4Address.getLocalHost().getHostAddress()));
  }

  private void ecouter() throws Exception {
    String donnee = null;
    Socket client = this.serveur.accept();
    String adresseClient = client.getInetAddress().getHostAddress();
    System.out.println("\r\nConnection depuis l'adresse " + adresseClient);

    BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));

    while((donnee = br.readLine()) != null){
      if(donnee.equals("haut")){
        System.out.println("\r\nInclinaison de la tourelle vers le haut");
      }else if(donnee.equals("gauche")){
        System.out.println("\r\nRotation de la tourelle vers la gauche");
      }else if(donnee.equals("bas")){
        System.out.println("\r\nInclinaison de la tourelle vers le bas");
      }else if(donnee.equals("droite")){
        System.out.println("\r\nRotation de la tourelle vers la droite");
      }else if(donnee.equals("tir")){
        System.out.println("\r\nLa tourelle tire");
      }else{
        System.out.println("\r\nMessage inconnu de " + adresseClient + ": " + donnee);
      }
    }
  }

  public InetAddress getAdresseSocket() {
      return this.serveur.getInetAddress();
  }

  public int getPort() {
      return this.serveur.getLocalPort();
  }

  public static void main(String[] args) throws Exception {
      ServeurTourelle tourelle = new ServeurTourelle();
      System.out.println("\r\nLa tourelle est allumée: " +
              "IPv4:" + tourelle.getAdresseSocket().getHostAddress() +
              " Port:" + tourelle.getPort());

      tourelle.ecouter();
  }
}
