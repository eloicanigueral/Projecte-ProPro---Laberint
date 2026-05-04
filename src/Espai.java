/**
 * @class Espai
 * @brief Classe per gestionar els espais del laberint aixi com totes les accions que passen a dins d'un espai.
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
    private ArrayList<Integer> clausTirades;
    private int smartGlassesTirades;
    private ArrayList<Porta> portes;
    private ArrayList<Integer> espaisConectats;
    private boolean restesHumanes;


    public Espai(int idEspai, int maxPersonatges){
        this.idEspai = idEspai;
        nPersonatges=0;
        smartGlassesTirades=0;
        this.maxPersonatges = maxPersonatges;
        personatges = new ArrayList<Personatge>();
        clausTirades = new ArrayList<Integer>();
        portes = new ArrayList<Porta>();
        espaisConectats = new ArrayList<Integer>();
        restesHumanes = false;
    }
    
    public void conectarEspais(ArrayList<Espai> espaisConnectats){
        System.out.println("Ha entrat a conectar espais (espais)");
        for(int i=0; i<espaisConnectats.size(); i++){
            Porta p = new Porta(espaisConnectats.get(i).mostrarId(),this,espaisConnectats.get(i));
            portes.add(p);

        }
        for(int i=0; i<portes.size();i++){
            System.out.println(portes.get(i).getCodi());
        }
    }
    public void addPorta(Porta p){
        portes.add(p);
    }

    
    /**
    @pre: --
    @post: retorna true si la sala ha arribat al màxim de la seva capacitat. false altrament.
    */
    public boolean estaPle(){
        return nPersonatges == maxPersonatges;
    }

    /** 
    @pre: --
    @post: si la sala no està plena, el personatge p entra a l'espai, es canvia l'espai actual del personatge 
    * i s'incrementa en un el nombre de personatges que hi ha a l'espai.
    */
    public void entrar(Personatge p){
        if(!estaPle()){
            personatges.add(p);
            p.canviEspai(this);
            nPersonatges++;
        }
        
    }

    /** 
    @pre: hi ha personatge p a l'espai.
    @post: s'elimina el personatge de l'espai i es resta en un el nombre de personatges que hi ha.
    */
    public void sortir(Personatge p){
        personatges.remove(p);
        nPersonatges--;
    }

    /**
    @pre: --
    @post: retorna id d'aquest espai.
    */
    public int mostrarId(){
        return idEspai;
    }

    /**
    @pre: --
    @post: retorna una ArrayList de les portes que hi ha a l'espai. 
    *Si una porta està a l'espai es suposa que té pany per anar a l'altre costat.
    */
    public ArrayList<Porta> getPortes(){
        return portes;
    }
   
    /**
    @pre: --
    @post: retorna una ArrayList dels personatges que hi ha a l'espai.
    */
    public ArrayList<Personatge> getPersonatges(){
        return personatges;
    }
    
    /**
    @pre: --
    @post: retorna true si hi ha claus tirades en aquest espai. False altrament.
    */
    public boolean hiHaClaus(){
        return clausTirades.size() > 0;
    }

    /**
    @pre: --
    @post: retorna true si hi ha un objecte SmartGlasses tirat en aquest espai. False altrament.
    */
    public boolean hiHaSmartGlasses(){
        return smartGlassesTirades > 0;
    }

    /**
    @pre: --
    @post: retorna una ArrayList de les claus que hi ha tirades en aquest espai.
    */
    public ArrayList<Integer> veureClaus(){
        return clausTirades;
    }

    /**
    @pre: c està tirada a l'espai.
    @post: es treu clau c de l'array list de claus tirades.
    */
    public void agafarClau(Integer c){
        clausTirades.remove(c);
    }

    /**
    @pre: --
    @post: retorna un objecte SmartGlasses nou i resta en un el nombre de SmartGlasses que hi ha tirades en aquest espai.
    */
    public SmartGlasses recollirSmartGlasses(){
        SmartGlasses ulleres = new SmartGlasses();
        smartGlassesTirades--;
        return ulleres;
    }

    /**
    @pre: --
    @post: afageix c a la llista de claus tirades d'aquest espai.
    */
    public void deixarClau(Integer c){
        clausTirades.add(c);
    }

    /**
    @pre: --
    @post: suma en un el nombre de SmartGlasses que hi ha tirades en aquesta sala.
    */
    public void deixarSmartGlasses(){
        smartGlassesTirades++;
    }

    /**
    @pre: --
    @post: retorna true si hi ha algun humà en aquest espai. false altrament.
    */
    public boolean hiHaHuma(){
        for(int i=0; i<personatges.size();i++){
            if(personatges.get(i) instanceof Huma) return true;
        }
        return false;
    }

    /**
    @pre: --
    @post: retorna true si hi ha algun guàrdia en aquest espai. false altrament.
    */
    public boolean hiHaGuardia(){
        for(int i=0; i<personatges.size();i++){
            if(personatges.get(i) instanceof Guardia) return true;
        }
        return false;
    }

    /**
    @pre: --
    @post: retorna true si hi ha algun alien petit o alien gran en aquest espai. false altrament.
    */
    public boolean hiHaAlien(Personatge excepcio){
        for(int i=0; i<personatges.size(); i++){
            if(personatges.get(i) != excepcio && (personatges.get(i) instanceof AlienGran || personatges.get(i) instanceof AlienPetit)) return true;
        }
        return false;
    }

    /**
    @pre: --
    @post: retorna true si hi ha algun alien petit o alien gran en aquest espai o hi ha restes humanes. false altrament.
    */
    public boolean esPerillos(){
        boolean perillos = false;
        int i=0;
        while(!perillos && i<personatges.size()){
            if(personatges.get(i) instanceof AlienGran || personatges.get(i) instanceof AlienPetit) perillos = true;
            if(restesHumanes) perillos = true;
            i++;
        }
        return perillos;
    }

    /**
    @pre: --
    @post: canvia boolean d'aquest espai que hi han restes humanes.
    */
    public void deixarRestes(){
        restesHumanes = true;
    }

    /**
    @pre: --
    @post: retorna ture si aquest espai és l'exterior. false altrament.
    */ 
    public boolean esSortida(){
        return idEspai == -1;
    }
    

}
