//package salesDatabase;
//
//import core.BaseEntity;
//
//import javax.persistence.Entity;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//import java.util.Set;
//
//@Entity
//@Table(name = "store_locations")
//public class StoreLocation extends BaseEntity {
//
//    private String locationName;
//    private Set<Sales> sales;
//
//    public String getLocationName() {
//        return locationName;
//    }
//
//    public void setLocationName(String locationName) {
//        this.locationName = locationName;
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
