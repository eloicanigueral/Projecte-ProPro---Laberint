/**
 * @class Personatge
 * @brief Modul per gestionar els personatges.
 *
 * @details Hi ha diversos tipus de personatge amb capacitats/habilitats/caracteristiques diferents.
 *
 * @author eloicanigueral
 */

import java.util.ArrayList;

public class Personatge {

    private int idPersonatge;
    private ArrayList<Clau> claus = new ArrayList<Clau>();
    private boolean smartGlasses = false;
    private boolean viu = true;
    private Espai espaiActual; // espai o int???
    private Memoria memoria;
    private boolean haSortit = false;


    /** @return Retorna l'espai actual del personatge. */
    public Espai espaiActual(){
        return espaiActual;
    }

    public void canviEspai(Espai e){
        espaiActual = e;
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
    public void recollirItem(Objecte o){
        if(viu){   
            if(o instanceof Clau){
                claus.add((Clau) o);
            }
            else if(o instanceof SmartGlasses){
                smartGlasses = true;
            }
        }
    }    

    /** @return Retorna si el personatge ha sortit del laberint */
    public boolean haSortit(){
        return haSortit;
    }
}