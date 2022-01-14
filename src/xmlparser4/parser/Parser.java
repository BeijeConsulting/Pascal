package xmlparser4.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import xmlparser4.document.Attributo;
import xmlparser4.document.Documento;
import xmlparser4.document.Elemento;
import xmlparser4.document.Nodo;

public class Parser {

    public static void main(String[] args) {
        parse("./test_parser2.xml");
    }

    public static Documento parse(String fileName){
        //leggere il file
        File file;
        FileReader fr = null;
        BufferedReader bfr = null;
        
        
        //TODO controlli sul nome del file
        
        try{
            file = new File(fileName);
            fr = new FileReader(file);
            bfr = new BufferedReader(fr);
            //leggo fino al primo carattere valido <
            int readChar;
            while (((readChar = bfr.read()) != '<')){ //puo essere -1 per la fine del doc
                if (readChar == -1 ) throw new FileNotValidoException("File non valido: sembra essere vuoto");
            }
            
             LeggiBufferato(bfr);
            
            
            
        } catch(FileNotFoundException fEx){
            System.err.println("File non trovato: " + fileName);
            fEx.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotValidoException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }finally{
            try {
                if (bfr != null) bfr.close();
                if (fr != null)fr.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        return null;
    }
    
    private static Documento LeggiBufferato(BufferedReader bfr) throws IOException, FileNotValidoException {
        int readChar;
        //prima apertura di tag
        if((readChar = bfr.read()) == '?') {
            //leggo tag di intestazione
            //TODO riconosci tag intestazione
            while((readChar = bfr.read()) != '>')
            if (readChar == -1 ) throw new FileNotValidoException("File non valido: il tag di intestazione non si chiude");
            readChar = bfr.read();
        }
        
        //cerco l'inizio di root
        while (((readChar = bfr.read()) != '<')){ //puo essere -1 per la fine del doc
            if (readChar == -1 ) throw new FileNotValidoException("File non valido: manca inizio di una root");
        }
        
        Documento doc = new Documento();

        String nextToken = nextToken(bfr);
        doc.addFiglio(leggiToken(nextToken, null, bfr));
        
        return doc;
        
    }
    
    private static Elemento leggiToken(String token, Elemento padre, BufferedReader bfr) throws IOException, FileNotValidoException {
        Elemento elem = null;
        //test
        System.out.println("Token trimmato: " + token.trim());
        if(isTagDiChiusura(token)){    //caso 3
            System.out.println("caso 3");
            if (padre.getTagName().equals(token.substring(1, token.indexOf('>')))){
                //test
                System.out.println("Letta chiusura tag: " + padre.getTagName());
                return null;
            }
            else throw new FileNotValidoException("Tag non chiusa");
        }
        else if(isTagAutoconclusivo(token)){
            System.out.println("caso 4 : autocon");
            elem = leggiAutoconclusivo(token);
            return elem;
        }
        else if(isTagDiApertura(token)){ //caso 1
            System.out.println("caso 1");
            String tagName = parseTagName(parseTagBodyFromTokenApertura(token));
            elem = new Elemento(tagName);
            elem.setAttributi(parseAttributi(parseTagBodyFromTokenApertura(token)));
            //TODO leggi se ha attributi
            Elemento figlioLetto = leggiToken(nextToken(bfr), elem, bfr);
            while(figlioLetto !=null){
                elem.addFiglio(figlioLetto);
                figlioLetto = leggiToken(nextToken(bfr), elem, bfr);
            }
            return elem;
        }
        else if(isTagConNodo(token)){    //caso 2
            System.out.println("caso 2");

            elem  = leggiNodoDa(token);
            if (leggiToken(nextToken(bfr), elem, bfr) == null)
                return elem;
            else throw new FileNotValidoException("Tag non chiusa"); //non dovrebbe succedere se caso 3 funziona
        } 
        else throw new FileNotValidoException("Errore sconosciuto");
    }
    
    private static Nodo leggiAutoconclusivo(String token) {
        String trimmed = token.trim();
        String tagBody = trimmed.substring(0, trimmed.length()-2);
        String tagName = parseTagName(tagBody);
        Nodo elem = new Nodo(tagName);
        elem.setAttributi(parseAttributi(tagBody));
        return elem;
    }

    private static boolean isTagAutoconclusivo(String token) {
        String trimmed = token.trim();
        return ((trimmed.charAt(trimmed.length()-1)) == '>' && token.charAt(trimmed.length()-2) == '/');
    }

    private static boolean isTagDiChiusura(String token) {
        String trimmed = token.trim();
        return (token.charAt(0) == '/' && (trimmed.charAt(trimmed.length()-1) == '>'));
    }

    private static Nodo leggiNodoDa(String token) {
        String tagBody = token.substring(0, token.indexOf('>'));
        Nodo nodo = new Nodo(parseTagName(tagBody));
        nodo.setAttributi(parseAttributi(tagBody));
        String nodeText = token.substring(token.indexOf('>')+1);
        nodo.setText(nodeText);
        return nodo;
    }

    static String parseTagBodyFromTokenApertura(String tokenApertura){
        String trimmed = tokenApertura.trim();
        return trimmed.substring(0, trimmed.length()-1);
    }

    private static String parseTagName(String tagBody){
        return (tagBody.split(" "))[0];
    }

    private static List<Attributo> parseAttributi(String tagBody){
        //TODO da fare
        return null;
    }

    private static boolean isTagConNodo(String token) {
        int posChius = token.indexOf('>');
        if (posChius == -1) return false;
        else return true;
    }

    private static boolean isTagDiApertura(String token) {
        String daControllare = token.trim();
        return ((daControllare.charAt(daControllare.length()-1)) == '>');
    }
    
    private static String nextToken(BufferedReader bfr) throws IOException, FileNotValidoException {
        String token = "";
        int letto;
        while((letto = bfr.read()) != '<'){
            if (letto != -1 ) //se non ho finito il file
                token += (char) letto;
            else break;
        }
        return token;
    }
    
    private static Elemento leggiElemento(BufferedReader bfr) throws IOException, FileNotValidoException {
        Elemento elemento;
        //inizio con il reader che punta alla < del tag di inizio
        //memorizzo tutto il tag in String
        String tag = "";
        int letto;
        while((letto = bfr.read()) != '>'){
            if (letto == -1 ) throw new FileNotValidoException("File non valido: si chiude in un tag");
            tag += (char) letto;
        }
        //riconosco il tag
        if (tag.charAt(tag.length()-1) == '/') {
            //tag autoconclusivo
            elemento = new Elemento(tag.substring(0, tag.length()-1));
        }
        else {
            elemento = new Elemento(tag); //TODO
        }
        return elemento;
    }
    
    
}
