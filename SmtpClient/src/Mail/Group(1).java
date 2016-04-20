
package Mail;

import java.util.ArrayList;
import java.util.List;

/**
 * This program create a group of person. After add a person in a group you
 * can not remove but user can choice to implement the remove function of a specific
 * person.
 * 
 * user have to change the path of file in constructor to initialized victims, messages
 * and properties.
 * implements with the help of https://www.youtube.com/watch?v=OrSdRCt_6YQ doing by 
 * Olivier Liechti
 * 
 * @author annie Sandra, Norah noulala
 */
public class Group {
   
   private List <Person> members = new ArrayList<>(); //groupe of person 
   
   /**
    * This method Load the properties in the file(filename). In this experience we
    * load the addresses of people to copy in mail, and the default number of group
    * we can to create.
    *
    * @param person the person to add in a group.
    */
   public void addMember(Person person){
      members.add(person);
   }
   /* return the list of pperson in the group*/
   public List<Person> getMembers(){
      return new ArrayList<>(members);
   }
   
}
