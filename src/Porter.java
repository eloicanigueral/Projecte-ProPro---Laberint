import java.util.ArrayList;
import java.util.Random;

/**
 * @class Porter
 * @brief Classe per gestionar les característiques del porter.
 *
 * @details
 * Aquest nou personatge té l'habilitat de poder obrir totes les portes, ja que té una clau mestre.
 * Pren un altre tipus de decisions a l'hora de canviar de sala, aquest dona prioritat a les sales encara no 
 * visitades en comptes de la més segura.
 * Apart d'això totes les altres característiques són les mateixes que les d'un humà.
 *
 * @author arnaulloret
 */

public class Porter extends Personatge{
    public Porter(String nom, int capacitatMemoria){
        super(nom,capacitatMemoria);
    }
    public void actuar(){
        Porta escollida = escollirSeguentPorta();
    }

    public Porta escollirSeguentPorta(){
        ArrayList<Porta> portes = new ArrayList<>(espaiActual.getPortes());
        Porta escollida;
        for(int i=0;i<portes.size();i++){
            boolean borra = false;
            if(portes.get(i).altreCostat().estaPle()) borra=true;
            //if(!portes.get(i).estaOberta() && !claus.contains(portes.get(i).comprovarClau())) borra=true;
            if(borra) portes.remove(i);
        }
        Random rand = new Random();
        escollida = portes.get(rand.nextInt(portes.size()));
        return escollida;
    }
    /** canvia de sala
    @pre: --
    @post: aplica l'estratègia per triar la millor porta segons el seu criteri i canvia de sala. */
    
}