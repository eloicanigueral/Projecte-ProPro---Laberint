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
    public static final int MOVIMENTS_PORTA_OBERTA = 3;

    public Porta(int codi, Espai o, Espai d){
        this.codi = codi;
        comptadorMoviments=0;
        oberta = false;
        origen = o;
        desti = d;
    }
    
    /**
     * @pre --
     * @post la porta queda oberta durant el nombre de moviemnts configurat.
     */
    public void obrir(){
        comptadorMoviments=MOVIMENTS_PORTA_OBERTA;
        oberta = true;
    }
    
    /**
     * @pre --
     * @post baixa en 1 el comptador de moviments. Si aquest ha arribat a 0 tanca la porta.
     */
    public void baixarComptador(){
        comptadorMoviments--;
        if(comptadorMoviments <= 0) oberta = false;
    }

    /** indica si la porta està oberta
    @pre --
    @post retorna true si la porta està oberta, false altrament. 
    */
    public boolean estaOberta(){
        return oberta;
    }
    
    /**
     * @pre --
     * @post retorna el codi de la porta
     */
    public int comprovarClau(){
        return codi;
    }
    
    /**
     * @pre --
     * @post retorna l'espai que hi ha a l'altre costat de la porta
     */
    public Espai altreCostat(){
        return desti;
    }
}