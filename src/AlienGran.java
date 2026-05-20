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
        p.espaiActual().sortir(p);
    }

    /**
     * @pre: --
     * @post: Busca una victima en la sala actual i la mata (retornant el seu nom)
     */
    public String buscarVictima(Espai e){
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
            mataA = buscarVictima(espaiActual);
        } else {
            Porta seguent = escollirSeguentPorta();
            if (seguent != null){
                Espai origen = espaiActual;
                Espai desti = seguent.altreCostat();
                idDesti = desti.mostrarId();
                boolean potEntrar = !desti.estaPle() || (desti.hiHaVictimes() && !desti.hiHaGuardia());
                
                if(potEntrar){
                    if(desti.hiHaVictimes() && !desti.hiHaGuardia()){
                        mataA = buscarVictima(desti);
                    }
                    memoria.recordarEspai(espaiActual, espaiActual.esPerillos());
                    espaiActual.sortir(this);
                    desti.entrar(this);
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

        if(noRecorda.size()>0){
            escollida = noRecorda.get(rand.nextInt(noRecorda.size()));
        }
        else if(recorda.size()>0){
            escollida = recorda.get(rand.nextInt(recorda.size()));
        }
        else{ 
            escollida = null;
        }
        
        return escollida; 
    }

}