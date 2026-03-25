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
    private SmartGlasses ulleres;
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
    public void recollirItem(ArrayList<Objecte> o){ 
        for (int i = 0; i < o.size(); i++) {
            System.out.println(o.get(i));
            if (o.get(i).esClau()){
                Clau clau = (Clau) o.get(i);
                claus.add(clau);
            }
            else {

            }

        }
    }

    /** @return Retorna si el personatge ha sortit del laberint */
    public boolean haSortit(){
        return haSortit;
    }

    public void actuar(); //mirar si cal.. i com ferho... pq tots tenen un actuar diferent pero tots son personatges
}