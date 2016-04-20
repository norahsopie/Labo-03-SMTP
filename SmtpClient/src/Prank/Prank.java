/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prank;

import Mail.Message;
import Mail.Person;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author annie
 */
public class Prank {
   private Person sender;
   private List<Person> victimRecipient  = new ArrayList<>();
   private List<Person> withnessRecipient = new ArrayList<>();
   private String message;
   
   public String getMessage(){
      return message;
   }
   public List<Person> getVictimRecipient(){
      return new ArrayList<>(victimRecipient);
   }
   
   public List<Person> getWithnessRecipient(){
      return new ArrayList<>(withnessRecipient);
   }
   
   public void addWithnessRecipient(Person person){
        withnessRecipient.add(person);
   }

   public void addVictimRecipient(Person person){
      victimRecipient.add(person);
   }
   public void setMessage(String message){
      this.message = message;
   }
   public void setSender(Person person){
      this.sender = person;
   }
   public void addVictimRecipient(List<Person> victimRecipient ){
      for(Person person: victimRecipient)
         addVictimRecipient(person);
   }
   
   public void addWithnessRecipient(List<Person> withnessRecipient){
      for(Person person: withnessRecipient)
         addWithnessRecipient(person);
   }
   
   public Message generateMail(){
      
      Message finalMessage = new Message();
      finalMessage.setBody(message + "\r\n" + sender.getFirstName());
      
      int i=0;
      String[] to = new String[victimRecipient.size()];
      for(Person victim : victimRecipient){
         to[i] = victim.getAddress() + " ";
         ++i;
         
      }
      finalMessage.setTo(to);
      i=0;
     String[] cc = new String[withnessRecipient.size()];
     for(Person victim : withnessRecipient){
         to[i] = victim.getAddress() + " ";
         ++i;
      }
     finalMessage.setCC(cc);
     finalMessage.setFrom(sender.getAddress());
     
     return finalMessage;
   }
}
