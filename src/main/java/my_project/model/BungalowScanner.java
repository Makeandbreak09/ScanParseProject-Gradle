package my_project.model;

import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.model.Scanner;

/**
 * Diese Klasse scannt Strings für die Sprache L_Knebi = k(ne)*bi
 */
public class BungalowScanner extends Scanner<String,String> {

    private String debugOutput;

    @Override
    public boolean scan(String input) {
        debugOutput = "\nScanner recognized following tokens:\n";
        //Wenn der Input null oder leer ist, wird false zurückgegeben.
        if(input == null || input.length() == 0){
            debugOutput+="Sorry, can't scan empty String.";
            return false;
        }
        //Andernfalls wird eine neue Tokenliste erstellt.
        this.tokenList = new List();
        for(int i = 0; i<input.length(); i++){
            if(input.charAt(i) == 'a'){
                //Falls das nächste Zeichen ein 'a' ist, wird ein neuer Token 'SIDEWALL' in die liste eingefügt.
                this.tokenList.append(new Token(input.charAt(i),"SIDEWALL"));
                debugOutput+="SIDEWALL > ";
            } else if (input.charAt(i) == 'w'){
                if(i<input.length()-1){
                    if(input.charAt(i+1) == 'f'){
                        //Falls die nächste zwei Zeichen ein 'w' gefolgt von einem 'f' ist, wird ein neuer Token 'WINDOW' in die liste eingefügt.
                        this.tokenList.append(new Token(input.substring(i,i+2),"WINDOW"));
                        debugOutput+="WINDOW > ";
                        i++;
                    } else {
                        //Falls das nächste Zeichen ein 'w' ist, wird ein neuer Token 'WALL' in die liste eingefügt.
                        this.tokenList.append(new Token(input.charAt(i),"WALL"));
                        debugOutput+="WALL > ";
                    }
                } else {
                    //Falls das nächste Zeichen ein 'w' ist, wird ein neuer Token 'WALL' in die liste eingefügt.
                    this.tokenList.append(new Token(input.charAt(i),"WALL"));
                    debugOutput+="WALL > ";
                }
            } else if(input.charAt(i) == 't'){
                //Falls das nächste Zeichen ein 't' ist, wird ein neuer Token 'DOOR' in die liste eingefügt.
                this.tokenList.append(new Token(input.charAt(i),"DOOR"));
                debugOutput+="DOOR > ";
            } else return false;
        }
        //Zum Schluss wird noch ein Token '#' eingefügt, um zu zeigen, dass die Liste zu Ende ist.
        this.tokenList.append(new Token("#","NODATA"));
        tokenList.toFirst(); // WICHTIG!
        return true;
    }

    public String getDebugOutput(){
        return debugOutput;
    }
}
