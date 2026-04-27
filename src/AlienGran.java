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
import java.util.Random;


public class AlienGran extends Personatge{

    /**
     * @pre Es crida el constructor de l'alien gran juntament amb la seva capacitat de memòria
     * 
     * @post Es crea l'alien gran amb la seva capacitat de memoria determinada
     */
    public AlienGran(String nom) { //oooo aquest tenia memoria per a tot el laberint??????? //aqui capacitat = nombre_espais crecc
        super(nom, -1);
        this.nom = nom;
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
    public void actuar(){
        if (!espaiActual.hiHaGuardia()){
            for(int i=0; i<espaiActual.getPersonatges().size(); i++){ //seria mes facil mirar si hi ha huma i/o porter abans de fer tot aquest for.....
                Personatge p = espaiActual.getPersonatges().get(i);
                if (p instanceof Huma || p instanceof Porter){ //sha de fer aixi de llarg??????.....
                    matar(p);
                    return; //no magrada aquest return................
                }
            }
        }

        Porta seguent = escollirSeguentPorta();
        if (seguent != null){
            espaiActual.sortir(this);
            seguent.altreCostat().entrar(this);
        }
    }

    /**
     * @post S'escull la seguent porta
     */
    public Porta escollirSeguentPorta(){ //sha de fer aixi.. amb 3 arraylist?? arnau ho te semblant crec.. perooo es aixi???????....
        //sha de refer i revisar toot.. pqqq si canviem lu de porta de sortida un bool o algo... en comptes de altrecostat == null... ii lu altre no he mirat...
        ArrayList<Porta> portes = espaiActual.getPortes();
        ArrayList<Porta> recorda = new ArrayList<>();
        ArrayList<Porta> noRecorda = new ArrayList<>();
        
        for (int i = 0; i < portes.size(); i++) {
            if (portes.get(i).altreCostat() == null) continue; // sortida, saltar
            if (memoria.recorda(portes.get(i).altreCostat())) recorda.add(portes.get(i));
            else noRecorda.add(portes.get(i));
        }
        
        Random rand = new Random();
        if (noRecorda.size() > 0) return noRecorda.get(rand.nextInt(noRecorda.size()));
        
        // totes visitades, va a una de no perillosa
        ArrayList<Porta> noPerillosa = new ArrayList<>();
        for (int i = 0; i < recorda.size(); i++) {
            if (!memoria.esPerillos(recorda.get(i).altreCostat())) noPerillosa.add(recorda.get(i));
        }
        if (noPerillosa.size() > 0) return noPerillosa.get(rand.nextInt(noPerillosa.size()));
        
        // totes perilloses, random
        return recorda.get(rand.nextInt(recorda.size()));
    }

}