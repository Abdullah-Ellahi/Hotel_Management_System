package Classes;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Admin implements Serializable{
    private String Username;
    private String password;
    public ArrayList<Floor> floors = new ArrayList<Floor>();

    public Admin(String username, String password) {
        Username = username;
        this.password = password;
    }

    public void DisplayAdmins(){
        Scanner s = new Scanner(System.in);
        File accountfile = new File("Accounts.txt");

        Scanner saccount;

        try {
            saccount = new Scanner(accountfile);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            throw new RuntimeException(e);
        }
        int index;
        System.out.println("Admins Are:");
        while (saccount.hasNextLine()) {
            String account = saccount.nextLine();
            index = account.indexOf(" ");
                System.out.println("Admin name: " + account.substring(0, index));
        }
        saccount.close();
    }
    public void AddAdmin(){
        Scanner s = new Scanner(System.in);

        System.out.println("Enter Username:");
        String username = s.next();
        System.out.println("Enter Password:");
        String pswrd = s.next();
        try {
            File file = new File("Accounts.txt");
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(username+" "+pswrd+"\n");
            System.out.println("Admin added successfully!");
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error: File not found!");
            throw new RuntimeException(e);
        }
        catch (Exception e) {
            System.out.println("Error: Unable To Add Admin!");
        }
    }

    public void RemoveAdmin() {
        Scanner s = new Scanner(System.in);

        File oldfile = new File("Accounts.txt");
        File newfile = new File("temp.txt");

        try {
            newfile.createNewFile();
            FileWriter fileWriter = new FileWriter(newfile, false);

            Scanner sOld = new Scanner(oldfile);

            System.out.println("Enter Details of the Admin To Be Removed:");
            System.out.println("Username:");
            String username = s.next();
            System.out.println("Password:");
            String pswrd = s.next();

            int index, a = 0 ;
            String account;

            while (sOld.hasNextLine()) {
                account = sOld.nextLine();

                if (!(account.equals(username + " " + pswrd))) {
                    fileWriter.write(account+"\n");
                }
                else
                    a++;
            }
            fileWriter.close();
            sOld.close();

            oldfile.delete();
            if(oldfile.exists())
                oldfile.delete();

            if(a==0){
                System.out.println("Admin Not Found!");
            }

            if (newfile.renameTo(new File("Accounts.txt")))
                if(a!=0)
                    System.out.println("Admin removed successfully!");
            else
                System.out.println("Error: Admin not removed!");

        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        catch (Exception e) {
            System.out.println("Error: Unable To remove Admin!");
        }
    }

    public void AddFloor() {
        Scanner s = new Scanner(System.in);

        Floor floor = new Floor();
        this.floors.add(floor);

        System.out.println("Floor " + floor.getFloorNo() + " Added Successfully!");

        System.out.println("Do you want to add Room");
        System.out.println("1. yes");
        System.out.println("2. No");
        int choice = s.nextInt();

        while (choice != 2) {
            if (choice == 1) {
                try {
                    this.floors.get(floor.getFloorNo()).AddRoom();
                } catch (NullPointerException n) {
                    System.out.println("Floor is NULL!");
                }
            } else if (choice == 2) {
                System.out.println("No Room Added To Floor!");
            } else System.out.println("Invalid Choice!");

            System.out.println("Do You Want To Add More Rooms:");
            System.out.println("1. yes");
            System.out.println("2. No");
            choice = s.nextInt();
        }
    }

    public void RemoveFloor(int FloorNo){
        Scanner s = new Scanner(System.in);
        int a=0;

        for(int i=0 ; i<this.floors.size() ; i++){
            if(this.floors.get(i).getFloorNo() == FloorNo) {
                this.floors.remove(FloorNo);
                System.out.println("Floor Removed Successfully!");
                a++;
                Floor.index--;
                for(i = FloorNo; i < this.floors.size(); i++){
                    this.floors.get(i).setFloorNo(this.floors.get(i).getFloorNo()-1);
                }
                break;
            }
        }
        if(a == 0){
            System.out.println("Floor not found!");
        }

    }
    public String getUsername() {
        return Username;
    }
    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public void DisplayFloors() {
        int a=0;
        System.out.println("FLOORS ARE:\n");

        for(int i=0 ; i<this.floors.size() ; i++){
            System.out.println("Floor ID: "+ this.floors.get(i).getFloorNo());
            System.out.println("Single Rooms: "+ this.floors.get(i).getNoOfSingleRooms());
            System.out.println("Double Rooms: "+ this.floors.get(i).getNoOfDoubleRooms());
            System.out.println("Triple Rooms: "+ this.floors.get(i).getNoOfTripleRooms());
            System.out.println("Quad Rooms: "+ this.floors.get(i).getNoOfQuadRooms());
            System.out.println("No Of Cleaning Staff:"+ this.floors.get(i).getCleaningstaff().getQuantity());
            System.out.println();
            a++;
        }

        if(a == 0){
            System.out.println("No Floors Found!\n");
        }
    }

    public void ManageCleaningStaff() {
        Scanner s = new Scanner(System.in);

        System.out.println("Enter Floor No:\n");
        int FloorNo = s.nextInt();

        try {
            System.out.println("No Of Cleaning staff Working On This Floor:" + this.floors.get(FloorNo).getCleaningstaff().getQuantity());

            System.out.println("Do you want to change quantity of Cleaning Staff?");
            System.out.println("1. yes");
            System.out.println("2. No");
            int choice = s.nextInt();

            if (choice == 1) {
                System.out.println("Enter New Quantity:");
                int newQuantity = s.nextInt();
                this.floors.get(FloorNo).setCleaningstaff(newQuantity);
            } else if (choice == 2) {
                System.out.println("No Cleaning Staff Not Changed!");
            } else
                System.out.println("Invalid Choice!");
        }
        catch (IndexOutOfBoundsException i)
        {
            System.out.println("Floor Not Found!");
        }
    }

}
