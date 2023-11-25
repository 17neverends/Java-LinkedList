import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class LinkedList {
    Node head;
    Node tail;

    static class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }

    public static LinkedList insert(LinkedList list, int data) {
        Node new_node = new Node(data);
        new_node.next = null;
        if (list.tail == null) {
            list.head = new_node;
            list.tail = new_node;
        } else {
            list.tail.next = new_node;
            list.tail = new_node;
        }
        return list;
    }

    public static LinkedList addFirst(LinkedList list, int data) {
        Node new_node = new Node(data);
        new_node.next = list.head;
        list.head = new_node;
        return list;
    }

    public static void printList(LinkedList list) {
        Node currNode = list.head;

        System.out.print("\nОдносвязный список: ");

        while (currNode != null) {
            System.out.print(currNode.data + " ");
            currNode = currNode.next;
        }
        System.out.println("\n");
        System.out.println("head: " + (list.head != null ? list.head.data : "null"));
        System.out.println("tail: " + (list.tail != null ? list.tail.data : "null"));
    }

    public static LinkedList deleteAll(LinkedList list) {
        list.head = null;
        list.tail = null;
        System.out.println("Список очищен");
        return list;
    }

    public static int countElements(LinkedList list) {
        Node currNode = list.head;
        int count = 0;
        while (currNode != null) {
            count++;
            currNode = currNode.next;
        }
        return count;
    }

    public static boolean isEmpty(LinkedList list) {
        return list.head == null;
    }

    public static LinkedList deleteFirst(LinkedList list) {
        if (list.head != null) {
            list.head = list.head.next;
            System.out.println("Первый элемент удален");
        } else {
            System.out.println("Лист пустой");
        }
        return list;
    }

    public static LinkedList deleteLast(LinkedList list) {
        if (list.head != null) {
            if (list.head.next == null) {
                list.head = null;
                list.tail = null; 
            } else {
                Node currNode = list.head;
                while (currNode.next.next != null) {
                    currNode = currNode.next;
                }
                list.tail = currNode;
                currNode.next = null;
            }
            System.out.println("Последний элемент удален");
        } else {
            System.out.println("Пустой лист");
        }
    
        System.out.println("head: " + (list.head != null ? list.head.data : "null"));
        System.out.println("tail: " + (list.tail != null ? list.tail.data : "null"));
    
        return list;
    }
    
    

    public static boolean searchValue(LinkedList list, int value) {
        Node currNode = list.head;
        while (currNode != null) {
            if (currNode.data == value) {
                return true;
            }
            currNode = currNode.next;
        }
        return false;
    }

    public static int findMax(LinkedList list) {
        if (list.head == null) {
            return Integer.MIN_VALUE;
        }

        int max = list.head.data;
        Node currNode = list.head.next;
        while (currNode != null) {
            if (currNode.data > max) {
                max = currNode.data;
            }
            currNode = currNode.next;
        }
        return max;
    }

    public static int findMin(LinkedList list) {
        if (list.head == null) {
            return Integer.MAX_VALUE;
        }

        int min = list.head.data;
        Node currNode = list.head.next;
        while (currNode != null) {
            if (currNode.data < min) {
                min = currNode.data;
            }
            currNode = currNode.next;
        }
        return min;
    }

    public static LinkedList deleteByValue(LinkedList list, int value) {
        Node currNode = list.head;
    
        if (currNode != null && currNode.data == value) {
            list.head = currNode.next;
            if (list.head == null) {
                list.tail = null;
            }
            System.out.println(value + " найден и удален");
            return list;
        }
    
        while (currNode != null && currNode.next != null) {
            if (currNode.next.data == value) {
                currNode.next = currNode.next.next;
                if (currNode.next == null) {
                    list.tail = currNode;
                }
                System.out.println(value + " найден и удален");
                return list;
            }
            currNode = currNode.next;
        }
    
        if (currNode == null) {
            System.out.println(value + " не найден");
        }
    
        return list;
    }
    
    public static LinkedList deleteAllByValue(LinkedList list, int value) {
        Node currNode = list.head;
    
        while (currNode != null && currNode.data == value) {
            list.head = currNode.next;
            if (list.head == null) {
                list.tail = null;
            }
            currNode = list.head;
        }
    
        while (currNode != null && currNode.next != null) {
            if (currNode.next.data == value) {
                currNode.next = currNode.next.next;
                if (currNode.next == null) {
                    list.tail = currNode;
                }
            } else {
                currNode = currNode.next;
            }
        }
    
        System.out.println("Все элементы со значением " + value + " удалены");
        return list;
    }
    

    public static LinkedList updateAllByValue(LinkedList list, int oldValue, int newValue) {
        Node currNode = list.head;

        while (currNode != null) {
            if (currNode.data == oldValue) {
                currNode.data = newValue;
            }
            currNode = currNode.next;
        }
        System.out.println("Все вхождения " + oldValue + " заменены " + newValue);
        return list;
    }

    public static boolean isSymmetric(LinkedList list) {
        if (list.head == null) {
            return true;
        }
    
        Node Pointer = list.head;
        Stack<Integer> stack = new Stack<>();
    
        while (Pointer != null) {
            stack.push(Pointer.data);
            Pointer = Pointer.next;
        }
    
        Pointer = list.head;
    
        while (Pointer != null) {
            if (Pointer.data != stack.pop()) {
                return false;
            }
            Pointer = Pointer.next;
        }
    
        return true;
    }
    

    public static boolean canRemoveTwoElementsToOrder(LinkedList list) {
        int[] array = new int[countElements(list)];
        Node currNode = list.head;
        int i = 0;
        while (currNode != null) {
            array[i++] = currNode.data;
            currNode = currNode.next;
        }
        int count = 0;
        for (int j = 0; j < array.length - 1; j++) {
            if (array[j] > array[j + 1]) {
                count++;
            }
            if (count > 2) {
                return false;
            }
        }
        return true;
    }

    public static int countUniqueValues(LinkedList list) {
        Set<Integer> uniqueValues = new HashSet<>();
        Node currNode = list.head;
        while (currNode != null) {
            uniqueValues.add(currNode.data);
            currNode = currNode.next;
        }
        return uniqueValues.size();
    }

    public static LinkedList deleteDuplicates(LinkedList list) {
        Set<Integer> seenValues = new HashSet<>();
        Node currNode = list.head;
    
        while (currNode != null && seenValues.contains(currNode.data)) {
            list.head = currNode.next;
            currNode = list.head;
        }
    
        while (currNode != null && currNode.next != null) {
            if (seenValues.contains(currNode.next.data)) {
                currNode.next = currNode.next.next;
            } else {
                seenValues.add(currNode.next.data);
                currNode = currNode.next;
            }
        }
        Node tail = list.head;
        while (tail != null && tail.next != null) {
            tail = tail.next;
        }
        list.tail = tail;
    
        System.out.println("Удалены повторяющиеся элементы");
        return list;
    }
    
    
    public static LinkedList reverseListOrder(LinkedList list) {
        Node current = list.head;
        Node newHead = null;
        Node newTail = null;
    
        while (current != null) {
            Node next = current.next;
            current.next = newHead;
            newHead = current;
    
            if (newTail == null) {
                newTail = current; 
            }
    
            current = next;
        }
            list.head = newHead;
        list.tail = newTail;
    
        System.out.println("Лист перевернут");
        return list;
    }
    
    

    public void sortByPointer() {
        if (head == null || head.next == null) {
            return;
        }
    
        Node sortedList = null;
        Node current = head;
    
        while (current != null) {
            Node next = current.next;
            sortedList = sortedInsert(sortedList, current);
            current = next;
        }
    
        head = sortedList;
    
        Node tailNode = head;
        while (tailNode.next != null) {
            tailNode = tailNode.next;
        }
        tail = tailNode;
    }
    
    private Node sortedInsert(Node sortedList, Node newNode) {
        if (sortedList == null || sortedList.data >= newNode.data) {
            newNode.next = sortedList;
            return newNode;
        }
    
        Node current = sortedList;
        while (current.next != null && current.next.data < newNode.data) {
            current = current.next;
        }
    
        newNode.next = current.next;
        current.next = newNode;
    
        return sortedList;
    }


    public void sortByValue() {
        if (head == null || head.next == null) {
            return;
        }
    
        boolean swapped;
        Node last = null;
    
        do {
            swapped = false;
            Node current = head;
            Node nextNode = head.next;
    
            while (nextNode != last) {
                if (current.data > nextNode.data) {
                    swapData(current, nextNode);
                    swapped = true;
                }
                current = nextNode;
                nextNode = nextNode.next;
            }
    
            last = current;
        } while (swapped);
    }
    
    private void swapData(Node node1, Node node2) {
        int temp = node1.data;
        node1.data = node2.data;
        node2.data = temp;
    }
    



public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list = insert(list, 1);
        list = insert(list, 2);
        list = insert(list, 3);
        list = insert(list, 23);
        list = insert(list, 31);
        list = insert(list, 24);
        list = insert(list, 311);
        list = insert(list, 22);
        list = insert(list, 31);
        printList(list);
        deleteAll(list);
        printList(list);
        list = insert(list, 1);
        list = insert(list, 2);
        list = insert(list, 3);
        list = insert(list, 23);
        list = insert(list, 31);
        list = insert(list, 24);
        list = insert(list, 311);
        list = insert(list, 22);
        list = insert(list, 32);
        printList(list);
        System.out.println("Длина списка: " + countElements(list));
        System.out.println("Проверка на пустой лист: " + isEmpty(list));
        printList(list);
        list = deleteFirst(list);
        printList(list);
        list = deleteLast(list);
        printList(list);
        System.out.println("Поиск значения: " + searchValue(list, 2));
        System.out.println("Max: " + findMax(list));
        System.out.println("Min: " + findMin(list));
        printList(list);
        list = deleteByValue(list, 2);
        printList(list);
        list = insert(list, 3);
        list = insert(list, 3);
                printList(list);
        list = deleteAllByValue(list, 3);
        printList(list);
        list = updateAllByValue(list, 22, 10);
        printList(list);
        System.out.println("Симметрия:  " + isSymmetric(list));
        System.out.println("Гипотеза: " + canRemoveTwoElementsToOrder(list));
        System.out.println("Сет: " + countUniqueValues(list));
        list = insert(list, 31);
        list = insert(list, 31);
        list = insert(list, 31);    
        printList(list);
        deleteDuplicates(list);
        printList(list);
        reverseListOrder(list);
        printList(list);
        list.sortByValue();
        printList(list);
        list.sortByPointer();
        printList(list);
    }
}