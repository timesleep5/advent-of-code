package day_08;

import java.util.NoSuchElementException;

public class Node {
    private final String name;
    private Node left;
    private Node right;

    public String getName() {
        return name;
    }

    Node(String name) {
        this.name = name;
    }

    boolean nameEndsWith(String character) {
        return name.endsWith(character);
    }

    void setNeighbors(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    Node getNodeOfInstruction(char instruction) {
        return switch (instruction) {
            case 'L' -> left;
            case 'R' -> right;
            default -> throw new IllegalArgumentException("Wrong instruction: " + instruction);
        };
    }
}
