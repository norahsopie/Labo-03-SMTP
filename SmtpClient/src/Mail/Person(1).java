
package Mail;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This program create a new person with firstname lastname and addresseMail. We can
 * also create a person with just the adresses and extract its lastname and firstname
 * if its have a structure of HEIG-VD email.
 * 
 * note: after create a person you can not change the information of that person envent 
 * if you want to create a new person.
 * 
 * implements with the help of https://www.youtube.com/watch?v=OrSdRCt_6YQ doing by 
 * Olivier Liechti
 * 
 * @author annie Sandra, Norah noulala
 */
public class Person {
   
   private String firstName;  
   private String lastName;
   private String adresseMail; //the adresse of the person
   
   /**
    * Constructor with all informations of the person. We just initiate the firsname
    * lastname and adresse
    *
    * @param firstName the name of the person.
    * @param lastName  the surname of the person.
    * @param address the addesse email of the person.
    */
   public Person(String firstName, String lastName, String address){
      
      this.firstName = firstName;
      this.lastName =lastName;
      this.adresseMail = address;
      
   }
   
    /**
    * Constructor with all informations of the person. We just initiate the addresse 
    * of the person and the last, first name if its address have a structure of heig-vd
    * email.
    *
    * @param address the addesse email of the person.
    */
   public Person(String address){
      
      this.adresseMail = address;
      //for the user of heig vd mail server (firstname.larstname@heig-vd)
      Pattern pattern = Pattern.compile("(.*)\\.(.*)@");
      Matcher matcher = pattern.matcher(address);
      boolean found = matcher.find();
      
      //if its structure is like it we can retrieve the firstname and lastname
      if(found){
         
         this.firstName = matcher.group(1);
         firstName = firstName.substring(0,1).toUpperCase() + firstName.substring(1);
         this.lastName = matcher.group(2);
         lastName = lastName.substring(0,1).toUpperCase() + lastName.substring(1);
      }
   }
   
   public String getFirstName(){
      return firstName;
   }
   
   public String getLastName (){
      return lastName;
   }
   
   public String getAddress(){
      return adresseMail;
   }
   
   
}
