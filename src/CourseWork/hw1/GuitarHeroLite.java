package CourseWork.hw1;
import CourseWork.hw1.synthesizer.GuitarString;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;
import java.util.HashMap;

/** A client that uses the synthesizer package to replicate a plucked guitar string sound */
public class GuitarHeroLite {
    /*
    The ith character of the string keyboard corresponds to a frequency of 440 × 2^((i - 24) / 12), so that the character 'q' is 110Hz, 'i' is 220Hz, 'v' is 440Hz, and ' ' is 880Hz. Don't even think of including 37 individual GuitarString variables or a 37-way if statement! Instead, create an array of 37 GuitarString objects and use keyboard.indexOf(key) to figure out which key was typed. Make sure your program does not crash if a key is pressed that does not correspond to one of your 37 notes.
     */
    private static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private static double[] frequencies;

    private static double[] getFrequencies() {
        double[] freqs = new double[keyboard.length()];
        for (int i = 0; i < keyboard.length(); i++) {
            freqs[i] = 440. *  Math.pow(2, (i - 24.0) / 12.0);
        }
        return freqs;
    }

    private static HashMap<Character, GuitarString> getGuitarStrings() {
        HashMap<Character, GuitarString> map = new HashMap<>();
        int i = 0;
        for (Character key : keyboard.toCharArray()) {
            map.put(key, new GuitarString(frequencies[i++]));
        }
        return map;
    }

    private static final double CONCERT_A = 440.0;
    private static final double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);

    public static void main(String[] args) {
        frequencies = getFrequencies();
        HashMap<Character, GuitarString> strings = getGuitarStrings();

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (keyboard.indexOf(key) >= 0) strings.get(key).pluck();
            }

        /* compute the superposition of samples */
            double sample = 0.0;
            for (GuitarString string : strings.values()) {
                sample += string.sample();
            }

        /* play the sample on standard audio */
            StdAudio.play(sample);

        /* advance the simulation of each guitar string by one step */
            for (GuitarString string : strings.values()) {
                string.tic();
            }
        }
    }
}

