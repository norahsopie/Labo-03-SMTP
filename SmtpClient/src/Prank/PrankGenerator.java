/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prank;

import Config.ConfigurationManager;
import Mail.Group;
import Mail.Person;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author annie
 */
public class PrankGenerator {
   private ConfigurationManager configManager ;
   
   public PrankGenerator(ConfigurationManager configManager){
      this.configManager = configManager;
   }
   public List<Prank> generatePanks(){
      List<Prank> pranks = new ArrayList<>(); 
      List<String> messages = configManager.getMessages();
      int messageIndex = 0;
      int numberOfGroups = configManager.getNumberOfGroups();
      int numberOfVictims = configManager.getVictims().size();
      
      if((numberOfVictims/numberOfGroups) < 3){
         numberOfGroups = numberOfVictims/3;
         System.out.println("the number max of group we can form is : "+numberOfGroups);
         
      }
      List<Group> groups = generateGroups(configManager.getVictims(),numberOfGroups);
      for(Group group: groups){
         Prank prank = new Prank();
         List<Person> victims = group.getMembers();
         Collections.shuffle(victims);
         Person sender = victims.remove(0);
         prank.setSender(sender);
         prank.addVictimRecipient(victims);
         prank.addWithnessRecipient(configManager.getWithnessesToCC());
         String message = messages.get(messageIndex);
         messageIndex = (messageIndex+1)% messages.size();
         prank.setMessage(message);
         pranks.add(prank);
      }
      return pranks;
      
   }
   
   public List<Group> generateGroups(List<Person> victims, int numberOfGroups){
      
      List<Person> availableVictims = new ArrayList(victims);
      Collections.shuffle(availableVictims);
      List<Group> groups = new ArrayList<>();
      
      for(int i=0; i<numberOfGroups;++i){
         Group group = new Group();
         groups.add(group);
      }
      int turn = 0;
      Group targetGroup;
      while(availableVictims.size()>0){
         
         targetGroup = groups.get(turn);
         turn = (turn +1)%groups.size();
         Person victim = availableVictims.remove(0);
         targetGroup.addMenber(victim);
      }
      return groups;
   }
   
}
