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
        //lu de ensenyar les sales i a on hi ha la sala sortida i tal... ferho aqui tb no??
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
        //ensenyar els personatges morts
        sc.close();
    }
    
    
}