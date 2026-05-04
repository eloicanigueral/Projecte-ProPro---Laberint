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
import java.util.Random;

public class AlienPetit extends Personatge{

    private ArrayList<Integer> claus;
    /**
     * @pre Es crida el constructor de l'alien petit juntament amb la seva capacitat de memòria
     * 
     * @post Es crea un alien petit amb la seva capacitat de memoria inicial
     */
    public AlienPetit(String nom, int capacitatMemoria, ArrayList<Integer> claus) {
        super(nom, capacitatMemoria, claus);
        //this.nom = nom; //no cal no???....
    }

    /**
     * @pre Està a una sala juntament amb un humà
     * 
     * @post Elimina / mata a un personatge que estigui a la mateixa sala que ell en el seu torn
     */
    public void matar(Personatge p){ //cridar Espai.restesHumanes (o algo aixi...)
        //agafar les claus que no tingui!!!!!!!!!!!!! (les altres es queden al terra!!!!!!!!!)
        //sumar la memoooriaa!!!!!!!!!
        p.morir(p.claus, p.teSmartGlasses()); ///mmm clar aquest teSmartGlasses no es pot no??
        this.memoria.augmentarCapacitat(p.memoria.capacitatMemoria()); //aixo esta be???.. jujuju
        espaiActual.sortir(p);
        System.out.println("   -> " + nom + " MATA a " + p.getNom());
    }

    /**
     * @post es decideix quina accio fara l'alien (moure's de sala / quedar-se i matar)
     */
    public void actuar(){
        if(espaiActual.hiHaAlien(this)){
            Porta seguent = escollirSeguentPorta();
            if (seguent != null) {
                Espai desti = seguent.altreCostat();
                if(!desti.estaPle()){
                    //memoria.recordarEspai(espaiAcutal,true);
                    espaiActual.sortir(this);
                    desti.entrar(this);
                }
            }
            return; //no macaba de molar el return aquest.................. (aques ho puc arreglar amb un else a sota...)
        }

        if (!espaiActual.hiHaGuardia()){
            for(int i=0; i<espaiActual.getPersonatges().size(); i++){
                Personatge p = espaiActual.getPersonatges().get(i);
                if (p instanceof Huma || p instanceof Porter){
                    matar(p);
                    return; //no magrada aquest return................
                }
            }
        }

        Porta seguent = escollirSeguentPorta();
        if (seguent != null) {
            Espai desti = seguent.altreCostat();
            if(!desti.estaPle()){
                //memoria.recordarEspai(espaiAcutal,true);
                espaiActual.sortir(this);
                desti.entrar(this);
            }
        }
    }

    /**
     * @post S'escull la seguent porta
     */
    public Porta escollirSeguentPorta(){ //la he copiat de huma... no me la he ni mirat... lha canviat..  
        ArrayList<Porta> portes = espaiActual.getPortes();
        Porta escollida = null;
        for(int i=0;i<portes.size();i++){
            boolean borra = false;
            if(portes.get(i).altreCostat().estaPle()) borra=true;
            if(!portes.get(i).estaOberta() && !claus.contains(portes.get(i).comprovarClau())) borra=true;
            if(borra) portes.remove(i);
        }
        
        ArrayList<Porta> recorda = new ArrayList<>();
        ArrayList<Porta> noRecorda = new ArrayList<>();
        for(int i=0; i<portes.size(); i++){
            if(memoria.recorda(portes.get(i).altreCostat())) recorda.add(portes.get(i));
            else noRecorda.add(portes.get(i));
        }
        if(noRecorda.size() == 0){
            ArrayList<Porta> perillosa = new ArrayList<>();
            ArrayList<Porta> noPerillosa = new ArrayList<>();
            for(int i=0; i<recorda.size(); i++){
                if(memoria.esPerillos(recorda.get(i).altreCostat())) perillosa.add(recorda.get(i));
                else noPerillosa.add(recorda.get(i));
            }
            if(noPerillosa.size() == 0 && !espaiActual.esPerillos()){}
            else if(noPerillosa.size() == 0 && espaiActual.esPerillos()){
                Random rand = new Random();
                escollida = perillosa.get(rand.nextInt(perillosa.size()));
            }
            else if(noPerillosa.size() > 0){
                Random rand = new Random();
                escollida = noPerillosa.get(rand.nextInt(noPerillosa.size()));
            }
        }
        else{
            Random rand = new Random();
            escollida = noRecorda.get(rand.nextInt(noRecorda.size()));
        }
        
        return escollida; 
    }

    //emm he de crear una que sigui per poder augmentar la memoria de lalien!!!!
    //a memoria hi tinc un augmentarCapacitat.....

    @Override
    public int nombreClaus() {
        return claus.size();
    }
}