/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;

import Mail.Person;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This program shows load all the config file. Its given as a laoder
 * 
 * user have to change the path of file in constructor to initialized victims, messages
 * and properties.
 * 
 * @author annie Sandra, Norah noulala
 */
public class ConfigurationManager {
   
   private static final Logger LOG = Logger.getLogger(ConfigurationManager.class.getName());

   private final List<Person> victims;   //receivers of tranks
   private final List<String> messages;  //messages to send 
   private int numberOfGroups;           //number of groupp receiver
   private List<Person> withnessesToCC;  //receiver withnesses of tranks
   
   public ConfigurationManager() throws IOException{
      
     victims = LoadAddressesFromFile("C:\\Users\\annie\\Desktop\\RES\\Laboratoires\\SmtpClient\\src\\Config\\victims.utf8");
     messages = LoadMessagesFromFile("C:\\Users\\annie\\Desktop\\RES\\Laboratoires\\SmtpClient\\src\\Config\\message.utf8");
     LoadProperties("C:\\Users\\annie\\Desktop\\RES\\Laboratoires\\SmtpClient\\src\\Config\\config.properties");
   }
   
   public List<Person> getVictims(){
      return new ArrayList<>(victims);
   }
   
   public List<Person> getWithnessesToCC(){
      return new ArrayList<>(withnessesToCC);
   }
   
   public List<String> getMessages(){
      return new ArrayList<>(messages);
   }
   
   public int getNumberOfGroups(){
      return numberOfGroups;
   }
   
   /**
    * This method Load the properties in the file(filename). In this experience we
    * load the addresses of people to copy in mail, and the default number of group we can
    * to create.
    *
    * @param filename the name of the file we want to laod properties.
    * @throws java.io.IOException
    */
   private void LoadProperties(String filename)throws IOException {
      
     FileInputStream fis = new FileInputStream(filename);
     Properties properties = new Properties();
     properties.load(fis);
     this.withnessesToCC = new ArrayList<>();
     String witnesses = properties.getProperty("withnessesToCC");
     String[] witnesseAddresses = witnesses.split(",");
     for(String address : witnesseAddresses){
      this.withnessesToCC.add(new Person (address) );
     }
      numberOfGroups = Integer.parseInt(properties.getProperty("numberOfGroups"));
     
   }
   
   /**
    * This method Load the addresses in the file(filename) using utf-8 format. In this experience we
    * load the addresses of recipients people
    *
    * @param filename the name of the file we want to laod addresses
    * @param encoding the encoding we want to use for encoding/decoding message
    * @return List<Person> list of person corresponding to the addresses load in the file
    * @throws java.io.IOException
    */
   private List<Person> LoadAddressesFromFile(String filename)throws IOException{
      
      List<Person> result=new ArrayList<>();
      FileInputStream fis = new FileInputStream(filename);
      
      try{//if the file is open well
         
         //we read it with UTF-8 format
         InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
         BufferedReader reader = new BufferedReader(isr);
         
         try{//if we can read the file 
            
            String address = reader.readLine();
            //for each address we create a person and we add it to our result
            while(address != null){
               result.add(new Person(address));
               address = reader.readLine();
            }
            
         }catch(IOException ex){//if we can not read the file
            //we print the warning
            LOG.log(Level.SEVERE, null, ex);
         }
         
      }catch(IOException ex){//we can't open the file
        //we print the warning
        LOG.log(Level.SEVERE, null, ex);
      }
       
      return result;
     
   }
   
   /**
    * This method Load the default messages in the file(filename) using utf-8 format. In this experience we
    * load the default messages to create prank.
    * 
    * note: the messages ares separate with ==
    *
    * @param filename the name of the file we want to laod addresses
    * @return List<String> list of message load in the file
    * @throws java.io.IOException
    */
   private List<String> LoadMessagesFromFile(String filename)throws IOException{
      
      List<String> result = new ArrayList<>();
      FileInputStream fis = new FileInputStream(filename);
      
      try{//if the file is open well
         
         //we read the contain of file using UTF-8 format
         InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
         BufferedReader reader = new BufferedReader(isr);
         try{//if we can read the file 
            
            //we read it while there no line 
            String line = reader.readLine();
            while(line != null){
               StringBuilder body = new StringBuilder();
               
               //we read message by message using '==' to identify the end of a message
               while((line!=null) && (!line.equals("=="))){
                  body.append(line);
                  body.append("\r\n");
                  line = reader.readLine();
               }
               result.add(body.toString());
               line = reader.readLine();
            }
         
         }catch(IOException ex){//if we can not read the file
            //we print the warning
            LOG.log(Level.SEVERE, null, ex);
         }
         
      }catch(IOException ex){//we can't open the file
        //we print the warning
        LOG.log(Level.SEVERE, null, ex);
      }
       
      return result;
      
   }
   
}
