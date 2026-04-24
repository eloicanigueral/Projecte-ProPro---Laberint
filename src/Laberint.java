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


public class Laberint {

    private ArrayList<Espai> espais;
    private ArrayList<Porta> portes;
    private ArrayList<Personatge> personatges;
    private HashMap<Integer, ArrayList<Integer>> conexions; //= new HashMap<>(); ???

    /**
     * @post Es crea el laberint amb els espais, portes i personatges.
     */
    public Laberint() {

        espais = new ArrayList<>();
        portes = new ArrayList<>();
        personatges = new ArrayList<>();
        conexions = new HashMap<>();
        llegirLaberint();

        //despres de crear tot he de indicar quines son les sales d'entrada i de sortida...
        //amb un random o algo aixi... o tb podria ser que el constructor del laberint rebés com a paràmetre un fitxer amb la configuració del laberint i així ja es crearia tot a partir d'això... (aixo seria lo millor) (però ara per ara ho

    }


    /**
     * 
     * @pre: Es crida el metode juntament amb el nom del fitxer amb la configuració inicial del laberint
     * @post: Es crea el laberint a partir de la configuració del fitxer
     */
    public void llegirLaberint() {

        //suposo que es aqui on he de fer tot lu de new Hashmap i aquestes coses???
        
        System.out.println("començant a llegir el laberint..."); //........... borrarr!!!!

        //if else? que fa try i catch
        // que polles fa scanner
        try (Scanner punter = new Scanner(System.in)) {
            while (punter.hasNextLine()) {
                String linia = punter.nextLine();
                System.out.println("Llegint línia: " + linia); //........... borrarr!!!!

                llegirLinia(linia);
            }
            System.out.println("Fora while");

            connectarEspais();

            //prova cout per veure personatges:
            System.out.println();
            for (int i = 0; i<espais.size(); i++){
                List<Personatge> pers = espais.get(i).getPersonatges();
                System.out.println("Sala: " + espais.get(i).mostrarId());
                for(int j=0; j<pers.size(); j++){
                    System.out.print(pers.get(j).getNom() + ", ");
                }
                System.out.println();
            }
        } 
        System.out.println("No s'ha trobat el fitxer"); //aixo nomes si no sha pogut obrir/....

    }

    private void llegirLinia(String linia) {
      if (linia.isEmpty()) return;

      Scanner punter = new Scanner(linia);
      punter.useDelimiter(":");
    
      String tipus = punter.next();

      switch(tipus) {
        case "h":
          llegirPersonatge(tipus, punter);
          break;

        case "ag":
          String nom = punter.next();
          AlienGran ag = new AlienGran(nom);
          personatges.add(ag);
          break;

        case "ap":
          llegirPersonatge(tipus, punter);
          break;

        case "g":
          llegirPersonatge(tipus, punter);
          break;
        case "p":
          llegirPersonatge(tipus, punter);
          break;

        case "sala":
            llegirEspai(tipus, punter);
            break;
          //la primera sala es la de entrada, +la ultima es de sortida???????
        case "pas":
            llegirEspai(tipus, punter);
            break; 
      }

    }

    /** fer pre i post!!!! */
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

    //FER PRE I POSSTT!!!!!!!!!!!!!!!!!!!!!
    private ArrayList<Integer> llegirClaus(Scanner punter){
        ArrayList<Integer> claus = new ArrayList<>();
        Scanner clausScanner = new Scanner(punter.next().replace("[", "").replace("]", "")); //mirar els .replace aquests....
        clausScanner.useDelimiter(",");
        while (clausScanner.hasNextInt()) {
            claus.add(clausScanner.nextInt());
        }
        return claus;
    }

    /** fer pre i post!!!! */
    private void llegirEspai(String tipus, Scanner punter) {
        int id = punter.nextInt();

        ArrayList<Integer> portesEspai = new ArrayList<>();
        Scanner portaScanner = new Scanner(punter.next().replace("(", "").replace(")", ""));
        portaScanner.useDelimiter(","); //abans he de llegir el ()...??
        while (portaScanner.hasNextInt()) {
           portesEspai.add(portaScanner.nextInt()); //porta.add(new Porta(portaScanner.nextInt())); ... hi ha errror amb lu de les portes.. comsabem els espais....
        }
        conexions.put(id, portesEspai);

        int max = punter.nextInt();

        if (tipus.equals("sala")) {

            Scanner personatgeScanner = new Scanner(punter.next().replace("[", "").replace("]", ""));
            personatgeScanner.useDelimiter(",");
            Espai e = new Espai(id, max);
            espais.add(e);
            
            while (personatgeScanner.hasNext()) { //existeix?? sjjssj !!!!!!!!!!!!!!!!!!!!!!! FEEEEEEEEEEEEEEEEEEEEEEERRRRRRRRRRRRRRR!!!!!
                String nom = personatgeScanner.next();        //canviar nom        //buscar a personatges el que tingui aquest nom i afegirlo a la sala
                for (int i=0; i<personatges.size(); i++){
                    if (personatges.get(i).nom.equals(nom)){ //fua.. que raro.. aixo es pot????
                        e.entrar(personatges.get(i));
                    }
                }
                //i fer personatges[i].setEspaiActual(sala) o algo aixi
                // no fa res, cal buscar el personatge per nom i afegir-lo a la sala. 
                // Però l'espai es crea després del while, així que has de guardar els noms i afegir-los un cop creat l'espai
            }

        } else if (tipus.equals("pas")) {
            Espai p = new Espai(id, max);
            espais.add(p);
        }
    }
    
    /**
     * fer PRE I POST....!!!!!!!!!!!!!!!!!!!
     * @param id
     * @return
     */
    private void connectarEspais(){
        for (int i=0; i<espais.size(); i++){
            ArrayList<Integer> arrayPortes = conexions.get(espais.get(i).mostrarId()); //dins del get aixo__??... 
            ArrayList<Espai> arrayEspais = new ArrayList<>();
            for (int j=0; j<arrayPortes.size(); j++){
                if (arrayPortes.get(j) != 0){
                    arrayEspais.add(altreCostat(arrayPortes.get(j))); //pq aquest -1??
                    //arrayEspais.add(arrayPortes.get(j).altreCostat()); //si aixo funciona deixar aixi i aixi estalvio un emtode aqui a laberint
                }
            }
            espais.get(i).conectarEspais(arrayEspais);
        }
    }

    //mirarrr...................... el for no magrada.. metode per fer srvir a porta...
    //retorna la sala a la que conecta
    public Espai altreCostat(int id) {
        for (Espai e : espais) {
            if (e.mostrarId() == id) return e;
        }
        return null; //o tirar excepcio o algo aixi
    }


    /** @return La sala de sortida d'aquest laberint. */
    public Espai salaSortida(Espai s) {
        return s;
    }


    /** @return El laberint és buit (sense cap sala). */
    public boolean buit() {
        return false;
    }

    /**
     * @pre Queda algun personatge humà viu dins el laberint
     * 
     * @post Avança un torn
     */
    public void seguentTorn(){ //emmm sha de comprovar aquest pre en algun lloc no????!!!!!!!!
        for (Personatge p : personatges) {
            if (p.estaViu()) {
                p.actuar();
                //p.recollirObjecte();
            }
        }
    }  //millor fer tot aixo en una altra classe aprat.. que sigui per tota la simulacio i tal.. com un main

    //metode moviment...
    /// balblabal crido actuar del personatge que li toqui
    /// i dsps miro si hi ha objectes al terra, si nhi ha, recollir objecte personatge
}