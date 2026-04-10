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
        System.out.print("Quin és el nom del fitxer d'entrada de dades?");
        String nomFitxer = sc.nextLine();
        Laberint lab = new Laberint(nomFitxer);

        System.out.print("S'ha acabat l'execució.");
    }
}