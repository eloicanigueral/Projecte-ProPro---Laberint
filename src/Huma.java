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

public class Huma extends Personatge{
    private SmartGlasses ulleres;

    public Huma(String tipusPersonatge, int capacitatMemoria){
        super("huma",capacitatMemoria);
        this.ulleres = null;
    }
    public void actuar(){}
    /** decideix com actua el personatge al seu moviment
    @pre: --
    @post: l'humà ha escollit la millor sala seguint la seva estratègia i ha canviat de sala. */

    public Espai escollirSeguentPorta(){
        if(ulleres != null){
            //ruta ulleres
        }
        else{
            //ruta sense ulleres
        }
        
    }
    /** aplica l'algoritme per escollir la segÜent millor sala
    @pre: --
    @post: retorna la millor Sala per anar aquest humà. */

    
}