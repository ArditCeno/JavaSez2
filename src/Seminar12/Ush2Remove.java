package Seminar12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

class Student {
    String emri;
    List<String> lendet;

    Student(String emri, List<String> lendet) {
        this.emri = emri;
        this.lendet = lendet;
    }

    public String toString() {
        return emri + lendet;
    }
}

class Node {
    Student data;
    Node prev;
    Node next;

    Node(Student data) {
        this.data = data;
    }
}

class CustomLinkedList {
    private Node head;
    private Node tail;
    private int size;

    public void add(Student data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    public boolean removeIf(Predicate<Student> filter) {
        boolean removed = false;
        Node current = head;
        while (current != null) {
            if (filter.test(current.data)) {
                Node toRemove = current;
                current = current.next;
                removeNode(toRemove);
                removed = true;
            } else {
                current = current.next;
            }
        }
        return removed;
    }

    private void removeNode(Node node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }
        size--;
    }

    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    public int size() {
        return size;
    }
}

public class Ush2Remove {
    public static void main(String[] args) {

        CustomLinkedList customList = new CustomLinkedList();
        customList.add(new Student("Ardit", Arrays.asList("Analize", "Fizike", "C", "C++", "Java")));
        customList.add(new Student("Abjola", Arrays.asList("Analize", "Fizike", "C")));
        customList.add(new Student("Jana", Arrays.asList("Analize", "Fizike", "C", "Java")));
        customList.add(new Student("Erla", Arrays.asList("Analize", "Fizike")));
        customList.add(new Student("Anxhela", Arrays.asList("Analize", "Fizike", "C", "Java", "C++", "Algoritmike")));
        customList.add(new Student("Gledisa", Arrays.asList("Analize", "Fizike", "C")));
        customList.add(new Student("Briselda", Arrays.asList("Analize", "Fizike", "C", "Java")));
        customList.add(new Student("Esi", Arrays.asList("Analize")));

        System.out.println("Para removeIf :");
        customList.printList();
        System.out.println();

        customList.removeIf(s -> s.lendet.size() <= 3);

        System.out.println("Pas removeIf:");
        customList.printList();
        System.out.println();

        LinkedList<Student> javaList = new LinkedList<>();
        javaList.add(new Student("Ardit", Arrays.asList("Analize", "Fizike", "C", "C++", "Java")));
        javaList.add(new Student("Abjola", Arrays.asList("Analize", "Fizike", "C")));
        javaList.add(new Student("Jana", Arrays.asList("Analize", "Fizike", "C", "Java")));
        javaList.add(new Student("Erla", Arrays.asList("Analize", "Fizike")));
        javaList.add(new Student("Anxhela", Arrays.asList("Analize", "Fizike", "C", "Java", "C++", "Algoritmike")));
        javaList.add(new Student("Gledisa", Arrays.asList("Analize", "Fizike", "C")));
        javaList.add(new Student("Briselda", Arrays.asList("Analize", "Fizike", "C", "Java")));
        javaList.add(new Student("Esi", Arrays.asList("Analize")));

        System.out.println("Para removeIf:");
        System.out.println(javaList);
        System.out.println();

        javaList.removeIf(s -> s.lendet.size() <= 3);

        System.out.println("Pas removeIf:");
        System.out.println(javaList);
    }
}
