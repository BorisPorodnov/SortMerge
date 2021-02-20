package com.company;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        List<String> files = Arrays.stream(args).filter(it -> it.charAt(0) != '-')
            .collect(Collectors.toList());

        List<String> inputFiles = new ArrayList<>();

        String outFile = files.get(0);
        for (int i = 1; i < files.size(); i++) {
            inputFiles.add(files.get(i));
        }

        List<String> flags = Arrays.stream(args).filter(it -> it.charAt(0) == '-')
            .collect(Collectors.toList());

        String flagInt = flags.get(1);

        switch (flagInt) {
            case "-i":
                int[] text = readFile(inputFiles);
                int[] resultText = sort(text);

                try {
                    assert resultText != null;
                    write(resultText);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "-s":

            default:
                System.out.println(" this name was not found ");
                break;
        }

    }

    private static int[] readFile(List<String> inputFiles) {

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

    private static boolean tryParse(String element) {

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

    private static void write(int[] sortMas) throws FileNotFoundException {

        PrintWriter out = new PrintWriter("outFile.txt");

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < sortMas.length; i++) {
            builder.append(sortMas[i]);
            builder.append("\n");
        }
        out.print(builder.toString());
        out.close();
    }

    private static int[] sort(int[] mass) {

        // проверяем не нулевой ли он?
        if (mass == null) {
            System.out.print("Массив пуст");
            return null;
        }
        // проверяем не 1 ли элемент в массиве?
        if (mass.length < 2) {
            return mass;
        }
        int[] leftArray = new int[mass.length / 2];
        int[] rightArray = new int[mass.length - mass.length / 2];
        System.arraycopy
            (mass, 0, leftArray, 0, mass.length / 2);
        System.arraycopy
            (mass, mass.length / 2, rightArray, 0, mass.length - (mass.length / 2));

        return sortMerge(sort(leftArray), sort(rightArray));
    }

    private static int[] sortMerge(int[] leftArray, int[] rightArray) {

        int[] res = new int[leftArray.length + rightArray.length];

        int n = leftArray.length;
        int m = rightArray.length;

        int i = 0, j = 0, k = 0;

        while (i < n && j < m) {
            if (leftArray[i] <= rightArray[j]) {
                res[k] = leftArray[i];
                i++;
            } else {
                res[k] = rightArray[j];
                j++;
            }
            k++;
        }
        while (i < n) {
            res[k] = leftArray[i];
            i++;
            k++;
        }
        while (j < m) {
            res[k] = rightArray[j];
            j++;
            k++;
        }
        return res;
    }
}