import java.util.ArrayList;
import java.util.Random;

/**
 * @class Guardia
 * @brief Classe per especificar com serà el guardia del laberint.
 *
 * @details
 * Aquesta classe dona les característiques especials que té el guardia del laberint. 
 * Aquest personatge té l'habilitat de protegir els personatges (humans) que estan a la mateixa
 * sala que ell. En el cas que estigui sol a una sala amb un àlien, aquest no se'l pot menjar 
 * (és immortal). I si hi ha àliens i personatges en aquella sala no hi haurà morts.
 * 
 * 
 *
 * @author arnaulloret
 */

public class Guardia extends Personatge{
    public Guardia(String nom, int capacitatMemoria, ArrayList<Integer> claus){
        super(nom,capacitatMemoria,claus);
    }

    /**
    @pre: --
    @post: mètode principal perquè el personatge actui. Primer recull les claus del terra (no agafa repetides)
    * i es mou o no de sala depenent del retorn del mètode escollirSeguentPorta. Si es mou desprotegeix els humans de la sala actual
    * es mou i protegeix els humans de la sala nova.
    */
    public void actuar(){
        ArrayList<Integer>clausRecollides = new ArrayList<>();
        clausRecollides = recollirClaus();

        Porta seguent = null;
        if(!espaiActual.hiHaAlien(this)){
            seguent = escollirSeguentPorta();
        }
        Espai origen = espaiActual;
        Espai desti = null;
        int idDesti = 0;
        if(seguent != null){
            desti = seguent.altreCostat();
            idDesti = desti.mostrarId();
            if(!desti.estaPle()){
                memoria.recordarEspai(espaiActual,espaiActual.esPerillos());
                espaiActual.sortir(this);
                desti.entrar(this);
            }
            else{
                idDesti*=-1;
            }
        }
        else{
            idDesti=0;
        }
        mostrarMoviment(clausRecollides,false,idDesti,null);

    }

    /** 
    @pre: --
    @post: aplica l'estratègia per triar la millor porta segons el seu criteri.
    */
    public Porta escollirSeguentPorta(){
        ArrayList<Porta> portes = new ArrayList<>(espaiActual.getPortes());

        Porta escollida = null;
        ArrayList<Porta> recorda = new ArrayList<>();
        ArrayList<Porta> noRecorda = new ArrayList<>();
        for(int i=0;i<portes.size();i++){
            boolean borra = false;
            if(portes.get(i).altreCostat().estaPle()) borra=true; //si volem que guardia entri a sales plenes treure aixo
            if(!portes.get(i).estaOberta() && !claus.contains(portes.get(i).comprovarClau())) borra=true;
            if(borra){
                portes.remove(i);
                i--;
            } 
            else{
                if(memoria.recorda(portes.get(i).altreCostat())) recorda.add(portes.get(i));
                else noRecorda.add(portes.get(i));
            }
            
        }

        if(noRecorda.size() == 0){
            ArrayList<Porta> perillosa = new ArrayList<>();
            ArrayList<Porta> noPerillosa = new ArrayList<>();
            for(int i=0; i<recorda.size(); i++){
                if(memoria.esPerillos(recorda.get(i).altreCostat())) perillosa.add(recorda.get(i));
                else noPerillosa.add(recorda.get(i));
            }
            if(espaiActual.esPerillos()) escollida = null;
            if(!espaiActual.esPerillos() && perillosa.size() > 0){
                escollida = perillosa.get(rand.nextInt(perillosa.size()));
            }
            if(espaiActual.esPerillos() && perillosa.size() == 0){
                escollida = null;
            }
        }
        else{
            escollida = noRecorda.get(rand.nextInt(noRecorda.size()));
        }
        
        return escollida;

    }

    
    
}