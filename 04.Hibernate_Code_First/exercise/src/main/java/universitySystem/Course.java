//package universitySystem;
//
//import core.BaseEntity;
//
//import javax.persistence.*;
//import java.time.LocalDate;
//import java.util.Set;
//
//@Entity
//@Table(name = "courses")
//public class Course extends BaseEntity {
//    private String name;
//    private String description;
//    private LocalDate startDate;
//    private LocalDate endDate;
//    private int credits;
//    private Teacher teacher;
//    private Set<Student> students;
//
//    public Course() {
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
//    @Column(name = "description")
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    @Column(name = "start_date")
//    public LocalDate getStartDate() {
//        return startDate;
//    }
//
//    public void setStartDate(LocalDate startDate) {
//        this.startDate = startDate;
//    }
//
//    @Column(name = "end_date")
//    public LocalDate getEndDate() {
//        return endDate;
//    }
//
//    public void setEndDate(LocalDate endDate) {
//        this.endDate = endDate;
//    }
//
//    @Column(name = "credits")
//    public int getCredits() {
//        return credits;
//    }
//
//    public void setCredits(int credits) {
//        this.credits = credits;
//    }
//
//    @ManyToOne(targetEntity = Teacher.class)
//    public Teacher getTeacher() {
//        return teacher;
//    }
//
//    public void setTeacher(Teacher teacher) {
//        this.teacher = teacher;
//    }
//
//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinTable(name = "courses_students",
//    joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
//    inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"))
//    public Set<Student> getStudents() {
//        return students;
//    }
//
//    public void setStudents(Set<Student> students) {
//        this.students = students;
//    }
//}
