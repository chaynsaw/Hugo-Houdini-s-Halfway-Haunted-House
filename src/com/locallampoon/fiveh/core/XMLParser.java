package com.locallampoon.fiveh.core;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLParser {

    private static final String ROOM_FILE = "src/com/locallampoon/fiveh/data/rooms.xml";


    // comment out parseRooms() and uncomment main for testing
    public static Map<String, Room> parseRooms() {
//    public static void main(String[] args) {


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
            int roomID;
            // iterate through room nodes to get detailed room info
            for (int i = 0; i < rooms.getLength(); i++) {
                Node roomNode = rooms.item(i);

                if (roomNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) roomNode;

                    // gather elements to create Room object
                    String id = element.getAttribute("id");
                    String roomName = element.getElementsByTagName("roomName").item(0).getTextContent();
                    String desc = element.getElementsByTagName("desc").item(0).getTextContent();
                    NodeList exitNodes = element.getElementsByTagName("exit");
                    List<String> exits = new ArrayList<>();
                    // iterate through exitNodes to get exits array
                    for (int j = 0; j < exitNodes.getLength(); j++) {
                        Node currentNode = exitNodes.item(j);
                        int index = Integer.parseInt(currentNode.getAttributes().getNamedItem("dir").getNodeValue());
                        String value = currentNode.getTextContent();
                        exits.add(index, value);
                    }
                    NodeList itemNodes = element.getElementsByTagName("item");
                    List<String> items = new ArrayList<>();
                    // iterate through itemNodes to get items
                    for (int j = 0; j < itemNodes.getLength(); j++) {
                        Node currentNode = itemNodes.item(j);
                        items.add(currentNode.getTextContent());
                    }
                    // put new KVP into map using id as key and new Room ctor as value
                    roomMap.put(id, new Room(roomName, desc, exits, items));
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return roomMap;

        // uncomment to use as main for testing
//        Room r = roomMap.get("masterBedroom");
//        System.out.println(r.getRoomName());
//        System.out.println(r.getDesc());
//        System.out.println(r.getExits());
//        System.out.println(r.getItems());
    }
}