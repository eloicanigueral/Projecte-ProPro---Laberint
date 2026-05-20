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

    /**
    @pre: --
    @post: mètode principal perquè el personatge actui. Escull la següent porta amb algorisme de escollirSeguentPorta i es mou o no.
    */
    public void actuar(){
        Porta escollida = null;
        escollida = escollirSeguentPorta();
        
        int idDesti = 0;
        Espai origen = espaiActual;
        Espai desti = null;
        
        //Si té una porta per anar.
        if(escollida != null){
            //Sempre la deixa oberta el nombre de moviments configurat.
            escollida.obrir();
            ultimaPortaOberta=escollida;
            desti = escollida.altreCostat();
            idDesti = desti.mostrarId();
            
            //Si no està ple l'espai destí es mou recordant l'espai on estava.
            if(!desti.estaPle()){
                memoria.recordarEspai(origen,origen.esPerillos());
                espaiActual.sortir(this);
                desti.entrar(this);
            }
            //Si està ple no es mou.
            else{
               idDesti*=-1; 
            }
        }
        else{
            idDesti = 0;
        }
        mostrarMoviment(new ArrayList<Integer>(),false,idDesti,null);
    }

    /**
    @pre: --
    @post: aplica l'estratègia per triar la millor porta segons el seu criteri.
    */
    public Porta escollirSeguentPorta(){
        ArrayList<Porta> portes = new ArrayList<>(espaiActual.getPortes());
        Porta escollida=null;
        ArrayList<Porta> recorda = new ArrayList<>();
        ArrayList<Porta> noRecorda = new ArrayList<>();
        //Borra de les portes on pot anar les que siguin de sortida del laberint. 
        //Si la porta de l'altre costat és perillosa i l'espai on està no ho és el borra.
        for(int i=0;i<portes.size();i++){
            boolean borra = false;
            if(portes.get(i).altreCostat().esSortida()) borra=true;
            if(memoria.esPerillos(portes.get(i).altreCostat()) && !espaiActual.esPerillos()) borra=true;
            if(borra){
                portes.remove(i);
                i--;
            }
            else{
                if(memoria.recorda(portes.get(i).altreCostat())) recorda.add(portes.get(i));
                else noRecorda.add(portes.get(i));
            }
        }
        if(noRecorda.size()>0){
            escollida = noRecorda.get(rand.nextInt(noRecorda.size()));
        }
        else if(recorda.size()>0 && noRecorda.size() ==0){
           ArrayList<Porta> perillosa = new ArrayList<>();
           ArrayList<Porta> noPerillosa = new ArrayList<>();
           for(int i=0;i<recorda.size();i++){
                if(memoria.esPerillos(recorda.get(i).altreCostat())) perillosa.add(recorda.get(i));
                else noPerillosa.add(recorda.get(i));
           }
           if(noPerillosa.size()>0){
                escollida = noPerillosa.get(rand.nextInt(noPerillosa.size()));
           }
           else{
                if(espaiActual.esPerillos()){
                    escollida = perillosa.get(rand.nextInt(perillosa.size()));
                }
                else escollida=null;
                
           }
        }
        return escollida;
    }   
}