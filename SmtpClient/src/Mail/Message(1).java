
package Mail;

/**
 * This program create a group of person. it store all the mail arguments(sender,recipients
 * withnesses recipient, black withnesses recipient, subject and body,)of on message.
 *
 * Note : store it in string to facilite the use,It's just give setter and getter of 
 *        all that arguments
 * 
 * implements with the help of https://www.youtube.com/watch?v=OrSdRCt_6YQ doing by 
 * Olivier Liechti
 * 
 * @author annie Sandra, Norah noulala
 */
public class Message {
   
   private String from;
   private String [] to = new String[0];  //the receiver's of trank
   private String [] cc = new String[0];  //the withnesses person in string of the trank
   private String [] bcc = new String[0]; //the black copie person of the trank
   private String  subject;               //the subject of trank
   private String body;                   //the contains of trank
   
   public String getFrom(){
      return from;
   }
   
   public String getSubject(){
      return subject;
   }
   
   public String getBody(){
      return body;
   }
   
   public String[] getTo(){
      return to;
   }
   
   public String[] getBcc(){
      return bcc;
   }
   
   public String[] getCc(){
      return cc;
   }
   
   public void setFrom(String from){
      this.from = from;
   }
   
   public void setTo(String[] to){
      this.to = to;
   }
   
   public void setCC(String[] cc){
      this.cc = cc;
   }
   
   public void setBcc(String[] bcc){
      this.bcc = bcc;
   }
   
   public void setSubject(String subject){
      this.subject = subject;
   }
   
   public void setBody(String body){
      this.body = body;
   }

   
}
