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

    public Huma(String nom, int capacitatMemoria, ArrayList<Integer> claus, boolean ulleres){
        super(nom,capacitatMemoria,claus);
        if(ulleres){
            this.ulleres = new SmartGlasses();
        }
    }

    /**
     * @pre cert
     * @post inclou les portes de sortida de les noves claus recollides per l'humà
     */
    private void actualitzarSmartGlasses(ArrayList<Integer> claus){
        for(int i=0; i<claus.size();i++){
            ulleres.inclourePortaSortida(claus.get(i));
        } 
    }

    /**
    @pre: --
    @post: mètode principal perquè el personatge actui. Primer recull les claus del terra (no agafa repetides), recull smartglasses 
    * i es mou o no de sala depenent del retorn del mètode escollirSeguentPorta.
    */
    public void actuar(){
        ArrayList<Integer> clausRecollides = new ArrayList<>();
        clausRecollides = recollirClaus();
        if(ulleres != null) actualitzarSmartGlasses(clausRecollides);
        boolean ulleresRecollides = recollirSmartGlasses();

        Porta seguent = null;
        seguent = escollirSeguentPorta();

        int idDesti = 0;
        Espai origen = espaiActual;
        Espai desti = null;
        //Si ha retornat una porta per continuar
        if(seguent != null){
            desti = seguent.altreCostat();
            idDesti=desti.mostrarId();

            if(desti.esSortida()){ 
                haSortit = true;
                espaiActual.sortir(this);
                desti.entrar(this);
            }
            else if(!desti.estaPle()){
                memoria.recordarEspai(espaiActual,espaiActual.esPerillos());
                espaiActual.sortir(this);
                desti.entrar(this);
            }
            else if(desti.estaPle()){
                idDesti*=-1;
            }
        }  
        //No es mou a cap sala
        else{
            idDesti=0;
        }
        mostrarMoviment(clausRecollides,ulleresRecollides,idDesti,null);    
        
    }
    
    /**
    @pre: --
    @post: retorna la seguent porta escollida per l'algorisme de com actua un humà.
    */
    public Porta escollirSeguentPorta(){ 
        //Agafa les portes de l'espai
        ArrayList<Porta> portes = new ArrayList<>(espaiActual.getPortes());

        Porta escollida = null;
        ArrayList<Porta> recorda = new ArrayList<>();
        ArrayList<Porta> noRecorda = new ArrayList<>();
        //Borra les portes que no té la clau i que no estan obertes
        for(int i=0;i<portes.size();i++){
            boolean borra = false;
            if(!portes.get(i).estaOberta() && !claus.contains(portes.get(i).comprovarClau())) borra=true;
            if(borra && ulleres != null && portes.get(i).altreCostat().esSortida()) ulleres.exclourePortaSortida(portes.get(i));
            if(borra){
                portes.remove(i);
                i--;
            }
            //Separa les portes en funció de si les recorda o no. Si ha trobat la porta de sortida retorna directament aquesta porta.
            else{
                if(memoria.recorda(portes.get(i).altreCostat())) recorda.add(portes.get(i));
                else noRecorda.add(portes.get(i));
                if(portes.get(i).altreCostat().esSortida()) return portes.get(i);
            }
        }
        //Si té unes SmartGlasses: crida el mètode de SmartGlasses que li retorna la següent millor porta.
        //Si la porta retornada no té la clau i no està oberta la descarta. Si l'altre costat és perillos el descarta. Si és la sortida i no té clau exclou aquella porta de les ulleres.
        if(ulleres != null){
            escollida = ulleres.camiRapid(espaiActual);
            if(!portes.contains(escollida)) escollida = null;
            if(escollida!=null && escollida.altreCostat().esPerillos()) escollida = null;
            if(escollida!=null && escollida.altreCostat().esSortida()){
                if(!claus.contains(escollida.comprovarClau())) ulleres.exclourePortaSortida(escollida); 
            }
            if(escollida != null) return escollida;
        }
        //Si recorda totes les sales que pot anar:
        if(noRecorda.size() == 0){
            //Separa entre perilloses i no perilloses:
            ArrayList<Porta> perillosa = new ArrayList<>();
            ArrayList<Porta> noPerillosa = new ArrayList<>();
            for(int i=0; i<recorda.size(); i++){
                if(memoria.esPerillos(recorda.get(i).altreCostat())) perillosa.add(recorda.get(i));
                else noPerillosa.add(recorda.get(i));
            }
            
            //Si totes son perillos i l'espai actual també és perillos va a una sala aleatòria.
            if(noPerillosa.size() == 0 && espaiActual.esPerillos()){
                if(perillosa.size() > 0){
                    escollida = perillosa.get(rand.nextInt(perillosa.size()));
                }
            }
            //Si n'hi ha alguna de no perillosa: escull aleatoriament entre les no perilloses.
            else if(noPerillosa.size() > 0){
                escollida = noPerillosa.get(rand.nextInt(noPerillosa.size()));
            }
        }
        //Si hi ha alguna sala que no recorda tria aleatoriament entre les que no recorda.
        else{
            escollida = noRecorda.get(rand.nextInt(noRecorda.size()));
        }
        
        return escollida; 
    }

    /**
    @pre: --
    @post: retorna l'array list de claus qeu té aquest huma
    */
    public ArrayList<Integer> veureClaus(){
        return claus;
    }

}