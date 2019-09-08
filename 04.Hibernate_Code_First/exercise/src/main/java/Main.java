import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
  public static void main(String[] args) {
    EntityManagerFactory entityManagerFactory = Persistence
            .createEntityManagerFactory("football");//Check persistence file

    //Remove comments to change the task
    EntityManager entityManager = entityManagerFactory.createEntityManager();

  }
}
