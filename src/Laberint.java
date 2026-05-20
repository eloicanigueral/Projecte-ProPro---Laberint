/**
 * @class Laberint
 * @brief Laberint al que s'ha d'escapar.
 *
 * @details El laberint està format per un conjunt d'espais connectats entre si.
 * Dins dels espais s'hi troben personatges i objectes. Un espai pot ser un passadís o una sala.
 * 
 * Aquesta classe serà la responsable de gestionar la simulació del joc,
 * controlant l'ordre de moviment dels personatges i executant els torns
 * de simulació fins que s'acabi el joc.
 * 
 * Es guardarà el nombre de sales que té el laberint, com estan connectades entre si,
 * el nombre de personatges i de quin tipus són.
 * 
 * @author eloicanigueral
 */

import java.util.ArrayList;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.io.FileNotFoundException;
import java.time.format.SignStyle;
import java.util.Scanner;
import java.util.Collections;



public class Laberint {

    private ArrayList<Espai> espais;
    private ArrayList<Porta> portes;
    private ArrayList<Porta> portesObertes;
    private ArrayList<Personatge> personatges; //tots els personatges VIUS que queden dins del laberint
    private HashMap<Integer, ArrayList<Integer>> conexions; //mapa que conte, segons la id de cada espai, un arraylist de les sales amb les que conecta (les portes que te)

    private ArrayList<Personatge> morts; //els personatges que han mort
    private ArrayList<Personatge> salvats; //elspersonatges que s'han salvat

    

    /**
     * @pre --
     * @post Es crea el laberint amb els espais, portes i personatges.
     */
    public Laberint() {
        espais = new ArrayList<>();
        portes = new ArrayList<>();
        portesObertes = new ArrayList<>();
        personatges = new ArrayList<>();
        conexions = new HashMap<>();

        morts = new ArrayList<>();
        salvats = new ArrayList<>();
        llegirLaberint();
    }


    /**
     * @pre: Es crida el metode juntament amb el nom del fitxer amb la configuració inicial del laberint
     * @post: Es crea el laberint a partir de la configuració del fitxer
     */
    public void llegirLaberint() {        
        try (Scanner punter = new Scanner(System.in)) {
            while (punter.hasNextLine()) {
                String linia = punter.nextLine();
                llegirLinia(linia);
            }
            punter.close();
            connectarEspais();
            conexions.clear();
        } catch (Exception e) {
            System.out.println("No s'ha trobat el fitxer");
        }
    }

    /**
     * @pre: Es crida aquest metode amb la linia a llegir
     * @post: Si aquesta no esta buida, es llegeix i es guarda la informacio rellevant corresponent segons pertoqui (ja sigui per un personatge, o per un espai)
     */
    private void llegirLinia(String linia) {
        if (linia.isEmpty()) return;

        Scanner liniaScanner = new Scanner(linia);
        liniaScanner.useDelimiter(":");
    
        String tipus = liniaScanner.next();

        switch(tipus) {
            case "h":
            llegirPersonatge(tipus, liniaScanner);
            break;

            case "ag":
            String nom = liniaScanner.next();
            AlienGran ag = new AlienGran(nom);
            personatges.add(ag);
            break;

            case "ap":
            llegirPersonatge(tipus, liniaScanner);
            break;

            case "g":
            llegirPersonatge(tipus, liniaScanner);
            break;

            case "p":
            llegirPersonatge(tipus, liniaScanner);
            break;

            case "sala":
                llegirEspai(tipus, liniaScanner);
                break;

                case "pas":
                llegirEspai(tipus, liniaScanner);
                break; 
        }

        liniaScanner.close();
    }

    /** 
     * @pre: Es tracta d'un tipus de personatge del laberint, entrat correctament segons el format d'entrada de personatges
     * @post: Es llegeix el personatge, i depenguent del seu tipus, es guarda la informacio corresponent, creant aquest mateix i afegint-lo a l'array de personatges del laberint
     */
    private void llegirPersonatge(String tipus, Scanner punter) {
        String nom = punter.next();
        int memoria = punter.nextInt();

        if (tipus.equals("h")) {
            ArrayList<Integer> claus = llegirClaus(punter);
            boolean ulleres = punter.nextInt() == 1;
            Huma h = new Huma(nom, memoria, claus, ulleres);
            personatges.add(h);

        } else if (tipus.equals("ap")) {
            ArrayList<Integer> claus = llegirClaus(punter);

            AlienPetit ap = new AlienPetit(nom, memoria, claus);
            personatges.add(ap);

        } else if (tipus.equals("g")) {
            ArrayList<Integer> claus = llegirClaus(punter);

            Guardia g = new Guardia(nom, memoria, claus);
            personatges.add(g);

        } else if (tipus.equals("p")) {
            Porter p = new Porter(nom, memoria);
            personatges.add(p);
        }
    }

    /** 
     * @pre: S'entra un "punter" a una llista de claus en format [x,y,z,...] on x,y,z son numeros enters que corresponen a la clau d'alguna sala (o sortida)
     * @post: Es llegeix i retorna aquesta llista en fomrat ArrayList<> sense els []
    */
    private ArrayList<Integer> llegirClaus(Scanner punter){
        ArrayList<Integer> claus = new ArrayList<>();
        Scanner clausScanner = new Scanner(punter.next().replace("[", "").replace("]", ""));
        clausScanner.useDelimiter(",");
        
        while (clausScanner.hasNextInt()) {
            claus.add(clausScanner.nextInt());
        }
        clausScanner.close();
        return claus;
    }

    /** 
     * @pre: Es tracta d'un espai del laberint (ja sigui una sala o un passadis), entrat corresponentment segons el fomrat d'entrada indicat. Els personatges que s'indiquin que estan a aquest espai, cal que s'hagin creat previament (han d'existir abans de crear l'espai).
     * @post: Es llegeix i crea l'espai, es guarda la informacio necessaria, s'afageixen als personatges si escau, i s'afegeix a l'array d'espais del Laberint. Tambe es guarda un arraylist amb les conexions corresponents a altres espais lligat al seu id (amb un HashMap)
     */
    private void llegirEspai(String tipus, Scanner punter) {
        int id = punter.nextInt();

        ArrayList<Integer> portesEspai = new ArrayList<>();
        Scanner portaScanner = new Scanner(punter.next().replace("(", "").replace(")", ""));
        portaScanner.useDelimiter(",");

        //es llegeixen les portes de l'espai i es guarden en l'arraylist portesEspai
        while (portaScanner.hasNextInt()) {
            portesEspai.add(portaScanner.nextInt());
        }
        portaScanner.close();
        //s'afageix al Hashmap, que segons l'id d'un espai, te tot l'arraylist de portes on connecta
        conexions.put(id, portesEspai);

        int max = punter.nextInt();

        if (tipus.equals("sala")) {
            Scanner personatgeScanner = new Scanner(punter.next().replace("[", "").replace("]", ""));
            personatgeScanner.useDelimiter(",");

            //crea l'espai (false perque no es sortida), i l'afageix a l'arrayList d'espais del laberint
            Espai e = new Espai(id, max, false);
            espais.add(e);
            
            //llegeix els personatges que comencen en aquest espai i els afegeix/mou
            while (personatgeScanner.hasNext()) {
                String nom = personatgeScanner.next();
                //buscar a personatges el que tingui aquest nom i afegirlo a la sala
                for (int i=0; i<personatges.size(); i++){
                    if (personatges.get(i).nom.equals(nom)){
                        e.entrar(personatges.get(i));
                    }
                }
            }
            personatgeScanner.close();
        } else if (tipus.equals("pas")) {
            Espai p = new Espai(id, max,false);
            espais.add(p);
        }
    }
    
    /**
     * @pre: --
     * @post: Es connecten les portes de cada espai. La porta d'un espai, que tingui de desti l'altre espai corresponent
     */
    private void connectarEspais(){
        for (int i=0; i<espais.size(); i++){
            ArrayList<Integer> arrayPortes = conexions.get(espais.get(i).mostrarId()); //arrayList que es queda amb els id de les portes de l'espai a la posicio espais[i] -> pilla l'arrayList de portes que te l'espai amb id=espais[i]  
            ArrayList<Espai> arrayEspais = new ArrayList<>(); //son els espais amb què es conecta la sala espais[i]
            //per a cada porta, busca l'espai amb el que s'ha de connectar (i si no existeix, vol dir que connecta amb una sortida)
            for (int j=0; j<arrayPortes.size(); j++){
                if (arrayPortes.get(j) != 0){
                    Espai desti = altreCostat(arrayPortes.get(j));
                    if (desti==null){
                        desti = new Espai(arrayPortes.get(j), Integer.MAX_VALUE, true);
                    }
                    arrayEspais.add(desti);
                }
            }
            espais.get(i).conectarEspais(arrayEspais);
        }
    }

    /**
     * @return: Busca dins dels espais guardats del laberint, quin te el id=id i retorna aquest Espai, si no existeix (voldra dir que es un id de sortida), retorna null
     */
    public Espai altreCostat(int id) {
        for (int i=0; i<espais.size(); i++){
            if(espais.get(i).mostrarId() == id) return espais.get(i);
        }
        return null;
    }

    /** 
     * @return El laberint és buit (sense cap sala). 
    */
    public boolean buit() {
        return espais.size()<=0;
    }

    /**
     * @pre: Hi ha personatges al laberint (a l'arraylist  personatges)
     * @post: S'ordena l'arraylist personatges segons la prioritat de moviment de cada un, on a la posicio 0 hi queda el que mes prioritat te, i a la ultima el que menys
     */
    private void ordenarPrioritat(){
        Collections.sort(personatges, (a, b) -> {
            if(b.nombreClaus() != a.nombreClaus()) return b.nombreClaus() - a.nombreClaus();
            return a.getNom().compareTo(b.getNom());
        });
    }

    /**
     * @pre Queda algun personatge humà viu dins el laberint
     * @post Avança un torn: on cada personatge que quedi viu actua (segons l'ordre de prioritat = ordre que estan a l'arrayList personatges), es mostra per pantalla el que ha passat en el torn i es baixa el comptador de la porta oberta (si es que n'hi ha)
     */
    public void seguentTorn(){ 
        ordenarPrioritat();

        for (int i=0; i<personatges.size(); i++) {    
            if (!personatges.get(i).estaViu()) {
                morts.add(personatges.get(i));
                personatges.remove(i);
                i--;
            } else {
                personatges.get(i).actuar();

                if (personatges.get(i) instanceof Porter){ //si porter s'ha mogut, afegir porta oberta
                    Porta oberta = personatges.get(i).ultimaPortaOberta;
                    if (oberta != null){
                        if (portesObertes.contains(oberta)){
                            oberta.obrir(); //comprovar si funciona
                        }
                        else{
                            portesObertes.add(oberta);
                        }
                    }
                }


                if (personatges.get(i).haSortit()) {
                    salvats.add(personatges.get(i));
                    personatges.remove(i);
                    i--;
                } 
            }
            
            

        }
        //esta dins del for per tant son X moviments de personatges diferents, no pas X torns diferents, nomes els primers en moure's veuran la porta oberta
        for (int j = 0; j<portesObertes.size(); j++){
            portesObertes.get(j).baixarComptador();
            if (!portesObertes.get(j).estaOberta()) {
                portesObertes.remove(j);
                j--;
            }
        }
    }
    
    /**
     * @return Retorna true si el joc ja s'ha acabat -> que no queda cap huma viu dins del laberint
     */
    public boolean acabat(){
        for (int i=0; i<personatges.size(); i++) {
            if (personatges.get(i) instanceof Huma) 
                return false;
        }
        return true;
    }

    /**
     * @pre: El joc s'ha acabat
     * @post: Es mostren els resultats (de la gent que ha mort, la que s'ha salvat, etc
     */
    public void mostrarResultats() { //emmm aixo nose pas si sha de fer..... pq no ho posa enllco, pero queda be per veureho millor a verue qui sha salvat i qui ha mort.. preguntar!!!
        System.out.println();
        System.out.println(" ---- Resultats ---- ");

        if(salvats.size()>0){
            System.out.println("Personatges salvats:");
            for (int i=0; i<salvats.size(); i++) {
                Personatge p = salvats.get(i);
                System.out.println("   - " + p.getNom() + " [" + p.getClass().getSimpleName() + "]");
            }
        }
        else System.out.println("No s'ha salvat cap humà");
        
        System.out.println(); //aixi es fa per imprimir un "intro?" endl;

        if(morts.size()>0){
            System.out.println("Personatges morts:");
            for (int i=0; i<morts.size(); i++) {
                Personatge p = morts.get(i);
                System.out.println("   - " + p.getNom() + " [" + p.getClass().getSimpleName() + "]");
            }
        }
        else System.out.println("No ha mort ningú");
        

    }
}