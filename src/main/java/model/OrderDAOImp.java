
package model;
import com.mycompany.flooringprogram.DatabaseConnection;
import model.OrderDAO;
import model.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class OrderDAOImp implements OrderDAO {
    
  private Connection connection;

   public OrderDAOImp() {
        this.connection = DatabaseConnection.setCon();
    }
    
 
    
    @Override
    public void addOrder ( Order order ) {
                
     String query = "INSERT INTO Orders (CustomerName, State, TaxRate, ProductType, Area, CostPerSquareFoot, LaborCostPerSquareFoot, MaterialCost, LaborCost, Tax, Total) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
    
    try (PreparedStatement orderst = connection.prepareStatement(query)) {
        orderst.setString(1, order.getCustomerName());
        orderst.setString(2, order.getState());
        orderst.setBigDecimal(3, order.getTaxRate());
        orderst.setString(4, order.getProductType());
        orderst.setBigDecimal(5, order.getArea());
        orderst.setBigDecimal(6, order.getCostPerSquareFoot());
        orderst.setBigDecimal(7, order.getLaborCostPerSquareFoot());
        orderst.setBigDecimal(8, order.getMaterialCost());
        orderst.setBigDecimal(9, order.getLaborCost());
        orderst.setBigDecimal(10, order.getTax());
        orderst.setBigDecimal(11, order.getTotal());
       

       
        int rowsAffected = orderst.executeUpdate();
        if (rowsAffected == 0) {
            throw new SQLException("Failed to insert order into the database.");
        }
    } catch (SQLException e) {
        e.printStackTrace(); 
    }
    }

    @Override
    public List<Order> getOrders () {
        
        
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM Orders";
        
        
        try (PreparedStatement orderst = connection.prepareStatement(query);
             ResultSet rs = orderst.executeQuery()) {

            while (rs.next()) {
               
                Order order = new Order(
                        rs.getInt("OrderNumber"),
                        rs.getString("CustomerName"),
                        rs.getString("State"),
                        rs.getBigDecimal("TaxRate"),
                        rs.getString("ProductType"),
                        rs.getBigDecimal("Area"),
                        rs.getBigDecimal("CostPerSquareFoot"),
                        rs.getBigDecimal("LaborCostPerSquareFoot")
                        
                ); 
                
                order.calculateCosts();
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
        
        
       
    }

    @Override
    public void updateOrder ( Order order ) {
         String query = "UPDATE Orders SET CustomerName = ?,"
                 + " State = ?, TaxRate = ?, ProductType = ?, Area = ?, CostPerSquareFoot = ?, LaborCostPerSquareFoot = ?, MaterialCost = ?, LaborCost = ?, Tax = ?, Total = ? WHERE OrderNumber = ?";
         
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, order.getCustomerName());
            pstmt.setString(2, order.getState());
            pstmt.setBigDecimal(3, order.getTaxRate());
            pstmt.setString(4, order.getProductType());
            pstmt.setBigDecimal(5, order.getArea());
            pstmt.setBigDecimal(6, order.getCostPerSquareFoot());
            pstmt.setBigDecimal(7, order.getLaborCostPerSquareFoot());
            pstmt.setBigDecimal(8, order.getMaterialCost());
            pstmt.setBigDecimal(9, order.getLaborCost());
            pstmt.setBigDecimal(10, order.getTax());
            pstmt.setBigDecimal(11, order.getTotal());
            pstmt.setInt(12, order.getOrderNumber());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
       
    }

    @Override
    public void deleteOrder ( int orderNumber ) {
 String query = "DELETE FROM Orders WHERE OrderNumber = ?"; 
 try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, orderNumber);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Order getOrder ( int orderNumber ) {
        
           Order order = null; 
           String query = "SELECT * FROM Orders WHERE OrderNumber = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, orderNumber);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    order = new Order(
                            rs.getInt("OrderNumber"),
                            rs.getString("CustomerName"),
                            rs.getString("State"),
                            rs.getBigDecimal("TaxRate"),
                            rs.getString("ProductType"),
                            rs.getBigDecimal("Area"),
                            rs.getBigDecimal("CostPerSquareFoot"),
                            rs.getBigDecimal("LaborCostPerSquareFoot")
                            
                    );

                
                    order.calculateCosts();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }


    
}
