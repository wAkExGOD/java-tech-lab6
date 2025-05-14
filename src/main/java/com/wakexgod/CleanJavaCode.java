package com.wakexgod;

import java.io.*;
import java.util.Scanner;

public class CleanJavaCode {
    public static void execute() {
        Scanner scanConsole = new Scanner(System.in);
        PrintWriter writer = null;

        try {
            System.out.println("Введите абсолютный путь к файлу .java:");
            String inputFilePath = scanConsole.nextLine();

            // Создание директории output, если она не существует
            File outputDir = new File("output");
            if (!outputDir.exists()) {
                outputDir.mkdir();
            }

            // Путь к выходному файлу
            String outputFilePath = "output/cleaned_code.java";
            writer = new PrintWriter(new BufferedWriter(new FileWriter(outputFilePath)));

            // Считывание файла и удаление лишних пробелов
            try (Scanner scanFile = new Scanner(new FileReader(inputFilePath))) {
                while (scanFile.hasNextLine()) {
                    String line = scanFile.nextLine();
                    String cleanedLine = line.replaceAll("[\\s]+", " ").trim(); // Удаление лишних пробелов
                    writer.println(cleanedLine);
                }
            }

            System.out.println("Чистый код сохранен в: " + outputFilePath);

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден!");
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
}