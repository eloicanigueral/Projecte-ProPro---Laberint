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

import java.util.ArrayList;


public class Laberint {

    private ArrayList<Espai> espais;
    private ArrayList<Personatge> personatges;

    /** Crea un laberint. */
    public Laberint() {
        this.espais = new ArrayList<Espai>();
        this.personatges = new ArrayList<Personatge>(); 
        personatges.add(new Personatge("huma", 10)); //aixo es aixi???? (de prova)
    }

    /** @return La sala d'entrada d'aquest laberint. */ //HA DE RETORNAR UNA LLISTA... PQ NHI POT HAVER MES DE UNA TANT DE ENTRADA COM DE SORTIDA
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
     * @pre Queda algun personatge humà viu dins el laberint
     * 
     * @post Avança un torn
     */
    public void seguentTorn(){}  //millor fer tot aixo en una altra classe aprat.. que sigui per tota la simulacio i tal.. com un main

    //metode moviment...
    /// balblabal crido actuar del personatge que li toqui
    /// i dsps miro si hi ha objectes al terra, si nhi ha, recollir objecte personatge
}