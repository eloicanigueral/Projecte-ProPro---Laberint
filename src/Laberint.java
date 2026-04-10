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
        this.espais = new ArrayList<Espai>();
        this.portes = new ArrayList<Porta>();
        this.personatges = new ArrayList<Personatge>(); 
        


        Espai s1 = new Espai(1, 5);
        Espai s2 = new Espai(2, 5);
        Espai s3 = new Espai(3, 5);
        Espai s4 = new Espai(4, 5);

        Porta p1 = new Porta(1, s1, s2);
        Porta p2 = new Porta(2, s2, s3);
        Porta p3 = new Porta(3, s3, s4);

        s1.addPorta(p1);
        s2.addPorta(p1);
        s2.addPorta(p2);
        s3.addPorta(p2);
        s3.addPorta(p3);
        s4.addPorta(p3);

        Huma h = new Huma(5);
        h.afegirClau(1);
        h.afegirClau(2);
        h.afegirClau(3);

        s1.entrar(h);

        espais.add(s1); 
        espais.add(s2);
        espais.add(s3); 
        espais.add(s4);
        portes.add(p1); 
        portes.add(p2);
        portes.add(p3);
        personatges.add(h);
        
        //personatges.add(new Huma(10)); //aixo es aixi???? (de prova)
        //espais.add(new Espai(10)); //aixo es aixi???? (de prova)
        //espais.add(new Espai(10)); //aixo es aixi???? (de prova)
        //portes.add(new Porta(espais.get(0), espais.get(1))); //aixo es aixi???? (de prova)


        //despres de crear tot he de indicar quines son les sales d'entrada i de sortida...
        //amb un random o algo aixi... o tb podria ser que el constructor del laberint rebés com a paràmetre un fitxer amb la configuració del laberint i així ja es crearia tot a partir d'això... (aixo seria lo millor) (però ara per ara ho

    }


    /**
     * 
     * @pre: Es crida el metode juntament amb el nom del fitxer amb la configuració inicial del laberint
     * @post: Es crea el laberint a partir de la configuració del fitxer
     */
    public void llegirLaberint(String nomFitxer) {
        
        File fitxer = new File(nomFitxer);
        

        //if else? que fa try i catch
        // que polles fa scanner
        try (Scanner punter = new Scanner(fitxer)){
            while (punter.hasNextLine()) {
                String linia = punter.nextLine();
                llegirLinia(linia);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No s'ha trobat el fitxer: " + nomFitxer);
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
          // Processar línia de porta
          break;
        case "ap":
          // Processar línia de personatge
          break;
        case "g":
          // Processar línia de porta
          break;
        case "p":
          // Processar línia de porta
          break;
        case "sala":
          // Processar línia de porta
          //la primera sala es la de entrada, +la ultima es de sortida?
          break;
        case "pas":
          // Processar línia de porta
          break;
          
      }

    }

//tipus:nom:memoria:[c1,c2,...]:ulleres
    private void llegirPersonatge(String tipus, Scanner punter) {
        String nom = punter.next();
        int memoria = punter.nextInt();

        ArrayList<Integer> claus = new ArrayList<>();
        Scanner clausScanner = new Scanner(punter.next());
        clausScanner.useDelimiter(",");
        while (clausScanner.hasNextInt()) {
            claus.add(clausScanner.nextInt());
        }
        if (tipus.equals("h")) {
            boolean ulleres = punter.nextBoolean();
            //Huma h = new Huma(memoria); //aixo sha de fer?? pq el de abaix no existeix........ no podem cridar personatge diredctament
            //h.setUlleres(ulleres); aligual he de fer aixo per les ulleres nose.
            llegirHuma(nom, memoria, claus, ulleres); //clar aixo es crida directe a huma.. mirar classe.....
            personatges.add(h);
        } else if (tipus.equals("ag")) {
            // Llegir Alien Gran
        } else if (tipus.equals("ap")) {
            // Llegir Alien Petit
        }
        //...

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