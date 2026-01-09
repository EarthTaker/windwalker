package edu.snhu.garrison.windwalker.Services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import edu.snhu.garrison.windwalker.Model.User;

/**
 * Service Class - Handles Authenticating & Registering Users.
 */
@Service
public class UserAuthenticationService {

    // Logger to Generate Logs instead of only Console feedback.
    private static final Logger logger = LoggerFactory.getLogger(UserAuthenticationService.class);

    private File file;

    // Contains the XML document already parsed.
    private Document document;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Constructor to build the XML Document on service initialization:
     *
     * File - The XML file containing users acting as the user database for the
     * application.
     *
     */
    public UserAuthenticationService() {

        try {
            // Build the XML Document for use throughout the class.
            file = new File("windwalker/src/main/resources/users.xml");

            // Create a DocumentBuilder, use builder to parse XML user authentication file.
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            this.document = builder.parse(file);

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

    /**
     * Authenticates a user based on the provided username and password. Targets
     * the parent element to iterate through child elements, comparing the
     * provided credentials with the stored credentials.
     *
     * @param user - Login Credentials passed.
     * @return - boolean value indicating whether the authentication was
     * successful.
     */
    public boolean authenticateUser(User user) {

        // Grab the list of users from the XML
        NodeList users = getUsers();

        // Until the total number of users found within the list is reached, iterate
        // through each user element.
        for (int i = 0; i < users.getLength(); i++) {
            Element userElement = (Element) users.item(i);
            String username = userElement.getElementsByTagName("username").item(0).getTextContent();
            String password = userElement.getElementsByTagName("password").item(0).getTextContent();

            // Compare with provided credentials.
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {

                return true;
            }

        }

        return false;

    }

    /**
     * Registers a user in users.xml unless an existing email matches.
     *
     * @param user User object to be registered.
     * @return true when the user is written to XML; false if email already
     * exists or persistence fails.
     */
    public boolean registerUser(User user) {

        // Grab the list of users from the XML
        NodeList users = getUsers();

        // Go through the document, ensure the user doesn't already exist.
        for (int i = 0; i < users.getLength(); i++) {
            Element userElement = (Element) users.item(i);
            String email = userElement.getElementsByTagName("email").item(0).getTextContent();

            // Compare with provided email.
            if (email.equalsIgnoreCase(user.getEmail())) {

                return false;
            }

        }

        //Assign a unique ID to the new user.
        user.setId(UUID.randomUUID().toString());

        //Grab the root element <users> to append new user.
        Element root = document.getDocumentElement();

        //Create another parent <user> element
        Element newUser = document.createElement("user");

        //Create elements for each <user> attribute
        Element id = document.createElement("id");
        id.setTextContent(user.getId());
        newUser.appendChild(id);
        Element username = document.createElement("username");
        username.setTextContent(user.getUsername());
        newUser.appendChild(username);

        //Encode password before storing in XML.
        Element password = document.createElement("password");
        password.setTextContent(passwordEncoder.encode(user.getPassword()));
        newUser.appendChild(password);

        Element firstName = document.createElement("firstName");
        firstName.setTextContent(user.getFirstName());
        newUser.appendChild(firstName);
        Element lastName = document.createElement("lastName");
        lastName.setTextContent(user.getLastName());
        newUser.appendChild(lastName);
        Element email = document.createElement("email");
        email.setTextContent(user.getEmail());
        newUser.appendChild(email);

        // Append the new <user> element to the root <users> element.
        root.appendChild(newUser);

        try {
            // Write the updated document back to the XML file.
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);

            return true;

            //Catch failures to update the XML file.
        } catch (TransformerException e) {

            //Error logging for development.
            logger.error("Error registering user: Failed to write to users.xml.", e);

            return false;
        }
    }

    /**
     * Helper Method that generates a list of users pulled from the XML file.
     *
     * @return A List of Users pulled from the xml file.
     */
    private NodeList getUsers() {

        // Target users.xml file, filter through DOM tree to find user elements.
        NodeList users = document.getElementsByTagName("user");
        return users;
    }
}
