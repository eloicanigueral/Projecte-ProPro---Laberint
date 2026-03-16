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
 * @author eloicanigueral
 */

public class SmartGlasses extends Objecte {

    /** @return Retorna el seguent espai al qual s'ha d'accedir per arribar de forma ràpida a la sortida */
    public Espai camiOptim(){}

    /**
     * @pre S'ha accedit a un espai que no estava previst per la ruta de les smartGlasses
     * 
     * @post Recalcula la ruta tornant a buscar el camí més òptim per tal d'arribar a la sortida
     */
    public void recalcularRuta(){}
}