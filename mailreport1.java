import java.util.*;  
import javax.mail.*;  
import javax.mail.internet.*;  
import javax.activation.*;  
 import java.io.*;
 
class mailreport1{  
 public static void main(String [] args)throws IOException{  
  
  String emailid="";
  BufferedReader br1 =new BufferedReader(new InputStreamReader(new FileInputStream("email.txt")));
  emailid=br1.readLine();
 String to=emailid ;
  final String user="hc.technicalteam@gmail.com";//change accordingly  
  final String password="c27somaiya";//change accordingly  
   
  //1) get the session object     
   Properties props = new Properties();  
  props.put("mail.smtp.host", "smtp.gmail.com");  
  props.put("mail.smtp.socketFactory.port", "465");  
  props.put("mail.smtp.socketFactory.class",  
            "javax.net.ssl.SSLSocketFactory");  
  props.put("mail.smtp.auth", "true");  
  props.put("mail.smtp.port", "465");  
   
  Session session = Session.getDefaultInstance(props,  
   new javax.mail.Authenticator() {  
   protected PasswordAuthentication getPasswordAuthentication() {  
   return new PasswordAuthentication("hc.technicalteam@gmail.com","c27somaiya");//change accordingly  
   }  
  });  
     
  //2) compose message     
  try{  
    MimeMessage message = new MimeMessage(session);  
    message.setFrom(new InternetAddress(user));  
    message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
    message.setSubject("Heart Report");  
    message.setText("Dear User,\nThis is your heart report. \n \n \nRegards,\n SupportTeam,\n HeartCare+.");  
    //3) create MimeBodyPart object and set your message text     
    BodyPart messageBodyPart1 = new MimeBodyPart();  
   messageBodyPart1.setText("Dear User,\nThis is your heart report. \n \n \nRegards,\nSupportTeam,\nHeartCare+.");  
      
    //4) create new MimeBodyPart object and set DataHandler object to this object      
   MimeBodyPart messageBodyPart2 = new MimeBodyPart();  
  
    String filename = "report.docx";//change accordingly  
   DataSource source = new FileDataSource(filename);  
    messageBodyPart2.setDataHandler(new DataHandler(source));  
   messageBodyPart2.setFileName(filename);  
     
     
    //5) create Multipart object and add MimeBodyPart objects to this object      
   Multipart multipart = new MimeMultipart();  
   multipart.addBodyPart(messageBodyPart1);  
   multipart.addBodyPart(messageBodyPart2);  
  
    //6) set the multiplart object to the message object  
    message.setContent(multipart );  
     
    //7) send message  
    Transport.send(message);  
   
   System.out.println("message sent....");  
   }catch (MessagingException ex) {ex.printStackTrace();}  
 }  
}  