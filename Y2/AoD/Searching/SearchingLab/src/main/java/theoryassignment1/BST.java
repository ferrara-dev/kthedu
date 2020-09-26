package theoryassignment1;

import Assignment2.BinarySearchTree;

public class BST {
    private static final char [] characters = {'W','O','E','C','A','L','H'};

    public static void main(String...args){
        BinarySearchTree<Character,Integer> bst = new BinarySearchTree<Character, Integer>();
        for(Character character : characters){
            bst.put(character,1);
        }
        print(bst);
    }

    private static void print(BinarySearchTree<Character,Integer> bst){
        bst.print();
        System.out.print("Printing post order ---> ");
        bst.printPostOrder();
        System.out.println();
        System.out.print("Printing pre order ---> ");
        bst.printPreOrder();
        System.out.println();
        System.out.print("Printing in order ---> ");
        bst.printInOrder();
    }
}
