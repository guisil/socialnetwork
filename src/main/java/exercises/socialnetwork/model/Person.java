package exercises.socialnetwork.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Class representing a person in the social network.
 * It corresponds to a vertex in the graph.
 *
 * Created by guisil on 05/08/2016.
 */
public class Person {

    private final String name;
    private final List<Person> friends;

    private int distanceFromStart = -1;
    private int distanceFromEnd = -1;


    Person(String name) {
        this.name = name;
        friends = new LinkedList<>();
    }

    /**
     * Gets the name of the person.
     * @return name of the person
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the list of friends.
     * @return list of friends
     */
    public List<Person> getFriends() {
        return friends;
    }

    /**
     * Adds a friend to the list of friends.
     * @param friend Person to add to the list of friends
     */
    public void addFriend(Person friend) {
        friends.add(friend);
    }

    /**
     * Gets the distance from the starting point of the current search.
     * @return distance from the starting point
     */
    public int getDistanceFromStart() {
        return distanceFromStart;
    }

    /**
     * Sets the distance from the starting point of the current search.
     * @param distance distance from the starting point
     */
    public void setDistanceFromStart(int distance) {
        this.distanceFromStart = distance;
    }

    /**
     * Sets this Person as the starting point of the current search
     * (distance to the starting point is set as zero).
     */
    public void setAsStartPoint() {
        this.distanceFromStart = 0;
    }

    /**
     * Gets the distance from the ending point of the current search.
     * @return distance from the ending point
     */
    public int getDistanceFromEnd() {
        return distanceFromEnd;
    }

    /**
     * Sets the distance from the ending point of the current search.
     * @param distance distance from the ending point
     */
    public void setDistanceFromEnd(int distance) {
        this.distanceFromEnd = distance;
    }

    /**
     * Sets this Person as the ending point of the current search
     * (distance to the ending point is set as zero).
     */
    public void setAsEndPoint() {
        this.distanceFromEnd = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Person)) {
            return false;
        }
        Person other = (Person) obj;
        return other.getName().equals(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        return result;
    }
}
