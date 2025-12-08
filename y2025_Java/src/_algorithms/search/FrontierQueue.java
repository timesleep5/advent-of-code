package _algorithms.search;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class FrontierQueue extends AbstractQueue<List<State>> implements Frontier {
    private final LinkedList<List<State>> list;

    public FrontierQueue() {
        list = new LinkedList<>();
    }

    @Override
    public Iterator<List<State>> iterator() {
        return list.iterator();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public List<State> pop() {
        return list.poll();
    }

    @Override
    public void push(List<State> path) {
        list.add(path);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean offer(List<State> states) {
        return list.offer(states);
    }

    @Override
    public List<State> poll() {
        return list.poll();
    }

    @Override
    public List<State> peek() {
        return list.peek();
    }
}
