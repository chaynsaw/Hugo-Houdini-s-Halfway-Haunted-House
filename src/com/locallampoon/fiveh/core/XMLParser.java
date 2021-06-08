package com.locallampoon.fiveh.core;

import javax.management.ObjectName;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLParser {

    public static Room hall, trophyRoom, diningRoom, garden, kitchen, pantry, study, library, dungeon, sacraficialChamber,
            upstairsLanding, nursery, masterBedroom, guestRoom, attic;

    private static final String ROOM_FILE = "src/com/locallampoon/fiveh/data/rooms.xml";

    private static Room roomCreator(String idName, String roomName, String roomDescription, List<String> roomPaths,
                                    List<String> items){
        switch (idName) {
            case "hall":
                return hall = new Room(roomName, roomDescription);
            case "trophyRoom":
                return trophyRoom = new Room(roomName, roomDescription);
            case "diningRoom":
                return diningRoom = new Room(roomName, roomDescription);
            case "garden":
                return garden = new Room(roomName, roomDescription);
            case "kitchen":
                return kitchen = new Room(roomName, roomDescription);
            case "pantry":
                return pantry = new Room(roomName, roomDescription);
            case "study":
                return study = new Room(roomName, roomDescription);
            case "library":
                return library = new Room(roomName, roomDescription);
            case "dungeon":
                return dungeon = new Room(roomName, roomDescription);
            case "sacraficialChamber":
                return sacraficialChamber = new Room(roomName, roomDescription);
            case "upstairsLanding":
                return upstairsLanding = new Room(roomName, roomDescription);
            case "nursery":
                return nursery = new Room(roomName, roomDescription);
            case "masterBedroom":
                return masterBedroom = new Room(roomName, roomDescription);
            case "guestRoom":
                return guestRoom = new Room(roomName, roomDescription);
            case "attic":
                return attic = new Room(roomName, roomDescription);
            default:
                return null;
        }
    }

//    private void setRoomExits(int roomPID, List<String> roomExits) {
//        for (int i = 0; i < roomExits.size(); i++) {
//            String adjacentRoom = roomExits.get(i);
//            if (!roomExits.get(i).equals("") || !roomExits.get(i).equals(" ")) {
//                switch (i) {
//                    case 0:
//                        hall.setNorth((Room) adjacentRoom);
//                }
//            }
//        }
//    }

//    private void setRoomExits(Room thisRoom, int roomPID, List<String> roomExits, Map<Integer, Room> integerRoomMap) {
//        for (String adjacentRoom : roomExits) {
//            if (!adjacentRoom.equals("")) {
//                switch (roomPID) {
//                    case 22364559:
//                        thisRoom.setNorth(integerRoomMap.get(adjacentRoom.hashCode()));
//                }
//            }
//        }
//    }

    // comment out parseRooms() and uncomment main for testing
    public static Map<Integer, Room> parseRooms() {
//    public static void main(String[] args) {


        // Instantiate the Factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        // Map of new rooms
        Map<Integer, Room> roomMap = new HashMap<>();

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
                    // TODO: get objects created dynamically first with a loop + given their roomName and Description.
                    //  Then loop a second time and assign the necessary directional values. make this in a method with switch
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
//                    roomCreator(id, roomName, desc, exits, items);
                    // put new KVP into map using id as key and new Room ctor as value
                    roomID = id.hashCode()*7;
                    System.out.println("The Room reference is " + id + " and the hash value is " + roomID);
                    roomMap.put(roomID, roomCreator(id, roomName, desc, exits, items));
                }
            }
//            roomMap.forEach(adjRoom -> {
//
//            });
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