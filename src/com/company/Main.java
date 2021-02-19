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

    public static void main(String[] args){

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

                int[] readText = new int[0];
                try {
                    readText = read(inputFiles);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                int[] sortMasRes = sort(readText);
                try {
                    write(sortMasRes);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println(" this name was not found ");
                break;
        }
    }

    private static int[] read(List<String> inputFiles) throws IOException {

        List<Integer> list = new ArrayList<>();

        for (String inputFile : inputFiles) {
            List<String> lines = Files.readAllLines(Paths.get(inputFile));

            for (String elem : lines) {
                list.add(Integer.valueOf(elem));
            }
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return sort(result);
    }

    public static void write(int[] sortMas) throws FileNotFoundException {
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

        int[] leftMass = new int[mass.length / 2];
        int[] rightMass = new int[mass.length - mass.length / 2];
        System.arraycopy
            (mass, 0, leftMass, 0, mass.length / 2);
        System.arraycopy
            (mass, mass.length / 2, rightMass, 0, mass.length - (mass.length / 2));

        return sortMerge(sort(leftMass), sort(rightMass));
    }

    private static int[] sortMerge(int[] leftMass, int[] rightMass) {

        int[] res = new int[leftMass.length + rightMass.length];

        int n = leftMass.length;
        int m = rightMass.length;

        int i = 0, j = 0, k = 0;

        while (i < n && j < m) {
            if (leftMass[i] <= rightMass[j]) {
                res[k] = leftMass[i];
                i++;
            } else {
                res[k] = rightMass[j];
                j++;
            }
            k++;
        }
        while (i < n) {
            res[k] = leftMass[i];
            i++;
            k++;
        }
        while (j < m) {
            res[k] = rightMass[j];
            j++;
            k++;
        }
        return res;
    }

}