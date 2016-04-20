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
 *
 * @author annie
 */
public class ConfigurationManager {
   
   private static final Logger LOG = Logger.getLogger(ConfigurationManager.class.getName());

   private final List<Person> victims;
   private final List<String> messages;
   private int numberOfGroups;
   private List<Person> withnessesToCC;
   
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
   
   private List<Person> LoadAddressesFromFile(String filename)throws IOException{
      
      List<Person> result=new ArrayList<>();
      FileInputStream fis = new FileInputStream(filename);
      
      try{
         
         InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
         BufferedReader reader = new BufferedReader(isr);
         try{
            String address = reader.readLine();
            while(address != null){
               result.add(new Person(address));
               address = reader.readLine();
            }
            
         }catch(IOException ex){
            LOG.log(Level.SEVERE, null, ex);
         }
         
      }catch(IOException ex){
         LOG.log(Level.SEVERE, null, ex);
      }
       
      return result;
     
   }
   
   private List<String> LoadMessagesFromFile(String filename)throws IOException{
      
      List<String> result = new ArrayList<>();
      FileInputStream fis = new FileInputStream(filename);
      
      try{
         
         InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
         BufferedReader reader = new BufferedReader(isr);
         try{
            String line = reader.readLine();
            while(line != null){
               StringBuilder body = new StringBuilder();
               while((line!=null) && (!line.equals("=="))){
                  body.append(line);
                  body.append("\r\n");
                  line = reader.readLine();
               }
               result.add(body.toString());
               line = reader.readLine();
            }
         
         }catch(IOException ex){
            LOG.log(Level.SEVERE, null, ex);
         }
         
      }catch(IOException ex){
        
        LOG.log(Level.SEVERE, null, ex);
      }
       
      return result;
      
   }
   
}
