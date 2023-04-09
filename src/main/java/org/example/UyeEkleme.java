package org.example;

import java.io.*;

public class UyeEkleme extends Main {
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
