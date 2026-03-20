/**
 * @class Porta
 * @brief Classe per gestionar els objectes del laberint.
 *
 * @details 
 * Aquesta classe gestiona mètodes que tenen els objectes del nostre laberint (claus i Smart Glasses).
 * @author arnaulloret
 */

public interface Objecte {
    private boolean alTerra=false;
    public boolean estaTirat(){
        return alTerra;
    }
    /** @pre:
    @post: retorna true si l'objecte està al terra. false altrament */

    
}
