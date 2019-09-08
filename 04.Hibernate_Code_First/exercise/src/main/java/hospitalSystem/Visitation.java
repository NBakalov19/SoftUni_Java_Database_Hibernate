//package hospitalSystem;
//
//import core.BaseEntity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.ManyToOne;
//import java.time.LocalDate;
//
//@Entity(name = "visitations")
//public class Visitation extends BaseEntity {
//
//    private LocalDate date;
//    private String comments;
//    private Patient patient;
//
//    public Visitation() {
//    }
//
//    @Column(name = "visitation_date")
//    public LocalDate getDate() {
//        return date;
//    }
//
//    public void setDate(LocalDate date) {
//        this.date = date;
//    }
//
//    @Column(name = "comment")
//    public String getComments() {
//        return comments;
//    }
//
//    public void setComments(String comment) {
//        this.comments = comment;
//    }
//
//    @ManyToOne(targetEntity = Patient.class, fetch = FetchType.LAZY)
//    public Patient getPatient() {
//        return patient;
//    }
//
//    public void setPatient(Patient patient) {
//        this.patient = patient;
//    }
//}