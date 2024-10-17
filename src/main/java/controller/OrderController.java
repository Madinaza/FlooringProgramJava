
package controller;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import model.Order;
import model.OrderDAO;


public class OrderController {
     private final OrderDAO orderDAO;
    private final Scanner scanner;

    public OrderController(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        int choice = 0;

        do {
            displayMenu();
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                        addOrder();
                        break;
                    case 2:
                        updateOrder();
                        break;
                    case 3:
                        deleteOrder();
                        break;
                    case 4:
                        viewOrders();
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); 
            }
        } while (choice != 5);
        
        scanner.close();
    }

    private void displayMenu() {
        System.out.println("1. Add Order");
        System.out.println("2. Update Order");
        System.out.println("3. Delete Order");
        System.out.println("4. View Orders");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private void addOrder() {
        System.out.println("Adding a new order...");

        String customerName;
    while (true) {
        System.out.print("Enter Customer Name: ");
        customerName = scanner.nextLine().trim(); 

       
        if (customerName.isEmpty()) {
            System.out.println("Customer name cannot be empty. Please enter a valid name.");
        } else {
            break;
        }
    }

    String state;
    while (true) {
        System.out.print("Enter State: ");
        state = scanner.nextLine().trim(); 

      
        if (state.isEmpty()) {
            System.out.println("State cannot be empty. Please enter a valid state.");
        } else {
            break; 
        }
    }

        BigDecimal taxRate = getBigDecimalInput("Enter Tax Rate (as number): ");

        System.out.print("Enter Product Type: ");
        String productType = scanner.nextLine();

        BigDecimal area = getBigDecimalInput("Enter Area (as number): ");

        BigDecimal costPerSquareFoot = getBigDecimalInput("Enter Cost Per Square Foot (as number): ");

        BigDecimal laborCostPerSquareFoot = getBigDecimalInput("Enter Labor Cost Per Square Foot (as number): ");

        Order newOrder = new Order(0, customerName, state, taxRate, productType, area, costPerSquareFoot, laborCostPerSquareFoot);
        orderDAO.addOrder(newOrder);
        System.out.println("Order added successfully!");
    }

    private void updateOrder() {
        System.out.print("Enter Order Number to update: ");
        int orderNumber = scanner.nextInt();
        scanner.nextLine(); 

        Order order = orderDAO.getOrder(orderNumber);
        if (order == null) {
            System.out.println("Order not found.");
            return;
        }

        System.out.println("Current Order Details: " + order);
        System.out.println("Updating the order...");

      
    }

    private void deleteOrder() {
        System.out.print("Enter Order Number to delete: ");
        int orderNumber = scanner.nextInt();
        scanner.nextLine(); 
        orderDAO.deleteOrder(orderNumber);
        System.out.println("Order deleted successfully!");
    }

    private void viewOrders() {
        List<Order> orders = orderDAO.getOrders();

        if (orders.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            for (Order order : orders) {
                System.out.println(order.getOrderNumber() + " " +
                                   order.getCustomerName() + " " +
                                   order.getState() + " " +
                                   order.getTaxRate() + " " +
                                   order.getProductType() + " " +
                                   order.getArea() + " " +
                                   order.getCostPerSquareFoot() + " " +
                                   order.getLaborCostPerSquareFoot() + " " +
                                   order.getMaterialCost() + " " +
                                   order.getLaborCost() + " " +
                                   order.getTax() + " " +
                                   order.getTotal());
            }
        }
    }

    private BigDecimal getBigDecimalInput(String prompt) {
        BigDecimal value = null;
        while (value == null) {
            System.out.print(prompt);
            try {
                String input = scanner.nextLine();
                if (input.isEmpty()) {
                    return null; 
                }
                value = new BigDecimal(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid decimal number.");
            }
        }
        return value;
    }
    
}
