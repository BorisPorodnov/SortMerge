package com.company;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    public int[] readInt(List<String> inputFiles) {

        boolean numeric;
        List<Integer> list = new ArrayList<>();

        for (String inputFile : inputFiles) {
            List<String> lines = null;
            try {
                lines = Files.readAllLines(Paths.get(inputFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < lines.size(); i++) {
                String str = lines.get(i);
                numeric = tryParse(str);
                if (numeric) {
                    list.add(Integer.parseInt(str));
                } else {
                    System.out.println("Ошибка смотри после строки " + i + " " + "Файл содержит элемент: " + str);
                    System.out.println("После исправления запустите приложение повторно");
                }
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public String[] readString(List<String> inputFiles) {

        List<String> words = new ArrayList<>();
        try {
            for (String inputFile : inputFiles) {
                words.addAll(Files.readAllLines(Paths.get(inputFile)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words.toArray(new String[0]);
    }

    public void write(int[] sortMas) {

        try {
            PrintWriter out = new PrintWriter("outFile.txt");

            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < sortMas.length; i++) {
                builder.append(sortMas[i]);
                builder.append("\n");
            }
            out.print(builder.toString());
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void write(String[] sortArray) {

        try {
            PrintWriter out = new PrintWriter("outFile.txt");

            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < sortArray.length; i++) {
                builder.append(sortArray[i]);
                builder.append("\n");
            }
            out.print(builder.toString());
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean tryParse(String element) {

        if (element == null || element.length() == 0) {
            return false;
        }
        try {
            Integer.parseInt(element);
            return true;

        } catch (NumberFormatException e) {
            return false;
        }
    }

}
