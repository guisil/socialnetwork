package exercises.socialnetwork.engine;

import exercises.socialnetwork.model.Person;
import exercises.socialnetwork.model.SocialNetwork;

import java.io.*;

/**
 * Class responsible for loading the social network.
 *
 * Created by guisil on 05/08/2016.
 */
public class Loader {

    /**
     * Loads the social network from the given input stream.
     * @param stream InputStream of the social network file
     * @return SocialNetwork object containing the loaded social network
     */
    public SocialNetwork loadSocialNetwork(InputStream stream) throws IOException {

        SocialNetwork network = new SocialNetwork();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(stream))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] names = line.split(",");
                if (names.length != 2) {
                    System.out.println("Skipping line with " + names.length + " names");
                    continue;
                }

                Person person1 = network.addPersonIfNotPresent(names[0]);
                Person person2 = network.addPersonIfNotPresent(names[1]);
                person1.addFriend(person2);
                person2.addFriend(person1);
            }
        }

        return network;
    }
}
