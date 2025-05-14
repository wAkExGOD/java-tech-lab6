package com.wakexgod;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FrequencyAnalyzer {
    public static void execute() {
        Scanner scanConsole = new Scanner(System.in);
        PrintWriter writer = null;

        try {
            Console.log("Введите абсолютный путь к файлу:");
            String inputFilePath = scanConsole.nextLine();
            writer = new PrintWriter(new BufferedWriter(new FileWriter("output.txt")));

            Scanner scanFile = new Scanner(new FileReader(inputFilePath));
            StringBuilder text = new StringBuilder();

            while (scanFile.hasNextLine()) {
                text.append(scanFile.nextLine()).append("\n");
            }

            // Подсчет частоты букв и слов
            Map<Character, Integer> letterFrequency = countLetterFrequency(text.toString());
            Map<String, Integer> wordFrequency = countWordFrequency(text.toString());

            // Запись результатов в файл
            writer.println("Частота букв:");
            for (Map.Entry<Character, Integer> entry : letterFrequency.entrySet()) {
                writer.println(entry.getKey() + ": " + entry.getValue());
            }

            writer.println("\nЧастота слов:");
            for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
                writer.println(entry.getKey() + ": " + entry.getValue());
            }

        } catch (FileNotFoundException e) {
            Console.log("Файл не найден!");
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            Console.log("Файл пуст!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
            scanConsole.close();
        }
    }

    private static Map<Character, Integer> countLetterFrequency(String text) {
        Map<Character, Integer> frequency = new HashMap<>();
        for (char c : text.toLowerCase().toCharArray()) {
            if (Character.isLetter(c)) {
                frequency.put(c, frequency.getOrDefault(c, 0) + 1);
            }
        }
        return frequency;
    }

    private static Map<String, Integer> countWordFrequency(String text) {
        Map<String, Integer> frequency = new HashMap<>();
        Pattern pattern = Pattern.compile("\\w+", Pattern.UNICODE_CHARACTER_CLASS | Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String word = matcher.group().toLowerCase();
            frequency.put(word, frequency.getOrDefault(word, 0) + 1);
        }
        return frequency;
    }
}