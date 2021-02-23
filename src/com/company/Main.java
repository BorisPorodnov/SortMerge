package com.company;

public class Main {

    public static void main(String[] args) {

        FileManager fileManager = new FileManager();
        MergeSorting mergeSorting = new MergeSorting();
        ParameterResolver parameterResolver = new ParameterResolver(args);

        switch (parameterResolver.getDataType()) {
            case "-i" -> {
                int[] text = fileManager.readInt(parameterResolver.getInputFiles());
                int[] resultText = mergeSorting.sort(text);
                fileManager.write(resultText);
            }
            case "-s" -> {
                String[] resText = fileManager.readString(parameterResolver.getInputFiles());
                String[] sorted = mergeSorting.sort(resText);
                fileManager.write(sorted);
            }
        }
    }

}