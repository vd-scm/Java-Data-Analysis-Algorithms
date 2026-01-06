//
// Project: Map Performance
// Author: Vince David M. Muego
//
// main clas MapPerformance that anaylyze the performance
// of each map in Java (HashMap, LinkedHashMap, TreeMap)

import java.io.*;
import java.util.*;

public class MapPerformance {
    public static void main(String[] args) throws IOException {
        String file = "/Users/david/Desktop/Frankenstein.txt";
        System.out.println("Filename: " + file);

        // Read file
        String[] words = importingFile(file);

        // initialize each map type
        Map<String, Integer> hashMap = new HashMap<>(); // unordered
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>(); // insertion order
        Map<String, Integer> treeMap = new TreeMap<>(); // sorted order

        // Measure performance of each map type
        measuringPerformance(hashMap, "HashMap", words);
        measuringPerformance(linkedHashMap, "LinkedHashMap", words);
        measuringPerformance(treeMap, "TreeMap", words);

        System.out.println("Total words: " + words.length);
        System.out.println("Unique words: " + new HashSet<>(Arrays.asList(words)).size());

        // outputs 15 key-value pairs for each map type
        buildingMaps(hashMap, "HashMap");
        buildingMaps(linkedHashMap, "LinkedHashMap");
        buildingMaps(treeMap, "TreeMap");
    }

    private static String[] importingFile(String file) throws IOException {
        StringBuilder content = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        while ((line = br.readLine()) != null) {
            line = line.toLowerCase().replaceAll("[^a-z0-9 ]", ""); // remove non alphabet/numbers char except spaces
            content.append(line).append(" "); // add line with space
        }

        br.close();
        return content.toString().trim().split("\\s+"); // split content to words with spaces
    }

    // time it takes to build a word frequency map (millisec)
    private static void measuringPerformance(Map<String, Integer> map, String mapType, String[] words) {
        long startTime = System.currentTimeMillis(); // record start time

        for (int i = 0; i < words.length; i++) {
            String word = words[i];

            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1); // word already exists -> increment count
            }
            else {
                map.put(word, 1); // new word -> start count at 1
            }
        }

        long endTime = System.currentTimeMillis(); // record end time
        System.out.printf("Build %-15s %d millisecs\n", mapType + ":", (endTime - startTime));
    }

    // print 15 key-value pairs of given map
    private static void buildingMaps(Map<String, Integer> map, String mapType) {
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());

        System.out.printf("%-18s", mapType + ":");
        for (int i = 0; i < Math.min(entries.size(), 15); i++) {
            Map.Entry<String, Integer> entry = entries.get(i);
            System.out.print(entry.getKey() + "=" + entry.getValue() + " ");
        }
        System.out.println();
    }
}

/*
output:
Filename: /Users/david/Desktop/Frankenstein.txt
Build HashMap:        11 millisecs
Build LinkedHashMap:  15 millisecs
Build TreeMap:        24 millisecs
Total words: 75055
Unique words: 7183
HashMap:          frowning=1 half=17 strasburgh=4 shooting=1 abrupt=1 idol=1 hall=1 halo=1 childs=1 pretend=3 guards=1 wreck=4 drunk=2 imperfect=2 gloomy=10
LinkedHashMap:    start=1 of=2641 the=4191 project=2 gutenberg=2 ebook=2 84=2 frankenstein=25 or=175 modern=11 prometheus=1 by=461 mary=1 wollstonecraft=1 godwin=1
TreeMap:          1=4 10=2 11=2 11th=2 12=2 12th=2 13=2 13th=1 14=2 15=2 16=2 17=12 18=2 18th=2 19=2

Process finished with exit code 0
*/