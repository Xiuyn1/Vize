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