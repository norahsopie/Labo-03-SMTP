
package Prank;

import Mail.Message;
import Mail.Person;
import java.util.ArrayList;
import java.util.List;

/**
 * This program create a prank(joke). Each prank have a sender, a list of victim 
 * recipient, a list of withness recipient and the message. User can set and get all 
 * the elements , he can also generate message with those elements.
 * 
 * implements with the help of https://www.youtube.com/watch?v=OrSdRCt_6YQ doing by 
 * Olivier Liechti
 * 
 * @author annie Sandra, Norah noulala
 */
public class Prank {
   
   private Person sender;                                      //sender
   private List<Person> victimRecipient  = new ArrayList<>();  //principal recipient
   private List<Person> withnessRecipient = new ArrayList<>(); //recipient in copie
   private String message;                                     //message to send
   
   public String getMessage(){
      return message;
   }
   public List<Person> getVictimRecipient(){
      return new ArrayList<>(victimRecipient);
   }
   
   public List<Person> getWithnessRecipient(){
      return new ArrayList<>(withnessRecipient);
   }
   
   /*add a person recepient in copy*/
   public void addWithnessRecipient(Person person){
        withnessRecipient.add(person);
   }
   
   /*add a person recepient */
   public void addVictimRecipient(Person person){
      victimRecipient.add(person);
   }
   
   
   public void setMessage(String message){
      this.message = message;
   }
   public void setSender(Person person){
      this.sender = person;
   }
   
   /*add a list of person recepient*/
   public void addVictimRecipient(List<Person> victimRecipient ){
      for(Person person: victimRecipient)
         addVictimRecipient(person);
   }
   
   /*add a list of person recepient in copy*/
   public void addWithnessRecipient(List<Person> withnessRecipient){
      for(Person person: withnessRecipient)
         addWithnessRecipient(person);
   }
   
   /**
    * This function store the address of each victim(withness and principal) and sender
    * create a new message, set all its element of  with the prank arguments. 
    *
    * @return Message the message generate with the list of victims sender and message.
    */
   public Message generateMail(){
      
      Message finalMessage = new Message();
      /*set the body with message*/
      finalMessage.setBody(message + "\r\n" + sender.getFirstName());
      
      /*we create the table of String, store the address of victim and set the to[]
      of the message */
      int i=0;
      String[] to = new String[victimRecipient.size()];
      for(Person victim : victimRecipient){
         to[i] = victim.getAddress() + " ";
         ++i;
         
      }
      finalMessage.setTo(to);
      
      /*we create the table of String, store the address of victim withness and set the 
       cc[] of the message */
      i=0;
      String[] cc = new String[withnessRecipient.size()];
      for(Person victim : withnessRecipient){
         cc[i] = victim.getAddress() + " ";
         ++i;
      }
      finalMessage.setCC(cc);
      finalMessage.setFrom(sender.getAddress());
     
     return finalMessage;
   }
}
