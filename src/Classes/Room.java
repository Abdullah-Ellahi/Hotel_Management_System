package Classes;

import java.io.Serializable;

public class Room implements Serializable{
    public static int index;
    private int ID;
    private RoomType type;
    private RoomStatus status;
    private int capacity;


    public Room(RoomType type, RoomStatus status) {
        this.ID = index;
        index++;
        this.type = type;
        this.status = status;
        if (this.type == RoomType.single) {
            this.capacity = 1;
        } else if (this.type == RoomType.Double) {
            this.capacity = 2;
        } else if (this.type == RoomType.Triple) {
            this.capacity = 3;
        } else if (this.type == RoomType.Quad) {
            this.capacity = 4;
        }
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

}