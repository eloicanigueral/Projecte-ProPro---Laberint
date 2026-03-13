/**
 * @class Laberint
 * @brief Laberint al que s'ha d'escapar.
 *
 * @details Laberint format per sales, cadascuna d'elles amb una
 * entrada i dues sortides.  Podem imaginar que hi ha una porta a
 * l'entrada de cada sala, i les sortides són portes d'entrada a una
 * altra sala. Si una sortida comunica amb un laberint buit (en lloc
 * d'una sala) representa que no hi ha sortida.
 * 
 * @invariant No hi ha cicles.
 * @invariant Només hi ha una sala del tresor.
 * @invariant Les sales tenen sortida a dues altres sales, o a cap (són cul de sac).
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