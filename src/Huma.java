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
    //private SmartGlasses ulleres;
    
    public Huma(int capacitatMemoria){
        super("huma",capacitatMemoria);
        //this.ulleres = null;
    }
    public void actuar(){
        Porta seg = escollirSeguentPorta();
        if(seg != null){
            Espai desti = seg.altreCostat(espaiActual());
            espaiActual().sortir(this);
            desti.entrar(this);
            System.out.println("Huma mou a sala " + desti.mostrarId());
        }
    }
    /** decideix com actua el personatge al seu moviment
    @pre: --
    @post: l'humà ha escollit la millor sala seguint la seva estratègia i ha canviat de sala. */

    public Porta escollirSeguentPorta(){
        ArrayList<Porta> portes = espaiActual().getPortes();
        for(int i=0; i<portes.size();i++){
            if(teClau(portes.get(i).comprovarClau())){
                return portes.get(i);
            }
        }
        return null;
        // if(ulleres != null){
        //     //ruta ulleres
        // }
        // else{
        //     //ruta sense ulleres
        // }
        
    }
    /** aplica l'algoritme per escollir la segÜent millor sala
    @pre: --
    @post: retorna la millor Sala per anar aquest humà. */

    
}