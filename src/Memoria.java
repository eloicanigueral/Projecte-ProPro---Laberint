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

public class Memoria{

    /**
     * @pre El personatge esta a un espai e
     * 
     * @post S'afegeix l'espai a la cua d'espais visitats (que es recorden), juntament
     * indicant si aquest és o no perillós (s'ha vist algun alien o restes humanes)
     */
    public void recordarEspai(Espai e){}

    /**
     * @post S'elimina de memoria l'espai visitat fa més temps (el primer de la cua)
     */
    public void oblidarEspai(){}

    /**
     * @pre Es tracta d'un alien petit
     * 
     * @post Se li suma la capacitat de memoria
     */
    public void augmentarCapacitat(int x){}
}