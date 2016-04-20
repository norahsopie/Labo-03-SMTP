/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smtpclient;

import Config.ConfigurationManager;
import Mail.Group;
import Mail.Message;
import Mail.Person;
import Prank.Prank;
import Prank.PrankGenerator;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SmtpClient {
   
   private static final Logger LOG = Logger.getLogger(SmtpClient.class.getName());
   private String host;
   private int port;
   
   
   private Socket smtpSocket = null;
   protected BufferedReader reader;
   protected PrintWriter writer;
   
   
   public SmtpClient(String hostname, int port){
      
      this.port = port;
      this.host = hostname;
   
   }
   public void sendMessage(Message message) throws IOException {
      
      System.out.println("Trying to connect to server ......");
      smtpSocket = new Socket(host,port);
      writer = new PrintWriter(new OutputStreamWriter(smtpSocket.getOutputStream()));
      reader = new BufferedReader(new InputStreamReader(smtpSocket.getInputStream()));
      
      String line = reader.readLine();
      System.out.println("Server " + line);
      
      writer.printf("EHLO localhost \r\n");
      writer.flush();
      System.out.println("EHLO localhost");
      
      line = reader.readLine();
      System.out.println("Server : " + line);

      if (!line.startsWith("250")) {
         throw new IOException("Error server line : " + line);
      }

      while (line.startsWith("250-")) {
         line = reader.readLine();
         System.out.println("Server : " + line);
      }

      writer.printf("MAIL FROM:" + message.getFrom() + "\r\n");
      writer.flush();
      System.out.println("MAIL FROM:" + message.getFrom());

      line = reader.readLine();
      System.out.println("Server : " + line);
      for(String to : message.getTo()){
      writer.printf("RCPT TO:" + to + "\r\n");
      writer.flush();
      System.out.println("RCPT TO:" + to);

      line = reader.readLine();
      System.out.println("Server : " + line);
      }
      
      for(String cc : message.getCc()){
      writer.printf("RCPT TO:" + cc + "\r\n");
      writer.flush();
      System.out.println("RCPT TO:" + cc);

      line = reader.readLine();
      System.out.println("Server : " + line);
      }
      
      for(String bcc : message.getBcc()){
      writer.printf("RCPT TO:" + bcc + "\r\n");
      writer.flush();
      System.out.println("RCPT TO:" + bcc);

      line = reader.readLine();
      System.out.println("Server : " + line);
      }
      
      writer.printf("DATA \r\n");
      writer.flush();
      System.out.println("DATA");

      line = reader.readLine();
      System.out.println("Server : " + line);

      writer.printf("From: " + message.getFrom() + "\r\n");
      System.out.println("From: " + message.getFrom());

      writer.printf("To: " + message.getTo()[0]);
      for(int i=1; i < message.getTo().length;++i){
         writer.printf("," + message.getTo()[i]);
      }
      writer.printf("\r\n");

      writer.printf("Cc: " + message.getCc()[0]);
      for(int i=1; i < message.getCc().length;++i){
         writer.printf("," + message.getCc()[i]);
      }
      writer.printf("\r\n");
      
      writer.printf(message.getBody());
      writer.printf("\r\n");
      writer.printf(".\r\n");
      System.out.println(".");
      writer.flush();

      line = reader.readLine();
      System.out.println("Server : " + line);

      writer.printf("quit \r\n");
      writer.flush();
      System.out.println("quit \r\n");

      writer.close();
      reader.close();
      smtpSocket.close();

   }

   public static void main(String[] args) {
      
      //connect localhost server on 2525 port
      SmtpClient smtp = new SmtpClient("localhost", 2525);
      try {
         
         //we create it to retrieve the information in the configuration files 
         ConfigurationManager manager = new ConfigurationManager();
         PrankGenerator prankGenerator = new PrankGenerator(manager);
         //pranks corresponding to the configuration files are generated
         List<Prank> pranks = new ArrayList<>(prankGenerator.generatePanks());
         //we send each blague to a corresponding victim
         for(Prank prank: pranks){
            smtp.sendMessage(prank.generateMail());
         }
         
      } catch (IOException ex) {
         LOG.log(Level.SEVERE, null, ex);
      }
   }
}
