package exercises.socialnetwork.engine;

import org.junit.Before;
import org.junit.Test;
import exercises.socialnetwork.model.Person;
import exercises.socialnetwork.model.SocialNetwork;

import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * Test class for Loader.
 *
 * Created by guisil on 05/08/2016.
 */
public class LoaderTest {

    private Loader loader;

    @Before
    public void setUp() throws Exception {
        loader = new Loader();
    }


    @Test
    public void shouldLoadSocialNetwork() throws Exception {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("TinySocialNetwork.txt");
        SocialNetwork loaded = loader.loadSocialNetwork(stream);
        assertEquals("Loaded social network different from expected", completeTinyNetwork(), loaded);
    }

    @Test
    public void shouldSkipSomeLines() throws Exception {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("TinySocialNetwork_Errors.txt");
        SocialNetwork loaded = loader.loadSocialNetwork(stream);
        assertEquals("Loaded social network different from expected", incompleteTinyNetwork(), loaded);
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

    private SocialNetwork incompleteTinyNetwork() {
        SocialNetwork network = new SocialNetwork();

        Person hanSolo = network.addPersonIfNotPresent("HAN_SOLO");
        Person lukeSkywalker = network.addPersonIfNotPresent("LUKE_SKYWALKER");
        Person leiaOrgana = network.addPersonIfNotPresent("LEIA_ORGANA");
        Person chewbacca = network.addPersonIfNotPresent("CHEWBACCA");
        // skip YODA
        Person arthurDent = network.addPersonIfNotPresent("ARTHUR_DENT");
        Person fordPrefect = network.addPersonIfNotPresent("FORD_PREFECT");

        hanSolo.addFriend(lukeSkywalker);
        // skip LEIA
        hanSolo.addFriend(chewbacca);

        lukeSkywalker.addFriend(hanSolo);
        lukeSkywalker.addFriend(leiaOrgana);
        // skip CHEWBACCA

        // skip SOLO
        leiaOrgana.addFriend(lukeSkywalker);
        leiaOrgana.addFriend(chewbacca);

        chewbacca.addFriend(hanSolo);
        chewbacca.addFriend(leiaOrgana);
        // skip LUKE

        arthurDent.addFriend(fordPrefect);
        fordPrefect.addFriend(arthurDent);

        return network;
    }
}