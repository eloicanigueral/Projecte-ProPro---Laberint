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

public class AlienPetit extends Personatge{

    /**
     * @pre Està a una sala juntament amb un humà
     * 
     * @post Elimina / mata a un personatge que estigui a la mateixa sala que ell en el seu torn
     */
    public void matar(){}

    /**
     * @post es decideix quina accio fara l'alien (moure's de sala / quedar-se i matar)
     */
    public void actuar(){}

    /**
     * @post S'escull la seguent porta
     */
    public Espai escollirSeguentPorta(){}

}