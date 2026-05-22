**Lab\_smartglasses.txt**  
Aquest joc de proves s’ha creat per provar el funcionament de les smartGlasses. Aquestes primer porten als personatges a la sortida més propera que tenen. Quan arriben allà, al no tenir la clau d’aquella sortida es descarta i es recalcula el camí fins a l’altre sortida. Per allà poden sortir. Així provem que descarta bé una sortida, segueixen la ruta òpima fins la sortida més propera i recalcula bé fins l’altre sortida.

**lab\_ap.txt**  
En aquest, el que fem és comprovar si l’Àlien Petit actua com és degut, en aquest mateix fitxer hem anat fet petites modificacions per veure si tot funcionava correctament: en un principi, no li vam donar cap clau, per comprovar que no es mogués si no tenia claus. Un cop comprovat que funcionava bé, li vam donar la clau de la sala 1, i vam establir com a límit de personatges 2; com que ja hi apareixen 2 humans, l’àlien no hauria de poder entrar, i efectivament ho intenta però no ho pot fer. Per últim, vam ampliar el límit de la sala, i vam poder comprovar que aquest funcionava correctament.

**lab\_ag.txt**  
Per a aquest fitxer de proves, seguint els passos del de l’àlien petit, vam voler comprovar el mateix per a l’àlien gran. A diferència del petit, aquest sí que ha d’entrar a la sala plena si hi ha humans; efectivament, ho fa. També vam provar de canviar els humans, i que estigués plena d’àliens, i en aquest cas, no hi entrava.

**lab\_ag\_guardia.txt**  
Aprofitant el primer la primera versio del lab\_ag.txt, afeigm també un guardia a la sala amb els humans, i vam poder comprovar el correcte funcionament d’aquest. Ja que si la sala esta plena, com que l’àlien gran no es podria menjar a ningú, fa l’intent d’entrar però se li denega. I si augmentem la capaciat de la sala, veiem com aquest va canviant de sala i per molt que es quedi a la sala amb els humans, no els pot matar perquè hi ha el guàrdia que els protegeix.

**lab\_ag\_g\_p.txt**  
Aquest joc de proves ens serveix per comprovar el correcte funcionament de tots els personatges (en conjunt), ja que utilitzem al porter perquè els hi obri les portes als humans, també al guàrdia perquè protegeix-hi als humans, i un àlien gran. És una simulació curta, tan sols 3 torns, però ens permet veure el correcte comportament de tots els personatges: Veiem com al primer torn, l’àlien gran es mou a la sala 1 (i no mata perquè hi ha el guàrdia),i el porter es mou a la sala 2 (deixant la porta oberta). En el seguent torn, veiem que el guàrdia no es mou, perquè com que hi ha humans a la sala, es queda a protegir-los, i també veiem com els dos humans (que no tenen claus de cap porta, excepte la sortida) es mouen a la sala 2 aprofitant la porta que el porter ha deixat oberta; per últim veiem com l’alien es mou també a la sala 2 i en mateix moviment aprofita per matar. Ja en el 3r torn, el guàrdia no es mou ja que no té clau, i els 2 humans aprofiten per sortir del laberint i salvar-se.

**lab\_porterObre.txt**  
Amb aquest joc de proves comprovem que el porter li obre la porta als humans perquè puguin sortir de la sala on estan (que no tenen claus).

**lab\_recullClaus.txt**  
Aquest joc de proves comprova que els personatges recullen les claus que hi ha al terra. Primer l’alien petit mata a un humà, li agafa les claus que no té (en deixa una al terra perquè ja la té) i quan els humans passen per aquella sala un d’ells agafa la clau que no ha agafat l’alien.

**lab\_final.txt**  
Aquí es comprova tot el funcionament del programa: els humans amb smartGlasses actuen de forma coherent. Es veu com els altres humans no segueixen el camí curt cap a la sortida. El porter deixa les portes obertes. El guardia protegeix bé als humans quan està amb ells. Es veu que hi ha decisions random perquè el resultat sempre és diferent.

