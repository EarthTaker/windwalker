package edu.snhu.garrison.windwalker.Services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Service Class - Handles user login by loading user credentials from the XML file. 
 * 
 * Implements the UserDetailsService interface to integrate with Spring Security's authentication framework.
 */
@Service
public class XmlUserDetailsService implements UserDetailsService {

    // Logger to Generate Logs instead of only Console feedback.
    private static final Logger logger = LoggerFactory.getLogger(UserAuthenticationService.class);

    /**
     * Loads user details from an XML file based on the provided username.
     */
    @Override
    public UserDetails loadUserByUsername(String username) {

        // Grab the list of users from the XML
        NodeList users = getUsersFromDocument();

        // Iterate through users to find a matching username
        // Until the total number of users found within the list is reached, iterate through each user element.
        for (int i = 0; i < users.getLength(); i++) {
            Element userElement = (Element) users.item(i);
            String xmlUserName = userElement.getElementsByTagName("username").item(0).getTextContent();

            // If the username matches, create and return a UserDetails object
            if (xmlUserName.equalsIgnoreCase(username)) {

                //Check Password
                String encodedPassword = userElement.getElementsByTagName("password").item(0).getTextContent();

                //Return a UserDetails object - Holds the encoded password pulled from the XML file for comparison against the login attempt.
                return User.withUsername(xmlUserName)
                        .password(encodedPassword)
                        .roles("USER")
                        .build();
            }
        }

        // If no matching user is found, throw an exception.
        throw new UsernameNotFoundException("User '" + username + "' not found.");
    }

    /**
     * Helper Method that generates a list of users pulled from the XML file.
     *
     * @return A List of Users pulled from the xml file.
     */
    private NodeList getUsersFromDocument() {
        try {
            // Build the XML Document for use throughout the class.
            File file = new File("windwalker/src/main/resources/users.xml");

            // Create a DocumentBuilder, use builder to parse XML user authentication file.
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);

            // Target users.xml file, filter through DOM tree to find user elements.
            NodeList users = document.getElementsByTagName("user");
            return users;

            // FileNotFoundException - In case the XML file cannot be opened.
        } catch (FileNotFoundException e) {
            logger.error("Authentication startup failed: users.xml not found.", e);
            throw new IllegalStateException("Missing users.xml", e);

            // SAXException - In case the XML file does not adhere to the expected format.
        } catch (SAXException e) {
            logger.error("Authentication startup failed: users.xml is not valid XML.", e);
            throw new IllegalStateException("Malformed users.xml", e);

            // ParserConfigurationException - In case the builder tool fails to create a
            // document.
        } catch (ParserConfigurationException e) {
            logger.error("Authentication startup failed: XML parser misconfigured.", e);
            throw new IllegalStateException("XML parser configuration error", e);

            // IOException - In case the file is not able to be read.
        } catch (IOException e) {
            logger.error("Authentication startup failed: I/O error reading users.xml.", e);
            throw new IllegalStateException("I/O error loading users.xml", e);
        }

    }

}
