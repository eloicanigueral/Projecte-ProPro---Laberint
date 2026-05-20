/**
 * @class SmartGlasses
 * @brief Modul per gestionar les smart glasses.
 *
 * @details Aquestes ulleres son un item molt útil pels humans, ja que serveix per trobar el camí més ràpid per arribar a la sortida.
 * S'ha de tenir en compte que aquestes no tenen en compte els espais perillosos, ni les claus que el personatge pugui tenir, així que no sempre serà possible
 * ni òptim seguir la ruta proposada per les ulleres.
 *   
 * @invariant .
 * @invariant .
 *
 * @author arnaulloret
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;
public class SmartGlasses {
    private ArrayList<Porta> sortidesExcloses; //les portes que no tindrà en compte les SmartGlasses per sortir del laberint

    public SmartGlasses(){
        sortidesExcloses = new ArrayList<>();
    }
    
    /**
     * @pre cert
     * @post calcula el camí més ràpid cap a una sortida no excloa i retorna la següent porta per seguir el cami més ràpid fins a la sortida.
     */
    public Porta camiRapid(Espai origen){
       //fa un bfs per arribar a la sortida mes propera
        Queue<Espai> cua = new LinkedList<>();
        HashMap<Espai, Porta> primerPas = new HashMap<>();

        ArrayList<Porta> portesOrigen = origen.getPortes();
        for(int i=0; i<portesOrigen.size();i++){
            Porta porta = portesOrigen.get(i);
            if(sortidesExcloses.contains(porta)) continue;

            Espai desti = porta.altreCostat();
            if(desti.esSortida()) return porta;
            primerPas.put(desti,porta);
            cua.add(desti);
        }

        while(!cua.isEmpty()){
            Espai actual = cua.poll();
            ArrayList<Porta> portes = actual.getPortes();
            
            for(int i=0; i<portes.size();i++){
                Porta porta = portes.get(i);
                if(sortidesExcloses.contains(porta)) continue;

                Espai desti = porta.altreCostat();
                if(desti.esSortida()) return primerPas.get(actual);
                if(!primerPas.containsKey(desti)){
                    primerPas.put(desti,primerPas.get(actual));
                    cua.add(desti);
                }
            }
        }
        return null;
    }

    
    /**
     * @pre p és una porta de sortida
     * @post la porta p s'exclou de l'algorisme de camí ràpid.
     */
    public void exclourePortaSortida(Porta p){
        if(!sortidesExcloses.contains(p)){
            sortidesExcloses.add(p);
        }
    }

    /**
     * @pre p s'ha exclós anteriorment
     * @post la porta p s'inclou a l'algorisme de camí ràpid.
     */
    public void inclourePortaSortida(Integer clau){
        for(int i=0; i<sortidesExcloses.size();i++){
            Porta p = sortidesExcloses.get(i);
            if(p.comprovarClau() == clau){
                sortidesExcloses.remove(i);
                i--;
            }
        }
    }

    /**
     * @pre --
     * @post s'esborra list de sortides excloses perquè seguent personatge no tingui res a les ulleres.
     */
    public void reiniciarUlleres(){
        sortidesExcloses.clear();
    }

    
}