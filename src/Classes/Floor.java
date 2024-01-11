package Classes;

import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Floor implements Serializable {
    public static int index = 0;
    private int floorNo;
    public ArrayList<Room> rooms ;
    private int NoOfSingleRooms;
    private int NoOfDoubleRooms;
    private int NoOfTripleRooms;
    private int NoOfQuadRooms;
    private CleaningStaff cleaningstaff;

    public CleaningStaff getCleaningstaff() {
        return this.cleaningstaff;
    }

    public void setCleaningstaff(int cleaningstaff) {
        this.cleaningstaff.setQuantity(cleaningstaff);
    }


    public Floor() {
        this.floorNo = index;
        this.rooms = new ArrayList<Room>();
        this.NoOfSingleRooms = 0;
        this.NoOfDoubleRooms = 0;
        this.NoOfTripleRooms = 0;
        this.NoOfQuadRooms = 0;
        this.cleaningstaff = new CleaningStaff(0);
        index++;
    }

    public void AddRoom(){

        try {
            Scanner s = new Scanner(System.in);

            System.out.println("Enter Room Type:");
            System.out.println("1. Single");
            System.out.println("2. Double");
            System.out.println("3. Triple");
            System.out.println("4. Quad");
            int ch = s.nextInt();

            RoomType type;
            switch (ch) {
                case 1:
                    type = RoomType.single;
                    break;
                case 2:
                    type = RoomType.Double;
                    break;
                case 3:
                    type = RoomType.Triple;
                    break;
                case 4:
                    type = RoomType.Quad;
                    break;
                default:
                    System.out.println("Invalid Room Type!");
                    type = RoomType.single;
                    System.out.println("Room Type Automatically set to single");
                    break;
            }
            Room room = new Room(type, RoomStatus.Clean);
            this.rooms.add(room);

            if (room.getType() == RoomType.single) {
                NoOfSingleRooms++;
            } else if (room.getType() == RoomType.Double) {
                NoOfDoubleRooms++;
            } else if (room.getType() == RoomType.Triple) {
                NoOfTripleRooms++;
            } else if (room.getType() == RoomType.Quad) {
                NoOfQuadRooms++;
            }

            System.out.println("Room Added Successfully, And Room Status Is Set To Clean!");
        }
        catch (Exception e) {
            System.out.println("Error:");
        }
    }
    public void RemoveRoom() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter Room ID You want to Remove:");
        int RoomID = s.nextInt();

        int a = 0;
        RoomType type = null;

        for (int i = 0; i < this.rooms.size(); i++) {
            if (this.rooms.get(i).getID() == RoomID) {
                type = this.rooms.get(i).getType();
                this.rooms.remove(this.rooms.get(i));
                System.out.println("Room of ID " + RoomID + " is removed Successfully!");
                a++;
            }
        }
        if (a == 0)
            System.out.println("Room of ID "+RoomID+" not found on this floor!");

        if(type == RoomType.single) {
            NoOfSingleRooms--;
        }
        else if(type == RoomType.Double) {
            NoOfDoubleRooms--;
        }
        else if(type == RoomType.Triple) {
            NoOfTripleRooms--;
        }
        else if(type == RoomType.Quad) {
            NoOfQuadRooms--;
        }
    }

    public void DisplayRooms(){
        int a=0;
        System.out.println("Rooms On The Floor "+this.floorNo+" Are:\n");

        for (int i = 0; i < this.rooms.size(); i++) {
            System.out.println("Room ID: "+this.rooms.get(i).getID() +
                    "\nRoom status: "+this.rooms.get(i).getStatus() +
                    "\nRoom Type: " + this.rooms.get(i).getType() +
                    "\nRoom Capacity: "+this.rooms.get(i).getCapacity());
            System.out.println();
            a++;
        }
        if(a == 0){
            System.out.println("There are no rooms on this floor!");
        }
    }

    public int getFloorNo() {
        return floorNo;
    }
    public void setFloorNo(int floorNo) {
        this.floorNo = floorNo;
    }
    public ArrayList<Room> getRooms() {
        return rooms;
    }
    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public int getNoOfSingleRooms() {
        return NoOfSingleRooms;
    }
    public void setNoOfSingleRooms(int noOfSingleRooms) {
        NoOfSingleRooms = noOfSingleRooms;
    }

    public int getNoOfDoubleRooms() {
        return NoOfDoubleRooms;
    }
    public void setNoOfDoubleRooms(int noOfDoubleRooms) {
        NoOfDoubleRooms = noOfDoubleRooms;
    }

    public int getNoOfTripleRooms() {
        return NoOfTripleRooms;
    }
    public void setNoOfTripleRooms(int noOfTripleRooms) {
        NoOfTripleRooms = noOfTripleRooms;
    }

    public int getNoOfQuadRooms() {
        return NoOfQuadRooms;
    }
    public void setNoOfQuadRooms(int noOfQuadRooms) {
        NoOfQuadRooms = noOfQuadRooms;
    }

}
