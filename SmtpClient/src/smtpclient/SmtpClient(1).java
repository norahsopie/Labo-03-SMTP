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

/**
 * This program implement the Smtp Client protocole. each client smtp have a 
 * hostname, the socket, a reader and a writer to communicate with the server.
 * This class is responsible to send messages to a group or a person following the 
 * protocol smtp
 * 
 * implements with the help of https://www.youtube.com/watch?v=OrSdRCt_6YQ doing by 
 * Olivier Liechti
 * 
 * @author annie Sandra, Norah noulala
 */
public class SmtpClient {
   
   private static final Logger LOG = Logger.getLogger(SmtpClient.class.getName());
   private String hostname;           //the hostname
   private int port;                  //the port to connect 
   
   
   private Socket smtpSocket = null;   //socket to connect with server
   protected BufferedReader reader;    //to read server message
   protected PrintWriter writer;       //to write to the server
   
   /**
    * This Constructor initialize the port and hostname.
    * 
    *
    * @param hostname the host name 
    * @param port     the connection port 
    */
   public SmtpClient(String hostname, int port){
      
      this.port = port;
      this.hostname = hostname;
   
   }
   
   /**
    * This function connect host to server and handles communication with server.
    * In this function we listen to the server(read)  and write her, write the 
    * listining and write the sends.
    * 
    * Note : have to be call in the block of try
    *
    * @param message  message with all the element for communication 
    * @throws java.io.IOException
    */
   public void sendMessage(Message message) throws IOException {
      
      /*connection to the server*/
      System.out.println("Trying to connect to server ......");
      smtpSocket = new Socket(hostname,port);
      
      /*initialization of  reader and writer*/
      writer = new PrintWriter(new OutputStreamWriter(smtpSocket.getOutputStream()));
      reader = new BufferedReader(new InputStreamReader(smtpSocket.getInputStream()));
      
      /*listing the request of server and oui print it*/
      String line = reader.readLine();
      System.out.println("Server " + line);
      
      /*initiate the communication  with her and print it*/
      writer.printf("EHLO localhost \r\n");
      writer.flush();
      System.out.println("EHLO localhost");
      
      /*listing to the server and print it*/
      line = reader.readLine();
      System.out.println("Server : " + line);
      
      /*if the response is not good(begin != 250) we send the message and throw exception*/
      if (!line.startsWith("250")) {
         throw new IOException("Error server line : " + line);
      }
      
      /*listing the server while it speaking */
      while (line.startsWith("250-")) {
         line = reader.readLine();
         System.out.println("Server : " + line);
      }
      
      /*we specify the sender to the server*/
      writer.printf("MAIL FROM:" + message.getFrom() + "\r\n");
      writer.flush();
      System.out.println("MAIL FROM:" + message.getFrom());
      
      /*listining to the server*/
      line = reader.readLine();
      System.out.println("Server : " + line);
      /*specify the receiver's*/
      for(String to : message.getTo()){
      writer.printf("RCPT TO:" + to + "\r\n");
      writer.flush();
      System.out.println("RCPT TO:" + to);
      
      /*listining to the server */
      line = reader.readLine();
      System.out.println("Server : " + line);
      }
      
      /*specify the withnesses receiver*/
      for(String cc : message.getCc()){
      writer.printf("RCPT TO:" + cc + "\r\n");
      writer.flush();
      System.out.println("RCPT TO:" + cc);
      
      /*listinings to the server */
      line = reader.readLine();
      System.out.println("Server : " + line);
      }
      
      /*specify the black recipient message*/
      for(String bcc : message.getBcc()){
      writer.printf("RCPT TO:" + bcc + "\r\n");
      writer.flush();
      System.out.println("RCPT TO:" + bcc);
      
      /*listinings to the server */
      line = reader.readLine();
      System.out.println("Server : " + line);
      }
      
      /*ask the send of DATA*/
      writer.printf("DATA \r\n");
      writer.flush();
      System.out.println("DATA");
      
      /*listinings to the server */
      line = reader.readLine();
      System.out.println("Server : " + line);
      /*beguin the send of DATA*/
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
      /*end of send of DATA*/
      writer.printf("\r\n");
      writer.printf(".\r\n");
      System.out.println(".");
      writer.flush();
      
      /*listining to the server*/
      line = reader.readLine();
      System.out.println("Server : " + line);
      
      /*end of communication*/
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
         //we send each Prank to a corresponding victim
         for(Prank prank: pranks){
            smtp.sendMessage(prank.generateMail());
         }
         
      } catch (IOException ex) {
         //for debugging, Exception
         LOG.log(Level.SEVERE, null, ex);
      }
   }
}
