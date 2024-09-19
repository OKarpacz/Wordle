import java.util.Scanner;
import java.util.*;
public class Wordle {
    private static final String[] Word_List_5 = {
            "about", "alert", "argue", "beach", "above", "alike", "arise", "began",
            "abuse", "alive", "array", "begin", "actor", "allow", "aside", "begun",
            "acute", "alone", "asset", "being", "admit", "along", "audio", "below",
            "adopt", "alter", "audit", "bench", "adult", "among", "avoid", "billy",
            "after", "anger", "award", "birth", "again", "angle", "aware", "black",
            "agent", "angry", "badly", "blame", "agree", "apart", "baker", "blind",
            "ahead", "apple", "bases", "block", "alarm", "apply", "basic", "blood",
            "album", "arena", "basis", "board", "boost", "buyer", "china", "cover",
            "booth", "cable", "chose", "craft", "bound", "calif", "civil", "crash",
            "brain", "carry", "claim", "cream", "brand", "catch", "class", "crime",
            "bread", "cause", "clean", "cross", "break", "chain", "clear", "crowd",
            "breed", "chair", "click", "crown", "brief", "chart", "clock", "curve",
            "bring", "chase", "close", "cycle", "broad", "cheap", "coach", "daily",
            "broke", "check", "coast", "dance", "brown", "chest", "could", "dated",
            "build", "chief", "count", "dealt", "built", "child", "court", "death",
            "debut", "entry", "forth", "group", "delay", "equal", "forty", "grown",
            "depth", "error", "forum", "guard", "doing", "event", "found", "guess",
            "doubt", "every", "frame", "guest", "dozen", "exact", "frank", "guide",
            "draft", "exist", "fraud", "happy", "drama", "extra", "fresh", "harry",
            "drawn", "faith", "front", "heart", "dream", "false", "fruit", "heavy",
            "dress", "fault", "fully", "hence", "drill", "fibre", "funny", "night",
            "drink", "field", "giant", "horse", "drive", "fifth", "given", "hotel",
            "drove", "fifty", "glass", "house", "dying", "fight", "globe", "human",
            "eager", "final", "going", "ideal", "early", "first", "grace", "image",
            "earth", "fixed", "grade", "index", "eight", "flash", "grand", "inner",
            "elite", "fleet", "grant", "input", "empty", "floor", "grass", "issue",
            "enemy", "fluid", "great", "irony", "enjoy", "focus", "green", "juice",
            "enter", "force", "gross", "joint", "judge", "metal", "media", "newly",
            "known", "local", "might", "noise", "label", "logic", "minor", "north",
            "large", "loose", "minus", "noted", "laser", "lower", "mixed", "novel",
            "later", "lucky", "model", "nurse", "laugh", "lunch", "money", "occur",
            "layer", "lying", "month", "ocean", "learn", "magic", "moral", "offer",
            "lease", "major", "motor", "often", "least", "maker", "mount", "order",
            "leave", "march", "mouse", "other", "legal", "music", "mouth", "ought",
            "level", "match", "movie", "paint", "light", "mayor", "needs", "paper",
            "limit", "meant", "never", "party", "peace", "power", "radio", "round",
            "panel", "press", "raise", "route", "phase", "price", "range", "royal",
            "phone", "pride", "rapid", "rural", "photo", "prime", "ratio", "scale",
            "piece", "print", "reach", "scene", "pilot", "prior", "ready", "scope",
            "pitch", "prize", "refer", "score", "place", "proof", "right", "sense",
            "plain", "proud", "rival", "serve", "plane", "prove", "river", "seven",
            "plant", "queen", "quick", "shall", "plate", "sixth", "stand", "shape",
            "point", "quiet", "roman", "share", "pound", "quite", "rough", "sharp",
            "sheet", "spare", "style", "times", "shelf", "speak", "sugar", "tired",
            "shell", "speed", "suite", "title", "shift", "spend", "super", "today",
            "shirt", "spent", "sweet", "topic", "shock", "split", "table", "total",
            "shoot", "spoke", "taken", "touch", "short", "sport", "taste", "tough",
            "shown", "staff", "taxes", "tower", "sight", "stage", "teach", "track",
            "since", "stake", "teeth", "trade", "sixty", "start", "texas", "treat",
            "sized", "state", "thank", "trend", "skill", "steam", "theft", "trial",
            "sleep", "steel", "their", "tried", "slide", "stick", "theme", "tries",
            "small", "still", "there", "truck", "smart", "stock", "these", "truly",
            "smile", "stone", "thick", "trust", "smith", "stood", "thing", "truth",
            "smoke", "store", "think", "twice", "solid", "storm", "third", "under",
            "solve", "story", "those", "undue", "sorry", "strip", "three", "union",
            "sound", "stuck", "threw", "unity", "south", "study", "throw", "until",
            "space", "stuff", "tight", "upper", "upset", "whole", "waste", "wound",
            "urban", "whose", "watch", "write", "usage", "woman", "water", "wrong",
            "usual", "train", "wheel", "wrote", "valid", "world", "where", "yield",
            "value", "worry", "which", "young", "video", "worse", "while", "youth",
            "virus", "worst", "white", "worth", "visit", "would", "vital", "voice"
    };

    private static String getRandomWord(){
        Random random = new Random();
        return Word_List_5[random.nextInt(Word_List_5.length)];
    }

    public static final int Max_Atempts = 6;

    private static boolean provideFeedback(String guess, String wordToGuess) {
        char[] feedback = new char[5];
        Arrays.fill(feedback, '-');

        boolean[] used = new boolean[5];

        for (int i = 0; i < 5; i++) {
            if (guess.charAt(i) == wordToGuess.charAt(i)) {
                feedback[i] = 'G';
                used[i] = true;
            }
        }

        for (int i = 0; i < 5; i++) {
            if (feedback[i] != 'G') {
                for (int j = 0; j < 5; j++) {
                    if (guess.charAt(i) == wordToGuess.charAt(j) && !used[j]) {
                        feedback[i] = 'Y';
                        used[j] = true;
                        break;
                    }
                }
                if (feedback[i] != 'Y') {
                    feedback[i] = 'X';
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            if (feedback[i] == 'G') {
                System.out.print(guess.charAt(i) + " (Green) ");
            } else if (feedback[i] == 'Y') {
                System.out.print(guess.charAt(i) + " (Yellow) ");
            } else {
                System.out.print(guess.charAt(i) + " (Gray) ");
            }
        }
        System.out.println();

        return guess.equals(wordToGuess);
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            String wordToGuess = getRandomWord();
            System.out.println("Welcome to the Wordle Game!");
            //HERE
            System.out.print(wordToGuess);
            int attempts = 0;
            boolean isSolved = false;

            while(attempts < Max_Atempts && !isSolved) {
                System.out.println("What's your guess? Attempt " + (attempts + 1) + " of " + Max_Atempts);
                String guess = scanner.nextLine().toLowerCase();

                if (guess.length() != 5) {
                    System.out.println("Invalid guess! Please enter a 5-letter word.");
                    continue;
                }

                attempts++;
                isSolved = provideFeedback(guess, wordToGuess);

                if (isSolved) {
                    System.out.println("Congratulations! You've guessed the word.");
                } else if (attempts == Max_Atempts) {
                    System.out.println("Sorry, you've run out of attempts. The correct word was: " + wordToGuess);
                }
            }

            System.out.println("Do you want to play again? (yes/no)");
            String response = scanner.nextLine().toLowerCase();
            playAgain = response.equals("yes");
        }
        System.out.println("Thank you for playing!");
    }
}