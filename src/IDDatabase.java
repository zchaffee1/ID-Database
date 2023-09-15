import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

public class IDDatabase {
    private ArrayList<ID> database = new ArrayList<ID>();

    //add id to database
    public void add (ID id) throws Unusable{
        if(id.equals(null)){throw new Unusable("Id is empty");}
        for(int i = 0; i < database.size(); i++){
            if(database.get(i).getANum().equals(id.getANum())){
                throw new Unusable("Anumber already exists");
            }
        }
        database.add(id);
    }

    //add ids from file
    public void add(String fileName) throws Unusable{
        int fileSize = 0;
        Scanner inputFile;
        Scanner inputFile2;

        try{
            File file = new File(fileName);
            inputFile = new Scanner(file);
            inputFile2 = new Scanner(file);
        }
        catch(FileNotFoundException e){
            throw new Unusable("File not found: " + fileName);
        }

        while(inputFile.hasNextLine()){
            inputFile.nextLine();
            fileSize++;
        }

        if(fileSize == 0){
            inputFile.close();
            inputFile2.close();
            throw new Unusable("File is empty");
        }

        for(int i = 0; i < fileSize; i++){
            String fullID = inputFile2.nextLine();
            String[] id = fullID.split(",");
            try{
                //Checks to see if the Anumber is valid
                Character a = id[1].charAt(0);
                Integer length = id[1].length();
                if(!a.equals('A') || !length.equals(9)){
                    throw new Unusable("A number is invalid");
                }

                //Checks to see if the Anumber exists
                for(int j = 0; j < database.size(); j++){
                    if(database.get(j).getANum().equals(id[1])){
                        throw new Unusable("Anumber already exists");
                    }
                }

                //Checks to see if the position is the right size
                if(id[0].length() > 1){
                    throw new Unusable("Invalid position");
                }
                Character position = id[0].charAt(0);
                position = Character.toUpperCase(position);

                //Checks which position the ID belongs to 
                if (position.equals('S')){
                    StudentID sID = new StudentID(id[0].charAt(0), id[1], id[2], id[3], Integer.parseInt(id[4]), id[5]);
                    database.add(sID);
                }
                else if (position.equals('F')){
                    FacultyID fid = new FacultyID(id[0].charAt(0), id[1], id[2], id[3], Integer.parseInt(id[4]), id[5]);
                    database.add(fid);
                }
                else if (position.equals('T')){
                    StaffID tID = new StaffID(id[0].charAt(0), id[1], id[2], id[3], Integer.parseInt(id[4]), id[5]);
                    database.add(tID);
                }
                else{
                    throw new Unusable("Invalid position");
                }
            }
            catch(Exception e){
                System.out.print("Error adding id: ");
                System.out.println(fullID);
                System.out.println("Reason: " + e.toString() + "\n");
            }
        }

        inputFile.close();
        inputFile2.close();
        System.out.println("Adding File Complete");
    }

    //remove id from database based on Anumber
    public void removeANum(String aNum) throws Unusable{
        for(int i = 0; i < database.size(); i++){
            if(database.get(i).getANum().equals(aNum)){
                database.remove(i);
                return;
            }
        }

        throw new Unusable("A Number not found in database");
    }

    //remove id from database based on last name
    public void removeLastName(String lastName) throws Unusable{
        boolean idRemoved = false;
        for(int i = 0; i < database.size(); i++){
            if(database.get(i).getLastName().equals(lastName)){
                database.remove(i);
                idRemoved = true;
            }
        }

        if(!idRemoved){
            throw new Unusable("Last name not found in database");
        }
    }

    //Saves databse to file
    public void save()throws Unusable{
        if(database.size() == 0){throw new Unusable("Database is empty");}

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM_dd_yyyy_HH-mm_ss");
        try {
            File file = new File("DATABASE_" + dateFormat.format(date) + ".csv");
            PrintWriter writer = new PrintWriter(new FileOutputStream(file));
            for(int i = 0; i < database.size(); i++){
                String s = String.format("%c,%s,%s,%s,%d,%s",
                    database.get(i).getPosition(), database.get(i).getANum(), database.get(i).getFirstName(), 
                    database.get(i).getLastName(), database.get(i).getAge(), database.get(i).getParameter()
                );
                writer.println(s);
            }    
            writer.close(); 
            System.out.println("Save Completed!");
            System.out.println("File name: DATABASE_" + dateFormat.format(date) + ".csv");
        } catch (IOException e) {
            System.out.println("An error occured");
            System.out.println(e.toString());
        }
    }

    //display all IDs 
    public void showID(){
        if(database.size() == 0){
            System.out.println("Database is empty.");
        }
        else{
            System.out.printf("%-10s%-12s%-15s%-20s%-5s%-10s\n", "Position", "A Number", "First Name", "Last Name", "Age", "Parameter");
            for(int i = 0; i < database.size(); i++){
                database.get(i).print();
            }
        }
    }    
    
    //display all IDs based on position of ID, Staff, Faculty, or Student
    public void showID(char position){
        if(database.size() == 0){
            System.out.println("Database is empty.");
        }
        else{
            int idCount = 0;
            for(int i=0; i < database.size(); i++){
                if (database.get(i).getPosition() == (position)){
                    idCount++;
                    if(idCount == 1){
                        System.out.printf("%-10s%-12s%-15s%-20s%-5s%-10s\n", "Position", "A Number", "First Name", "Last Name", "Age", "Parameter");
                    }
                    database.get(i).print();
                }
            }
            if(idCount == 0){
                String fullPosition;
                if(position == 'T'){fullPosition = "Staff";}
                else if(position == 'F'){fullPosition = "Faculty";}
                else{fullPosition = "Student";}
                System.out.println("There are no IDs with the position " + fullPosition);
            }
        }
    }

    //display all IDs above age N
    public void showID(int age){
        if(database.size() == 0){
            System.out.println("Database is empty.");
        }
        else{
            int idCount = 0;
            for(int i=0; i < database.size(); i++){
                if(database.get(i).getAge() > age){
                    idCount++;
                    if(idCount == 1){
                        System.out.printf("%-10s%-12s%-15s%-20s%-5s%-10s\n", "Position", "A Number", "First Name", "Last Name", "Age", "Parameter");
                    }
                    database.get(i).print();
                }
            }
            if(idCount == 0){
                System.out.println("There is no ID with an age above " + age);
            }
        }
    }

    //get number of ids in database
    public int getTotalCount(){return this.database.size();}
}
