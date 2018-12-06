/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tb.poo;

/**
 *
 * @author tacioss
 */
public class Printer {
    
    public static void cprintln(Object line, String code) {
        System.out.println("\u001B[" + code + "m" + line + "\u001B[0m");
    }
    
    public static void cprint(Object line, String code) {
        System.out.print("\u001B[" + code + "m" + line + "\u001B[0m");
    }
    
    public static void println(Object line) {
        System.out.println(line);
    }
    
    public static void print(Object line) {
        System.out.print(line);
    }

    static void linha(int n, String code) {
        for(int i = 0; i < n; i++){
            Printer.cprint("-", code);
        }
    }

    static void spaces(int n) {
        for(int i = 0; i < n; i++){
            Printer.print(" ");
        }
    }

    
}
