/**
 * @class Alien Petit
 * @brief Modul per gestionar als aliens petits.
 *
 * @details Aquests àliens, poden adoptar aparença humana, i així doncs, passar desaparcebuts. Tenen un inventari de claus com els humans,
 * per tant, a la que maten algú, es queden amb les claus (que no tenien), a més, sumen la capacitat de memòria de la persona menjada.
 * 
 * @invariant N'hi pot haver més de 1.
 * @invariant Donen prioritat a l'àlien gran.
 * @invariant No es solapen, si entren a una sala on ja hi havia un alien menjant, surten al seguent torn com si fossin persones.
 * @invariant La seva estratègia és evitar als altres aliens per tal de maximitzar el seu èxit (menjar-se a persones), així que es comporten com persones, evitant els aliens si ho recorden.
 *
 * @author eloicanigueral
 */

import java.util.ArrayList;

public class AlienPetit extends Personatge{

    /**
     * @pre Es crida el constructor de l'alien petit juntament amb la seva capacitat de memòria
     * 
     * @post Es crea un alien petit amb la seva capacitat de memoria inicial
     */
    public AlienPetit(String nom, int capacitatMemoria, ArrayList<Integer> claus) {
        super(nom, capacitatMemoria, claus);
    }

    /**
     * @pre Està a una sala juntament amb un humà
     * 
     * @post Elimina / mata a un personatge que estigui a la mateixa sala que ell en el seu torn
     */
    public void matar(Personatge p){ //aquiii lud e pillar claus he de agafar lu que tin  apersonatge crec... mriar aqueset....!!!!!!!!!!!!
        p.morir(p.claus, p.teSmartGlasses()); //personatge mor
        p.espaiActual().sortir(p);
        this.memoria.augmentarCapacitat(p.memoria.capacitatMemoria()); //alien suma la capacitat memoria

        ArrayList<Integer> clausRecollides = new ArrayList<>();
        clausRecollides = recollirClaus();
        mostrarMoviment(clausRecollides, false, 0, p.getNom()); //aixo aqui aixi tal qual??? si aixi va be pues puc borrar el mataA i el mostrarmoviment altres.. nose mirar...
        System.out.println("   -> " + nom + " MATA a " + p.getNom()); //s'ha de borrar
    }

    /**
     * @pre --
     * @post es decideix quina accio fara l'alien (moure's de sala / quedar-se i/o matar)
     */
    public void actuar(){ //emmm dona la prioritat a l'alien gran????? hihaalien mira els dos...
        //revisar l'if aquest.. pq si es el primer q entra si q te prioritat per matar.. pero a la que entri un altre.. aixo donara false.. ii igualment pot continuar matant (a no ser que entri l'alien gros)
        
        ArrayList<Integer> clausRecollides = new ArrayList<>();
        clausRecollides = recollirClaus();
        int idDesti = 0;
        String mataA = null;
        
        if (!espaiActual.hiHaAlien(this) && !espaiActual.hiHaGuardia() && espaiActual.hiHaVictimes()){ //si no hi ha guardia, pot matar (si hi ha alguna victima -> huma o porter)
            int i = 0;
            boolean haMatat = false;
            while(i<espaiActual.getPersonatges().size() && !haMatat){
                Personatge p = espaiActual.getPersonatges().get(i);
                if(p instanceof Huma || p instanceof Porter){
                    matar(p); //posar tb larray i que es sumin les claus que pilli????
                    haMatat = true;
                    mataA = p.getNom();
                }
                i++;
            }

        } else{ //si hi ha un altre alien (a part de ell...) o no pot matar -> es comporta com un huma
            Porta seguent = escollirSeguentPorta();
            if (seguent != null) { //si decideix no moure-s...hauria de tenir un seguent = espaiActual() oa glo aixi no??? ooo indico com si s mou igual???
                Espai origen = espaiActual;
                Espai desti = seguent.altreCostat();
                idDesti = desti.mostrarId();

                if(!desti.estaPle()){
                    memoria.recordarEspai(espaiActual, espaiActual.esPerillos());
                    espaiActual.sortir(this);
                    desti.entrar(this);
                    System.out.println("   -> " + nom + " es mou de sala " + origen.mostrarId() + " a sala " + desti.mostrarId());
                } else {
                    idDesti*=-1;
                }
            }

        }
        mostrarMoviment(clausRecollides, false, idDesti, mataA);
    }

    /**
     * @pre --
     * @post S'escull la seguent porta
     */
    public Porta escollirSeguentPorta(){
        ArrayList<Porta> portes = new ArrayList<>(espaiActual.getPortes());

        ArrayList<Porta> recorda = new ArrayList<>();
        ArrayList<Porta> noRecorda = new ArrayList<>();

        Porta escollida = null;
        for(int i=0; i<portes.size(); i++){
            boolean borra = false;
            if (portes.get(i).altreCostat().esSortida()) borra = true;
            if(!portes.get(i).estaOberta() && !claus.contains(portes.get(i).comprovarClau())) borra=true;
            
            if(borra) {
                portes.remove(i);
                i--;
            } else{
                if(memoria.recorda(portes.get(i).altreCostat())) recorda.add(portes.get(i));
                else noRecorda.add(portes.get(i));
            }
        }

        if(noRecorda.size() == 0 && recorda.size()>0){
            ArrayList<Porta> perillosa = new ArrayList<>();
            ArrayList<Porta> noPerillosa = new ArrayList<>();
            for(int i=0; i<recorda.size(); i++){
                if(memoria.esPerillos(recorda.get(i).altreCostat())) perillosa.add(recorda.get(i));
                else noPerillosa.add(recorda.get(i));
            }

            if(noPerillosa.size()>0 ){
                escollida = noPerillosa.get(rand.nextInt(noPerillosa.size()));
            } else if(noPerillosa.size()==0 && !espaiActual.esPerillos()){ //totes son perilloses excepte l'espai actual, no es mou
                escollida = null;
            } else if(noPerillosa.size()==0 && perillosa.size()>0){ //si totes son perilloses i la actual tambe, es mou random
                escollida = perillosa.get(rand.nextInt(perillosa.size()));
            }
            else{
                escollida = recorda.get(rand.nextInt(recorda.size()));
            }
        } else if (noRecorda.size()>0){
            escollida = noRecorda.get(rand.nextInt(noRecorda.size()));
        }
        else {
            escollida = null;
            System.out.println("es burru i no recorda res");
        }

        return escollida; 
    }
}