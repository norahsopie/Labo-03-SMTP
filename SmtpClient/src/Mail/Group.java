/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mail;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author annie
 */
public class Group {
   
   private List <Person> members = new ArrayList<>();
  /* private List <String> victimMail;*/
   public void addMenber(Person person){
      members.add(person);
   }
   
   public List<Person> getMembers(){
      return new ArrayList<>(members);
   }
   
}
