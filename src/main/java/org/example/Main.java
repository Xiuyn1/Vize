package org.example;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.nio.Buffer;
import java.util.Scanner;
import java.io.*;
import java.util.*;
public class Main {
    static int secim1;
    int secim2;
    static String elitUyeGirisi;
    static String genelUyeGirisi;

    public static void main(String[] args) throws IOException {

        //İstenilmediği sürece sistemden çıkmasın diye menüyü döngü içinde tanımladım.
        while(Menu.secim1!=4) {

            secim1 = new Menu().Menu1();

        }
    }
}
class Menu extends Main {

    public int Menu1() throws IOException{
        Scanner input = new Scanner(System.in);

        //Birinci menü
        System.out.println("1- Elit üye ekleme\n2- Genel Üye ekleme\n3- Mail Gönderme\n4- Sistemden çıkış\nYapmak istediğiz işlem için gerekli sayıyı giriniz.");
        secim1 = input.nextInt();

        //Birinci menüdeki hangi işlemi yapmak istiyorsak onu seçtiğimiz yer
        if (secim1 == 1) {
            System.out.println("Elit üye eklemek için bilgileri ' İsim Soyisim Email ' şeklinde giriniz.");
            input.nextLine();
            elitUyeGirisi = input.nextLine();
            UyeEkleme.elitUyeEkleme();
        }
        else if (secim1 == 2) {
            System.out.println("Genel üye eklemek için bilgileri ' İsim Soyisim Email ' şeklinde giriniz.");
            input.nextLine();
            genelUyeGirisi = input.nextLine();
            UyeEkleme.genelUyeEkleme();
        }
        else if (secim1 == 3) {
            //Mail gönderme kısmına yönlendirme
            Menu2();
        }
        return secim1;
    }
    public void Menu2() throws IOException {
        Scanner input = new Scanner(System.in);

        MailGonderme nesne = new MailGonderme();
        //İkinci menü
        System.out.println("1- Elit üyelere mail\n2- Genel üyelere mail\n3- Tüm üyelere mail\n4- Ana menüye geri dönüş");
        secim2 = input.nextInt();
        //Kime mail atacağımızı seçtiğimiz yer
        if (secim2 == 1){
            nesne.UyelereMail("1");
        }
        else if (secim2 == 2) {
            nesne.UyelereMail("0");

        }
        else if (secim2 == 3) {
            nesne.UyelereMail("2");
        }
    }
}
class UyeEkleme extends Main {
    public static void elitUyeEkleme() throws IOException {
        //Elit üyelerin dosyaya yazıldığı kısım
        File elitUye = new File("üyeler.txt");
        FileWriter fWriterElit = new FileWriter(elitUye,true);
        BufferedWriter bWriterElit = new BufferedWriter(fWriterElit);

        bWriterElit.write(elitUyeGirisi+" 1\n");
        bWriterElit.close();
    }

    public static void genelUyeEkleme() throws IOException {
        //Genel üyelerin dosyaya yazıldığı kısım
        File genelUye = new File("üyeler.txt");
        FileWriter fWriterGenel = new FileWriter(genelUye,true);
        BufferedWriter bWriterGenel = new BufferedWriter(fWriterGenel);

        bWriterGenel.write(genelUyeGirisi+" 0\n");
        bWriterGenel.close();
    }

}
class MailGonderme {
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

