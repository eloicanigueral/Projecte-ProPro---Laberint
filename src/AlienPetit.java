/**
 * @class Alien Petit
 * @brief Modul per gestionar als aliens petits.
 *
 * @details Aquests àliens, poden adoptar aparença humana, i així doncs, passar desaparcebuts. Tenen un inventari de claus com els humans,
 * per tant, a la que maten algú, es queden amb les claus (que no tenien), a més, sumen la capacitat de memòria de la persona menjada.
 * 
 * @invariant N'hi pot haver més de 1.
 * @invariant Donen prioritat a l'àlien gran.
 * @invariant No es solapen, si entren a una sala on ja hi havia un alien menjant, surten al seguent torn com si fossin persones.
 * @invariant La seva estratègia és evitar als altres aliens per tal de maximitzar el seu èxit (menjar-se a persones), així que es comporten com persones, evitant els aliens si ho recorden.
 *
 * @author eloicanigueral
 */

import java.util.ArrayList;

public class AlienPetit extends Personatge{

    /**
     * @pre Es crida el constructor de l'alien petit juntament amb la seva capacitat de memòria
     * 
     * @post Es crea un alien petit amb la seva capacitat de memoria inicial
     */
    public AlienPetit(String nom, int capacitatMemoria, ArrayList<Integer> claus) {
        super(nom, capacitatMemoria, claus);
    }

    /**
     * @pre Està a una sala juntament amb un humà
     * 
     * @post Elimina / mata a un personatge que estigui a la mateixa sala que ell en el seu torn
     */
    public void matar(Personatge p){
        p.morir(p.claus, p.teSmartGlasses()); //personatge mor
        espaiActual.sortir(p);
        this.memoria.augmentarCapacitat(p.memoria.capacitatMemoria()); //alien suma la capacitat memoria

        //faig un if abans com a Huma per a veure si hi ha claus a lespai actual???/..... revisar ......................................
        ArrayList<Integer> tirades = espaiActual().veureClaus();
        for (int i = 0; i<tirades.size(); i++) {
            if (!claus.contains(tirades.get(i))) {
                claus.add(tirades.get(i)); //tots aquests de claus.add i tal.. cridar el metode de la classe personatge afegirClau?? si no?? (ara el tinc comentat...)
                espaiActual().agafarClau(tirades.get(i));
                i--;
            }
        }
    
        System.out.println("   -> " + nom + " MATA a " + p.getNom()); //s'ha de borrar
    }

    /**
     * @pre --
     * @post es decideix quina accio fara l'alien (moure's de sala / quedar-se i/o matar)
     */
    public void actuar(){ //emmm dona la prioritat a l'alien gran????? 
        //revisar l'if aquest.. pq si es el primer q entra si q te prioritat per matar.. pero a la que entri un altre.. aixo donara false.. ii igualment pot continuar matant (a no ser que entri l'alien gros)
        if (!espaiActual.hiHaAlien(this) && !espaiActual.hiHaGuardia() && espaiActual.hiHaVictimes()){ //si no hi ha guardia, pot matar (si hi ha alguna victima -> huma o porter)
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
                    
            // for(int i=0; i<espaiActual.getPersonatges().size(); i++){
            //     Personatge p = espaiActual.getPersonatges().get(i);
            //     if (p instanceof Huma || p instanceof Porter){
            //         matar(p);
            //         return; //no magrada aquest return................ 
            //     }
            // }

        } else{ //si hi ha un altre alien (a part de ell...) o no pot matar -> es comporta com un huma
            Porta seguent = escollirSeguentPorta();
            if (seguent != null) { //si decideix no moure-s...hauria de tenir un seguent = espaiActual() oa glo aixi no??? ooo indico com si s mou igual???
                Espai origen = espaiActual;
                Espai desti = seguent.altreCostat();
                if(!desti.estaPle()){
                    memoria.recordarEspai(espaiActual, espaiActual.esPerillos());
                    espaiActual.sortir(this);
                    desti.entrar(this);
                    System.out.println("   -> " + nom + " es mou de sala " + origen.mostrarId() + " a sala " + desti.mostrarId());
                }
                //else {} que es quan s'ha intentat moure peroo no ha pogut!!!!!!!!!!!!!!!!
                //si esta ple i no ha pogut entrar ha de ser la sala pero en negatiu.. mirar!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1

            }
        }
    }

    /**
     * @pre --
     * @post S'escull la seguent porta
     */
    public Porta escollirSeguentPorta(){ //la he copiat de huma... es igual a la majoria no??... la posem a personatges????? a no pq ulleres...  
        //vale revisar aquest.. ha de ser com huma, pq han de evitar altres aliens
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
        
        //      MIRAR COM HO TE LARNAU.. PEROO TENIA EL FOR AIXI COM DUPLICAT.. POSARHO TOT EN EL MATEIX... ELS IFS DINS DE LALTRE!!!
        // ArrayList<Porta> recorda = new ArrayList<>();
        // ArrayList<Porta> noRecorda = new ArrayList<>();
        // for(int i=0; i<portes.size(); i++){
        //     if(memoria.recorda(portes.get(i).altreCostat())) recorda.add(portes.get(i));
        //     else noRecorda.add(portes.get(i));
        // }


        if(noRecorda.size() == 0){ //si recorda totes -> ha de anar a una que recordi com a 'NO PERILLOSA' = que no hi hagi aliens
            //mirar comentari de aliengran.. que es com ho teniem abans,... ens estalviem un arraylist..!!!    
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
    }
}