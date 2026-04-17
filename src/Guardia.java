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
 * ????Apart d'això té la desaventatge de no poder recordar a quines sales hi ha àliens o personatges, només 
 * podrà recordar quines sales ha visitat.????
 * 
 *
 * @author arnaulloret
 */

public class Guardia extends Personatge{
    public Guardia(String tipusPersonatge, int capacitatMemoria){
        super(capacitatMemoria);
    }
    public void protegirHumans(){}
    /** aplica immunitat als humans que hi ha a la sala que entra
    @pre: --
    @post: els altres personatges humans de la sala actual del guardia passen a tenir immunitat */

    public void desprotegirHumans(){}
    /** treu la immunitat quan el guardia marxa de la sala
    @pre: --
    @post: els altres personatges de la sala deixen de tenir immunitat amb els aliens */

    public void actuar(){}
    /** tria a quina sala vol anar, desprotegeix els humans de la sala actual, es mou de sala, protegeix els humans de la sala nova
    @pre: --
    @post: s'han desprotegit els humans de la sala actual, s'ha escollit la millor porta, s'ha mogut de sala i s'han protegit els nous humans.
    */
}