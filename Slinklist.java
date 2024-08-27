/** -----------------------------------------------------
* Assignment 3
* COMP 249
* Written by: Karim Mawji (40281154)
* April 15th 2024
* -------------------------------------------------------
*/ 
package a3;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class Slinklist implements a singly linked list specifically designed to manage strings.
 */
public class Slinklist {
    /** Reference to the first node of the list. */
    Node head;

    /** The number of elements in the list. */
    private int size;

    /**
     * Constructs an empty Slinklist.
     */
    public Slinklist() {
        head = null;
        size = 0;
    }

    /**
     * Adds a new element at the beginning of the list.
     *
     * @param data The data to be added.
     */
    public void addAtHead(String data) {
        Node newNode = new Node(data, head);
        head = newNode;
        size++;
    }

    /**
     * Adds a new element at the end of the list.
     *
     * @param data The data to be added.
     */
    public void addAtEnd(String data) {
        if (head == null) {
            addAtHead(data);
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Node(data);
            size++;
        }
    }

    /**
     * Adds a new element after a specific existing element in the list.
     *
     * @param reference The data after which the new element is to be added.
     * @param data The new data to be added.
     */
    public void addAfter(String reference, String data) {
        Node current = head;
        while (current != null && !current.data.equals(reference)) {
            current = current.next;
        }
        if (current != null) {
            Node newNode = new Node(data, current.next);
            current.next = newNode;
            size++;
        }
    }

    /**
     * Removes and returns the first element of the list.
     *
     * @return The data of the removed head element, or null if the list is empty.
     */
    public String removeHead() {
        if (head == null) {
            return null;
        }
        Node temp = head;
        head = head.next;
        size--;
        return temp.data;
    }

    /**
     * Removes a specific element from the list.
     *
     * @param word The data of the element to be removed.
     * @return true if the element was found and removed, false otherwise.
     */
    public boolean remove(String word) {
        if (head == null) {
            return false; // List is empty
        }

        if (head.data.equals(word)) {
            head = head.next;
            size--;
            return true;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.data.equals(word)) {
                current.next = current.next.next; // Remove the node
                size--;
                return true;
            }
            current = current.next;
        }

        return false; // Word not found
    }

    /**
     * Removes and returns the last element of the list.
     *
     * @return The data of the last element, or null if the list is empty.
     */
    public String removeEnd() {
        if (head == null) {
            return null;
        }
        if (size == 1) {
            return removeHead();
        }
        Node current = head;
        while (current.next.next != null) {
            current = current.next;
        }
        String data = current.next.data;
        current.next = null;
        size--;
        return data;
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return The size of the list.
     */
    public int getSize() {
        return size;
    }

    /**
     * Replaces a specific word in the list with a new word.
     *
     * @param oldWord The word to be replaced.
     * @param newWord The new word to replace the old one.
     * @return true if the replacement was successful, false if the old word was not found.
     */
    public boolean replace(String oldWord, String newWord) {
        Node current = head;
        while (current != null) {
            if (current.data.equals(oldWord)) {
                current.data = newWord; // Replace the word
                return true; // Word replaced successfully
            }
            current = current.next;
        }
        return false; // Word not found
    }

    /**
     * Searches for a specific word in the list.
     *
     * @param word The word to search for.
     * @return true if the word is found, false otherwise.
     */
    public boolean search(String word) {
        Node pos = head;
        while (pos != null && !pos.data.equals(word)) {
            pos = pos.next;
        }
        return pos != null;
    }

    /**
     * Searches for words starting with a specified letter and adds them to a given list.
     *
     * @param c The starting letter.
     * @param a The list where matching words are added.
     */
    public void searchLetter(char c, ArrayList<String> a) {
        Node pos = head;
        while (pos != null) {
            if (pos.data.charAt(0) == c) {
                a.add(pos.data);
            }
            pos = pos.next;
        }
    }

    /**
     * Writes each word in the list to a BufferedWriter.
     *
     * @param writer The BufferedWriter to write to.
     * @throws IOException if an I/O error occurs.
     */
    public void wordList(BufferedWriter writer) throws IOException {
        Node pos = head;
        while (pos != null) {
            writer.write(pos.data + "\n");
            pos = pos.next;
        }
    }

    /**
     * Displays all elements in the list with their corresponding indices.
     */
    public void display() {
        Node pos = head;
        int count = 1;
        while (pos != null) {
            System.out.println(count + ": " + pos.data);
            pos = pos.next;
            count++;
        }
    }
}

/**
 * Node class for use within the Slinklist class.
 * Represents a node in a singly linked list.
 */
class Node {
    /** The data held by the node. */
    String data;

    /** Reference to the next node in the list. */
    Node next;

    /**
     * Constructs a node with specified data and no next node.
     *
     * @param data The data for this node.
     */
    Node(String data) {
        this(data, null);
    }

    /**
     * Constructs a node with specified data and a reference to the next node.
     *
     * @param data The data for this node.
     * @param next The next node in the list.
     */
    Node(String data, Node next) {
        this.data = data;
        this.next = next;
    }
}

