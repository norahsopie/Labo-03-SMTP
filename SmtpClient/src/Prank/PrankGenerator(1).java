
package Prank;

import Config.ConfigurationManager;
import Mail.Group;
import Mail.Person;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * This program generate the list of prank and list of group. Each generator have a 
 * configuration manager to load configuration in file.
 * 
 * implements with the help of https://www.youtube.com/watch?v=OrSdRCt_6YQ doing by 
 * Olivier Liechti
 * 
 * @author annie Sandra, Norah noulala
 */
public class PrankGenerator {
   private ConfigurationManager configManager ;
   
   public PrankGenerator(ConfigurationManager configManager){
      this.configManager = configManager;
   }
   
   /**
    * This function generate the list of Prank using the stores elements(number of group,
    * victims and messages)in the configuration manager 
    *
    * @return List<Prank> : the list of pranks generated
    */
   public List<Prank> generatePanks(){
      
      List<Prank> resultPranks = new ArrayList<>();
      /*we store all the parameter of the configurationManager*/
      List<String> messages = configManager.getMessages();
      int numberOfGroups = configManager.getNumberOfGroups();
      int numberOfVictims = configManager.getVictims().size();
      int turnMessage = 0;                                    //to choice message of Prank
      
      /* if there is not enought victim we set the number of group to the possible */
      if((numberOfVictims/numberOfGroups) < 3){
         numberOfGroups = numberOfVictims/3;
         System.out.println("the number max of group we can form is : "+numberOfGroups);
         
      }
      
      /*we generate a group of victims */
      List<Group> groups = generateGroups(configManager.getVictims(),numberOfGroups);
      /*for each group we create a prank and set sender, message, recipient and
      withness recipient recipient.And then we add it to the result resultPranks*/
      for(Group group: groups){
         
         //we create a new Prank
         Prank prank = new Prank();
         List<Person> victims = group.getMembers();
         Collections.shuffle(victims);
         Person sender = victims.remove(0);
         //we set the sender to the firstvictim
         prank.setSender(sender);
         prank.addVictimRecipient(victims);
         prank.addWithnessRecipient(configManager.getWithnessesToCC());
         
         String message = messages.get(turnMessage);
         /*on choisi aléatoirement le message à envoyer*/
         turnMessage = (turnMessage+1)% messages.size();
         
         /*we set message and add prank in the list of pranks*/
         prank.setMessage(message);
         resultPranks.add(prank);
         
      }
      return resultPranks;
      
   }
   
   
   /**
    * This function generate the list of Group using list of victims and number of group
    * 
    * @param victims list of victim who will form the group
    * @param numberOfGroups number of generate group
    * @return List<Group>  the list of group generated
    */
   public List<Group> generateGroups(List<Person> victims, int numberOfGroups){
      
      /*available victim to form the group*/
      List<Person> availableVictims = new ArrayList(victims);
      Collections.shuffle(availableVictims);
      List<Group> groups = new ArrayList<>();
      
      /*initialisation of list of group*/
      for(int i=0; i<numberOfGroups;++i){
         Group group = new Group();
         groups.add(group);
      }
      int turnGroup = 0;
      Group targetGroup;
      while(availableVictims.size()>0){
         
         /*get the group choice*/
         targetGroup = groups.get(turnGroup);
         /*choisi aléatoirement le prochain group*/
         turnGroup = (turnGroup +1)%groups.size();
         Person victim = availableVictims.remove(0);
         /*add the victim to the choice group*/
         targetGroup.addMember(victim);
         
      }
      return groups;
   }
   
}
