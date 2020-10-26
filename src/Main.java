
import java.io.*;
import java.util.ArrayList;


public class Main {
    /**
     * The method handles an exception that may occur when opening file
     * @param args The first argument must be the path to the file
     */
    public static void main(String args[]){
        try {
            if (args.length == 0) {
                throw new IllegalArgumentException("File opening error! File path not set!");
            }else {
                if(readLineFromFile(args[0]) != 0){
                    System.out.println(readLineFromFile(args[0]));
                }
            }
        }catch(IllegalArgumentException ex1){
            System.out.println(ex1.getMessage());
        }
    }

    /**
     *The method counts the number of words in the selected file
     * @param fileName Filename for reading text
     * @return Number of words in the read file
     */
    private static int readLineFromFile(String fileName){
        int simbolCode = 0;
        String word = "";
        ArrayList<String> listWords = new ArrayList<String>();//список в который заносятся слова считываемые из документа
        try(InputStreamReader reader = new InputStreamReader(new FileInputStream(fileName), "UTF-8")){
            while((simbolCode = reader.read()) != -1){
                if (simbolCode >= 1040 && simbolCode <= 1103) { //проверяем является ли байтовое представление
                    // прочитанного символа русской буквой
                    word += (char) simbolCode;//если прочитанный символ является русской буквы, то добавляем его в текущее слово
                }else{
                    if(word != "") {
                        listWords.add(word);//если прочитанный символ не является русской буквой, то добавляем в список текущее
                        //слово, которое состоит из символов предшествующих данному.
                    }
                    word ="";// затиракем текущее слово, что бы записать в него следующие символы.
                }
            }
        }catch(IOException ex1){
            System.out.println("File not found!");
        }
        return listWords.size();
    }


}
