package day_08;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Map {
    private final String START_NAME = "AAA";
    private final String END_NAME = "ZZZ";
    private final String[] input;
    private final Node[] nodes;
    private List<Node> currentNodes;
    private Node currentNode;
    private int[] cycleLengths;

    Map(String[] input) {
        this.input = input;
        nodes = createNodes();
        connectNodes();
        currentNode = findNodeWithName(START_NAME);
        currentNodes = getStartingNodes();
        cycleLengths = new int[currentNodes.size()];
    }

    // Part I
    int executeInstructionsAndGetRounds(char[] instructions) {
        int rounds = 0;
        while (!currentNode.getName().equals(END_NAME)) {
            executeInstructionsForCurrentNode(instructions);
            rounds++;
        }
        return rounds;
    }

    private void executeInstructionsForCurrentNode(char[] instructions) {
        for (char instruction : instructions) {
            currentNode = currentNode.getNodeOfInstruction(instruction);
        }
    }

    // Part II
    int[] countRoundsOfInstructionsForAllNodes(char[] instructions) {
        int rounds = 0;
        while (!allCycleLengthsFinished()) {
            rounds++;
            executeInstructionsForAllNodes(instructions);
            checkForFinishedCycle(rounds);
        }
        return cycleLengths;
    }

    private void checkForFinishedCycle(int rounds) {
        for (int nodeIndex = 0; nodeIndex < currentNodes.size(); nodeIndex++) {
            if (currentNodes.get(nodeIndex).nameEndsWith("Z")) {
                cycleLengths[nodeIndex] = rounds;
            }
        }
    }

    private boolean allCycleLengthsFinished() {
        for (int cycleLength : cycleLengths) {
            if (cycleLength == 0) return false;
        }
        return true;
    }

    private void executeInstructionsForAllNodes(char[] instructions) {
        for (char instruction : instructions) {
            currentNodes.replaceAll(node -> node.getNodeOfInstruction(instruction));
        }
    }

    private Node[] createNodes() {
        Node[] nodes = new Node[input.length];
        for (int nodeIndex = 0; nodeIndex < nodes.length; nodeIndex++) {
            String nodeName = input[nodeIndex].split(" ")[0];
            nodes[nodeIndex] = new Node(nodeName);
        }
        return nodes;
    }

    private void connectNodes() {
        for (int nodeIndex = 0; nodeIndex < nodes.length; nodeIndex++) {
            Node left = findNodeWithName(getLeftFromString(input[nodeIndex]));
            Node right = findNodeWithName(getRightFromString(input[nodeIndex]));
            nodes[nodeIndex].setNeighbors(left, right);
        }
    }

    private String getLeftFromString(String string) {
        return string.split("\\(")[1].split(",")[0];
    }

    private String getRightFromString(String string) {
        return string.split(", ")[1].split("\\)")[0];
    }

    private Node findNodeWithName(String name) {
        for (Node node : nodes) {
            if (name.equals(node.getName())) {
                return node;
            }
        }
        throw new NoSuchElementException("No such element name:" + name);
    }

    private List<Node> getStartingNodes() {
        List<Node> startingNodes = new ArrayList<>();
        for (Node node : nodes) {
            if (node.nameEndsWith("A")) {
                startingNodes.add(node);
            }
        }
        return startingNodes;
    }
}
