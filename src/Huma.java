/**
 * @class Huma
 * @brief Classe per gestionar les característiques dels humans del labertint.
 *
 * @details 
 * Aquesta classe defineix les característiques dels humans del laberint. Els humans són els personatges
 * que han d'escapar del laberint. Aquests poden morir si es troben amb aliens i poden portar l'accessori de les
 * SmartGlasses. 
 * 
 * L'estratègia que segueix per canviar de sala...
 * 
 * 
 * @author arnaulloret
 */
import java.util.ArrayList;
import java.util.Random;

public class Huma extends Personatge{
    private SmartGlasses ulleres;
    private boolean teUlleres;
    private ArrayList<Integer> claus;

    public Huma(String nom, int capacitatMemoria, ArrayList<Integer> claus, boolean ulleres){
        super(nom,capacitatMemoria);
        if(ulleres){
            this.ulleres = new SmartGlasses();
        }
        this.claus = claus;
        teUlleres = ulleres;
    }

    public void actuar(){
        if(!teUlleres && espaiActual().hiHaSmartGlasses()){
            teUlleres = true;
            ulleres = espaiActual().recollirSmartGlasses();
        }
        else if (espaiActual().hiHaClaus()) {
            ArrayList<Integer> tirades = espaiActual().veureClaus();
            for (int i = 0; i < tirades.size(); i++) {
                if (!claus.contains(tirades.get(i))) {
                    claus.add(tirades.get(i));
                    espaiActual().agafarClau(tirades.get(i));
                }
            }
        }

        Porta seguent = null;
        if(espaiActual.hiHaAlien()){
            //seguent = escollirPortaFugida();
        }
        else{
            seguent = escollirSeguentPorta();
        }

        if(seguent != null){
            Espai desti = seguent.altreCostat();
            if(!desti.estaPle()){
                //memoria.recordarEspai(espaiAcutal,true);
                espaiActual.sortir(this);
                desti.entrar(this);
            }
        }
    }
    

    public Porta escollirSeguentPorta(){
        ArrayList<Porta> portes = espaiActual.getPortes();
        Porta escollida;
        for(int i=0;i<portes.size();i++){
            boolean borra = false;
            if(portes.get(i).altreCostat().estaPle()) borra=true;
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
            if(noPerillosa.size() == 0 && !espaiActual.esPerillos()){}
            else if(noPerillosa.size() == 0 && espaiActual.esPerillos()){
                Random rand = new Random();
                escollida = perillosa.get(rand.nextInt(perillosa.size()));
            }
            else if(noPerillosa.size() > 0){
                Random rand = new Random();
                escollida = noPerillosa.get(rand.nextInt(noPerillosa.size()));
            }
        }
        else{
            Random rand = new Random();
            escollida = noRecorda.get(rand.nextInt(noRecorda.size()));
        }
        
        return escollida; 
    }
    //public Porta escollirPortaFugida(){
        
    //}
    /** aplica l'algoritme per escollir la segÜent millor sala
    @pre: --
    @post: retorna la millor Sala per anar aquest humà. */

    
}

/*
for(int i=0; i<portes.size();i++){
            if(!claus.contains(portes.get(i).getCodi()) && !portes.get(i).estaOberta()){
                portes.remove(i);
            }
        }
        ArrayList<Porta> perilloses, segures;
        if(portes.size() > 0){
           for(int i=0; i<portes.size();i++){
                if(memoria.esPerillos(portes.get(i).altreCostat())){
                    perilloses.add(portes.get(i));
                }
                else segures.add(portes.get(i));
            }
            if(segures.size() > 0){
                
            } 
        }

*/