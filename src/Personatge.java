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
    protected Espai espaiActual; // espai o int???
    protected Memoria memoria;
    protected ArrayList<Integer> claus;
    protected SmartGlasses ulleres;
    protected boolean haSortit = false;
    protected Random rand = new Random();
    /**
     * @pre Des de la classe corresponent al personatge, es crida el cosntructor indicant el seu tipus i capacitat de memoria
     * 
     * @post Es crea el personatge concret indicat, i s'inicialitzen els seus atributs
     */
    protected Personatge(String nom, int capacitatMemoria, ArrayList<Integer> claus) {
        this.nom = nom;
        this.memoria = new Memoria(capacitatMemoria);
        this.claus = claus;
        this.espaiActual = null;
    }

    protected Personatge(String nom, int capacitatMemoria) {
        this.nom = nom;
        this.memoria = new Memoria(capacitatMemoria);
        this.claus = new ArrayList<>();
        this.espaiActual = null;
    }

    protected String getNom(){
        return nom;
    }


    /** @return Retorna l'espai actual del personatge. */
    public Espai espaiActual(){
        return espaiActual;
    }

    /**
     * @pre S'indica l'espai al que es vol canviar
     * 
     * @post Si el personatge pot entrar a l'espai, hi canvia i s'actualitza l'espai actual, si no, es mante al mateix espai //AIXO HA DE SER AIXI??????
     * //SI NO POT ENTRAR A AUN HA DE ANAR PROVANTA  VERUE SIKK ESS  POT CANVIAR NO??? O KLK....
     */
    protected void canviEspai(Espai e){ //amb un bool i si no es pot doncs tornar a profvar una altra porta aligual no millor???
        if (potEntrarEspai(e)) espaiActual = e;
    }
    
    /**
     * @return Retorna si el personatge pot entrar a l'espai indicat
     */
    public boolean potEntrarEspai(Espai e) {
        return !e.estaPle(); // || (tipusPersonatge=="a_gran" && e.hiHaHuma()); //ben feta aquesta funcio??
    }

    /** AQUEST METODE TREUREL DE AQUI I POSARLO A ALS PERSONATGES QUE TOQUI!!!!!
     * @pre S'indica el codi de la clau que el personatge ha agafat, i per tant, s'ha d'afegir al seu inventari de claus
     * 
     * @post S'afageix la clau a l'inventari del personatge
     */
    // public void afegirClau(int codi){
    //   //  claus.add(new Clau(codi));
    // }

    /** @return Retorna si el personatge esta viu o no. */
    public boolean estaViu(){
        return viu;
    }

    /**@pre i @post !!!!!!! */
    public int nombreClaus(){
        //if (claus.size() == null) return 0;
        return claus.size();
    }

    /** 
     * @post El personatge mor */
    public void morir(ArrayList<Integer> claus, boolean smartGlasses){
        espaiActual.deixarRestes();
        for(int i=0; i<claus.size(); i++){
            espaiActual.deixarClau(claus.get(i));
        }
        if (smartGlasses)
            espaiActual.deixarSmartGlasses();
        viu = false;
    }

    /** AQUESTA IGUAL... NOMES LA NECESSITA HUMA. II ALIEN LA PART D ABAIX ---------------------------------
     * @post Recull l'objecte del terra i se'l guarda  */
    //tb m falta tot lu de memoria.. un que retorni la quantitat de memoria??? iii un que vaigi guardant per a aquest personatge... (un "recordar..." o afegirmemoria o algo aixi saes?)
    
    // public void recollirObjecte(){
    //     if (!smartGlasses && espaiActual.hiHaSmartGlasses()) {
    //         smartGlasses = true;
    //         ulleres = espaiActual.recollirSmartGlasses();
    //     }

    //     if (espaiActual.hiHaClaus()) {
    //         ArrayList<Clau> tirades = espaiActual.veureClaus();
    //         for (int i = 0; i < tirades.size(); i++) {
    //             if (!claus.contains(tirades.get(i))) {
    //                 claus.add(tirades.get(i));
    //                 espaiActual.agafarClau(tirades.get(i));
    //             }
    //         }
    //     }
        
    // }



    // falta boolean de potObrirPorta(){envio tot larray de claus a potObrirPorta(claus) !!!!!
    //per cada porta crida el potObrir aquest.. iii }
    //LU MATEIX QUE ABANS.. NOMES LES NECESITEN ALGUNES.... HUMA I ALIENPETIT....
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

    public boolean teSmartGlasses(){
        return ulleres != null; //OOO RETURN FALSE???
    }
}