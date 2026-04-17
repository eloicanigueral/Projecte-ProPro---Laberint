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
public class Huma extends Personatge{
    private SmartGlasses ulleres;
    private boolean teUlleres;
    private String nom;
    private ArrayList<Integer> claus;

    public Huma(String nom, int capacitatMemoria, ArrayList<Integer> claus, boolean ulleres){
        super(capacitatMemoria);
        if(ulleres){
            this.ulleres = new SmartGlasses();
        }
        this.claus = claus;
        this.nom = nom;
        teUlleres = ulleres;
    }

    public void actuar(){
        if(!teUlleres && espaiActual().hiHaSmartGlasses()){
            teUlleres = true;
            ulleres = espaiActual().recollirSmartGlasses();
        }
        else if (espaiActual().hiHaClaus()) {
            ArrayList<Clau> tirades = espaiActual().veureClaus();
            for (int i = 0; i < tirades.size(); i++) {
                if (!claus.contains(tirades.get(i))) {
                    claus.add(tirades.get(i));
                    espaiActual().agafarClau(tirades.get(i));
                }
            }
        }
        Porta seg = escollirSeguentPorta();
        if(seg != null){
            Espai desti = seg.altreCostat(espaiActual());
            espaiActual().sortir(this);
            desti.entrar(this);
            System.out.println("Huma mou a sala " + desti.mostrarId());
        }
    }
    

    public Porta escollirSeguentPorta(){
        ArrayList<Porta> portes = espaiActual().getPortes();
        for(int i=0; i<portes.size();i++){
            if(!teClau(portes.get(i).comprovarClau()) && !portes.get(i).estaOberta()){
                portes.remove(i);
            }
        }
        ArrayList<Porta> perilloses, segures;
        if(portes.size() > 0){
           for(int i=0; i<portes.size();i++){
                if(memoria.esPerillos(portes.get(i).altreCostat(this.espaiActual()))){
                    perilloses.add(portes.get(i));
                }
                else segures.add(portes.get(i));
            }
            
            
        }
        else return null;
        
        
        
    }
    /** aplica l'algoritme per escollir la segÜent millor sala
    @pre: --
    @post: retorna la millor Sala per anar aquest humà. */

    
}