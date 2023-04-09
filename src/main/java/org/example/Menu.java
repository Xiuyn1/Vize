package org.example;

import java.io.*;
import java.util.Scanner;

public class Menu extends Main {

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
