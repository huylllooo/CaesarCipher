import edu.duke.*;

public class CaesarCipher {
        private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        private String alphabetLowCase = alphabet.toLowerCase();
    public String encrypt(String input, int key) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Compute the shifted alphabet
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        String shiftedLowCase = alphabetLowCase.substring(key) + alphabetLowCase.substring(0,key);
        //Count from 0 to < length of encrypted, (call it i)
        for (int i = 0; i< encrypted.length(); i++ ) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //Find the index of currChar in the alphabet (call it idx)
            int idx = alphabet.indexOf(currChar);
            //If currChar is in the alphabet
            if (idx != -1) {
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar  = shiftedAlphabet.charAt(idx);
                //Replace the ith character of encrypted with newChar
                encrypted.setCharAt(i, newChar);
            }
            else {
                idx = alphabetLowCase.indexOf(currChar);
                if (idx != -1) {
                    //Get the idxth character of shiftedAlphabet (newChar)
                    char newChar  = shiftedLowCase.charAt(idx);
                    //Replace the ith character of encrypted with newChar
                    encrypted.setCharAt(i, newChar);
                }
            }
            //Otherwise: do nothing
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }
    private char encryptChar(char input, int key) {
        char encrypted;
        if (Character.isLowerCase(input)) {
           int idx = alphabetLowCase.indexOf(input);
           if (idx + key < 26)
             encrypted = alphabetLowCase.charAt(idx + key);
           else 
             encrypted = alphabetLowCase.charAt(idx + key - 26);
        } else {
            int idx = alphabet.indexOf(input);
           if (idx + key < 26)
             encrypted = alphabet.charAt(idx + key);
           else 
             encrypted = alphabet.charAt(idx + key - 26);
        }
        return encrypted;
    }
    public String encrypt(String input, int key1, int key2) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            if (i%2 == 0)
                encrypted.setCharAt(i, encryptChar(currChar, key1));
            else 
                encrypted.setCharAt(i, encryptChar(currChar, key2));
        }
        return encrypted.toString();
    }
    public void testCaesar() {
        int key = 17;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
    }
}

