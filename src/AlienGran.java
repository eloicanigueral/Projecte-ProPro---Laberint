/**
 * @class Alien Gran
 * @brief Modul per gestionar a alien gran. 
 *
 * @details Aquest alien té la capacitat d'obrir totes les portes del laberint. El seu objecitu és acabar amb els humans, i per tant, 
 * si hi ha una persona en el mateix espai que ell, se'l menjarà. 
 * 
 * @invariant Només n'hi ha 1 en tot el laberint.
 * @invariant No canviarà de sala fins haver-se menjat a tothom d'aquesta.
 * @invariant No sortirà mai del laberint
 * @invariant Té la capacitat d'entrar als espais encara que estiguin plens si hi ha minim 1 persona humana
 *
 * @author eloicanigueral
 */

import java.util.ArrayList;


public class AlienGran extends Personatge{

    /**
     * @pre Es crida el constructor de l'alien gran juntament amb la seva capacitat de memòria
     * 
     * @post Es crea l'alien gran amb la seva capacitat de memoria determinada
     */
    public AlienGran(String nom) {
        super(nom, -1);
    }

    /**
     * @pre Està a una sala juntament amb un humà
     * 
     * @post Elimina / mata a un personatge que estigui a la mateixa sala que ell en el seu torn
     */
    public void matar(Personatge p){
        p.morir(p.claus, p.teSmartGlasses());
        espaiActual.sortir(p);
        System.out.println("   -> " + nom + " MATA a " + p.getNom()); //per borrarrr!!!
    }

    /**
     * @pre: --
     * @post: Busca una victima en la sala actual i la mata (retornant el seu nom)
     */
    public String buscarVictima(Espai e){ // en vd.. podria fer un for amb un return  dins no???
        int i = 0;
        String victima = null;
        boolean haMatat = false;
        while(i<e.getPersonatges().size() && !haMatat){
            Personatge p = e.getPersonatges().get(i);
            if(p instanceof Huma || p instanceof Porter){
                matar(p);
                victima = p.getNom();
                haMatat = true;
            }
            i++;
        }
        return victima;
    }

    /**
     * @pre --
     * @post es decideix quina accio fara l'alien (moure's de sala / quedar-se i matar)
     */
    public void actuar(){
        int idDesti = 0;
        String mataA = null;

        if (!espaiActual.hiHaGuardia() && espaiActual.hiHaVictimes()){ //si no h i ha guardia i hi ha victimes, ha de matar
            // int i = 0;
            // boolean haMatat = false;
            // while(i<espaiActual.getPersonatges().size() && !haMatat){
            //     Personatge p = espaiActual.getPersonatges().get(i);
            //     if(p instanceof Huma || p instanceof Porter){
            //         matar(p);
            //         haMatat = true;
            //         mataA = p.getNom();
            //     }
            //     i++;
            // }
            mataA = buscarVictima(espaiActual); //si funcona.. aixi estalvio codi
        } else { /// he de afegir aqui quee... si es mou a una sala que hi ha humans.. ha de matar!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            Porta seguent = escollirSeguentPorta();
            if (seguent != null){
                Espai origen = espaiActual;
                Espai desti = seguent.altreCostat(); //revisar aixo de desti i tal.. ---------- mirar com ho te l'arnau pq nose pas si comprova limits.... (millor mirar el d alienpetit)
                idDesti = desti.mostrarId();
                boolean potEntrar = !desti.estaPle() || (desti.hiHaVictimes() && !desti.hiHaGuardia());
                
                if(potEntrar){
                    if(desti.hiHaVictimes() && !desti.hiHaGuardia()){
                        mataA = buscarVictima(desti);
                    }
                    memoria.recordarEspai(espaiActual, espaiActual.esPerillos());
                    espaiActual.sortir(this);
                    desti.entrar(this);
                    System.out.println("   -> " + nom + " es mou de sala " + origen.mostrarId() + " a sala " + desti.mostrarId());
                } else {
                    idDesti*=-1;
                }
            }
        }
        mostrarMoviment(new ArrayList<Integer>(), false, idDesti, mataA);

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
            if (portes.get(i).altreCostat().esSortida()){
                portes.remove(i);
                i--;
            } else{
                if(memoria.recorda(portes.get(i).altreCostat())) recorda.add(portes.get(i));
                else noRecorda.add(portes.get(i));
            }
        }               

        // if(noRecorda.size() == 0 && recorda.size()>0){
        //     ArrayList<Porta> perillosa = new ArrayList<>();
        //     ArrayList<Porta> noPerillosa = new ArrayList<>();
        //     for(int i=0; i<recorda.size(); i++){
        //         if(memoria.esPerillos(recorda.get(i).altreCostat())) perillosa.add(recorda.get(i));
        //         else noPerillosa.add(recorda.get(i));
        //     }

        //     if(noPerillosa.size()>0 ){
        //         escollida = noPerillosa.get(rand.nextInt(noPerillosa.size()));
        //     } else if(noPerillosa.size()==0 && !espaiActual.esPerillos()){ //totes son perilloses excepte l'espai actual, no es mou
        //         escollida = null;
        //     } else if(noPerillosa.size()==0 && perillosa.size()>0){ //si totes son perilloses i la actual tambe, es mou random
        //         escollida = perillosa.get(rand.nextInt(perillosa.size()));
        //     }
        //     else{
        //         escollida = recorda.get(rand.nextInt(recorda.size()));
        //     }
        // } else if (noRecorda.size()>0){
        //     escollida = noRecorda.get(rand.nextInt(noRecorda.size()));
        // }
        // else {
        //     escollida = null;
        //     System.out.println("es burru i no recorda res");
        // }

        //revisar aixo.. iii av erue si aixi va tb.. pq no cal perilloses no??? pel gran.. a tot arreu on vaigi la ocnvertira en perillosa
        if(noRecorda.size()>0){
            escollida = noRecorda.get(rand.nextInt(noRecorda.size()));
        }
        else if(recorda.size()>0){
            escollida = recorda.get(rand.nextInt(recorda.size()));
        }
        else{ //cal???????????????????????????????????????????????????????????????????????????
            System.out.println("es burru i no recorda res");
            escollida = null;
        }
        
        return escollida; 
    }

}