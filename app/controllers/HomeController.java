package controllers;

import com.google.inject.Inject;
import models.Person;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.PersonRepository;


/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    @Inject
    private PersonRepository personRepository;

    public Result index() {
        Person person = new Person();
        person.setName("John Doe");
        person.setAge(25);
        personRepository.save(person);
        return ok();
    }

}
