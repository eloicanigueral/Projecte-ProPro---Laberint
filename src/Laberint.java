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
    private ArrayList<Porta> portes;
    private ArrayList<Personatge> personatges;

    /** Crea un laberint. */
    public Laberint() {
        this.espais = new ArrayList<Espai>();
        this.portes = new ArrayList<Porta>();
        this.personatges = new ArrayList<Personatge>(); 
        


        Espai s1 = new Espai(1, 5);
        Espai s2 = new Espai(2, 5);
        Espai s3 = new Espai(3, 5);
        Espai s4 = new Espai(4, 5);  // sortida

        // portes que les connecten (codi, espaiA, espaiB)
        Porta p1 = new Porta(1, s1, s2);
        Porta p2 = new Porta(2, s2, s3);
        Porta p3 = new Porta(3, s3, s4);

        // afegim les portes als espais
        s1.addPorta(p1);
        s2.addPorta(p1);
        s2.addPorta(p2);
        s3.addPorta(p2);
        s3.addPorta(p3);
        s4.addPorta(p3);

        // humà a la sala 1
        Huma h = new Huma(5);
        h.afegirClau(1);  // pot obrir porta 1
        h.afegirClau(2);  // pot obrir porta 2
        h.afegirClau(3);  // pot obrir porta 3

        s1.entrar(h);

        espais.add(s1); espais.add(s2);
        espais.add(s3); espais.add(s4);
        portes.add(p1); portes.add(p2); portes.add(p3);
        personatges.add(h);
        
        //personatges.add(new Huma(10)); //aixo es aixi???? (de prova)
        //espais.add(new Espai(10)); //aixo es aixi???? (de prova)
        //espais.add(new Espai(10)); //aixo es aixi???? (de prova)
        //portes.add(new Porta(espais.get(0), espais.get(1))); //aixo es aixi???? (de prova)


        //despres de crear tot he de indicar quines son les sales d'entrada i de sortida...
        //amb un random o algo aixi... o tb podria ser que el constructor del laberint rebés com a paràmetre un fitxer amb la configuració del laberint i així ja es crearia tot a partir d'això... (aixo seria lo millor) (però ara per ara ho

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
    public void seguentTorn(){
        for (Personatge p : personatges) {
            if (p.estaViu()) {
                p.actuar();
                //p.recollirObjecte();
            }
        }
    }  //millor fer tot aixo en una altra classe aprat.. que sigui per tota la simulacio i tal.. com un main

    //metode moviment...
    /// balblabal crido actuar del personatge que li toqui
    /// i dsps miro si hi ha objectes al terra, si nhi ha, recollir objecte personatge
}