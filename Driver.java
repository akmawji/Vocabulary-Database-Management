/** -----------------------------------------------------
* Assignment 3
* COMP 249
* Written by: Karim Mawji (40281154)
* April 15th 2024
* -------------------------------------------------------
*/ 
package a3;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * The Driver class for the vocabulary management system.
 * It provides a text-based user interface to interact with the vocabulary list.
 */
public class Driver {
	/**
     * The main method that drives the program, providing a menu-driven interface to the user.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Dlinklist vocabList = loadFile("A3_input_file.txt");
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            menu();
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    browseATopic(scanner, vocabList);
                    break;
                case 2:
                    insertTopic(scanner, vocabList, true);
                    break;
                case 3:
                    insertTopic(scanner, vocabList, false);
                    break;
                case 4:
                    removeTopic(scanner, vocabList);
                    break;
                case 5:
                    modifyTopic(scanner, vocabList);
                    break;
                case 6:
                    searchTopicsForWord(scanner, vocabList);
                    break;
                case 7:
                    vocabList = loadFile(promptForFilename(scanner));
                    break;
                case 8:
                    showWordsStartingWith(scanner, vocabList);
                    break;
                case 9:
                    saveToFile(vocabList, scanner);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        } while (choice != 0);

        scanner.close();
    }
    /**
     * Displays the main menu to the user.
     */
    private static void menu() {
        System.out.println("\n-----------------------------\n"
                + "  Vocabulary Control Center\n"
                + "-----------------------------\n"
                + "1  browse a topic\n"
                + "2  insert a new topic before another one\n"
                + "3  insert a new topic after another one\n"
                + "4  remove a topic\n"
                + "5  modify a topic\n"
                + "6  search topics for a word\n"
                + "7  load from a file\n"
                + "8  show all words starting with a given letter\n"
                + "9  save to file\n"
                + "0  exit\n"
                + "-----------------------------\n"
                + "Enter Your Choice: ");
    }
    /**
     * Allows the user to browse a specific topic and view its words.
     * @param scanner The scanner for user input.
     * @param vocabList The linked list of vocabularies.
     */
    private static void browseATopic(Scanner scanner, Dlinklist vocabList) {
    	System.out.println("------------------------------\n"
    			+ "Pick a topic\n"
    			+ "------------------------------");
    	vocabList.display();
    	System.out.println("------------------------------\n"
    			+ "Enter Your Choice:");
    	int choice = scanner.nextInt();
    	Vocab browse = vocabList.getTopicAt(choice);
    	browse.getWords().display();;
    	 }
    /**
     * Prompts the user to enter a filename.
     * @param scanner The scanner for user input.
     * @return The filename entered by the user.
     */
    private static String promptForFilename(Scanner scanner) {
        System.out.print("Enter file name: ");
        return scanner.nextLine();
    }
    /**
     * Loads the vocabulary list from a specified file.
     * @param filename The name of the file to load from.
     * @return The loaded vocabulary list as a Dlinklist.
     */
    private static Dlinklist loadFile(String filename) {
        Dlinklist vocabList = new Dlinklist();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            Vocab currentVocab = null;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("#")) {
                    if (currentVocab != null) {
                        vocabList.addAtTail(currentVocab);
                    }
                    currentVocab = new Vocab(line.substring(1));
                } else if (!line.isEmpty() && currentVocab != null) {
                    currentVocab.getWords().addAtEnd(line);
                }
            }

            if (currentVocab != null) {
                vocabList.addAtTail(currentVocab);
            }
        } catch (IOException e) {
            System.err.println("Error loading file: " + e.getMessage());
        }

        return vocabList;
    }
    /**
     * Inserts a new topic into the vocabulary list, either before or after a specified topic.
     * @param scanner The scanner for user input.
     * @param vocabList The linked list of vocabularies.
     * @param before Boolean flag to determine insertion point (before or after).
     */
    private static void insertTopic(Scanner scanner, Dlinklist vocabList, boolean before) {
        System.out.println("Enter the reference topic name:");
        String referenceTopic = scanner.nextLine();
        System.out.println("Enter the new topic name:");
        String newTopicName = scanner.nextLine();
        Vocab newVocab = new Vocab(newTopicName);
        System.out.println("Enter words for the topic '" + newTopicName + "' - to quit press Enter:");
        String word;
        while (true) {
            word = scanner.nextLine();
            if (word.isEmpty()) {
                break;
            }
            newVocab.getWords().addAtEnd(word);
        }

        if (before) {
            vocabList.addBefore(referenceTopic, newVocab);
        } else {
            vocabList.addAfter(referenceTopic, newVocab);
        }
    }
    /**
     * Removes a specified topic from the vocabulary list.
     * @param scanner The scanner for user input.
     * @param vocabList The linked list of vocabularies.
     */
    private static void removeTopic(Scanner scanner, Dlinklist vocabList) {
        System.out.println("Enter the name of the topic you would like to remove:");
        String removeTopic = scanner.nextLine();
        vocabList.remove(removeTopic);
    }
    /**
     * Modifies the content of a specified topic in the vocabulary list.
     * @param scanner The scanner for user input.
     * @param vocabList The linked list of vocabularies.
     */
    private static void modifyTopic(Scanner scanner, Dlinklist vocabList) {
    	System.out.println("------------------------------\n"
    			+ "Pick a topic\n"
    			+ "------------------------------");
    	vocabList.display();
    	System.out.println("------------------------------\n"
    			+ "Enter Your Choice:");
    	int choice = scanner.nextInt();
    	System.out.println("a add a word\n"
    			+ "r remove a word\n"
    			+ "c change a word\n"
    			+ "0 Exit");
    	String choice2 = scanner.next();
    	switch (choice2) {
    	case "a":
    		System.out.println("Type a word and press Enter, or press Enter to end input");
    		String choice3 = scanner.next();
    		vocabList.getTopicAt(choice).getWords().addAtHead(choice3);
    		break;
    	case "r":
    		System.out.println("Type a word and press Enter, or press Enter to end input");
    		String choice4 = scanner.next();
    		vocabList.getTopicAt(choice).getWords().remove(choice4);
    		break;
    	case "c":
    		System.out.println("Type the old word and press Enter, or press Enter to end input");
    		System.out.println("Type the new word and press Enter, or press Enter to end input");
    		String choice5 = scanner.next();
    		String choice6 = scanner.next();
    		vocabList.getTopicAt(choice).getWords().replace(choice5,choice6);
    		break;
    	}
    	
    }
    /**
     * Searches for a word across all topics in the vocabulary list.
     * @param scanner The scanner for user input.
     * @param vocabList The linked list of vocabularies.
     */
    private static void searchTopicsForWord(Scanner scanner, Dlinklist vocabList) {
        System.out.println("Enter a word:");
        String choice = scanner.next();
        boolean found = vocabList.search(choice);
        if(found) {
        	System.out.println("word is in list");
        }
        else {
        	System.out.println("word is not in list");
        }
    }
    /**
     * Shows all words starting with a specific letter across all topics in the vocabulary list.
     * @param scanner The scanner for user input.
     * @param vocabList The linked list of vocabularies.
     */
    private static void showWordsStartingWith(Scanner scanner, Dlinklist vocabList) {
    	System.out.println("Enter a letter:");
        String choice = scanner.next();
        ArrayList<String> wordsLetter = vocabList.searchLetter(choice.charAt(0));
        wordsLetter.sort(null);
        System.out.println(wordsLetter.toString());
    }
    /**
     * Saves the current state of the vocabulary list to a file.
     * @param vocabList The linked list of vocabularies to be saved.
     * @param scanner The scanner for user input.
     */
    private static void saveToFile(Dlinklist vocabList, Scanner scanner) {
       System.out.println("Enter file name:"); 
       String fileName = scanner.next();
       vocabList.saveToFile(fileName);
    }

}
