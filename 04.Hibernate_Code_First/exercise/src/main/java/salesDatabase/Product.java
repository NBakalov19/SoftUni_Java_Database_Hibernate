//package salesDatabase;
//
//import core.BaseEntity;
//
//import javax.persistence.*;
//import java.math.BigDecimal;
//import java.util.Set;
//
//@Entity
//@Table(name = "products")
//public class Product extends BaseEntity {
//
//    private String name;
//    private int quantity;
//    private BigDecimal price;
//    private Set<Sales> sales;
//
//    public Product() {
//    }
//
//    @Column(name = "name")
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @Column(name = "quantity")
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }
//
//    @Column(name = "price")
//    public BigDecimal getPrice() {
//        return price;
//    }
//
//    public void setPrice(BigDecimal price) {
//        this.price = price;
//    }
//
//    @OneToMany(targetEntity = Sales.class, mappedBy = "product")
//    public Set<Sales> getSales() {
//        return sales;
//    }
//
//    public void setSales(Set<Sales> sales) {
//        this.sales = sales;
//    }
//}
