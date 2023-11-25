import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MyLinkedList extends JFrame {
    static Node head;
    static Node tail;

    static class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }

    public MyLinkedList() {
        initializeUI();
    }

    public static MyLinkedList insert(MyLinkedList list, int data) {
        MyLinkedList.Node new_node = new Node(data);
        new_node.next = null;
        if (MyLinkedList.tail == null) {
            MyLinkedList.head = new_node;
            MyLinkedList.tail = new_node;
        } else {
            MyLinkedList.tail.next = new_node;
            MyLinkedList.tail = new_node;
        }
        return list;
    }

    public static MyLinkedList addFirst(MyLinkedList list, int data) {
        Node new_node = new Node(data);
        new_node.next = MyLinkedList.head;
        MyLinkedList.head = new_node;
        return list;
    }

    public static void printList(MyLinkedList list) {
        MyLinkedList.Node currNode = MyLinkedList.head;
    
        StringBuilder listString = new StringBuilder("Односвязный список: ");
        while (currNode != null) {
            listString.append(currNode.data).append(" ");
            currNode = currNode.next;
        }
        listString.append("\nhead: ").append(MyLinkedList.head != null ? MyLinkedList.head.data : "null")
                .append(" tail: ").append(MyLinkedList.tail != null ? MyLinkedList.tail.data : "null");
        JOptionPane.showMessageDialog(null, listString.toString());
    }

    public static MyLinkedList deleteAll(MyLinkedList list) {
        MyLinkedList.head = null;
        MyLinkedList.tail = null;
        JOptionPane.showMessageDialog(null,"Список очищен");
        return list;
    }

    public static int countElements(MyLinkedList list) {
        MyLinkedList.Node currNode = MyLinkedList.head;
        int count = 0;
        while (currNode != null) {
            count++;
            currNode = currNode.next;
        }
        return count;
    }

    public static boolean isEmpty(MyLinkedList list) {
        return MyLinkedList.head == null;
    }

    public static MyLinkedList deleteFirst(MyLinkedList list) {
        if (MyLinkedList.head != null) {
            MyLinkedList.head = MyLinkedList.head.next;
            JOptionPane.showMessageDialog(null,"Первый элемент удален");
        } else {
            JOptionPane.showMessageDialog(null,"Лист пустой");
        }
        return list;
    }

    public static MyLinkedList deleteLast(MyLinkedList list) {
        if (MyLinkedList.head != null) {
            if (MyLinkedList.head.next == null) {
                MyLinkedList.head = null;
                MyLinkedList.tail = null; 
            } else {
                MyLinkedList.Node currNode = MyLinkedList.head;
                while (currNode.next.next != null) {
                    currNode = currNode.next;
                }
                MyLinkedList.tail = currNode;
                currNode.next = null;
            }
            JOptionPane.showMessageDialog(null,"Последний элемент удален");
        } else {
            JOptionPane.showMessageDialog(null,"Лист пустой");
        }
                JOptionPane.showMessageDialog(null,"head: " + (MyLinkedList.head != null ? MyLinkedList.head.data : "null")
                + "tail: " + (MyLinkedList.tail != null ? MyLinkedList.tail.data : "null"));

    
        return list;
    }
    
    

    public static boolean searchValue(MyLinkedList list, int value) {
        MyLinkedList.Node currNode = MyLinkedList.head;
        while (currNode != null) {
            if (currNode.data == value) {
                return true;
            }
            currNode = currNode.next;
        }
        return false;
    }

    public static int findMax(MyLinkedList list) {
        if (MyLinkedList.head == null) {
            return Integer.MIN_VALUE;
        }

        int max = MyLinkedList.head.data;
        MyLinkedList.Node currNode = MyLinkedList.head.next;
        while (currNode != null) {
            if (currNode.data > max) {
                max = currNode.data;
            }
            currNode = currNode.next;
        }
        return max;
    }

    public static int findMin(MyLinkedList list) {
        if (MyLinkedList.head == null) {
            return Integer.MAX_VALUE;
        }

        int min = MyLinkedList.head.data;
        MyLinkedList.Node currNode = MyLinkedList.head.next;
        while (currNode != null) {
            if (currNode.data < min) {
                min = currNode.data;
            }
            currNode = currNode.next;
        }
        return min;
    }

    public static MyLinkedList deleteByValue(MyLinkedList list, int value) {
        MyLinkedList.Node currNode = MyLinkedList.head;
    
        if (currNode != null && currNode.data == value) {
            MyLinkedList.head = currNode.next;
            if (MyLinkedList.head == null) {
                MyLinkedList.tail = null;
            }
            JOptionPane.showMessageDialog(null,"Найден и удален" + value);
            return list;
        }
    
        while (currNode != null && currNode.next != null) {
            if (currNode.next.data == value) {
                currNode.next = currNode.next.next;
                if (currNode.next == null) {
                    MyLinkedList.tail = currNode;
                }
            JOptionPane.showMessageDialog(null,"Найден и удален" + value);
                return list;
            }
            currNode = currNode.next;
        }
    
        if (currNode == null) {
            JOptionPane.showMessageDialog(null,"Не найден" + value);
        }
    
        return list;
    }
    
    public static MyLinkedList deleteAllByValue(MyLinkedList list, int value) {
        MyLinkedList.Node currNode = MyLinkedList.head;
    
        while (currNode != null && currNode.data == value) {
            MyLinkedList.head = currNode.next;
            if (MyLinkedList.head == null) {
                MyLinkedList.tail = null;
            }
            currNode = MyLinkedList.head;
        }
    
        while (currNode != null && currNode.next != null) {
            if (currNode.next.data == value) {
                currNode.next = currNode.next.next;
                if (currNode.next == null) {
                    MyLinkedList.tail = currNode;
                }
            } else {
                currNode = currNode.next;
            }
        }
    
            JOptionPane.showMessageDialog(null,"Найден и удалены все" + value);
        return list;
    }
    

    public static MyLinkedList updateAllByValue(MyLinkedList list, int oldValue, int newValue) {
        MyLinkedList.Node currNode = MyLinkedList.head;

        while (currNode != null) {
            if (currNode.data == oldValue) {
                currNode.data = newValue;
            }
            currNode = currNode.next;
        }
            JOptionPane.showMessageDialog(null,"Новый | прошлый" + oldValue + '|' + newValue);
        return list;
    }

    public static boolean isSymmetric(MyLinkedList list) {
        if (MyLinkedList.head == null) {
            return true;
        }
    
        MyLinkedList.Node Pointer = MyLinkedList.head;
        Stack<Integer> stack = new Stack<>();
    
        while (Pointer != null) {
            stack.push(Pointer.data);
            Pointer = Pointer.next;
        }
    
        Pointer = MyLinkedList.head;
    
        while (Pointer != null) {
            if (Pointer.data != stack.pop()) {
                return false;
            }
            Pointer = Pointer.next;
        }
    
        return true;
    }
    

    public static boolean canRemoveTwoElementsToOrder(MyLinkedList list) {
        int[] array = new int[countElements(list)];
        MyLinkedList.Node currNode = MyLinkedList.head;
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

    public static int countUniqueValues(MyLinkedList list) {
        Set<Integer> uniqueValues = new HashSet<>();
        MyLinkedList.Node currNode = MyLinkedList.head;
        while (currNode != null) {
            uniqueValues.add(currNode.data);
            currNode = currNode.next;
        }
        return uniqueValues.size();
    }

    public static MyLinkedList deleteDuplicates(MyLinkedList list) {
        Set<Integer> seenValues = new HashSet<>();
        MyLinkedList.Node currNode = MyLinkedList.head;
    
        while (currNode != null && seenValues.contains(currNode.data)) {
            MyLinkedList.head = currNode.next;
            currNode = MyLinkedList.head;
        }
    
        while (currNode != null && currNode.next != null) {
            if (seenValues.contains(currNode.next.data)) {
                currNode.next = currNode.next.next;
            } else {
                seenValues.add(currNode.next.data);
                currNode = currNode.next;
            }
        }   
        MyLinkedList.Node tail = MyLinkedList.head;
        while (tail != null && tail.next != null) {
            tail = tail.next;
        }
        MyLinkedList.tail = tail;
    
            JOptionPane.showMessageDialog(null,"Удалены все повторяющиеся элементы");
        return list;
    }
    
    
    public static MyLinkedList reverseListOrder(MyLinkedList list) {
        MyLinkedList.Node current = MyLinkedList.head;
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
            MyLinkedList.head = newHead;
        MyLinkedList.tail = newTail;
    
            JOptionPane.showMessageDialog(null,"Лист перевернут");
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

    private void initializeUI() {
        setTitle("Односвязные списки");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        JPanel buttonPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton insertButton = new JButton("Добавить как последний элемент");
        JButton addFirstButton = new JButton("Добавить как первый элемент");
        JButton printListButton = new JButton("Распечатать");
        JButton deleteAllButton = new JButton("Удалить все");
        JButton countElementsButton = new JButton("Длина листа");
        JButton isEmptyButton = new JButton("Пустой лист");
        JButton deleteFirstButton = new JButton("Удалить первый элемент");
        JButton deleteLastButton = new JButton("Удалить последний элемент");
        Font buttonFont = new Font("Arial", Font.PLAIN, 20);
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int data = Integer.parseInt(JOptionPane.showInputDialog("Введите значение:"));
                insert(null, data);
            }
        });

        addFirstButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int data = Integer.parseInt(JOptionPane.showInputDialog("Введите значение:"));
                addFirst(null, data);
            }
        });

        printListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printList(null);
            }
        });

        deleteAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteAll(null);
            }
        });

        countElementsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Длина листа: " + countElements(null));
            }
        });

        isEmptyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Проверка на пустой лист: " + isEmpty(null));
            }
        });

        deleteFirstButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteFirst(null);
            }
        });

        deleteLastButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteLast(null);
            }
        });

        JButton deleteByValueButton = new JButton("Удалить по значению");
        deleteByValueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = Integer.parseInt(JOptionPane.showInputDialog("Введите значение для удаления:"));
                deleteByValue(null, value);
            }
        });
        buttonPanel.add(deleteByValueButton);
    
        JButton deleteAllByValueButton = new JButton("Удалить все по значению");
        deleteAllByValueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = Integer.parseInt(JOptionPane.showInputDialog("Введите значение для удаления:"));
                deleteAllByValue(null, value);
            }
        });
        buttonPanel.add(deleteAllByValueButton);
            JButton updateAllByValueButton = new JButton("Обновить все по значению");
        updateAllByValueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int oldValue = Integer.parseInt(JOptionPane.showInputDialog("Введите старое значение:"));
                int newValue = Integer.parseInt(JOptionPane.showInputDialog("Введите новое значение:"));
                updateAllByValue(null, oldValue, newValue);
            }
        });
        buttonPanel.add(updateAllByValueButton);
        JButton isSymmetricButton = new JButton("Проверить на симметрию");
        isSymmetricButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Симметричен: " + isSymmetric(null));
            }
        });
        buttonPanel.add(isSymmetricButton);
    
        JButton canRemoveTwoElementsToOrderButton = new JButton("Можно удалить два элемента для упорядочивания");
        canRemoveTwoElementsToOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Можно удалить два элемента: " + canRemoveTwoElementsToOrder(null));
            }
        });
        buttonPanel.add(canRemoveTwoElementsToOrderButton);
    
        JButton countUniqueValuesButton = new JButton("Подсчитать уникальные значения");
        countUniqueValuesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Уникальных значений: " + countUniqueValues(null));
            }
        });
        buttonPanel.add(countUniqueValuesButton);
    
        JButton deleteDuplicatesButton = new JButton("Удалить дубликаты");
        deleteDuplicatesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteDuplicates(null);
            }
        });
        buttonPanel.add(deleteDuplicatesButton);
    
        JButton reverseListOrderButton = new JButton("Перевернуть порядок");
        reverseListOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reverseListOrder(null);
            }
        });
        buttonPanel.add(reverseListOrderButton);


        JButton findMaxButton = new JButton("Max");
        findMaxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int max = findMax(null);
                JOptionPane.showMessageDialog(null, "Максимальное значение: " + max);
            }
        });
        buttonPanel.add(findMaxButton);


        JButton findMinButton = new JButton("Min");
        findMinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int min = findMin(null);
                JOptionPane.showMessageDialog(null, "Минимальное значение: " + min);
            }
        });
        buttonPanel.add(findMinButton);
        buttonPanel.add(insertButton);
        buttonPanel.add(addFirstButton);
        buttonPanel.add(printListButton);
        buttonPanel.add(deleteAllButton);
        buttonPanel.add(countElementsButton);
        buttonPanel.add(isEmptyButton);
        buttonPanel.add(deleteFirstButton);
        buttonPanel.add(deleteLastButton);
        add(buttonPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        stylizeButton(findMinButton, buttonFont, 450, 100);
        stylizeButton(findMaxButton, buttonFont, 300, 50);
        stylizeButton(insertButton, buttonFont, 300, 50);
        stylizeButton(addFirstButton, buttonFont, 300, 50);
        stylizeButton(printListButton, buttonFont, 300, 50);
        stylizeButton(deleteAllButton, buttonFont, 300, 50);
        stylizeButton(countElementsButton, buttonFont, 300, 50);
        stylizeButton(isEmptyButton, buttonFont, 300, 50);
        stylizeButton(deleteFirstButton, buttonFont, 300, 50);
        stylizeButton(deleteLastButton, buttonFont, 300, 50);
        stylizeButton(deleteByValueButton, buttonFont, 300, 50);
        stylizeButton(deleteAllByValueButton, buttonFont, 300, 50);
        stylizeButton(updateAllByValueButton, buttonFont, 300, 50);
        stylizeButton(isSymmetricButton, buttonFont, 300, 50);
        stylizeButton(canRemoveTwoElementsToOrderButton, buttonFont, 300, 50);
        stylizeButton(countUniqueValuesButton, buttonFont, 300, 50);
        stylizeButton(deleteDuplicatesButton, buttonFont, 300, 50);
        stylizeButton(reverseListOrderButton, buttonFont, 300, 50);
        stylizeInputDialog();

    }
    private void stylizeInputDialog() {
        UIManager.put("OptionPane.messageFont", new Font("Verdana", Font.PLAIN, 18));
        UIManager.put("OptionPane.background", new Color(240, 240, 240)); 
        UIManager.put("OptionPane.foreground", new Color(50, 50, 50)); 
        UIManager.put("TextField.font", new Font("Verdana", Font.PLAIN, 18));
        UIManager.put("TextField.background", new Color(255, 255, 255)); 
        UIManager.put("TextField.foreground", new Color(50, 50, 50)); 
        UIManager.put("Button.font", new Font("Verdana", Font.PLAIN, 18));
        UIManager.put("Button.background", new Color(150, 150, 150)); 
        UIManager.put("Button.foreground", new Color(255, 255, 255));
    }
    
    private void stylizeButton(JButton button, Font font, int width, int height) {
        button.setFont(font);
        button.setBackground(new Color(240, 240, 240));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(width, height));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(200, 200, 200));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(240, 240, 240));
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MyLinkedList();
            }
        });
    }
}
