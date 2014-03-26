package de.htw.saarland.stl;
import java.io.*;

public class StdinTest2 {
    public static void testString() {
        String s;
        for (int i = 0; i < 5; i++) {
            s = Stdin.readlnString(i + ".Zeile");
            System.out.println(i + ": |" +s + "|");
        }        
    }
    

    public static void testFile(){
        File out = new File("testeingabe.txt");
        try {
            PrintWriter pw = new PrintWriter(out);
            pw.println("123");
            pw.println("3,14");
            pw.println("Dies ist ein Satz");
            pw.println("aaa bbb cc ");
            pw.close();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
//        Stdin.switchToFile(out);
//        int i = Stdin.readlnInt();
//        double x = Stdin.readlnDouble();
//        String zeile = Stdin.readlnString();
//        String wort1 = Stdin.readString();
//        System.out.println("i = " + i);
//        System.out.println("x = " + x);
//        System.out.println("zeile = " + zeile);
//        System.out.println("wort1 = " + wort1);
                       
    }
    
    public static void testFile2(){
        File out = new File("testeingabe.txt");
        try {
            PrintWriter pw = new PrintWriter(out);
            pw.println("123");
            pw.println("3,14");
            pw.println("Dies ist ein Satz");
            pw.println("aaa bbb cc ");
            pw.close();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
//        Stdin.switchToFile("testeingabe.txt");
//        int i = Stdin.readlnInt();
//        double x = Stdin.readlnDouble();
//        String zeile = Stdin.readlnString();
//        String wort1 = Stdin.readString();
//        System.out.println("i = " + i);
//        System.out.println("x = " + x);
//        System.out.println("zeile = " + zeile);
//        System.out.println("wort1 = " + wort1);
                       
    }
    public static void main(String [] args) {
        testFile();
        testFile2();
    }

}
