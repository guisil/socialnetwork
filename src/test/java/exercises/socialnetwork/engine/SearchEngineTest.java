package exercises.socialnetwork.engine;

import org.junit.Before;
import org.junit.Test;
import exercises.socialnetwork.model.Person;
import exercises.socialnetwork.model.SocialNetwork;

import static org.junit.Assert.*;

/**
 * Test class for SearchEngine.
 *
 * Created by guisil on 05/08/2016.
 */
public class SearchEngineTest {

    private SearchEngine searchEngine;

    @Before
    public void setUp() throws Exception {
        searchEngine = new SearchEngine();
    }

    @Test
    public void shouldFindShortestPathDistance_0() throws Exception {
        SocialNetwork network = completeTinyNetwork();
        String source = "HAN_SOLO";
        String destination = "HAN_SOLO";
        int expectedDistance = 0;

        assertEquals("Distance between " + source + " and " + destination + " different from expected",
                expectedDistance, searchEngine.findShortestPathDistance(network, source, destination));
    }

    @Test
    public void shouldFindShortestPathDistance_1() throws Exception {
        SocialNetwork network = completeTinyNetwork();
        String source = "HAN_SOLO";
        String destination = "LEIA_ORGANA";
        int expectedDistance = 1;

        assertEquals("Distance between " + source + " and " + destination + " different from expected",
                expectedDistance, searchEngine.findShortestPathDistance(network, source, destination));
    }

    @Test
    public void shouldFindShortestPathDistance_2() throws Exception {
        SocialNetwork network = completeTinyNetwork();
        String source = "HAN_SOLO";
        String destination = "YODA";
        int expectedDistance = 2;

        assertEquals("Distance between " + source + " and " + destination + " different from expected",
                expectedDistance, searchEngine.findShortestPathDistance(network, source, destination));
    }

    @Test
    public void shouldFailForNonExistingPerson() throws Exception {
        SocialNetwork network = completeTinyNetwork();
        String source = "HAN_SOLO";
        String destination = "FRODO_BAGGINS";

        try {
            searchEngine.findShortestPathDistance(network, source, destination);
            fail("should have thrown an exception");
        } catch(IllegalArgumentException ex) {
            assertEquals(destination + " does not exist in the social network", ex.getMessage());
        }
    }

    @Test
    public void shouldNotFindShortestPathDistance() throws Exception {
        SocialNetwork network = completeTinyNetwork();
        String source = "HAN_SOLO";
        String destination = "ARTHUR_DENT";
        int expectedDistance = -1;

        assertEquals("Distance between " + source + " and " + destination + " different from expected",
                expectedDistance, searchEngine.findShortestPathDistance(network, source, destination));
    }


    private SocialNetwork completeTinyNetwork() {
        SocialNetwork network = new SocialNetwork();

        Person hanSolo = network.addPersonIfNotPresent("HAN_SOLO");
        Person lukeSkywalker = network.addPersonIfNotPresent("LUKE_SKYWALKER");
        Person leiaOrgana = network.addPersonIfNotPresent("LEIA_ORGANA");
        Person chewbacca = network.addPersonIfNotPresent("CHEWBACCA");
        Person yoda = network.addPersonIfNotPresent("YODA");
        Person arthurDent = network.addPersonIfNotPresent("ARTHUR_DENT");
        Person fordPrefect = network.addPersonIfNotPresent("FORD_PREFECT");

        hanSolo.addFriend(lukeSkywalker);
        hanSolo.addFriend(leiaOrgana);
        hanSolo.addFriend(chewbacca);

        lukeSkywalker.addFriend(hanSolo);
        lukeSkywalker.addFriend(leiaOrgana);
        lukeSkywalker.addFriend(chewbacca);
        lukeSkywalker.addFriend(yoda);

        leiaOrgana.addFriend(hanSolo);
        leiaOrgana.addFriend(lukeSkywalker);
        leiaOrgana.addFriend(chewbacca);

        chewbacca.addFriend(hanSolo);
        chewbacca.addFriend(leiaOrgana);
        chewbacca.addFriend(lukeSkywalker);

        yoda.addFriend(lukeSkywalker);

        arthurDent.addFriend(fordPrefect);
        fordPrefect.addFriend(arthurDent);

        return network;
    }
}