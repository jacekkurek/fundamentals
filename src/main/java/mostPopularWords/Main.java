package mostPopularWords;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    private static void saveHeaderWords(Connection connection, Path path) {
        List<String> wordList = new LinkedList<>();
        try {
            Document document = connection.get();
            Elements headers = document.select("span.title");
            for (Element header : headers) {
                String[] words = header.text().split(" |,|\\.|!|\"|:|\\?|-");
                for (String word : words) {
                    wordList.add(word.toLowerCase());
                }
            }
            Files.write(path, wordList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void filterWords(Path source, Path target, int minLength) {
        List<String> exclude = Arrays.asList(new String[]{"nie", "dla", "bardzo", "jest", "po"});
        List<String> words = new LinkedList<>();
        List<String> filtered = new LinkedList<>();
        try {
            words = Files.readAllLines(source);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String word : words) {
            boolean add = false;
            if (word.length() >= minLength) {
                for (String s : exclude) {
                    if (!s.equals(word)) {
                        add = true;
                    }
                }
            }
            if (add) {
                filtered.add(word);
            }

            try {
                Files.write(target, filtered);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private static void findFrequentWords(Path path) {
        List<String> words = null;
        Map<String, Integer> frequencyMap = new HashMap<>();
        List<String> rankingList = new LinkedList<>();
        try {
            words = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String word : words) {
            if (frequencyMap.containsKey(word)) {
                frequencyMap.put(word, frequencyMap.get(word) + 1);
            } else {
                frequencyMap.put(word, 1);
            }
        }
        Set<Map.Entry<String, Integer>> resultSet = frequencyMap.entrySet();
        for (Map.Entry<String, Integer> entry : resultSet) {
            rankingList.add(entry.getValue() + " : " + entry.getKey());
        }
        Collections.sort(rankingList, Collections.reverseOrder());
        for (String s : rankingList) {
            System.out.println(s);
        }

    }


    public static void main(String[] args) {


        Connection connection = Jsoup.connect("http://www.onet.pl/");
        Path pathWords = Paths.get("popular_words.txt");
        Path pathFiltered = Paths.get("popular_words_filtered.txt");
        saveHeaderWords(connection, pathWords);
        filterWords(pathWords, pathFiltered, 5);
        findFrequentWords(pathFiltered);


    }


}

