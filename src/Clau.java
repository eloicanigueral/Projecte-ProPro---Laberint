/**
 * @class Clau
 * @brief Classe per gestionar el comportament de les claus.
 *
 * @details
 * Cada clau està associada a un codi que identifica la porta que pot obrir.
 * Una clau pot ser utilitzada per qualsevol personatge que la tingui.
 * Les claus es troben al labertint.
 * Una clau pot ser mestre (pot obrir qualsevol porta)
 * 
 *
 * @author arnaulloret
 */

public class Clau {
    public int getCodi(){}
    /**retorna el codi de la clau
    @pre: --
    @post: retorna el codi identificador de la clau */

    public boolean esMestre(){}
    /** per saber si la clau es mestre o no
    @pre: --
    @post retorna true si la clau és mestre (pot obrir tot), false altrament */

}