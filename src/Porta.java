/**
 * @class Porta
 * @brief Classe per gestionar les portes del laberint.
 *
 * @details 
 * Representa una porta del laberint que connecta dos espais: 
 * tant una sala i un passadís, una sala amb una altre sala, o una sala i l'exterior.
 * 
 * Cada porta pot tenir pany en un costat o a tots dos i només es podrà 
 * obrir amb la clau corresponent. 
 * 
 * Quan un personatge passa per una porta es tancarà automaticament. 
 * En el cas que el personatge sigui el clauer (nou personatge) la porta es mantidrà 
 * oberta un nombre de moviments, i durant aquest temps tots els
 * personatges podràn passar encara que no tinguin la clau de la porta.
 * 
 * Encara que estigui oberta el sentit de pas serà el mateix (només s'entra per on hi ha el pany).
 * @author arnaulloret
 */
//import java.util.ArrayList;

public class Porta {
    private int codi;
    private int comptadorMoviments;
    private boolean oberta;
    Espai origen, desti;

    public Porta(int codi, Espai o, Espai d){
        this.codi = codi;
        comptadorMoviments=0;
        oberta = false;
        origen = o;
        desti = d;
    }

    public int getCodi(){
        return codi;
    }
    /** per saber si un personatge podrà obrir una porta des d'un espai determinat.
    @pre: p està a l'espai origen.
    @post retorna true si el personatge pot obrir la porta (té la clau), false altrament. */

    public void obrir(){
        comptadorMoviments=3;
        oberta = true;
    }
    /** obre la porta
    @pre: --
    @post: la porta s'obre. */
    public void baixarComptador(){
        comptadorMoviments--;
        if(comptadorMoviments <= 0) oberta = false;
    }

    public boolean estaOberta(){
        return oberta;
    }
    /** indica si la porta està oberta
    @pre: --
    @post: retorna true si la porta està oberta, false altrament. */


    public int comprovarClau(){
        return codi;
    }
    /** per saber el codi de la porta
    @pre: --
    @post: retorna el codi identificador de la porta. */

    public Espai altreCostat(){
        
        return laberint.altreCostat(this.codi);
    }
}