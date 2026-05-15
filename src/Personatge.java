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
public abstract class Personatge {

    protected String nom;
    protected boolean viu = true;
    protected Espai espaiActual;
    protected Memoria memoria;
    protected ArrayList<Integer> claus;
    protected SmartGlasses ulleres;
    protected boolean haSortit = false;
    protected Random rand = new Random();


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
    }

    /**
     * @pre Des de la classe corresponent al personatge, es crida el cosntructor indicant el seu nom i capacitat de memoria
     * 
     * @post Es crea el personatge concret indicat, i s'inicialitzen els seus atributs
     */
    protected Personatge(String nom, int capacitatMemoria) {
        this.nom = nom;
        this.memoria = new Memoria(capacitatMemoria);
        this.claus = new ArrayList<>(); //buit ... es pot posar un null o algo?? (bueno no crec qserveix despres pel nombre de claus.. que tonri 0)
        this.espaiActual = null;
    }

    /** @return Retorna el nom del Personatge */
    protected String getNom(){ //canviar el nom de get no?? psoar algo diferent...
        return nom;
    }


    /** @return Retorna l'espai actual del personatge. */
    public Espai espaiActual(){
        return espaiActual;
    }

    /**     //mirar aquest metode!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! crec q no cal ja pero nose mirar i fer igual.. pq llavors lu que tenim de estaPle() que mirem a cad personatge de que serveix??
     * sino es podria deixar aixi nomes amb aqeust i no posar lu de estaPle.. ii com que aquest comprova si esta ple, si no pot, retorna algo en especial, per arreglar el  problema que teniem per la sortida de si no pot entrar en negatiu
     * @pre S'indica l'espai al que es vol canviar
     * 
     * @post Si el personatge pot entrar a l'espai, hi canvia i s'actualitza l'espai actual, si no, es mante al mateix espai //AIXO HA DE SER AIXI??????
     */
    protected void canviEspai(Espai e){
        if (potEntrarEspai(e)) espaiActual = e;
    }
    
    /**
     * @return Retorna si el personatge pot entrar a l'espai indicat
     */
    public boolean potEntrarEspai(Espai e) {
        return !e.estaPle(); // || (tipusPersonatge=="a_gran" && e.hiHaVictimes()); //ben feta aquesta funcio??
    }


    /**
     * @pre:
     * @post:
     */
    public void mostrarMoviment(ArrayList<Integer> clausRecollides, boolean haAgafatUlleres, int desti, String menjat){
        System.out.print(this.nom + ":["); //print ln aquest
        for(int i=0; i<clausRecollides.size(); i++){
            System.out.print(clausRecollides.get(i) + ",");
        }
        System.out.print("]:" + haAgafatUlleres + ":" + desti + ":[");
        if (menjat != null){
            System.out.print(menjat);
        }
        System.out.println("]"); //o aqust

    }


//-------------------------- tot aixo ho tenia comentat -------------------------------------------
        /** AQUESTA IGUAL... NOMES LA NECESSITA HUMA. II ALIEN LA PART D ABAIX ---------------------------------
     * @post Recull l'objecte del terra i se'l guarda  */
    //tb m falta tot lu de memoria.. un que retorni la quantitat de memoria??? iii un que vaigi guardant per a aquest personatge... (un "recordar..." o afegirmemoria o algo aixi saes?)
    
    public boolean recollirSmartGlasses(){
        boolean agafat = false;
        if (ulleres==null && espaiActual.hiHaSmartGlasses()) {
            ulleres = espaiActual.recollirSmartGlasses();
            agafat = true;
        }
        return agafat;
    }

    public ArrayList<Integer> recollirClaus(){
        ArrayList<Integer> recollides = new ArrayList<>();
        if (espaiActual.hiHaClaus()) {
            ArrayList<Integer> tirades = espaiActual.veureClaus();
            for (int i = 0; i<tirades.size(); i++) {
                if (!claus.contains(tirades.get(i))) {
                    claus.add(tirades.get(i));
                    recollides.add(tirades.get(i));
                    espaiActual.agafarClau(tirades.get(i));
                    i--; //revisar si cal
                } 
            }
        }
        return recollides;      
    }

    // falta boolean de potObrirPorta(){envio tot larray de claus a potObrirPorta(claus) !!!!!
    //per cada porta crida el potObrir aquest.. iii }

    /**
     * @return Retorna si el personatge té la clau amb el codi indicat, o si és un porter o l'alien gran (que poden obrir totes les portes)
     */
    //  public boolean teClau(int codi){
    //     //  boolean trobat = false;
    //     //  int i=0;

    //     //  if (tipusPersonatge.equals("a_gran") || tipusPersonatge.equals("porter")) trobat = true; //el porter i l'alien gran sempre poden obrir les portes
        
    //     // while(!trobat && i<claus.size()){
    //     //     Clau c = claus.get(i);
    //     //     if(c.getCodi() == codi) trobat = true; 
    //     //     i++;
    //     // }
    //     return trobat;
    // }

// --------------------------------- fins aqui lu comentat -----------------------------------

    /** @return Retorna si el personatge esta viu o no. */
    public boolean estaViu(){
        return viu;
    }

    /**@pre --
     * @post retorna el nombre de claus que te el personatge
     */
    public int nombreClaus(){
        //if (claus.size() == null) return 0; //es pot fer aixo?? iii aixi al constructor sense claus posar null??
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
     * @pre:
     * @post:
     */
    public boolean teSmartGlasses(){
        return ulleres!=null;
    }
}