package com.mycompany.flooringprogram;

import controller.OrderController;
import model.OrderDAOImp;
import model.OrderDAO;



public class FlooringProgram {

    public static void main(String[] args) {
           OrderDAO orderDAO = new OrderDAOImp();
     OrderController orderController = new OrderController(orderDAO);
    orderController.run();
    }
   
       
    
    
         
  

}
