import java.util.ArrayList;


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
        Porta escollida = null;
        escollida = escollirSeguentPorta();
        
        int idDesti = 0;
        if(escollida != null){
            escollida.obrir();
            Espai origen = espaiActual;
            Espai desti = escollida.altreCostat();
            if(!desti.estaPle()){
                memoria.recordarEspai(origen,origen.esPerillos());
                espaiActual.sortir(this);
                desti.entrar(this);
            }
            else{
               idDesti*=-1; 
            }
        }
        else{
            idDesti = 0;
        }
        mostrarMoviment(new ArrayList<Integer>(),false,idDesti,null);
    }

    public Porta escollirSeguentPorta(){
        ArrayList<Porta> portes = new ArrayList<>(espaiActual.getPortes());
        Porta escollida=null;
        ArrayList<Porta> perillosa = new ArrayList<>();
        ArrayList<Porta> noPerillosa = new ArrayList<>();
        for(int i=0;i<portes.size();i++){
            boolean borra = false;
            if(portes.get(i).altreCostat().esSortida()) borra=true;
            if(memoria.esPerillos(portes.get(i).altreCostat()) && !espaiActual.esPerillos()) borra=true;
            if(borra){
                portes.remove(i);
                i--;
            }
        }
        for(int i=0;i<portes.size();i++){
            if(memoria.esPerillos(portes.get(i).altreCostat())) perillosa.add(portes.get(i));
            else noPerillosa.add(portes.get(i));
        }
        if(noPerillosa.size()>0){
            escollida = portes.get(rand.nextInt(noPerillosa.size()));
        }
        else if(perillosa.size()>0){
           escollida = portes.get(rand.nextInt(perillosa.size())); 
        }
           
        
        return escollida;
    }
    /** canvia de sala
    @pre: --
    @post: aplica l'estratègia per triar la millor porta segons el seu criteri i canvia de sala. */
    
}