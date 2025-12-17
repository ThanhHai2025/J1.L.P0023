package Controller;

import Model.Fruit;
import Model.Order;
import Utility.Validation;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author Admin
 */
public class Manager {

    Validation validation = new Validation();

    public void createFruit(ArrayList<Fruit> listOfFruit) {
        while (true) {
            String fruitId = validation.checkInputStringRegex("Enter fruit Id: ", "^[A-Za-z0-9]+$");
            if (!validation.checkIdExist(listOfFruit, fruitId)) {
                System.out.println("ID exist.");
                return;
            }
            String fruitName = validation.checkInputString("Enter fruit Name: ");
            double price = validation.checkInputDouble("Enter Price: ");
            int quantity = validation.checkInputInt("Enter Quantity: ");
            String origin = validation.checkInputString("Enter Origin: ");
            listOfFruit.add(new Fruit(fruitId, fruitName, price, quantity, origin));
            if (!validation.checkInputYN("Do you want to continue: ")) {
                return;
            }
        }
    }

    public void viewOrder(Hashtable<String, ArrayList<Order>> tableOrder) {
        if (tableOrder.isEmpty()) {
            System.out.println("No order.");
            return;
        }
        for (String name : tableOrder.keySet()) {
            System.out.println("Customer: " + name);
            ArrayList<Order> listOfOrder = tableOrder.get(name);
            displayListOrder(listOfOrder);
        }
    }

    public void shopping(ArrayList<Fruit> listOfFruit, Hashtable<String, ArrayList<Order>> tableOrder) {
        if (listOfFruit.isEmpty()) {
            System.out.println("No have item.");
            return;
        }
        ArrayList<Order> listOfOrder = new ArrayList<>();
        while (true) {
            displayListFruit(listOfFruit);
            int maxItem = getItemCount(listOfFruit);
            int item = validation.checkInputLimit("Enter item: ", 1, maxItem);
            Fruit fruit = getFruitByItem(listOfFruit, item);
            int quantity = validation.checkInputLimit("Enter quantity: ", 1, fruit.getQuantity());
            fruit.setQuantity(fruit.getQuantity() - quantity);
            if (!validation.checkItemExist(listOfOrder, fruit.getFruitId())) {
                updateOrder(listOfOrder, fruit.getFruitId(), quantity);
            } else {
                listOfOrder.add(new Order(fruit.getFruitId(), fruit.getFruitName(), quantity, fruit.getPrice()));
            }
            if (!validation.checkInputYN("Do you want to continue:")) {
                break;
            }
        }
        displayListOrder(listOfOrder);
        String name = validation.checkInputString("Enter name: ");
        tableOrder.put(name, listOfOrder);
        System.out.println("Add successful.");
    }

    private void displayListFruit(ArrayList<Fruit> listOfFruit) {
        int count = 1;
        System.out.printf("%-10s%-20s%-20s%-15s\n", "Item", "Fruit name", "Origin", "Price");
        for (Fruit fruit : listOfFruit) {
            if (fruit.getQuantity() != 0) {
                System.out.printf("%-10s%-20s%-20s%-15.0f$\n", count, fruit.getFruitName(), fruit.getOrigin(), fruit.getPrice());
                count++;
            }
        }
    }

    private int getItemCount(ArrayList<Fruit> listOfFruit) {
        int count = 0;
        for (Fruit fruit : listOfFruit) {
            if (fruit.getQuantity() > 0) {
                count++;
            }
        }
        return count;
    }

    private Fruit getFruitByItem(ArrayList<Fruit> listOfFruit, int item) {
        int count = 1;
        for (Fruit fruit : listOfFruit) {
            if (fruit.getQuantity() > 0) {
                if (count == item) {
                    return fruit;
                }
                count++;
            }
        }
        return null;
    }

    private void updateOrder(ArrayList<Order> listOfOrder, String id, int quantity) {
        for (Order order : listOfOrder) {
            if (order.getFruitId().equalsIgnoreCase(id)) {
                order.setQuantity(order.getQuantity() + quantity);
                return;
            }
        }
    }

    private void displayListOrder(ArrayList<Order> listOfOrder) {
        double total = 0;
        System.out.printf("%15s%15s%15s%15s\n", "Product", "Quantity", "Price", "Amount");
        for (Order order : listOfOrder) {
            System.out.printf("%15s%15s%15.0f$%15.0f$\n", order.getFruitName(), order.getQuantity(), order.getPrice(), order.getPrice() * order.getQuantity());
            total += order.getPrice() * order.getQuantity();
        }
        System.out.println("Total: " + total);
    }

}
