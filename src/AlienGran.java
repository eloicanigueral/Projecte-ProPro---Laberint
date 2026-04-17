/**
 * @class Alien Gran
 * @brief Modul per gestionar a alien gran. 
 *
 * @details Aquest alien té la capacitat d'obrir totes les portes del laberint. El seu objecitu és acabar amb els humans, i per tant, 
 * si hi ha una persona en el mateix espai que ell, se'l menjarà. 
 * 
 * @invariant Només n'hi ha 1 en tot el laberint.
 * @invariant No canviarà de sala fins haver-se menjat a tothom d'aquesta.
 * @invariant No sortirà mai del laberint
 * @invariant Té la capacitat d'entrar als espais encara que estiguin plens si hi ha minim 1 persona humana
 *
 * @author eloicanigueral
 */

public class AlienGran extends Personatge{
    private String nom;


    /**
     * @pre Es crida el constructor de l'alien gran juntament amb la seva capacitat de memòria
     * 
     * @post Es crea l'alien gran amb la seva capacitat de memoria determinada
     */
    public AlienGran(String nom) { //oooo aquest tenia memoria per a tot el laberint??????? //aqui capacitat = nombre_espais crecc
        this.nom = nom;
        super(-1);
    }

    /**
     * @pre Està a una sala juntament amb un humà
     * 
     * @post Elimina / mata a un personatge que estigui a la mateixa sala que ell en el seu torn
     */
    public void matar(Personatge p){
        p.morir();
    }

    /**
     * @post es decideix quina accio fara l'alien (moure's de sala / quedar-se i matar)
     */
    public void actuar(){}

    /**
     * @post S'escull la seguent porta
     */
    public Espai escollirSeguentPorta(){ //PER FERRR!!!!!!!
        return null;
    }

}