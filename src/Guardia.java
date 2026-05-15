import java.util.ArrayList;
import java.util.Random;

/**
 * @class Guardia
 * @brief Classe per especificar com serà el guardia del laberint.
 *
 * @details
 * Aquesta classe dona les característiques especials que té el guardia del laberint. 
 * Aquest personatge té l'habilitat de protegir els personatges (humans) que estan a la mateixa
 * sala que ell. En el cas que estigui sol a una sala amb un àlien, aquest no se'l pot menjar 
 * (és immortal). I si hi ha àliens i personatges en aquella sala no hi haurà morts.
 * 
 * ????Apart d'això té la desaventatge de no poder recordar a quines sales hi ha àliens o personatges, només 
 * podrà recordar quines sales ha visitat.????
 * 
 *
 * @author arnaulloret
 */

public class Guardia extends Personatge{
    public Guardia(String nom, int capacitatMemoria, ArrayList<Integer> claus){
        super(nom,capacitatMemoria,claus);
    }
    public void protegirHumans(){}
    /** aplica immunitat als humans que hi ha a la sala que entra
    @pre: --
    @post: els altres personatges humans de la sala actual del guardia passen a tenir immunitat */

    public void desprotegirHumans(){}
    /** treu la immunitat quan el guardia marxa de la sala
    @pre: --
    @post: els altres personatges de la sala deixen de tenir immunitat amb els aliens */

    public void actuar(){
        ArrayList<Interger>clausRecollides = new ArrayList<>();
        clausRecollides = recollirClaus();

        Porta seguent = null;
        if(!espaiActual.hiHaAlien(this)){
            seguent = escollirSeguentPorta();
        }

        if(seguent != null){
            Espai origen = espaiActual;
            Espai desti = seguent.altreCostat();
            int idDesti = 0;
            if(!desti.estaPle()){
                memoria.recordarEspai(espaiActual,espaiActual.esPerillos());
                espaiActual.sortir(this);
                desti.entrar(this);
            }
            else{
                iDesti*=-1;
            }
        }
        else{
            idDesti=0;
        }
        mostrarMoviment(clausRecollides,false,idDesti,null);

    }
    public Porta escollirSeguentPorta(){
        ArrayList<Porta> portes = new ArrayList<>(espaiActual.getPortes());
        Porta escollida = null;
        for(int i=0;i<portes.size();i++){
            boolean borra = false;
            if(portes.get(i).altreCostat().estaPle()) borra=true; //si volem que guardia entri a sales plenes treure aixo
            if(!portes.get(i).estaOberta() && !claus.contains(portes.get(i).comprovarClau())) borra=true;
            if(borra) portes.remove(i);
        }

        ArrayList<Porta> recorda = new ArrayList<>();
        ArrayList<Porta> noRecorda = new ArrayList<>();
        for(int i=0; i<portes.size(); i++){
            if(memoria.recorda(portes.get(i).altreCostat())) recorda.add(portes.get(i));
            else noRecorda.add(portes.get(i));
        }
        if(noRecorda.size() == 0){
            ArrayList<Porta> perillosa = new ArrayList<>();
            ArrayList<Porta> noPerillosa = new ArrayList<>();
            for(int i=0; i<recorda.size(); i++){
                if(memoria.esPerillos(recorda.get(i).altreCostat())) perillosa.add(recorda.get(i));
                else noPerillosa.add(recorda.get(i));
            }
            if(espaiActual.esPerillos()) escollida = null;
            if(!espaiActual.esPerillos() && perillosa.size() > 0){
                escollida = perillosa.get(rand.nextInt(perillosa.size()));
            }
            if(espaiActual.esPerillos() && perillosa.size() == 0){
                escollida = null;
            }
        }
        else{
            escollida = noRecorda.get(rand.nextInt(noRecorda.size()));
        }
        
        return escollida;

    }

    
    /** tria a quina sala vol anar, desprotegeix els humans de la sala actual, es mou de sala, protegeix els humans de la sala nova
    @pre: --
    @post: s'han desprotegit els humans de la sala actual, s'ha escollit la millor porta, s'ha mogut de sala i s'han protegit els nous humans.
    */
}