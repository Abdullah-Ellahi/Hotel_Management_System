import Classes.*;

import javax.swing.*;
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner s = new Scanner(System.in);

        File accountfile = new File("Accounts.txt");
        File file = new File("Data.txt");
        File indexfile = new File("index.txt");

        String id = "ddMMyy";
        id.substring(0,1);

        // Setting The Static Indexes Of Floor And Room Classes So That The Previous And Next Floors/Rooms Should Not Have The Same ID:
        Scanner sIndex = new Scanner(indexfile);
        String indexes = sIndex.nextLine();
        int index1 = Integer.parseInt(String.valueOf((indexes.charAt(0))));
        int index2 = Integer.parseInt(String.valueOf(indexes.charAt(2)));
        Floor.index = index1;
        Room.index = index2;
        sIndex.close();

        Scanner saccount;
        try {
            saccount = new Scanner(accountfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println();
        System.out.println("                                        LOGIN TO YOUR ACCOUNT TO CONTINUE ");
        System.out.println("Enter username (without spaces):");
        String username = s.next();
        System.out.println("Enter Password:");
        String password = s.next();


        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Admin admin = (Admin) ois.readObject();
        fis.close();
        ois.close();

        admin.setUsername(username);
        admin.setPassword(password);

        int a = 0;
        int floorNo;
        int roomID = 0;
        int ch2;

        while (saccount.hasNextLine()) {
            String account = saccount.nextLine();
            if (account.equals(username + " " + password)) {
                a++;
                System.out.println();
                System.out.println("                                        Welcome " + username);
                break;
            }
        }
        if (a == 0) {
            System.out.println("Username or password is incorrect!");
            saccount.close();
        }
        else {
            saccount.close();

            int choice = 0;

            while (choice != 4) {

                try {
                    System.out.println();
                    System.out.println("*****************************************************************************************************************************************************************");
                    System.out.println("*************************************************************       MAIN MENU       *****************************************************************************");
                    System.out.println("*****************************************************************************************************************************************************************");
                    System.out.println();
                    System.out.println("                                                1.  Floor Management");
                    System.out.println("                                                2.  User Logs");
                    System.out.println("                                                3.  Rooms Management");
                    System.out.println("                                                4.  Exit");

                    System.out.println("Enter your choice:");
                    choice = s.nextInt();

                    switch (choice) {
                        case 1:
                            System.out.println();
                            System.out.println("*****************************************************************************************************************************************************************");
                            System.out.println("***********************************************************    FLOOR MANAGEMENT    ***************************************************************************");
                            System.out.println("*****************************************************************************************************************************************************************");
                            System.out.println();
                            System.out.println("                                                1.  Add Floor");
                            System.out.println("                                                2.  Remove Floor");
                            System.out.println("                                                3.  Display Floors");
                            System.out.println("                                                4.  Manage Cleaning Staff");
                            System.out.println("                                                5.  Back To MAIN MENU");
                            System.out.println();

                            System.out.println("Enter your choice");
                            int ch1 = s.nextInt();

                            switch (ch1) {
                                case 1:
                                    admin.AddFloor();
                                    break;
                                case 2:
                                    System.out.println("Enter Floor No You Want To Remove:");
                                    admin.RemoveFloor(s.nextInt());
                                    break;

                                case 3:
                                    admin.DisplayFloors();
                                    break;

                                case 4:
                                    admin.ManageCleaningStaff();
                                    break;

                                case 5:
                                    break;
                                default:
                                    System.out.println("Invalid choice!");
                            }

                            break;
                        case 2:
                            System.out.println();
                            System.out.println("*****************************************************************************************************************************************************************");
                            System.out.println("*************************************************************    USER LOGS    *****************************************************************************");
                            System.out.println("*****************************************************************************************************************************************************************");
                            System.out.println();
                            System.out.println("                                                1.  Display Admins");
                            System.out.println("                                                2.  Add Admin");
                            System.out.println("                                                3.  Remove Admin");
                            System.out.println("                                                4.  Back To MAIN MENU");
                            System.out.println();

                            System.out.println("Enter your choice");
                            ch2 = s.nextInt();

                            switch (ch2) {
                                case 1:
                                    admin.DisplayAdmins();
                                    break;

                                case 2:
                                    admin.AddAdmin();
                                    break;

                                case 3:
                                    admin.RemoveAdmin();
                                    break;

                                case 4:
                                    break;

                                default:
                                    System.out.println("Invalid choice!");
                            }
                            break;
                        case 3:
                            System.out.println();
                            System.out.println("*****************************************************************************************************************************************************************");
                            System.out.println("***********************************************************    ROOM MANAGEMENT    ***************************************************************************");
                            System.out.println("*****************************************************************************************************************************************************************");
                            System.out.println();
                            System.out.println("                                                1.  Change Room Status");
                            System.out.println("                                                2.  Change room type");
                            System.out.println("                                                3.  Display Rooms");
                            System.out.println("                                                4.  Add Room");
                            System.out.println("                                                5.  Remove Room");
                            System.out.println("                                                6.  Back To MAIN MENU");
                            System.out.println();

                            System.out.println("Enter your choice");
                            ch2 = s.nextInt();

                            switch (ch2) {

                                case 1:
                                    System.out.println("ENTER FLOOR NUMBER ON WHICH YOU WANT TO CHANGE ROOM'S STATUS:");
                                    floorNo = s.nextInt();
                                    System.out.println("ENTER ROOM ID:");
                                    roomID = s.nextInt();

                                    System.out.println();
                                    System.out.println("    1.  Clean");
                                    System.out.println("    2.  Dirty");
                                    System.out.println("    3.  Out Of Order");
                                    System.out.println("    4.  Reserved");
                                    System.out.println();
                                    System.out.println("Select Changed Status: ");
                                    ch2 = s.nextInt();

                                    try {
                                        for (int i = 0; i < admin.floors.get(floorNo).rooms.size(); i++) {
                                            if (admin.floors.get(floorNo).rooms.get(i).getID() == roomID) {
                                                if (ch2 == 1) {
                                                    admin.floors.get(floorNo).rooms.get(i).setStatus(RoomStatus.Clean);
                                                    System.out.println("******   Room Status Changed Successfully!   ******");
                                                } else if (ch2 == 2) {
                                                    admin.floors.get(floorNo).rooms.get(i).setStatus(RoomStatus.Dirty);
                                                    System.out.println("******   Room Status Changed Successfully!   ******");
                                                } else if (ch2 == 3) {
                                                    admin.floors.get(floorNo).rooms.get(i).setStatus(RoomStatus.OutOfOrder);
                                                    System.out.println("******   Room Status Changed Successfully!   ******");
                                                } else if (ch2 == 4) {
                                                    if (admin.floors.get(floorNo).rooms.get(i).getStatus() == RoomStatus.Dirty || admin.floors.get(floorNo).rooms.get(i).getStatus() == RoomStatus.OutOfOrder) {
                                                        System.out.println("Room Not Cleaned, First Clean The Room TO Reserve It!");
                                                    } else {
                                                        admin.floors.get(floorNo).rooms.get(i).setStatus(RoomStatus.Reserved);
                                                        System.out.println("******   Room Status Changed Successfully!   ******");
                                                    }
                                                } else
                                                    System.out.println("Invalid choice, Room Status not Changed!");
                                                break;
                                            }
                                        }
                                    } catch (IndexOutOfBoundsException i) {
                                        System.out.println("Error: Either FloorNo Or Room ID is not Correct!");
                                    }
                                    break;

                                case 2:
                                    System.out.println("ENTER FLOOR NUMBER ON WHICH YOU WANT TO CHANGE ROOM'S TYPE:");
                                    floorNo = s.nextInt();
                                    System.out.println("ENTER ROOM ID:");
                                    roomID = s.nextInt();

                                    System.out.println();
                                    System.out.println("1. Single");
                                    System.out.println("2. Double");
                                    System.out.println("3. Triple");
                                    System.out.println("4. Quad");
                                    System.out.println();
                                    System.out.println("Select Changed Type: ");
                                    ch2 = s.nextInt();

                                    try {
                                        for (int i = 0; i < admin.floors.get(floorNo).rooms.size(); i++) {
                                            if (admin.floors.get(floorNo).rooms.get(i).getID() == roomID) {
                                                if (ch2 == 1) {
                                                    admin.floors.get(floorNo).rooms.get(i).setType(RoomType.single);
                                                    System.out.println("******   Room type Changed Successfully!   ******");
                                                    a++;
                                                    break;
                                                } else if (ch2 == 2) {
                                                    admin.floors.get(floorNo).rooms.get(i).setType(RoomType.Double);
                                                    System.out.println("******   Room type Changed Successfully!   ******");
                                                    a++;
                                                    break;
                                                } else if (ch2 == 3) {
                                                    admin.floors.get(floorNo).rooms.get(i).setType(RoomType.Triple);
                                                    System.out.println("******   Room type Changed Successfully!   ******");
                                                    a++;
                                                    break;
                                                } else if (ch2 == 4) {
                                                    admin.floors.get(floorNo).rooms.get(i).setType(RoomType.Quad);
                                                    System.out.println("******   Room type Changed Successfully!   ******");
                                                    a++;
                                                    break;
                                                } else
                                                    System.out.println("Invalid choice, Room Type not Changed!");
                                                a++;
                                                break;
                                            }
                                        }
                                        if (a == 0) {
                                            System.out.println("Error: Either FloorNo Or Room ID is not Correct!");
                                        }
                                    } catch (IndexOutOfBoundsException i) {
                                        System.out.println("Error: Either FloorNo Or Room ID is not Correct!");
                                    }
                                    break;

                                case 3:
                                    System.out.println("ENTER FLOOR NUMBER OF WHICH YOU WANT TO DISPLAY ROOMS:");

                                    try {
                                        admin.floors.get(s.nextInt()).DisplayRooms();
                                    } catch (NullPointerException N) {
                                        System.out.println("Error: No Floor Found!");
                                    } catch (IndexOutOfBoundsException I) {
                                        System.out.println("Error: No Floor Found!");
                                    }
                                    break;

                                case 4:
                                    System.out.println("ENTER FLOOR NUMBER ON WHICH YOU WANT TO ADD ROOM:");
                                    floorNo = s.nextInt();
                                    admin.floors.get(floorNo).AddRoom();
                                    break;

                                case 5:
                                    System.out.println("ENTER FLOOR NUMBER ON WHICH YOU WANT TO REMOVE ROOM:");
                                    floorNo = s.nextInt();
                                    admin.floors.get(floorNo).RemoveRoom();
                                    break;

                                case 6:
                                    break;

                                default:
                                    System.out.println("Invalid choice!");

                            }
                            break;

                        case 4:
                            System.out.println("******   THANK YOU! ******");
                            break;

                        default:
                            System.out.println("Invalid choice!");
                    }

                    if (choice == 4) {
                        break;
                    }
                }
                catch (InputMismatchException e){
                    System.out.println("Error: Input Is Not Of The Required Format!");
                }

            }

            // Writing The Current FLoor Index So That In The Future, Floor Numbers Should Not Be Mixed:
            FileWriter indexwriter = new FileWriter(indexfile,false);
            indexwriter.write(Floor.index+" "+ Room.index);
            indexwriter.close();

            // Writing The Whole Record As Admin Object Into The Data File:
           FileOutputStream fos = new FileOutputStream(file);
           ObjectOutputStream oos = new ObjectOutputStream(fos);
           oos.writeObject(admin);
           fos.close();
           oos.close();
        }
    }
}