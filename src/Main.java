/**
 * @class Main
 * @brief Classe Main des d'on s'executa tot el programa.
 *
 * @details ......
 * 
 * @author arnaulloret
 */

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        Laberint lab = new Laberint();

        System.out.println("S'ha acabat l'execució.");
        sc.close();
    }
}