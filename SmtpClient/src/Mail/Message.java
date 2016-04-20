/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mail;

/**
 *
 * @author annie
 */
public class Message {
   
   private String from;
   private String [] to = new String[0];
   private String [] cc = new String[0];
   private String [] bcc = new String[0];
   private String  subject;
   private String body;
   
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
