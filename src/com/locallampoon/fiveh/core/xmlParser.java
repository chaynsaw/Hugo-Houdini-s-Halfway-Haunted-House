package com.locallampoon.fiveh.core;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class xmlParser {

    private static final String ROOM_FILE = "src/com/locallampoon/fiveh/data/roomtest.xml";

    public Map parseRooms() {

        // Instantiate the Factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        // Map of new rooms
        Map<String, Room> roomMap = new HashMap<>();

        try {
            // process XML securely
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // parse XML file
            DocumentBuilder docBuild = dbf.newDocumentBuilder();

            Document doc = docBuild.parse(new File(ROOM_FILE));

            doc.getDocumentElement().normalize();
            // get <room> from xml
            NodeList rooms = doc.getElementsByTagName("room");

            for (int i = 0; i < rooms.getLength(); i++) {
                Node roomNode = rooms.item(i);

                if (roomNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) roomNode;

                    // gather elements to create Room object
                    String id = element.getAttribute("id");
                    String roomName = element.getElementsByTagName("roomName").item(0).getTextContent();
                    String desc = element.getElementsByTagName("desc").item(0).getTextContent();
                    NodeList exitNodes = element.getElementsByTagName("exit");
                    String[] exits = new String[exitNodes.getLength()];
                    // iterate through exitNodes to get exits array
                    for (int j = 0; j < exits.length; j++) {
                        exits[j] = exitNodes.item(j).getTextContent();
                    }
                    NodeList itemNodes = element.getElementsByTagName("item");
                    String[] items = new String[itemNodes.getLength()];
                    // iterate through itemNodes to get items
                    for (int j = 0; j < items.length; j++) {
                        items[j] = itemNodes.item(j).getTextContent();
                    }

                    // put new KVP into map using id as key and new Room ctor as value
                    roomMap.put(id, new Room(roomName, desc, exits, items));
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return roomMap;
    }
}