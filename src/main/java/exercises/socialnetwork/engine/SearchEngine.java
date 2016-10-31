package exercises.socialnetwork.engine;

import exercises.socialnetwork.model.Person;
import exercises.socialnetwork.model.SocialNetwork;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Class responsible for searches in the social network.
 *
 * Created by guisil on 05/08/2016.
 */
public class SearchEngine {

    /**
     * Finds the shortest path between two given persons in the social network.
     * @param network The social network to search
     * @param startName The starting point of the path to search
     * @param endName The ending point of the path to search
     * @return The distance between the two given persons in the given social network (-1 if no path was found)
     */
    public int findShortestPathDistance(SocialNetwork network, String startName, String endName) {

        // This algorithm is using a breadth-first approach,
        //  so it starts in the first node, then all its direct friends, and so on, one level at a time.

        // An improvement to the initial algorithm was made by having
        //  a second search starting from the other side (bidirectional search).
        // This way there are much less ramifications of the first search.
        // If there is a connection between the two given nodes,
        //  then the result will be achieved without the need to visit as many nodes as before.

        // When there is no connection between the nodes, then more of them are visited in this process.

        Person start = getPersonInNetwork(network, startName);
        Person end = getPersonInNetwork(network, endName);

        if (startName.equals(endName)) {
            return 0;
        }

        setStartAndEndSearchPoints(start, end);

        // initialise queues for each of the searches
        Queue<Person> startQueue = initialiseQueue(start);
        Queue<Person> endQueue = initialiseQueue(end);

        while(!startQueue.isEmpty() && !endQueue.isEmpty()) {

            int foundDistance;

            // from the starting point
            foundDistance = searchFriendsOfNextPersonInQueue(startQueue, true);
            if (foundDistance != -1) {
                return foundDistance;
            }

            // from the ending point
            foundDistance = searchFriendsOfNextPersonInQueue(endQueue, false);
            if (foundDistance != -1) {
                return foundDistance;
            }
        }
        // not found
        return -1;
    }


    private Person getPersonInNetwork(SocialNetwork network, String name) {
        Person person = network.getPerson(name);
        if (person == null) {
            throw new IllegalArgumentException(name + " does not exist in the social network");
        }
        return person;
    }

    private void setStartAndEndSearchPoints(Person start, Person end) {
        // set start and end points
        start.setAsStartPoint();
        end.setAsEndPoint();
    }

    private Queue<Person> initialiseQueue(Person firstPerson) {
        Queue<Person> queue = new LinkedList<>();
        queue.add(firstPerson);
        return queue;
    }

    private int searchFriendsOfNextPersonInQueue(Queue<Person> queue, boolean fromStartPoint) {
        Person currentPerson = queue.remove();
        for (Person friend : currentPerson.getFriends()) {
            if (getDistance(friend, fromStartPoint) != -1) {
                continue;
            }
            updateFriendDistance(currentPerson, friend, fromStartPoint);
            if (getDistance(friend, !fromStartPoint) != -1) { // visited by other search
                return getDistanceSum(friend);
            }
            queue.add(friend);
        }
        // not found yet
        return -1;
    }

    private int getDistance(Person person, boolean fromStartPoint) {
        if (fromStartPoint) {
            return person.getDistanceFromStart();
        } else {
            return person.getDistanceFromEnd();
        }
    }

    private int getDistanceSum(Person person) {
        return person.getDistanceFromStart() + person.getDistanceFromEnd();
    }

    private void updateFriendDistance(Person person, Person friend, boolean fromStartPoint) {
        if (fromStartPoint) {
            friend.setDistanceFromStart(person.getDistanceFromStart() + 1);
        } else {
            friend.setDistanceFromEnd(person.getDistanceFromEnd() + 1);
        }
    }
}
