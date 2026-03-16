/**
 * @class Laberint
 * @brief Laberint al que s'ha d'escapar.
 *
 * @details El laberint està format per un conjunt d'espais connectats entre si.
 * Dins dels espais s'hi troben personatges i objectes. Un espai pot ser un passadís o una sala.
 * 
 * Aquesta classe serà la responsable de gestionar la simulació del joc,
 * controlant l'ordre de moviment dels personatges i executant els torns
 * de simulació fins que s'acabi el joc.
 * 
 * Es guardarà el nombre de sales que té el laberint, com estan connectades entre si,
 * el nombre de personatges i de quin tipus són.
 * 
 * @author eloicanigueral
 */
public class Laberint {

    /**
     * Crea un laberint.
     */
    public Laberint() {
        
    }

    /** @return La sala d'entrada d'aquest laberint. */
    public Espai salaEntrada(Espai e) {
        return e;
    }

    /** @return La sala de sortida d'aquest laberint. */
    public Espai salaSortida(Espai s) {
        return s;
    }


    /** @return El laberint és buit (sense cap sala). */
    public boolean buit() {
        return false;
    }

    /**
     * @pre Queda algun personatge huma viu
     * 
     * @post Avança un torn
     */
    public void seguentTorn(){}
}