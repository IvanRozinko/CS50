package com.shpp.p2p.cs.irozinko.assignment5;

import com.shpp.cs.a.console.TextProgram;

/**Taking a string representing a single word, counting number of syllables
 * in that word by counting the number of groups of vowels, except for isolated
 * e's at the end of the word. (Y counts as a vowel). All words
 */
public class Assignment5Part1 extends TextProgram {

    /**
     * Runs the program. In a loop asking user to enter a word counting number of syllables
     */
    public void run() {
        /* Repeatedly prompt the user for a word and print out the estimated
         * number of syllables in that word.
         */
        while (true) {
            String word = readLine("Enter a single word: ");
            println("  Syllable count: " + syllablesIn(word));
        }
    }
    /**
     * Given a word, estimates the number of syllables in that word according to the
     * heuristic specified in the handout.
     *
     * @param word A string containing a single word.
     * @return An estimate of the number of syllables in that word.
     */
    private int syllablesIn(String word) {
        int result = 0;
        String newWord = word.toLowerCase();
        for (int i = 0; i < newWord.length(); i++){
            char letter = newWord.charAt(i);
            if (isVowel(letter)) {
                if( i > 0 && isVowel(newWord.charAt(i-1)) || letter == 'e' && i == newWord.length() - 1){
                    continue;
                }
                result ++;
            }
        }
        if (result == 0){
            return result + 1;
        }
    return result;
    }

    /**
     * This method check if parameter letter is vowel
     * @param letter char which method takes
     * @return boolean answer true/false
     */
    private boolean isVowel(char letter) {
        boolean result = false;
        char[] vowels = {'a', 'e', 'y', 'u', 'i', 'o'};
        for (char vowel : vowels) {
            if (vowel == letter) {
                result = true;
            }
        }
        return result;
    }
}
