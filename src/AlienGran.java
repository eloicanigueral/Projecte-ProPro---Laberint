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
    public void actuar(){
        if (!espaiActual.hiHaGuardia() && espaiActual.hiHaVictimes()){ //si no h i ha guardia i hi ha victimes, ha de matar
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
        } else { //si no pot matar.. s'ha de moure ----------------- mirar com tinc a alienpetit
            Porta seguent = escollirSeguentPorta();
            if (seguent != null){
                Espai origen = espaiActual;
                Espai desti = seguent.altreCostat(); //revisar aixo de desti i tal.. ---------- mirar com ho te l'arnau pq nose pas si comprova limits.... (millor mirar el d alienpetit)
                if(!desti.estaPle() || desti.hiHaVictimes()){ //encara que estigui plena la sala, si hi ha alguna victima pot entrar (per matarla despres)???
                    memoria.recordarEspai(espaiActual, espaiActual.esPerillos());
                    espaiActual.sortir(this);
                    desti.entrar(this);
                    System.out.println("   -> " + nom + " es mou de sala " + origen.mostrarId() + " a sala " + desti.mostrarId());
                }
                //else {} que es quan s'ha intentat moure peroo no ha pogut!!!!!!!!!!!!!!!!
            }
        }
    }

    /**  ----------------------------------------- revsiarr!!!!!!!!!!!!!!!!!!!11 ha de ser igual que la de huma.. comprarar amb l'arnau 
     * lu unic que he de canviar es que trii una porta que no sigui sortida.. (com alien petit)
     * mirar tambe la de alien petit pq hi ha posat comentaris que li poden servir a l'arnau...
     * @pre --
     * @post S'escull la seguent porta
     */
    public Porta escollirSeguentPorta(){ //s'ha de moure com huma, excepte que ha de triar una que NO sigui sortida

        ArrayList<Porta> portes = new ArrayList<>(espaiActual.getPortes());
        
        ArrayList<Porta> recorda = new ArrayList<>();
        ArrayList<Porta> noRecorda = new ArrayList<>();

        Porta escollida = null;

        for(int i=0; i<portes.size(); i++){
            boolean borra = false;
            if (portes.get(i).altreCostat().esSortida()) borra = true;
            if(!portes.get(i).estaOberta() && !claus.contains(portes.get(i).comprovarClau())) borra=true;
            if(borra) {
                portes.remove(i);
                i--;
            } else{
                if(memoria.recorda(portes.get(i).altreCostat())) recorda.add(portes.get(i));
                else noRecorda.add(portes.get(i));
            }
        }       
                
        ArrayList<Porta> perillosa = new ArrayList<>();
        ArrayList<Porta> noPerillosa = new ArrayList<>();
        for(int i=0; i<recorda.size(); i++){
            if(memoria.esPerillos(recorda.get(i).altreCostat())) perillosa.add(recorda.get(i));
            else noPerillosa.add(recorda.get(i));
        }
        
        
        //aquesta part de aqui: subt6utiur perl comentari???
        ArrayList<Porta> perillosa = new ArrayList<>();
        ArrayList<Porta> noPerillosa = new ArrayList<>();
        for(int i=0; i<recorda.size(); i++){
            if(memoria.esPerillos(recorda.get(i).altreCostat())) perillosa.add(recorda.get(i));
            else noPerillosa.add(recorda.get(i));
        }

        if(noPerillosa.size()>0 /*&& espaiActual.esPerillos() //si hi ha no perilloses.. directe random de ls noves.. no cal quedarse al mateix lloc, per tant no cal comprovar si lactual es o no es eprills*/){ //actuen evitant els altres aliens, per tant, evitant espais perillosos
            escollida = noPerillosa.get(rand.nextInt(noPerillosa.size()));
        } else if(noPerillosa.size()==0 && !espaiActual.esPerillos()){ //totes son perilloses excepte l'espai actual, no es mou
            escollida = null; //ooo espaiActual???.. llavors com indico que no sha mogut???....
        } else if(noPerillosa.size()==0){ //si totes son perilloses i la actual tambe, es mou random
            escollida = perillosa.get(rand.nextInt(perillosa.size()));
        } //jo crec q no cal else.. acabar d emirar i comprovar peroo... (lultim else if podria ser else tal qual i ja esta...)
    }
    else{
        escollida = noRecorda.get(rand.nextInt(noRecorda.size()));
    }
    
    return escollida; 


        //-------------------MIRAR AIXO.. INTENTAR ADAPTAR EL DE ABAIX: AIXI ENS ESTALVIEM UN ARRAYLIST!!!!!!!!!!!!!!!! -------------
        // if (noRecorda.size() > 0) return noRecorda.get(rand.nextInt(noRecorda.size()));
        
        // // totes visitades, va a una de no perillosa
        // ArrayList<Porta> noPerillosa = new ArrayList<>();
        // for (int i = 0; i < recorda.size(); i++) {
        //     if (!memoria.esPerillos(recorda.get(i).altreCostat())) noPerillosa.add(recorda.get(i));
        // }
        // if (noPerillosa.size() > 0) return noPerillosa.get(rand.nextInt(noPerillosa.size()));
        
        // if (recorda.size() == 0 && noRecorda.size() == 0) return null;
        // // totes perilloses, random
        // System.out.println(recorda.size());
        // return recorda.get(rand.nextInt(recorda.size()));
    //}

}