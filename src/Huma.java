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
    @pre: --
    @post: retorna true si l'humà té SmartGlasses. false altrament.
    */
    public boolean teUlleres(){
        return ulleres != null;
    }

    /**
    @pre: --
    @post: mètode principal perquè el personatge actui.
    */
    public void actuar(){
        if(!teUlleres() && espaiActual().hiHaSmartGlasses()){
            ulleres = espaiActual().recollirSmartGlasses();
        }
        if (espaiActual().hiHaClaus()) {
            ArrayList<Integer> tirades = espaiActual().veureClaus();
            for (int i = 0; i < tirades.size(); i++) {
                if (!claus.contains(tirades.get(i))) {
                    claus.add(tirades.get(i));
                    espaiActual().agafarClau(tirades.get(i));
                    i--;
                }
            }
        }

        Porta seguent = null;
        if(espaiActual.hiHaAlien(this)){
            seguent = escollirSeguentPorta();
        }
        else{
            seguent = escollirSeguentPorta();
        }

        if(seguent != null){
            Espai origen = espaiActual;
            Espai desti = seguent.altreCostat();
            if(desti.esSortida()){ //ARNAUUU TEH AFEGIT AIXOO!!!
                haSortit = true;
                espaiActual.sortir(this);
                System.out.println("   -> " + nom + " HA SORTIT DEL LABERINT!");
            }
            else if(!desti.estaPle()){
                memoria.recordarEspai(espaiActual,espaiActual.esPerillos());
                espaiActual.sortir(this);
                desti.entrar(this);
            }
            System.out.println("   -> " + nom + " es mou de sala " + origen.mostrarId() + " a sala " + desti.mostrarId());
        }

    }
    
    /**
    @pre: --
    @post: retorna la seguent porta escollida per l'algorisme de com actua un personatge.
    */
    public Porta escollirSeguentPorta(){ 
        ArrayList<Porta> portes = new ArrayList<>(espaiActual.getPortes());
        Porta escollida = null;
        for(int i=0;i<portes.size();i++){
            boolean borra = false;
            if(!portes.get(i).estaOberta() && !claus.contains(portes.get(i).comprovarClau())) borra=true;
            if(borra){
                portes.remove(i);
                i--;
            }
        }
        if(ulleres != null){
            escollida = ulleres.camiRapid(espaiActual);
            if(!portes.contains(escollida)) escollida = null;
            if(escollida!=null && escollida.altreCostat().esPerillos()) escollida = null;
            if(escollida!=null && escollida.altreCostat().esSortida()){
                if(!claus.contains(escollida.comprovarClau())) ulleres.exclourePortaSortida(escollida); 
            }
            if(escollida != null) return escollida;
        }
        
        ArrayList<Porta> recorda = new ArrayList<>();
        ArrayList<Porta> noRecorda = new ArrayList<>();
        for(int i=0; i<portes.size(); i++){
            if(memoria.recorda(portes.get(i).altreCostat())) recorda.add(portes.get(i));
            else noRecorda.add(portes.get(i));
            if(portes.get(i).altreCostat().esSortida()) return portes.get(i);
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
                if(perillosa.size() > 0){
                    escollida = perillosa.get(rand.nextInt(perillosa.size()));
                }
            }
            else if(noPerillosa.size() > 0){
                escollida = noPerillosa.get(rand.nextInt(noPerillosa.size()));
            }
        }
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

    @Override
    public boolean teSmartGlasses(){
        return this.ulleres != null;
    }
}