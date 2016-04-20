/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mail;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author annie
 */
public class Person {
   
   private String firstName;
   private String lastName;
   private String adresseMail;
   
   public Person(String firstName, String lastName, String address){
      this.firstName = firstName;
      this.lastName =lastName;
      this.adresseMail = address;
      
   }
   
   public Person(String address){
      this.adresseMail = address;
      Pattern pattern = Pattern.compile("(.*)\\.(.*)@");
      Matcher matcher = pattern.matcher(address);
      boolean found = matcher.find();
      
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
