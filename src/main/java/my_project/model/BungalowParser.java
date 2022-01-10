package my_project.model;

public class BungalowParser implements Parser {

    private BungalowScanner scanner;

    public BungalowParser(){
        scanner = new BungalowScanner();
    }

    @Override
    /**
     * Diese Methode parst eine Eingabe und stellt fest, ob sie zur Sprache L_Bungalow =a(w v wf)*t(w v wf)*a gehört
     */
    public boolean parse(String input) {
        //Nur wenn erst ein 'START'-, dann beliebig viele 'MIDDLE'-, dann ein 'END'- und zum Schluss ein 'NODATA'-Token kommt wird true zurückgegeben,
        //ansonsten false.
        if(scanner.scan(input)) {
            if (scanner.getType().equals("SIDEWALL")) {
                scanner.nextToken();
                if (scanner.getType().equals("WALL") || scanner.getType().equals("WINDOW")) {
                    scanner.nextToken();
                    while (scanner.getType().equals("WALL") || scanner.getType().equals("WINDOW")) scanner.nextToken();
                    if (scanner.getType().equals("DOOR")) {
                        scanner.nextToken();
                        if (scanner.getType().equals("WALL") || scanner.getType().equals("WINDOW")) {
                            scanner.nextToken();
                            while (scanner.getType().equals("WALL") || scanner.getType().equals("WINDOW")) scanner.nextToken();
                            if (scanner.getType().equals("SIDEWALL")) {
                                scanner.nextToken();
                                if (scanner.getType().equals("NODATA")) return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    /**
     * Diese Methode dient dazu die Funktionalität des verwendeten Scanners zu überprüfen.
     * @return true, falls der Scanner für die Sprache des Parsers das Wort akzeptiert, sonst false
     */
    public boolean getScannerResult(String input) {
        //Ruft die Methode scan mit dem input auf.
        return scanner.scan(input);
    }

    // ****************** AB HIER FOLGT EINE ÄQUIVALENTE VORGEHENSWEISE (für die Klasse redundant) ********************

    /**
     * Diese ist eine alternative Methode für das Parsen von L_Knebi = k(ne)*bi
     * @param input der zu parsenden String
     * @return true, falls die Eingabe ein Wort der Sprache ist, anderfalls false
     */
    public boolean alternativeParse(String input){
        if(scanner.scan(input)) {
            return checkS();
        }
        return false;
    }

    private boolean checkS(){
        if(scanner.getType().equals("SIDEWALL")) {
            scanner.nextToken();
            return checkA();
        }
        return false;
    }

    private boolean checkA(){
        if (scanner.getType().equals("WALL") || scanner.getType().equals("WINDOW")) {
            scanner.nextToken();
            if(scanner.getType().equals("WALL") || scanner.getType().equals("WINDOW")) {
                return checkA();
            }
            return checkB();
        }
        return false;
    }

    private boolean checkB(){
        if(scanner.getType().equals("DOOR")) {
            scanner.nextToken();
            return checkC();
        }
        return false;
    }

    private boolean checkC(){
        if (scanner.getType().equals("WALL") || scanner.getType().equals("WINDOW")) {
            scanner.nextToken();
            if(scanner.getType().equals("WALL") || scanner.getType().equals("WINDOW")) {
                return checkC();
            }
            return checkD();
        }
        return false;
    }

    private boolean checkD(){
        if (scanner.getType().equals("SIDEWALL")) {
            scanner.nextToken();
            if (scanner.getType().equals("NODATA")) return true;
        }
        return false;
    }

    //************** Ausgabe der Scanner-Analyse zur Nachvollziehbarkeit *********

    public String getScannerOutput(){
        return scanner.getDebugOutput();
    }

}
