package org.example;

import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Authenticator;
public class MailGonderme {
    public void UyelereMail(String usertype) throws IOException {

        //Dosyanın okunduğu kısım
        File elitUye = new File("üyeler.txt");
        FileReader fReaderElit = new FileReader(elitUye);
        String lineElit,Email[];
        BufferedReader bReaderElit = new BufferedReader(fReaderElit);

        //Başlık ve metni yazmak için olan fonksiyon
        String[] array = getSubjectAndText();

        //Dosya satır satır okunduğundan her üyenin mailini bulmak için döngü
        while((lineElit=bReaderElit.readLine()) != null) {

            //Maili bulmak için satırların kelimelere bölündüğü kısım
            Email = lineElit.split(" ");

            //Genel üye veya elit üyenin ayrıldığı kısım
            if(Email[3].equals(usertype)) {
                sendmail(Email[2], array[0],array[1]);
            }
            //Eğerki tüm üyelere mail gönderilecekse
            else if (usertype.equals("2")) {
                sendmail(Email[2], array[0],array[1]);
            }
        }
    }

    public String[] getSubjectAndText(){
        //Başlık ve metnin girildiği fonksiyon
        Scanner input = new Scanner(System.in);

        String subject,text;
        System.out.println("Göndermek istediğin mailin konusunu yazınız:");
        subject = input.nextLine();

        System.out.println("Göndermek istediğiniz maili yazınız:");
        text = input.nextLine();

        String[] stringarray = {subject,text};

        return stringarray;
    }
    public void sendmail(String mail,String subject,String text){

        //Mailin gönderildiği fonksiyon
        String host = "smtp.gmail.com";
        String port = "465";
        String username = "";
        String password = "";
        String fromAddress = "";
        String toAddress = mail;


        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromAddress));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            System.out.println("Failed to send email. Error message: " + e.getMessage());
        }
    }
}
