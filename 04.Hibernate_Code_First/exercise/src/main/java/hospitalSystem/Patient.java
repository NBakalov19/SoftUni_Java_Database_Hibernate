//package hospitalSystem;
//
//import core.BaseEntity;
//
//import javax.persistence.*;
//import java.time.LocalDate;
//import java.util.Set;
//
//@Entity
//@Table(name = "patients")
//public class Patient extends BaseEntity {
//
//    private String firstName;
//    private String lastName;
//    private String address;
//    private String email;
//    private LocalDate birthDate;
//    private byte[] picture;
//    private boolean haveInsurance;
//    private Set<Visitation> visitations;
//    private Set<Diagnose> diagnoses;
//    private Set<Medicament> medicament;
//
//    public Patient() {
//    }
//
//
//    @Column(name = "first_name", length = 50, nullable = false)
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
//    @Column(name = "address", nullable = false)
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    @Column(name = "email", length = 60, unique = true)
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    @Column(name = "birth_date", nullable = false)
//    public LocalDate getBirthDate() {
//        return birthDate;
//    }
//
//    public void setBirthDate(LocalDate birthDate) {
//        this.birthDate = birthDate;
//    }
//
//    @Column(name = "picture")
//    public byte[] getPicture() {
//        return picture;
//    }
//
//    public void setPicture(byte[] picture) {
//        this.picture = picture;
//    }
//
//    @Column(name = "medical_insurance")
//    public boolean isHaveInsurance() {
//        return haveInsurance;
//    }
//
//    public void setHaveInsurance(boolean haveInsurance) {
//        this.haveInsurance = haveInsurance;
//    }
//
//    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
//    public Set<Visitation> getVisitations() {
//        return visitations;
//    }
//
//    public void setVisitations(Set<Visitation> visitations) {
//        this.visitations = visitations;
//    }
//
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "patients_diagnoses",
//            joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "diagnose_id", referencedColumnName = "id"))
//    public Set<Diagnose> getDiagnoses() {
//        return diagnoses;
//    }
//
//    public void setDiagnoses(Set<Diagnose> diagnoses) {
//        this.diagnoses = diagnoses;
//    }
//
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "patients_medicaments",
//            joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "medicament_id", referencedColumnName = "id"))
//    public Set<Medicament> getMedicament() {
//        return medicament;
//    }
//
//    public void setMedicament(Set<Medicament> prescription) {
//        this.medicament = prescription;
//    }
//}
