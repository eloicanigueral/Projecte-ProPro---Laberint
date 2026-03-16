/**
 * @class Personatge
 * @brief Modul per gestionar els personatges.
 *
 * @details Hi ha diversos tipus de personatge amb capacitats/habilitats/caracteristiques diferents.
 *
 * @author eloicanigueral
 */


public class Personatge {

    /** @return Retorna l'espai actual del personatge. */
    public Espai salaActual(){}

    /** @return Retorna si el personatge esta viu o no. */
    public boolean estaViu(){}

    /** 
     * @post Recull l'objecte del terra i se'l guarda al seu inventari (segons el tipus de personatge, i si ho necessita) */
    public void recollirItem(Objecte o){}    

    /** @return Retorna si el personatge ha sortit del laberint */
    public boolean haSortit(){}
}