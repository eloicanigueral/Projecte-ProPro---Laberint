/**
 * @class Personatge
 * @brief Modul per gestionar els personatges.
 *
 * @details Hi ha diversos tipus de personatge amb capacitats/habilitats/caracteristiques diferents.
 *
 * @author eloicanigueral
 */

import java.util.ArrayList;

public abstract class Personatge {

    private String tipusPersonatge;
    private ArrayList<Clau> claus = new ArrayList<Clau>();
    private boolean smartGlasses = false;
    private SmartGlasses ulleres;
    private boolean viu = true;
    private Espai espaiActual; // espai o int???
    private Memoria memoria;
    private boolean haSortit = false;


    protected Personatge(String tipus, int capacitatMemoria) {
        this.tipusPersonatge = tipus;
        this.memoria = new Memoria(capacitatMemoria);
        this.claus = new ArrayList<>();
        this.salaActual = null;
    }


    /** @return Retorna l'espai actual del personatge. */
    public Espai espaiActual(){
        return espaiActual;
    }

    protected void canviEspai(Espai e){ //amb un bool i si no es pot doncs tornar a profvar una altra porta aligual no millor???
        if (potEntrarEspai(e)) espaiActual = e;
    }
    
    public boolean potEntrarEspai(Espai e) {
        return ! e.estaPle() || (tipusPersonatge=="a_gran" and e.hiHaHuma()); //ben feta aquesta funcio??
    }

    /** @return Retorna si el personatge esta viu o no. */
    public boolean estaViu(){
        return viu;
    }

    /** 
     * @post El personatge mor */
    public void morir(){
        viu = false;
    }

    /** 
     * @post Recull l'objecte del terra i se'l guarda  */
    //tb m falta tot lu de memoria.. un que retorni la quantitat de memoria??? iii un que vaigi guardant per a aquest personatge... (un "recordar..." o afegirmemoria o algo aixi saes?)
    public void recollirObjecte(){
        if (!smartGlasses && espaiActual.hiHaSmartGlasses()) {
            smartGlasses = true;
            ulleres = espaiActual.recollirSmartGlasses();
        }

        if (espaiActual.hiHaClaus()) { //he de fer dos fors???
            ArrayList<Clau> tirades = espaiActual.veureClaus();
            for (int i = 0; i < tirades.size(); i++) {
                if (!claus.contains(tirades.get(i))) {
                    claus.add(tirades.get(i));
                    espaiActual.agafarClau(tirades.get(i));
                }
            }
        
    }



    // falta boolean de potObrirPorta(){envio tot larray de claus a potObrirPorta(claus)
    //per cada porta crida el potObrir aquest.. iii }
    public boolean teClau(int codi){
        boolean trobat = false;
        int i=0;

        if (tipusPersonatge== "a_gran" || tipusPersonatge == "porter") trobat = true; //el porter i l'alien gran sempre poden obrir les portes
        
        while(!trobat && i<claus.size()){
            Clau c = claus.get(i);
            if(c.getCodi() == codi) trobat = true; 
            i++;
        }
        return trobat;
    }


    /** @return Retorna si el personatge ha sortit del laberint */
    public boolean haSortit(){
        return haSortit;
    }

    public String obtenirTipus(){
        return tipusPersonatge;
    }

    public abstract void actuar(); //mirar si cal.. i com ferho... pq tots tenen un actuar diferent pero tots son personatges

//     fer actuar() abstracte
// I fer privats/protegits els mètodes que no han de ser públics
}