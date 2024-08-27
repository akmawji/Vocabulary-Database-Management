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

/**
 * Class Dlinklist implements a doubly linked list designed to manage vocabulary entries.
 */
public class Dlinklist {
    /** Reference to the first node of the doubly linked list. */
    private Node head;

    /** Reference to the last node of the doubly linked list. */
    private Node tail;

    /** The number of nodes in the doubly linked list. */
    private int size;

    /**
     * Constructs an empty Dlinklist.
     */
    public Dlinklist() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Adds a new node with the specified data at the beginning of the list.
     *
     * @param data The Vocab object to be stored in the new node.
     */
    public void addAtHead(Vocab data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    /**
     * Adds a new node with the specified data at the end of the list.
     *
     * @param data The Vocab object to be stored in the new node.
     */
    public void addAtTail(Vocab data) {
        if (size == 0) {
            addAtHead(data);
        } else {
            Node newNode = new Node(data);
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
            size++;
        }
    }

    /**
     * Adds a new node with the specified data after the node with the given reference topic.
     *
     * @param referenceTopic The topic after which the new data should be added.
     * @param newData The new Vocab object to insert.
     */
    public void addAfter(String referenceTopic, Vocab newData) {
        Node current = head;
        while (current != null && !current.data.getTopic().equals(referenceTopic)) {
            current = current.next;
        }

        if (current != null) {
            Node newNode = new Node(newData, current.next, current);
            if (current.next != null) {
                current.next.prev = newNode;
            } else {
                tail = newNode;
            }
            current.next = newNode;
            size++;
        }
    }

    /**
     * Adds a new node with the specified data before the node with the given reference topic.
     *
     * @param referenceTopic The topic before which the new data should be added.
     * @param newData The new Vocab object to insert.
     */
    public void addBefore(String referenceTopic, Vocab newData) {
        Node current = head;
        while (current != null && !current.data.getTopic().equals(referenceTopic)) {
            current = current.next;
        }

        if (current == head) {
            addAtHead(newData);
        } else if (current != null) {
            Node newNode = new Node(newData, current, current.prev);
            current.prev.next = newNode;
            current.prev = newNode;
            size++;
        }
    }

    /**
     * Removes the node associated with the specified topic.
     *
     * @param topic The topic of the node to remove.
     */
    public void remove(String topic) {
        Node pos = head;
        while (pos != null && !pos.data.getTopic().equals(topic)) {
            pos = pos.next;
        }

        if (pos == null) {
            System.out.println("Topic not found: " + topic);
            return;
        }

        if (pos == head) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            }
        } else if (pos == tail) {
            tail = tail.prev;
            if (tail != null) {
                tail.next = null;
            }
        } else {
            pos.prev.next = pos.next;
            pos.next.prev = pos.prev;
        }

        size--;
    }

    /**
     * Searches for the specified word in all vocabularies in the list.
     *
     * @param word The word to search for.
     * @return true if the word is found in any of the vocabularies, false otherwise.
     */
    public boolean search(String word) {
        Node pos = head;
        boolean found = false;
        while (pos != null && !found) {
            found = pos.data.getWords().search(word);
            pos = pos.next;
        }
        return found;
    }

    /**
     * Searches for words starting with a specified letter in all vocabularies in the list.
     *
     * @param c The letter to search for.
     * @return An ArrayList containing all words starting with the specified letter.
     */
    public ArrayList<String> searchLetter(char c) {
        ArrayList<String> a = new ArrayList<>();
        Node pos = head;
        while (pos != null) {
            pos.data.getWords().searchLetter(c, a);
            pos = pos.next;
        }
        return a;
    }

    /**
     * Displays all topics in the list with their corresponding indices.
     */
    public void display() {
        Node pos = head;
        int count = 1;
        while (pos != null) {
            System.out.println(count + "\t" + pos.data.getTopic());
            pos = pos.next;
            count++;
        }
    }

    /**
     * Retrieves the Vocab object at the specified index in the list.
     *
     * @param index The 1-based index of the Vocab object to retrieve.
     * @return The Vocab object at the specified index, or null if not found.
     */
    public Vocab getTopicAt(int index) {
        Node pos = head;
        int count = 1;
        while (pos != null) {
            if (count == index) {
                return pos.data;
            }
            pos = pos.next;
            count++;
        }
        return null;
    }

    /**
     * Saves all vocabularies in the list to a specified file.
     *
     * @param filename The name of the file to save the vocabularies.
     */
    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            Node pos = head;
            while (pos != null) {
                writer.write("#" + pos.data.getTopic() + "\n");
                pos.data.getWords().wordList(writer);
                writer.newLine();
                pos = pos.next;
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Nested private class Node representing each node in the Dlinklist.
     */
    private class Node {
        /** The Vocab data contained in this node. */
        Vocab data;

        /** The next node in the list. */
        Node next;

        /** The previous node in the list. */
        Node prev;

        /**
         * Constructs a node containing Vocab data with pointers to next and previous nodes.
         *
         * @param data The Vocab data to be stored in the node.
         * @param next The next node in the list.
         * @param prev The previous node in the list.
         */
        Node(Vocab data, Node next, Node prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

        /**
         * Constructs a node containing Vocab data with no initial next or previous nodes.
         *
         * @param data The Vocab data to be stored in the node.
         */
        Node(Vocab data) {
            this(data, null, null);
        }
    }
}
