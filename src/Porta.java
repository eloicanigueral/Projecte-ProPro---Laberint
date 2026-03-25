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

public class Porta {
    public boolean potObrir(Personatge p, Espai origen){}
    /** per saber si un personatge podrà obrir una porta des d'un espai determinat.
    @pre: p està a l'espai origen.
    @post retorna true si el personatge pot obrir la porta (té la clau), false altrament. */

    public void obrir(){}
    /** obre la porta
    @pre: --
    @post: la porta s'obre. */

    public boolean estaOberta(){}
    /** indica si la porta està oberta
    @pre: --
    @post: retorna true si la porta està oberta, false altrament. */

    public void tancar(){}
    /** tanca la porta
    @pre: --
    @post: la porta es tanca. */

    public int getCodi(){}
    /** per saber el codi de la porta
    @pre: --
    @post: retorna el codi identificador de la porta. */
}