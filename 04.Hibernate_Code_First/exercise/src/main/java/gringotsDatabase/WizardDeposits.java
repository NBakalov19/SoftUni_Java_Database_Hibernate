//package gringotsDatabase;
//
//
//import org.hibernate.annotations.Check;
//
//import javax.persistence.*;
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "wizard_deposits")
//public class WizardDeposits {
//
//    private int id;
//    private String firstName;
//    private String lastName;
//    private String notes;
//    private int age;
//    private String magicWandCreator;
//    private int magicWandSize;
//    private String depositGroup;
//    private LocalDateTime depositStartDate;
//    private BigDecimal depositAmount;
//    private BigDecimal depositInterest;
//    private BigDecimal depositCharge;
//    private LocalDateTime depositExpirationDate;
//    private boolean isDepositExpired;
//
//    public WizardDeposits() {
//    }
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    @Column(name = "first_name", length = 50)
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    @Column(name = "last_name", length = 60, nullable = false)
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    @Column(name = "notes",length = 1000)
//    public String getNotes() {
//        return notes;
//    }
//
//    public void setNotes(String notes) {
//        this.notes = notes;
//    }
//
//
//    @Column(name = "age", nullable = false)
//    @Check(constraints = "age > 0")
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    @Column(name = "magic_wand_creator", length = 100)
//    public String getMagicWandCreator() {
//        return magicWandCreator;
//    }
//
//    public void setMagicWandCreator(String magicWandCreator) {
//        this.magicWandCreator = magicWandCreator;
//    }
//
//    @Column(name = "magic_wand_size", columnDefinition = "SMALLINT")
//    public int getMagicWandSize() {
//        return magicWandSize;
//    }
//
//    public void setMagicWandSize(int magicWandSize) {
//        this.magicWandSize = magicWandSize;
//    }
//
//    @Column(name = "deposit_group", length = 20)
//    public String getDepositGroup() {
//        return depositGroup;
//    }
//
//    public void setDepositGroup(String depositGroup) {
//        this.depositGroup = depositGroup;
//    }
//
//    @Column(name = "deposit_start_date")
//    public LocalDateTime getDepositStartDate() {
//        return depositStartDate;
//    }
//
//    public void setDepositStartDate(LocalDateTime depositStartDate) {
//        this.depositStartDate = depositStartDate;
//    }
//
//    @Column(name = "deposit_amount")
//    public BigDecimal getDepositAmount() {
//        return depositAmount;
//    }
//
//    public void setDepositAmount(BigDecimal depositAmount) {
//        this.depositAmount = depositAmount;
//    }
//
//    @Column(name = "deposit_interest")
//    public BigDecimal getDepositInterest() {
//        return depositInterest;
//    }
//
//    public void setDepositInterest(BigDecimal depositInterest) {
//        this.depositInterest = depositInterest;
//    }
//
//    @Column(name = "deposit_charge")
//    public BigDecimal getDepositCharge() {
//        return depositCharge;
//    }
//
//    public void setDepositCharge(BigDecimal depositCharge) {
//        this.depositCharge = depositCharge;
//    }
//
//    @Column(name = "deposit_expiration_date")
//    public LocalDateTime getDepositExpirationDate() {
//        return depositExpirationDate;
//    }
//
//    public void setDepositExpirationDate(LocalDateTime depositExpirationDate) {
//        this.depositExpirationDate = depositExpirationDate;
//    }
//
//    @Column(name = "is_deposit_expired", columnDefinition = "CHAR(1)")
//    public boolean isDepositExpired() {
//        return isDepositExpired;
//    }
//
//    public void setDepositExpired(boolean depositExpired) {
//        isDepositExpired = depositExpired;
//    }
//}
