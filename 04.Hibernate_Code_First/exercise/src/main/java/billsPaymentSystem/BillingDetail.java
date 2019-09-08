//package billsPaymentSystem;
//
//import core.BaseEntity;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "billing_details")
//@DiscriminatorColumn(name = "billing_detail_type", discriminatorType = DiscriminatorType.STRING)
//public class BillingDetail extends BaseEntity {
//
//    private String number;
//    private User owner;
//
//    public BillingDetail() {
//    }
//
//    @Column(name = "billing_number")
//    public String getNumber() {
//        return number;
//    }
//
//    public void setNumber(String number) {
//        this.number = number;
//    }
//
//    @ManyToOne(targetEntity = User.class)
//    @JoinColumn(name = "owner_id", referencedColumnName = "id")
//    public User getOwner() {
//        return owner;
//    }
//
//    public void setOwner(User owner) {
//        this.owner = owner;
//    }
//}
