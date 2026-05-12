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

import java.util.ArrayList;


public class AlienGran extends Personatge{

    /**
     * @pre Es crida el constructor de l'alien gran juntament amb la seva capacitat de memòria
     * 
     * @post Es crea l'alien gran amb la seva capacitat de memoria determinada
     */
    public AlienGran(String nom) {
        super(nom, -1);
    }

    /**
     * @pre Està a una sala juntament amb un humà
     * 
     * @post Elimina / mata a un personatge que estigui a la mateixa sala que ell en el seu torn
     */
    public void matar(Personatge p){
        p.morir(p.claus, p.teSmartGlasses());
        espaiActual.sortir(p);
        System.out.println("   -> " + nom + " MATA a " + p.getNom()); //per borrarrr!!!
    }

    /**
     * @pre --
     * @post es decideix quina accio fara l'alien (moure's de sala / quedar-se i matar)
     */
    public void actuar(){ //revisar actuar!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        if (!espaiActual.hiHaGuardia() && espaiActual.hiHaVictimes()){ //ha de matar
            int i = 0;
            boolean haMatat = false;
            while(i<espaiActual.getPersonatges().size() && !haMatat){
                Personatge p = espaiActual.getPersonatges().get(i);
                if(p instanceof Huma || p instanceof Porter){
                    matar(p);
                    haMatat = true;
                }
                i++;
            }
            
            
            
            // for(int i=0; i<espaiActual.getPersonatges().size(); i++){ //seria mes facil mirar si hi ha huma i/o porter abans de fer tot aquest for.....
            //     Personatge p = espaiActual.getPersonatges().get(i);
            //     if (p instanceof Huma || p instanceof Porter){
            //         matar(p);
            //         return; //no magrada aquest return................
            //     }
            // }
        } else { //si no pot matar.. s'ha de moure ----------------- mirar com tinc a alienpetit
            Porta seguent = escollirSeguentPorta();
            if (seguent != null){
                Espai origen = espaiActual;
                Espai desti = seguent.altreCostat();
                memoria.recordarEspai(espaiActual, espaiActual.esPerillos()); //alien gran havia de tenir memoria?????

                espaiActual.sortir(this);
                seguent.altreCostat().entrar(this);
                System.out.println("   -> " + nom + " es mou de sala " + origen.mostrarId() + " a sala " + desti.mostrarId());

            }
        }
    }

    /**  ----------------------------------------- per ferrrr i revsiarr!!!!!!!!!!!!!!!!!!!11 .. mirar com el tinc a alienpetit i quiens diferencies ha de tenir!
     * @pre --
     * @post S'escull la seguent porta
     */
    public Porta escollirSeguentPorta(){ //sha de fer aixi.. amb 3 arraylist?? arnau ho te semblant crec.. perooo es aixi???????....
        //sha de refer i revisar toot.. pqqq si canviem lu de porta de sortida un bool o algo... en comptes de altrecostat == null... ii lu altre no he mirat...
        //si hi ha huma no sempre pot entrar... si esta plena i nomes hi ha aliens no hi pot entrar.. com tinc aixo en compte???>....
        ArrayList<Porta> portes = new ArrayList<>(espaiActual.getPortes());
        ArrayList<Porta> recorda = new ArrayList<>();
        ArrayList<Porta> noRecorda = new ArrayList<>();


        
        for (int i = 0; i < portes.size(); i++) {
            if (portes.get(i).altreCostat().esSortida()) continue; // sortida, saltar //oooo aquest?? quin dels dos?
            if (portes.get(i).altreCostat().estaPle() && !portes.get(i).altreCostat().hiHaVictimes()) continue;
            if (memoria.recorda(portes.get(i).altreCostat())) recorda.add(portes.get(i));
            else noRecorda.add(portes.get(i));
        }
        
        //Random rand = new Random();
        if (noRecorda.size() > 0) return noRecorda.get(rand.nextInt(noRecorda.size()));
        
        // totes visitades, va a una de no perillosa
        ArrayList<Porta> noPerillosa = new ArrayList<>();
        for (int i = 0; i < recorda.size(); i++) {
            if (!memoria.esPerillos(recorda.get(i).altreCostat())) noPerillosa.add(recorda.get(i));
        }
        if (noPerillosa.size() > 0) return noPerillosa.get(rand.nextInt(noPerillosa.size()));
        
        if (recorda.size() == 0 && noRecorda.size() == 0) return null;
        // totes perilloses, random
        System.out.println(recorda.size());
        return recorda.get(rand.nextInt(recorda.size()));
    }

}