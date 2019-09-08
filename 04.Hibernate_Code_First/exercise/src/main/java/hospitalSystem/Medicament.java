//package hospitalSystem;
//
//import core.BaseEntity;
//
//import javax.persistence.Entity;
//import javax.persistence.ManyToMany;
//import java.util.Set;
//
//@Entity(name = "medicaments")
//public class Medicament extends BaseEntity {
//
//    private String name;
//    private Set<Patient> patients;
//
//    public Medicament() {
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @ManyToMany(mappedBy = "medicament", targetEntity = Patient.class)
//    public Set<Patient> getPatients() {
//        return patients;
//    }
//
//    public void setPatients(Set<Patient> patients) {
//        this.patients = patients;
//    }
//}
