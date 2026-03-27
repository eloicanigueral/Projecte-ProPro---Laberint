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

import java.util.ArrayList;
import java.util.List;
public class Espai {
    private int idEspai;
    private int nPersonatges=0;
    private int maxPersonatges;
    private ArrayList<Personatge> personatges;
    private ArrayList<Clau> clausTirades;
    private int smartGlassesTirades;
    private ArrayList<Porta> portes;

    public Espai(int idEspai, int maxPersonatges){
        nPersonatges=0;
        smartGlassesTirades=0;
    }
    public boolean esPle(){
        boolean esPle=false;
        if(nPersonatges == maxPersonatges) esPle=true;
        return esPle;
    }
    /** per saber si hi cap més gent a una sala
    @pre: --
    @post: retorna true si la sala ha arribat al màxim de la seva capacitat. false altrament.*/

    public void entrar(Personatge p){
        if(nPersonatges+1 == maxPersonatges){
            personatges.add(p);
            p.canviEspai(this);
            nPersonatges++;
        }
        
    }
    /** fer entrar un personatge a l'espai
    @pre: espai no és ple
    @post: el personatge passa a estar a dins de l'espai. */

    public void sortir(Personatge p){
        personatges.remove(p);
        nPersonatges--;
    }
    /** fer sortir a un personatge de l'espai
    @pre p està a dins de l'espai
    @post el personatge deixa d'estar dins de l'espai.*/

    public List<Porta> getPortes(){
        return portes;
    }
    /**per saber les portes que té aquest espai
    @pre: --
    @post: retorna una List de totes les portes que hi ha a l'espai*/

    public List<Personatge> getPersonatges(){
        return personatges;
    }
    /** per saber els personatges que hi ha dins un espai
    @pre: --
    @post: retorna una List dels personatges que hi ha actualment a l'espai. */
    public boolean hiHaClaus(){
        return clausTirades.size() > 0;
    }
    public boolean hiHaSmartGlasses(){
        if(smartGlassesTirades > 0) return true;
        else return false;
    }

    public ArrayList<Clau> veureClaus(){
        return clausTirades;
    }

    public void agafarClau(Clau c){
        clausTirades.remove(c);
    }
    public SmartGlasses recollirSmartGlasses(){
        SmartGlasses ulleres = new SmartGlasses();
        return ulleres;
    }

    public void deixarClau(Clau c){
        clausTirades.add(c);
    }

    

}
