/**
 * @class Main
 * @brief Classe Main des d'on s'executa tot el programa.
 *
 * @details ......
 * 
 * @author arnaulloret
 */

public class Main {

    // private static void seguentTorn(Laberint lab) {} //per fer...

    // private  boolean quedenHumans(Laberint lab){} //per fer... 
    public static void main(String[] args) {
        Laberint lab = new Laberint();
        
        for(int i=0; i<5; i++){
            System.out.println("---Torn" + (i+1) + "---");
            lab.seguentTorn();
        }
    }
}