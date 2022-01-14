package xmlparser4.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import xmlparser4.document.Attributo;
import xmlparser4.document.Documento;
import xmlparser4.document.Elemento;
import xmlparser4.document.Nodo;

public class Parser {

    public static void main(String[] args) {
        Documento doc = parse("./test_parser5.xml");
        List<String> albero = doc.getRoot().getAlbero(0);
        for (String string : albero) {
            System.out.println(string);
        }
        System.out.print("Documento getRootElement: \n");
        System.out.println(doc.getRoot().toString());
        System.out.print("Documento getChildNodes:\n");
         for (Nodo  nodo  : doc.getChildNodes()) {
             System.out.println(nodo.toString() + "\n");
         }
        System.out.println("get child elements di root");
        for (Elemento e : doc.getRoot().getFigliList()) {
            System.out.println(e.toString());
        }
        System.out.println();
        System.out.println("get element tag name: nome");
        System.out.println(doc.getRoot().getElementsByTagName("nome"));
        System.out.println("tag name : " +doc.getRoot().getTagName());
        System.out.println("text content : " + doc.getRoot().getElementsByTagName("class").get(0).getTextContent());
        System.out.println("attribute (name) : " + doc.getRoot().getElementsByTagName("property").get(0).getAttribute("name"));
    }

    public static Documento parse(String fileName) {
        // leggere il file
        File file;
        FileReader fr = null;
        BufferedReader bfr = null;

        // TODO controlli sul nome del file

        try {
            file = new File(fileName);
            fr = new FileReader(file);
            bfr = new BufferedReader(fr);
            // leggo fino al primo carattere valido <
            int readChar;
            while (((readChar = bfr.read()) != '<')) { // puo essere -1 per la fine del doc
                if (readChar == -1)
                    throw new FileNotValidoException("File non valido: sembra essere vuoto");
            }

            return LeggiBufferato(bfr);

        } catch (FileNotFoundException fEx) {
            System.err.println("File non trovato: " + fileName);
            fEx.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotValidoException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (bfr != null)
                    bfr.close();
                if (fr != null)
                    fr.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return null;
    }

    private static Documento LeggiBufferato(BufferedReader bfr) throws IOException, FileNotValidoException {
        int readChar;
        // prima apertura di tag
        if ((readChar = bfr.read()) == '?') {
            // leggo tag di intestazione
            // TODO riconosci tag intestazione
            while ((readChar = bfr.read()) != '>')
                if (readChar == -1)
                    throw new FileNotValidoException("File non valido: il tag di intestazione non si chiude");
            readChar = bfr.read();
        }

        // cerco l'inizio di root
        while (((readChar = bfr.read()) != '<')) { // puo essere -1 per la fine del doc
            if (readChar == -1)
                throw new FileNotValidoException("File non valido: manca inizio di una root");
        }

        Documento doc = new Documento();

        String nextToken = nextToken(bfr);
        doc.setRoot(leggiToken(nextToken, null, bfr));

        return doc;

    }

    private static Elemento leggiToken(String token, Elemento padre, BufferedReader bfr) throws IOException, FileNotValidoException {
        Elemento elem = null;
        //TODO testa riconosci commenti 
        if(isTagDiChiusura(token)){    //caso 3
            if (padre.getTagName().equals(token.substring(1, token.indexOf('>')))){
                return null;
            }
            else if(isTagNameCommentato(padre.getTagName())){
                if (padre.getTagName().equals(token.substring(1, token.indexOf("-->")))){
                    return null;
                }
            }
            else {
                throw new FileNotValidoException("Tag non chiusa");}
        }
        else if(isTagAutoconclusivo(token)){//caso 4 
            elem = leggiAutoconclusivo(token);
            return elem;
        }
        else if(isTagDiApertura(token)){ //caso 1
            String tagName = parseTagName(parseTagBodyFromTokenApertura(token));
            boolean isCommentato = isTagNameCommentato(tagName);
            elem = new Elemento(tagName);
            elem.setAttributi(parseAttributi(parseTagBodyFromTokenApertura(token)));
            Elemento figlioLetto = leggiToken(nextToken(bfr), elem, bfr);
            while(figlioLetto !=null){
                elem.addFiglio(figlioLetto);
                figlioLetto = leggiToken(nextToken(bfr), elem, bfr);
            }
            //FIXME devo supportare l'aggiunta di null ai figli
            if (isCommentato) return null;
            else return elem;
        }
        else if(isTagConText(token)){   
            elem  = leggiFogliaDa(token);
            if (leggiToken(nextToken(bfr), elem, bfr) == null)
                return elem;
            else throw new FileNotValidoException("Tag non chiusa"); //non dovrebbe succedere se caso 3 funziona
        } 
        else throw new FileNotValidoException("Errore sconosciuto");
        return null;
    }

    private static boolean isTagNameCommentato(String tagName) {
        return (tagName.startsWith("!--"));
    }

    private static Elemento leggiAutoconclusivo(String token) {
        String trimmed = token.trim();
        String tagBody = trimmed.substring(0, trimmed.length() - 2);
        String tagName = parseTagName(tagBody);
        Elemento elem = new Elemento(tagName);
        elem.setAttributi(parseAttributi(tagBody));
        return elem;
    }

    private static boolean isTagAutoconclusivo(String token) {
        String trimmed = token.trim();
        return ((trimmed.charAt(trimmed.length() - 1)) == '>' && token.charAt(trimmed.length() - 2) == '/');
    }

    private static boolean isTagDiChiusura(String token) {
        String trimmed = token.trim();
        return (token.charAt(0) == '/' && (trimmed.charAt(trimmed.length() - 1) == '>'));
    }

    private static Elemento leggiFogliaDa(String token) {
        String tagBody = token.substring(0, token.indexOf('>'));
        Elemento foglia = new Elemento(parseTagName(tagBody));
        foglia.setAttributi(parseAttributi(tagBody));
        String nodeText = token.substring(token.indexOf('>') + 1);
        foglia.setText(nodeText);
        return foglia;
    }

    static String parseTagBodyFromTokenApertura(String tokenApertura) {
        String trimmed = tokenApertura.trim();
        return trimmed.substring(0, trimmed.length() - 1);
    }

    private static String parseTagName(String tagBody) {
        return (tagBody.split(" "))[0];
    }

    private static List<Attributo> parseAttributi(String tagBody) {
        // take first word (tag name) and delete it
        // TODO testami
        List<Attributo> aList = new ArrayList<>();
        if (tagBody.indexOf("=") == -1)
            return aList; // se non ho attrubuti ritorno la lista vuota
        String attributiString = tagBody.substring(tagBody.indexOf(" ") + 1);

        String nomeAttr = null;
        String textAttr = null;

        String regex = "(.*?)=(\".*?\") ";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(attributiString + " ");

        while (matcher.find()) {
            nomeAttr = matcher.group(1);
            textAttr = matcher.group(2);
            aList.add(new Attributo(nomeAttr, textAttr));
        }
        return aList;
    }

    private static boolean isTagConText(String token) {
        int posChius = token.indexOf('>');
        if (posChius == -1)
            return false;
        else
            return true;
    }

    private static boolean isTagDiApertura(String token) {
        String daControllare = token.trim();
        return ((daControllare.charAt(daControllare.length() - 1)) == '>');
    }

    private static String nextToken(BufferedReader bfr) throws IOException, FileNotValidoException {
        String token = "";
        int letto;
        while ((letto = bfr.read()) != '<') {
            if (letto != -1) // se non ho finito il file
                token += (char) letto;
            else
                break;
        }
        return token;
    }

}