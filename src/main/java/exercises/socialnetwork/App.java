package exercises.socialnetwork;

import exercises.socialnetwork.engine.Loader;
import exercises.socialnetwork.engine.SearchEngine;
import exercises.socialnetwork.model.SocialNetwork;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Main class of the application.
 *
 * Created by guisil on 05/08/2016.
 */
public class App {

    /**
     * Main method for the execution of the application.
     * @param args either zero or 3 arguments
     *       (file path for the social network, start and end point for the distance calculation);
     *       if no arguments are provided, default values will be used
     */
    public static void main(String[] args) {

        InputStream socialNetworkFileStream;
        String startPoint;
        String endPoint;
        SocialNetwork loadedNetwork;

        try {
            if (args.length != 3) { // wrong number of arguments
                System.out.println("3 arguments needed: path of the file containing the social network, start and end user for the distance calculation.");
                return;
            } else { // all the necessary arguments are present
                socialNetworkFileStream = new FileInputStream(new File(args[0]));
                startPoint = args[1];
                endPoint = args[2];
            }

            // load the social network (graph)
            Loader loader = new Loader();
            loadedNetwork = loader.loadSocialNetwork(socialNetworkFileStream);
        } catch (IOException e) {
            System.out.println("Error loading the social network: " + e.getMessage());
            return;
        }

        System.out.println("Size of the loaded network: " + loadedNetwork.getSize());

        int distance;
        try {
            // determine the distance between the two given persons (nodes)
            SearchEngine engine = new SearchEngine();
            distance = engine.findShortestPathDistance(loadedNetwork, startPoint, endPoint);
        } catch(IllegalArgumentException ex) {
            System.out.println("Illegal argument: " + ex.getMessage());
            return;
        }

        if (distance < 0) {
            System.out.println("A path between " + startPoint + " and " + endPoint + " was not found");
        } else {
            System.out.println("The distance between " + startPoint + " and " + endPoint + " is " + distance);
        }
    }
}
