package exercises.socialnetwork.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Class representing the social network.
 * It corresponds to the graph.
 *
 * Created by guisil on 05/08/2016.
 */
public class SocialNetwork {

    private final Map<String, Person> persons;


    public SocialNetwork() {
        persons = new HashMap<>();
    }


    /**
     * Gets the map containing all the Persons in this social network.
     * @return map containing all the Persons
     */
    public Map<String, Person> getPersons() {
        return persons;
    }

    /**
     * Gets the size (number of Persons) of the social network.
     * @return size of the network
     */
    public int getSize() {
        return persons.size();
    }

    /**
     * Gets an existing Person from the social network.
     * @param name name of the Person
     * @return Person with the given name (null if not present in the network)
     */
    public Person getPerson(String name) {
        return persons.get(name);
    }

    /**
     * Gets a Person with the given name.
     * If there is no Person in the social network with that name,
     * a new Person is added and retrieved.
     * @param name name of the Person
     * @return Person with the given name
     */
    public Person addPersonIfNotPresent(String name) {
        Person person = persons.get(name);
        if (person == null) {
            person = new Person(name);
            persons.put(name, person);
        }
        return person;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SocialNetwork)) {
            return false;
        }
        SocialNetwork other = (SocialNetwork) obj;
        return other.getPersons().equals(persons);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + persons.hashCode();
        return result;
    }
}
