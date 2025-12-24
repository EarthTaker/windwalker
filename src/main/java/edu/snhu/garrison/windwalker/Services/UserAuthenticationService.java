package edu.snhu.garrison.windwalker.Services;

import java.io.File;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Service;

import edu.snhu.garrison.windwalker.Model.User;

@Service
public class UserAuthenticationService {

    // Logging used to log errors outside of the console.
    private static final Logger logger = LoggerFactory.getLogger(UserAuthenticationService.class);

    /**
     * Authenticates a user based on the provided username and password.
     * Targets the parent element to iterate through child elements, comparing
     * the provided credentials with the stored credentials.
     * 
     * @param user - Login Credentials passed.
     * @return - boolean value indicating whether the authentication was successful.
     */
    public boolean authenticateUser(User user) {
        try {
            File file = new File("windwalker\\src\\main\\resources\\users.xml");

            // Create a DocumentBuilder, use builder to parse XML user authentication file.
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);

            // Target users.xml file, filter through DOM tree to find user elements.
            NodeList users = document.getElementsByTagName("user");

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

            // ParserConfigurationException - In case the builder tool fails to create a
            // document.
            // SAXException - In case the XML file does not adhere to the expected format.
            // IOException - In case the file is not found.
        } catch (ParserConfigurationException | SAXException | IOException e) {

            // Log the error or handle it appropriately
            logger.error("Failed to authenticate user: {}", user.getUsername(), e);
            return false;
        }
    }
}
