/**
 * @class Memoria
 * @brief Modul per gestionar la memoria dels personatges.
 *
 * @details Es el nombre de sales que els personatges recorden. A mesura que avancin espais, deixaran de recordar les primeres.
 * 
 * @invariant La memoria pot augmentar en el cas dels àliens.
 * @invariant Cada personatge té una capacietat de memòria diferent.
 *
 * @author eloicanigueral
 */

import java.util.ArrayList;


public class Memoria{

    private int capacitatMem=0;
    private ArrayList<Pair<Espai, Boolean>> espais; //llista amb lespai i si es segur o no.. (mirar si true es perillos o es segur...)
                    //es crida aixi:  new Pair<Boolean,Ruta>(trobat,r)



    /**
     *  @post: Constructor amb la capacitat de memoria del personatge
     * 
    */
    public Memoria(int mem){
        espais = new ArrayList<>();
        capacitatMem=mem; //si mem es -1 .. pues que el capacitat aquest sigui el nombre de sales no???
    }

    /**
     * @pre El personatge esta a un espai e
     * 
     * @post S'afegeix l'espai a la cua d'espais visitats (que es recorden), juntament
     * indicant si aquest és o no perillós (s'ha vist algun alien o restes humanes)
     */
    public void recordarEspai(Espai e, boolean esPerillos){
        if (capacitatMem>0 && espais.size()>=capacitatMem) {
            espais.remove(0); 
        }
        espais.add(new Pair<>(e, esPerillos));
    }
    // recordarEspai(e, esPerillos) i recordaComAPerillos(e)



    /**
     * @post S'elimina de memoria l'espai visitat fa més temps (el primer de la cua)
     */
    public void oblidarEspai(){ //cal??????????????/
        espais.remove(0); 
    }  //per ferrr!!!!!!!!!!!!!!


    public int capacitatMemoria(){
        return capacitatMem;
    }

    /**
     * @pre Es tracta d'un alien petit
     * 
     * @post Se li suma la capacitat de memoria
     */
    public void augmentarCapacitat(int x){
        capacitatMem += x;
    }  //per ferrr!!!!!!!!!!!!!!

    public boolean esPerillos(Espai e){
        for (int i=0; i<espais.size(); i++) {
            if (espais.get(i).first == e) {
                return espais.get(i).second;
            }
        }
        return false;
    }
    public boolean recorda(Espai e){
        for (int i=0; i<espais.size(); i++) {
            if (espais.get(i).first == e) {
                return true;
            }
        }
        return false;
    }
}