package Assignment3;

import Assignment2.BinarySearchTree;
import Assignment4.hash.LinkedHashTable;
import util.List;

import java.util.Scanner;

public class Assignment3 {
    private static BinarySearchTree<Integer, List<String>> hashWordMap = new BinarySearchTree<Integer, List<String>>();

    public static void main(String...args){
        visualizeHashDistribution();
    }

    public static void visualizeHashDistribution(){
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            String words[] = line.split("\\s");
            for(String word : words){
                if(!hashWordMap.contains(word.hashCode())){
                    hashWordMap.put(word.hashCode(), List.listOf(word));
                    hashWordMap.get(word.hashCode());
                }
                else {
                    hashWordMap.put(word.hashCode(), List.listOf(word));
                    hashWordMap.get(word.hashCode());
                }
            }
        }
        for(Integer integer : hashWordMap.keys()){
            if(hashWordMap.get(integer).size() > 1){
                System.out.println(hashWordMap.get(integer));
            }
        }

    }
}
