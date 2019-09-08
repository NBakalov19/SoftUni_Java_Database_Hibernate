package core;

import entities.Address;
import entities.Employee;
import entities.Project;
import entities.Town;
import interfaces.OutputWriter;
import io.InputReaderImpl;
import io.OutputWriterImpl;

import javax.persistence.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class Executor {

  private EntityManager entityManager;
  private InputReaderImpl reader;
  private OutputWriter writer;

  public Executor() {
    this.setEntityManager();
    this.reader = new InputReaderImpl();
    this.writer = new OutputWriterImpl();
  }

  private void setEntityManager() {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
    this.entityManager = entityManagerFactory.createEntityManager();
  }

  public void execute(int command) throws IOException {
    while (true) {
      switch (command) {
        case 0:
          System.exit(0);
          break;
        case 2:
          this.removeObjects();
          break;
        case 3:
          this.containsEmployee();
          break;
        case 4:
          this.employeesWithSalaryOver();
          break;
        case 5:
          this.employeesFromDepartment();
          break;
        case 6:
          this.addAddressAndUpdateEmployee();
          break;
        case 7:
          this.addressesWithEmployeeCount();
          break;
        case 8:
          this.getEmployeeWithProject();
          break;
        case 9:
          this.findLastTenProjects();
          break;
        case 10:
          this.increaseSalaries();
          break;
        case 11:
          this.removeTowns();
          break;
        case 12:
          this.findEmployeeByFirstNameWithPattern();
          break;
        case 13:
          this.findEmployeesMaximumSalary();
          break;
        default:
          this.writer.writeLine("No such task.");
          break;
      }
    }

  }

  //Task 2
  private void removeObjects() {
    this.entityManager.getTransaction().begin();

    List<Town> towns = this.entityManager.createQuery("FROM Town", Town.class).getResultList();

    towns.forEach(town -> {
      if (town.getName().length() > 5) {
        this.entityManager.remove(town);
      } else {
        town.setName(town.getName().toLowerCase());
      }
    });

    this.entityManager.flush();
    this.entityManager.getTransaction().commit();
  }

  //Task 3
  private void containsEmployee() throws IOException {
    String name = this.reader.readLine();

    this.entityManager.getTransaction().begin();

    try {

      Employee employee =
              this.entityManager
                      .createQuery(
                              "FROM Employee WHERE CONCAT(firstName, ' ', lastName) = :name",
                              Employee.class)
                      .setParameter("name", name)
                      .getSingleResult();

      this.writer.writeLine("Yes");
    } catch (NoResultException e) {
      this.writer.writeLine("No");
    }
    this.entityManager.getTransaction().commit();
  }

  //Task 4
  private void employeesWithSalaryOver() {
    this.entityManager.getTransaction().begin();

    List<Employee> employees = this.entityManager
            .createQuery("FROM Employee WHERE salary > 50000",
                    Employee.class)
            .getResultList();

    employees.forEach(employee ->
            this.writer.writeLine(employee.getFirstName()));

    this.entityManager.getTransaction().commit();
  }

  //Task 5
  private void employeesFromDepartment() {
    this.entityManager.getTransaction().begin();

    List<Employee> employees = this.entityManager
            .createQuery(
                    "SELECT e FROM Employee e " +
                            "WHERE e.department.name = 'Research and Development'" +
                            "ORDER BY e.salary, e.id", Employee.class)
            .getResultList();

    for (Employee employee : employees) {
      this.writer.writeLine(
              String.format("%s %s from %s - $%s",
                      employee.getFirstName(),
                      employee.getLastName(),
                      employee.getDepartment().getName(),
                      employee.getSalary())
      );
    }

    this.entityManager.getTransaction().commit();
  }

  //Task 6
  private void addAddressAndUpdateEmployee() throws IOException {
    String lastName = this.reader.readLine();
    this.entityManager.getTransaction().begin();

    Town town = this.entityManager
            .createQuery(
                    "FROM Town WHERE id = 32",
                    Town.class)
            .getSingleResult();


    Address address = new Address();
    address.setText("Vitoshka 15");
    address.setTown(town);

    if (!this.entityManager.contains(address)) {
      this.entityManager.persist(address);
    }

    Employee employeeToUpdate = this.entityManager
            .createQuery(
                    "FROM Employee WHERE lastName = :lastName",
                    Employee.class)
            .setParameter("lastName", lastName)
            .getSingleResult();

    if (this.entityManager.contains(employeeToUpdate)) {
      Query updateEmployeeQuery = this.entityManager
              .createQuery(
                      "UPDATE Employee SET address = :address WHERE lastName =  :lastName")
              .setParameter("address", address)
              .setParameter("lastName", lastName);

      updateEmployeeQuery.executeUpdate();
    }
    this.entityManager.getTransaction().commit();
  }

  //Task 7
  private void addressesWithEmployeeCount() throws IOException {
    this.entityManager.getTransaction().begin();

    List<Address> employees = this.entityManager
            .createQuery("FROM Address ORDER BY size(employees) DESC, town.id",
                    Address.class)
            .setMaxResults(10)
            .getResultList();

    employees.forEach(e ->
            System.out.println(String.format("%s, %s - %s employees",
                    e.getText(), e.getTown().getName(), e.getEmployees().size())));

    this.entityManager.getTransaction().commit();
  }

  //Task 8
  private void getEmployeeWithProject() throws IOException {
    this.entityManager.getTransaction().begin();
    StringBuilder output = new StringBuilder();

    int employeeId = Integer.parseInt(this.reader.readLine());

    Employee employee = this.entityManager
            .createQuery(
                    "FROM Employee WHERE id = :employeeId"
                    , Employee.class)
            .setParameter("employeeId", employeeId)
            .getSingleResult();

    output.append(String.format("%s %s - %s",
            employee.getFirstName(),
            employee.getLastName(),
            employee.getJobTitle())).append(System.lineSeparator());

    Set<Project> projects = employee.getProjects();

    for (Project project : projects) {
      output.append(String.format("\t%s%n", project.getName()));
    }

    this.writer.writeLine(output.toString().trim());
    this.entityManager.getTransaction().commit();
  }

  //Task 9
  private void findLastTenProjects() {
    this.entityManager.getTransaction().begin();


    List<Project> lastTenProjects = this.entityManager
            .createQuery(
                    "FROM Project ORDER BY DATEDIFF(current_date, startDate) ASC"
                    , Project.class)
            .setMaxResults(10)
            .getResultList();

    lastTenProjects.stream()
            .sorted(Comparator.comparing(Project::getName)
            ).forEach(
            project -> this.writer.writeLine(
                    String.format("Project name: %s%n" +
                                    "\tProject Description: %s%n" +
                                    "\tProject Start Date: %s%n" +
                                    "\tProject End Date: %s",
                            project.getName(),
                            project.getDescription(),
                            project.getStartDate(),
                            project.getEndDate())));

    this.entityManager.getTransaction().commit();
  }

  //Task 10
  private void increaseSalaries() {
    this.entityManager.getTransaction().begin();


    List<String> departmentsToUpdate = new ArrayList<>();
    departmentsToUpdate.add("Engineering");
    departmentsToUpdate.add("Tool Design");
    departmentsToUpdate.add("Marketing");
    departmentsToUpdate.add("Information Services");

    Query updateSalaries = this.entityManager
            .createNativeQuery("UPDATE employees e\n" +
                    "JOIN departments d on e.department_id = d.department_id\n" +
                    "SET salary = salary * 1.12\n" +
                    "WHERE d.name IN ('Engineering', 'Tool Design', 'Marketing', 'Information Services');");

    updateSalaries.executeUpdate();

    List<Employee> employees = this.entityManager
            .createQuery(
                    "FROM Employee WHERE department.name IN (:departmentsToUpdate)",
                    Employee.class)
            .setParameter("departmentsToUpdate", departmentsToUpdate)
            .getResultList();

    employees.forEach(
            employee -> this.writer.writeLine(
                    String.format("%s %s ($%s)",
                            employee.getFirstName(),
                            employee.getLastName(),
                            employee.getSalary())
            )
    );
    this.entityManager.getTransaction().commit();
  }

  //Task 11
  private void removeTowns() throws IOException {
    this.entityManager.getTransaction().begin();

    String townToDelete = this.reader.readLine();

    Town town = this.entityManager.createQuery("FROM Town WHERE name = :name", Town.class)
            .setParameter("name", townToDelete)
            .getSingleResult();

    if (this.entityManager.contains(town)) {

      long countOfAddresses = this.entityManager
              .createQuery("FROM Address WHERE town.name = :name")
              .setParameter("name", townToDelete)
              .getResultList()
              .size();

      this.entityManager
              .createNativeQuery("DELETE a\n" +
                      "FROM addresses AS a\n" +
                      "JOIN towns As t ON a.town_id = t.town_id\n" +
                      "WHERE t.name = ?;")
              .setParameter(1, townToDelete)
              .executeUpdate();//Check

      this.entityManager
              .createQuery("DELETE Town WHERE name = :name")
              .setParameter("name", townToDelete)
              .executeUpdate();

      if (countOfAddresses < 2) {
        this.writer.writeLine(
                String.format("%d address in %s deleted", countOfAddresses, townToDelete));
      } else {
        this.writer.writeLine(
                String.format("%d addresses in %s deleted", countOfAddresses, townToDelete));
      }
    }
    this.entityManager.getTransaction().commit();
  }

  //Task 12
  private void findEmployeeByFirstNameWithPattern() throws IOException {
    this.entityManager.getTransaction().begin();

    String pattern = this.reader.readLine();
    StringBuilder output = new StringBuilder();

    List<Employee> employees = this.entityManager
            .createQuery(
                    "FROM Employee WHERE firstName LIKE :pattern"
                    , Employee.class)
            .setParameter("pattern", pattern + "%")
            .getResultList();

    for (Employee employee : employees) {
      output.append(String.format("%s %s - %s - ($%s)%n",
              employee.getFirstName(),
              employee.getLastName(),
              employee.getJobTitle(),
              employee.getSalary()));
    }

    this.writer.write(output.toString().trim());
    this.entityManager.getTransaction().commit();
  }

  //Task 13
  private void findEmployeesMaximumSalary() {
    this.entityManager.getTransaction().begin();

    StringBuilder output = new StringBuilder();

    List<Object[]> departments = this.entityManager
            .createQuery(
                    "SELECT e.department.name,MAX(e.salary) AS m " +
                            "FROM Employee e " +
                            "GROUP BY (e.department.name) " +
                            "HAVING MAX(e.salary) NOT BETWEEN 30000 AND 70000")
            .getResultList();

    for (Object[] department : departments) {
      output.append(
              String.format("%s - %s%n",
                      department[0],
                      department[1])
      );
    }

    this.writer.writeLine(output.toString().trim());

    this.entityManager.getTransaction().commit();
  }
}
