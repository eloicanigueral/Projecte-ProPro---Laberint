/**
 * @class Laberint
 * @brief Laberint al que s'ha d'escapar.
 *
 * @details Laberint format per sales i passadissos. Cada sala pot tenir més de 2 portes, 
 * els passadíssos només en tenen 2 (un a cada costat).
 * 
 * @invariant Pot haver més d'una entrada, i més d'una sortida.
 * @invariant Si una sala té dues sortides, aquestes porten a sales diferents.
 *
 * @author eloicanigueral
 */
public class Laberint {

    /** Crea un laberint buit. */
    public Laberint() {

    }

    /**
     * Crea un laberint a partir d'una sala i dos laberints.
     * La sala \a entrada s'estableix com a sala d'entrada al laberint.
     * Les portes esquerra i dreta de sortida d'aquesta sala connecten
     * amb el laberint \a esquerre i \a dret, respectivament.
     */
    public Laberint(Sala entrada, Laberint esquerre, Laberint dret) {
        
    }

    /** @return La sala d'entrada d'aquest laberint. */
    public Sala salaEntrada() {

    }

    /** @return La sala de sortida d'aquest laberint. */
    public Sala salaSortida() {

    }


    /** @return El laberint és buit (sense cap sala). */
    public boolean buit() {

    }
}