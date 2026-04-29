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
import java.util.Queue;
public class SmartGlasses {
    private ArrayList<Porta> sortidesExcloses;

    public SmartGlasses(){
        sortidesExcloses = new ArrayList<>();
    }
    /** @return Retorna el seguent espai al qual s'ha d'accedir per arribar de forma ràpida a la sortida */
    public Espai camiRapid(Espai origen){
       //fa un bfs per arribar a la sortida mes propera
        Queue<Espai> cua = new LinkedList<>();
        Espai e = new Espai(1,1);
        return e;
       
    }
//     ha de rebre l'espai origen per saber des de on ha de comencar....
// I afegir exclourePortaSortida(), inclourePortaSortida(), reinicialitzar() ???

    /**
     * @pre S'ha accedit a un espai que no estava previst per la ruta de les smartGlasses
     * 
     * @post Recalcula la ruta tornant a buscar el camí més òptim per tal d'arribar a la sortida
     */
    public void recalcularRuta(){

    }
}