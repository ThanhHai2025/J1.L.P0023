package Utility;

import Model.Fruit;
import Model.Order;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Validation {

    Scanner check = new Scanner(System.in);

    public int checkInputLimit(String mess, int min, int max) {
        System.out.println(mess);
        while (true) {
            try {
                int result = Integer.parseInt(check.nextLine().trim());
                if (result < min || result > max) {
                    System.err.println("Please input number in range [" + min + ", " + max + "].");
                    System.out.print("Invalid number. Please input again: ");
                    continue;
                }
                return result;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please input again: ");
            }
        }
    }

    public int checkInputInt(String mess) {
        System.out.println(mess);
        while (true) {
            try {
                int result = Integer.parseInt(check.nextLine().trim());
                return result;
            } catch (NumberFormatException e) {
                System.out.println("Must be input integer. Please enter again: ");
            }
        }
    }

    public double checkInputDouble(String mess) {
        System.out.println(mess);
        while (true) {
            try {
                double result = Double.parseDouble(check.nextLine().trim());
                return result;
            } catch (NumberFormatException e) {
                System.out.println("Must be input double. Please enter again: ");
            }
        }
    }
    
    public String checkInputString(String mess){
        System.out.println(mess);
        while(true){
            String result=check.nextLine().trim();
            if (result.isEmpty()) {
                System.out.println("Empty input. Enter again: ");
                continue;
            }
            return result;
        }
    }

    public String checkInputStringRegex(String mess, String regex) {
        System.out.println(mess);
        while (true) {
            String result = check.nextLine().trim();
            if (result.isEmpty()) {
                System.out.println("Empty input. Enter again: ");
                continue;
            }
            if(!result.matches(regex)){
                System.out.println("Wrong format. Enter again: ");
                continue;
            }
            return result;
        }
    }
    
    public boolean checkInputYN(String mess){
        System.out.print(mess);
        while(true){
            String result = check.nextLine().trim();
            if(result.equalsIgnoreCase("Y")){
                return true;
            }
            if(result.equalsIgnoreCase("N")){
                return false;
            }
            System.out.println("Only onput Y or N. Enter again: ");
        }
    }
    
    public boolean checkIdExist(ArrayList<Fruit> listOfFruit, String id){
        for(Fruit fruit : listOfFruit){
            if(id.equalsIgnoreCase(fruit.getFruitId())){
                return false;
            }
        }
        return true;
    }
    
    public boolean checkItemExist(ArrayList<Order> listOfOrder, String id){
        for(Order order : listOfOrder){
            if(order.getFruitId().equalsIgnoreCase(id)){
                return false;
            }
        }
        return true;
    }
}
