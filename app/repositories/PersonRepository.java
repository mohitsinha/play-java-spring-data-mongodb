package repositories;

import models.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author Mohit Sinha
 */
public interface PersonRepository extends MongoRepository<Person, String> {
    List<Person> findAllByNameAndAgeGreaterThanEqualOrderByAgeAsc(String name, int age);
}
