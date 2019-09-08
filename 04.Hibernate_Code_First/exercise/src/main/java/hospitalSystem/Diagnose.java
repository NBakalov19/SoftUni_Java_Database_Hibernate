//package hospitalSystem;
//
//import core.BaseEntity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.ManyToMany;
//import java.util.Set;
//
//@Entity(name = "diagnoses")
//public class Diagnose extends BaseEntity {
//    private String name;
//    private String comments;
//    private Set<Patient> patients;
//
//    public Diagnose() {
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
//    @Column(name = "comments")
//    public String getComments() {
//        return comments;
//    }
//
//    public void setComments(String comments) {
//        this.comments = comments;
//    }
//
//    @ManyToMany(mappedBy = "diagnoses",targetEntity = Patient.class)
//    public Set<Patient> getPatients() {
//        return patients;
//    }
//
//    public void setPatients(Set<Patient> patients) {
//        this.patients = patients;
//    }
//}
