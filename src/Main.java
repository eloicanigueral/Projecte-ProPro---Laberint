/**
 * @class Main
 * @brief Classe Main des d'on s'executa tot el programa.
 *
 * @details Classe per començar la simulació del laberint i mostrar els resultats
 * de la simulació.
 * 
 * @author arnaulloret
 */

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        Laberint lab = new Laberint();
        int torn = 1;
        if(lab.buit()) System.out.println("El laberint és buit");
        while(!lab.acabat()){
            System.out.println();
            System.out.println(" ---- Torn " + torn + " ---- ");
            lab.seguentTorn();
            torn++;
        }
        System.out.println();
        System.out.println("S'ha acabat el joc.");
        lab.mostrarResultats();
        sc.close();
    }
    
    
}