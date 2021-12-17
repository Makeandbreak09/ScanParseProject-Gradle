package my_project.model;

import KAGO_framework.model.abitur.datenstrukturen.List;

/**
 * Diese Klasse scannt Strings für die Sprache L_Knebi = k(ne)*bi
 */
public class KnebiScanner extends Scanner<String,String> {

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
            if(input.charAt(i) == 'k'){
                //Falls das nächste Zeichen ein 'k' ist, wird ein neuer Token 'START' in die liste eingefügt.
                this.tokenList.append(new Token(input.charAt(i),"START"));
                debugOutput+="START > ";
            } else if (input.charAt(i) == 'n'){
                if(i<input.length()-1){
                    if(input.charAt(i+1) == 'e'){
                        //Falls die nächste zwei Zeichen ein 'n' gefolgt von einem 'e' ist, wird ein neuer Token 'MIDDLE' in die liste eingefügt.
                        this.tokenList.append(new Token(input.substring(i,i+2),"MIDDLE"));
                        debugOutput+="MIDDLE > ";
                        i++;
                    } else return false;
                } else return false;
            } else if (input.charAt(i) == 'b'){
                if(i<input.length()-1){
                    if(input.charAt(i+1) == 'i'){
                        //Falls die nächste zwei Zeichen ein 'b' gefolgt von einem 'i' ist, wird ein neuer Token 'END' in die liste eingefügt.
                        this.tokenList.append(new Token(input.substring(i,i+2),"END"));
                        debugOutput+="END > ";
                        i++;
                    } else return false;
                } else {
                    return false;
                }
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
