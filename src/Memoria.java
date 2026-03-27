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
        capacitatMem=mem;
    }

    /**
     * @pre El personatge esta a un espai e
     * 
     * @post S'afegeix l'espai a la cua d'espais visitats (que es recorden), juntament
     * indicant si aquest és o no perillós (s'ha vist algun alien o restes humanes)
     */
    public void recordarEspai(Espai e){} //per ferrr!!!!!!!!!!!!!!
    // recordarEspai(e, esPerillos) i recordaComAPerillos(e)



    /**
     * @post S'elimina de memoria l'espai visitat fa més temps (el primer de la cua)
     */
    public void oblidarEspai(){}  //per ferrr!!!!!!!!!!!!!!

    /**
     * @pre Es tracta d'un alien petit
     * 
     * @post Se li suma la capacitat de memoria
     */
    public void augmentarCapacitat(int x){
        capacitatMem += x;
    }  //per ferrr!!!!!!!!!!!!!!
}