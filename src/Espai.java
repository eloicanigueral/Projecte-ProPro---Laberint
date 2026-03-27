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
        this.maxPersonatges = maxPersonatges;
        personatges = new ArrayList<Personatge>();
        clausTirades = new ArrayList<Clau>();
        portes = new ArrayList<Porta>();
    }
    
    public void addPorta(Porta p){
        portes.add(p);
    }
    public boolean estaPle(){
        return nPersonatges == maxPersonatges;
    }
    /** per saber si hi cap més gent a una sala
    @pre: --
    @post: retorna true si la sala ha arribat al màxim de la seva capacitat. false altrament.*/

    public void entrar(Personatge p){
        if(!estaPle()){
            personatges.add(p);
            p.canviEspai(this);
            nPersonatges++;
        }
        
    }

    public int mostrarId(){
        return idEspai;
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

    public ArrayList<Porta> getPortes(){
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

    public boolean hiHaHuma(){
        for(int i=0; i<personatges.size();i++){
            if(personatges.get(i) instanceof Huma) return true;
        }
        return false;
    }
    

}
