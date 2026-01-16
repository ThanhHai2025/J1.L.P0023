package View;

import Controller.Manager;
import Model.Fruit;
import Model.Order;
import Utility.Validation;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author Admin
 */
public class View {

    Validation validation = new Validation();
    Manager manager = new Manager();
    ArrayList<Fruit> listOfFruit = new ArrayList<>();
    Hashtable<String, ArrayList<Order>> tableOrder = new Hashtable<>();

    public void Menu() {
        System.out.println("====== Fruit Shop System ======");
        System.out.println("1. Create Fruit");
        System.out.println("2. View Orders");
        System.out.println("3. Shopping");
        System.out.println("4. Exit");
    }

    public void display() {
        while (true) {
            Menu();
            int choice = validation.checkInputLimit("Enter your choice: ", 1, 4);
            switch (choice) {
                case 1:
                    manager.createFruit(listOfFruit);
                    break;
                case 2:
                    manager.viewOrder(tableOrder);
                    break;
                case 3:
                    manager.shopping(listOfFruit, tableOrder);
                    break;
                case 4:
                    System.out.println("Exit program.");
                    return;
            }
        }
    }
}
