import java.util.Random;
import java.util.Scanner;

public class BinarySearchTreeDemo {
    public static void main(String[] args) {
        BinarySearchTree<Integer> sTree = new BinarySearchTree<>();
        Random random = new Random();
        Scanner input = new Scanner(System.in);
        //create an empty binary search tree of Integer object
        //BLOCK 1:
        //generate 10 random integers in the range 1 to 1000
        //these are your keys
        //insert these into the binary search tree
        //do an inorder traversal and display the keys
        //you should see that the keys are sorted
        //display the minimum integer using the findMin method
        //display the maximum integer using the findMax method
        //prompt the user to search for a key
        //use the recursive search method to search
        //and display if the key was found or not
        for (int i = 0; i < 10; i++) {
            int newKey = random.nextInt(1000) + 1;
            sTree.insert(newKey);
        }
        BinarySearchTree.Inorder(sTree.getTree());
        System.out.println();
        System.out.println("Minimum interger: " + sTree.findMin(sTree.getTree().root()));
        System.out.println("Maximum Interger: " + sTree.findMax(sTree.getTree().root()));
        System.out.println("Which key do you want to find? ");
        int wannaKey = input.nextInt();
        System.out.println("Found this key? ");
        if (sTree.search(wannaKey) == null) {
            System.out.println("Not Found!");
        } else {
            System.out.println("Found!");
        }
        //BLOCK 2:
        //generate 100 random integers (keys) in the range 1 to 1000
        //create a binary search tree and insert the keys
        //find the height of this tree and determine if this tree
        //is height balanced.
        //Repeat BLOCK 2 (tree with 100 nodes)
        //50 times using a while loop
        //Write the answers
        int count = 0;
        while (count < 50) {
            BinarySearchTree<Integer> twoTree = new BinarySearchTree<>();
            for (int i = 0; i < 100; i++) {
                int newKey = random.nextInt(1000) + 1;
                twoTree.insert(newKey);
            }
            System.out.println("Height " + twoTree.height(twoTree.getTree()));
            System.out.println("Is it balanced? " + twoTree.isHeightBalance(twoTree.getTree()));
            count++;
        }
    }
}
