/** -----------------------------------------------------
* Assignment 3
* COMP 249
* Written by: Karim Mawji (40281154)
* April 15th 2024
* -------------------------------------------------------
*/ 
package a3;

/**
 * Class Vocab represents a vocabulary with a topic and a list of words.
 * It uses a Slinklist to manage the collection of words.
 */
public class Vocab {
    /** Slinklist to hold the words related to the topic. */
    private Slinklist words;

    /** The topic associated with the vocabulary. */
    private String topic;

    /**
     * Constructs a new Vocab instance with the specified topic.
     * Initializes an empty list of words.
     *
     * @param topic The topic of the vocabulary.
     */
    public Vocab(String topic) {
        this.words = new Slinklist();
        this.topic = topic;
    }

    /**
     * Compares this Vocab instance with another object for equality.
     * Two vocabularies are considered equal if they have the same topic.
     *
     * @param other The object to be compared for equality with this Vocab.
     * @return true if the specified object represents a Vocab equivalent to this instance, false otherwise.
     */
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Vocab otherVocab = (Vocab) other;
        return topic != null && topic.equals(otherVocab.topic);
    }

    /**
     * Returns the list of words in the vocabulary.
     *
     * @return The Slinklist containing the words.
     */
    public Slinklist getWords() {
        return words;
    }

    /**
     * Sets the list of words for the vocabulary.
     *
     * @param words The Slinklist of words to set.
     */
    public void setWords(Slinklist words) {
        this.words = words;
    }

    /**
     * Returns the topic of the vocabulary.
     *
     * @return The topic of this Vocab instance.
     */
    public String getTopic() {
        return topic;
    }

    /**
     * Sets the topic of the vocabulary.
     *
     * @param topic The topic to set for this vocabulary.
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * Returns a string representation of this Vocab instance.
     * This includes the topic and a display of the words.
     *
     * @return A string representing this Vocab instance.
     */
    @Override
    public String toString() {
        words.display();
        return "topic= " + topic;
    }
}

