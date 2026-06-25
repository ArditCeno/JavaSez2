 /*1. (16 pikë)
 a) Shpjegoni konceptin e trashëgimisë në Java. Cili është ndryshimi midis trashëgimisë dhe ndërfaqeve?

 Trashëgimia është mekanizmi që lejon një klasë të trashëgojë atributet dhe metodat nga një klasë tjetër
 duke përdorur fjalën kyç "extends". Klasa që trashëgon quhet nënklasë (subclass) dhe ajo nga e cila
 trashëgohet quhet superklasë (superclass). Ndryshimi kryesor: një klasë mund të trashëgojë vetëm nga
 një superklasë (trashëgimi e vetme), por mund të implementojë shumë ndërfaqe. Ndërfaqet përmbajnë
 vetëm metoda abstrakte (para Java 8) dhe konstante, ndërsa trashëgimia mund të përfshijë implementim.

 b) Çfarë janë metodat abstrakte dhe klasat abstrakte? Jepni një shembull të një klase abstrakte në Java.

 Klasa abstrakte është një klasë që nuk mund të instancohet drejtpërdrejt dhe shënohet me fjalën "abstract".
 Metodat abstrakte janë metoda pa trup (pa implementim) që duhet të implementohen nga nënklasat.
 Shembull:
 abstract class Shape {
     abstract double llogaritSiperfaqen();
 }
 class Rrethi extends Shape {
     double rrezja;
     double llogaritSiperfaqen() { return Math.PI * rrezja * rrezja; }
 }

 c) Cili është ndryshimi midis një anëtari statik dhe një anëtari jo statik?

 Anëtari statik (static) i përket klasës dhe jo instancës, pra ndahet nga të gjitha objektet e klasës.
 Qaset me emrin e klasës (p.sh. Math.PI) dhe ekziston pa krijuar objekt. Anëtari jo statik i përket
 secilit objekt veç e veç dhe krijohet kur krijohet objekti. Anëtarët statikë nuk mund të qasen
 në anëtarë jo statikë direkt.

 d) Shpjegoni konceptin e trajtimit të ngjarjeve në java. Si realizohet kapja dhe trajtimi i tyre
 në Java? Për çfarë arsye përdoren klasat e brendshme në kapjen e ngjarjeve?

 Trajtimi i ngjarjeve (event handling) në Java bazohet në modelin e delegimit të ngjarjeve (Delegation
 Event Model). Një burim ngjarje (button, text field) gjeneron një ngjarje dhe e dërgon tek listeners
 që janë regjistruar për atë ngjarje. Realizohet duke implementuar interface-in përkatës (p.sh.
 ActionListener) dhe duke mbishkruar metodën (p.sh. actionPerformed). Klasat e brendshme (inner classes)
 përdoren sepse kanë qasje direkte në anëtarët e klasës që i përmban, duke e bërë kod më të lexueshëm
 dhe duke i mbajtur logjikën e trajtimit afër komponentëve.
 */
