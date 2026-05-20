/**
 * @class Personatge
 * @brief Modul per gestionar els personatges.
 *
 * @details Hi ha diversos tipus de personatge amb capacitats/habilitats/caracteristiques diferents.
 *
 * @author eloicanigueral
 */

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
public abstract class Personatge {

    protected String nom;
    protected boolean viu = true;
    protected Espai espaiActual;
    protected Memoria memoria;
    protected ArrayList<Integer> claus;
    protected SmartGlasses ulleres;
    protected boolean haSortit = false;
    protected Random rand = new Random();
    protected Porta ultimaPortaOberta;


    /**
     * @pre Des de la classe corresponent al personatge, es crida el cosntructor indicant el seu nom, capacitat de memoria i ArayList de claus
     * 
     * @post Es crea el personatge concret indicat, i s'inicialitzen els seus atributs
     */
    protected Personatge(String nom, int capacitatMemoria, ArrayList<Integer> claus) {
        this.nom = nom;
        this.memoria = new Memoria(capacitatMemoria);
        this.claus = claus;
        this.espaiActual = null;
        this.ultimaPortaOberta=null;
    }

    /**
     * @pre Des de la classe corresponent al personatge, es crida el cosntructor indicant el seu nom i capacitat de memoria
     * 
     * @post Es crea el personatge concret indicat, i s'inicialitzen els seus atributs
     */
    protected Personatge(String nom, int capacitatMemoria) {
        this.nom = nom;
        this.memoria = new Memoria(capacitatMemoria);
        this.claus = new ArrayList<>();
        this.espaiActual = null;
    }

    /** @return Retorna el nom del Personatge */
    protected String getNom(){
        return nom;
    }


    /** @return Retorna l'espai actual del personatge. */
    public Espai espaiActual(){
        return espaiActual;
    }

    /**     
     * @pre S'indica l'espai al que es vol canviar, i hi pot entrar
     * @post Si el personatge pot entrar a l'espai, hi canvia i s'actualitza l'espai actual, si no, es mante al mateix espai //AIXO HA DE SER AIXI??????
     */
    protected void canviEspai(Espai e){
        espaiActual = e;
    }

    /**
     * @pre: S'entra la inforamacio corresponent a mostrar
     * @post: Es mostra el moviment del personatge per pantalla
     */
    public void mostrarMoviment(ArrayList<Integer> clausRecollides, boolean haAgafatUlleres, int desti, String menjat){
        int agafaUlleres = 0;
        if (haAgafatUlleres) agafaUlleres = 1;
        
        
        System.out.print(this.nom + ":[");
        if (clausRecollides.size()>0){
            
            Collections.sort(clausRecollides);
            for(int i=0; i<clausRecollides.size()-1; i++){
                System.out.print(clausRecollides.get(i) + ",");
            }
            System.out.print(clausRecollides.get(clausRecollides.size()-1));
        }

        System.out.print("]:" + agafaUlleres + ":" + desti + ":[");
        if (menjat != null){
            System.out.print(menjat);
        }
        System.out.println("]");

    }


    /**
     * @pre: --
     * @post Recull les ulleres del terra i se les guarda  
     */    
    public boolean recollirSmartGlasses(){
        boolean agafat = false;
        if (ulleres==null && espaiActual.hiHaSmartGlasses()) {
            ulleres = espaiActual.recollirSmartGlasses();
            agafat = true;
        }
        return agafat;
    }


    /**
     * @pre: --
     * @post: Recull les claus del terra i se les guarda
     */
    public ArrayList<Integer> recollirClaus(){
        ArrayList<Integer> recollides = new ArrayList<>();
        if (espaiActual.hiHaClaus()) {
            ArrayList<Integer> tirades = espaiActual.veureClaus();
            for (int i = 0; i<tirades.size(); i++) {
                if (!claus.contains(tirades.get(i))) {
                    claus.add(tirades.get(i));
                    recollides.add(tirades.get(i));
                    espaiActual.agafarClau(tirades.get(i));
                    i--;
                } 
            }
        }
        return recollides;      
    }

    /** @return Retorna si el personatge esta viu o no. */
    public boolean estaViu(){
        return viu;
    }

    /**@pre --
     * @post retorna el nombre de claus que te el personatge
     */
    public int nombreClaus(){
        return claus.size();
    }

    /** 
     * @pre El personatge estava viu
     * @post El personatge mor i deixa al terra les restes, i els seus objectes (claus i SmartGlasses si escau)
     */
    public void morir(ArrayList<Integer> claus, boolean smartGlasses){
        espaiActual.deixarRestes();
        for(int i=0; i<claus.size(); i++){
            espaiActual.deixarClau(claus.get(i));
        }
        if (smartGlasses)
            espaiActual.deixarSmartGlasses();
        viu = false;
    }


    /** @return Retorna si el personatge ha sortit del laberint */
    public boolean haSortit(){
        return haSortit;
    }

    /**
     * @pre Personatge viu, i es el seu torn
     * 
     * @post Cada personatge actua segons la seva estrategia
     */
    public abstract void actuar(); //mirar si cal.. i com ferho... pq tots tenen un actuar diferent pero tots son personatges


    /**
     * @pre: --
     * @post: retorna si el personatge te smartglasses
     */
    public boolean teSmartGlasses(){
        return ulleres!=null;
    }
}