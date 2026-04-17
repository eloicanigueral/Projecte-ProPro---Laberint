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
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Laberint {

    private ArrayList<Espai> espais;
    private ArrayList<Porta> portes;
    private ArrayList<Personatge> personatges;

    /**
     * @post Es crea el laberint amb els espais, portes i personatges.
     */
    public Laberint() {
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
        
                System.out.println("començant a llegir el laberint..."); //........... borrarr!!!!

        //if else? que fa try i catch
        // que polles fa scanner
        try (Scanner punter = new Scanner(System.in)) {
            while (punter.hasNextLine()) {
                String linia = punter.nextLine();
                System.out.println("Llegint línia: " + linia); //........... borrarr!!!!

                llegirLinia(linia);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No s'ha trobat el fitxer");
        }
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
          // Processar línia de guardia
          break;
        case "p":
          // Processar línia de porter
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

        ArrayList<Integer> claus = new ArrayList<>();
        Scanner clausScanner = new Scanner(punter.next());
        clausScanner.useDelimiter(","); //abans he de llegir el []...??
        while (clausScanner.hasNextInt()) {
            claus.add(clausScanner.nextInt());
        }
        if (tipus.equals("h")) {
            boolean ulleres = punter.nextBoolean();
            Huma h = new Huma(nom, memoria, claus, ulleres);
            personatges.add(h);

        } else if (tipus.equals("ap")) {
            AlienPetit ap = new AlienPetit(nom, memoria, claus);
            personatges.add(ap);

        } else if (tipus.equals("g")) {
            // Llegir guardia
        } else if (tipus.equals("p")) {
            // Llegir porter
        }
    }

    /** fer pre i post!!!! */
    private void llegirEspai(String tipus, Scanner punter) {
        int id = punter.nextInt();

        ArrayList<Porta> porta = new ArrayList<>();
        Scanner portaScanner = new Scanner(punter.next());
        portaScanner.useDelimiter(","); //abans he de llegir el ()...??
        while (portaScanner.hasNextInt()) {
            claus.add(portaScanner.nextInt());
        }
        int max = punter.nextInt();

        if (tipus.equals("sala")) {

            Scanner personatgeScanner = new Scanner(punter.next());
            personatgeScanner.useDelimiter(","); //abans he de llegir el []...??
            while (personatgeScanner.hasNextString()) { //existeix?? sjjssj
                //buscar a personatges el que tingui aquest nom i afegirlo a la sala
                //i fer personatges[i].setEspaiActual(sala) o algo aixi
            }

            Espai e = new Espai(id, porta, max);
            espais.add(e);

        } else if (tipus.equals("pas")) {
            Espai p = new Espai(id, porta, max);
            espais.add(p);
        }
    }

    /** @return La sala d'entrada d'aquest laberint. */ //HA DE RETORNAR UNA LLISTA... PQ NHI POT HAVER MES DE UNA TANT DE ENTRADA COM DE SORTIDA
    public Espai salaEntrada(Espai e) {
        return e;
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