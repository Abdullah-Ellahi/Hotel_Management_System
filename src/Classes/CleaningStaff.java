package Classes;

import java.io.Serializable;

public class CleaningStaff implements Serializable {
    private int Quantity;
    public CleaningStaff(int quantity) {
        Quantity = quantity;
    }

    protected void CleanRoom(Room room){
        if(room.getStatus() == RoomStatus.Dirty){
            room.setStatus(RoomStatus.Clean);
            System.out.println("Room CLeaned Successfully!");
        }
        else if (room.getStatus() == RoomStatus.OutOfOrder) {
            System.out.println("Cannot Clean!\nRoom Is Out of Order!");
        }
        else if (room.getStatus() == RoomStatus.Clean) {
            System.out.println("Room Already Cleaned!");
        }
        else if (room.getStatus() == RoomStatus.Reserved) {
            System.out.println("Cannot Clean!\nRoom Is Reserved!");
        }
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
