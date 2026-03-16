/**
 * @class Espai
 * @brief Classe per gestionar cada espai del laberint
 *
 * @details
 * Un espai és una zona del laberint on es troben una sèrie de personatges
 * i objectes. Un espai pot ser una sala i un passadís del laberint. La única
 * diferència entre aquests dos és que un passadís només pot tenir dues portes
 * i una sala en pot tenir 4 com a màxim.
 * 
 * Cada espai tindrà una capacitat màxima de visitants i pot estar connectat amb altres 
 * espais o l'exterior.
 * 
 * Els personatges poden entrar i sortir dels espais sempre que no es superi la capacitat.
 *
 * @author arnaulloret
 */
public class Espai {
    public boolean esPle(){}
    /** per saber si hi cap més gent a una sala
    @pre: --
    @post: retorna true si la sala ha arribat al màxim de la seva capacitat. false altrament.*/

    public void entrar(Personatge p){}
    /** fer entrar un personatge a l'espai
    @pre: espai no és ple
    @post: el personatge passa a estar a dins de l'espai. */

    public void sortir(Personatge p){}
    /** fer sortir a un personatge de l'espai
    @pre p està a dins de l'espai
    @post el personatge deixa d'estar dins de l'espai.*/

    public List<Porta> getPortes(){}
    /**per saber les portes que té aquest espai
    @pre: --
    @post: retorna una List de totes les portes que hi ha a l'espai*/

    public List<Personatge> getPersonatges(){}
    /** per saber els personatges que hi ha dins un espai
    @pre: --
    @post: retorna una List dels personatges que hi ha actualment a l'espai. */
    
    
    

}
