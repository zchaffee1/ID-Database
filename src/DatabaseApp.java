import java.util.*; 

public class DatabaseApp {
    private IDDatabase database = new IDDatabase();
    private Scanner scan = new Scanner(System.in);

    private static final int DISPLAY_DB = 1;
    private static final int ADD_ID = 2;
    private static final int REMOVE_ID = 3;
    private static final int SAVE_DB = 4;
    private static final int QUIT = 5;

    public void mainMenu(){
        boolean invalidDecision = true;
        int decision = -1;

        //Asks user for what they want to do
        System.out.println(
            "What would you like to do?\n" +
            DISPLAY_DB + ". Display database\n" +
            ADD_ID + ". Add ID\n" +
            REMOVE_ID + ". Remove ID\n" +
            SAVE_DB + ". Save database\n" +
            QUIT + ". Quit"
        );

        decision = getInput(1,5);
        
        switch(decision){
            case DISPLAY_DB:
                display();
                break;
            case ADD_ID:
                addID();
                break;
            case REMOVE_ID:
                removeID();
                break;
            case SAVE_DB:
                save();
                break;
            case QUIT:
                System.out.println("Goodbye!");
                break;
            default:
                System.out.println("Error occured");
                break;
        }
    }

    //Gets input from user
    private int getInput(int min, int max){
        int decision = -1;
        boolean invalidDecision = true;

        do{
            try{
                decision = scan.nextInt();
                if (decision < min || decision > max){
                    throw new InputMismatchException();
                }
                invalidDecision = false;
            }
            catch (InputMismatchException e){
                System.out.println("Please enter a number " + min + "-" + max);
                scan.nextLine();
            }
        }
        while(invalidDecision);

        return decision;
    }

    //Contains all ways to display database
    private void display(){
        boolean invalidDecision = true;
        int decision = -1;

        //Options for displaying
        System.out.println(
            "Would you like to:\n" + 
            "1. Display entire database\n" + 
            "2. Display database based on position in university\n" + 
            "3. Display database based on age"
        );

        do{
            try{
                decision = scan.nextInt();
                if(decision < 1 || decision > 3){
                    throw new InputMismatchException();
                }
                invalidDecision = false;
                scan.nextLine();
            }
            catch(InputMismatchException e){
                System.out.println("Please enter a number 1-3");
                scan.next();
            }
        }
        while(invalidDecision);
        
        //Display entire database
        if(decision == 1){
            database.showID();
        }
        
        //display dapabase based on position in university
        else if(decision == 2){
            char position = 's';
            invalidDecision = true;

            //Position choices
            System.out.println(
                "What position would you like to display?\n"+
                "T(staff)\tS(student)\tF(faculty)"
            );
            do{
                try{
                    position = scan.next().charAt(0);
                    position = Character.toUpperCase(position);
                    if(position != 'S' && position != 'F' && position != 'T'){
                        throw new Exception();
                    }
                    invalidDecision = false;
                    scan.nextLine();
                }
                catch(Exception e){
                    System.out.println("Please enter a valid position:\n"+
                    "S(Student)\tF(Faculty)\tT(Staff)");
                    scan.next();
                }
            }
            while(invalidDecision); 
            database.showID(position);
        }

        //Display database based on age
        else{
            int age = 0;
            invalidDecision = true;
            System.out.println(
                "What age would you like to display?\n" +
                "(Every age above the one selected will be displayed)"
            );

            do{
                try{
                    age = scan.nextInt();
                    if(age < 1){
                        throw new InputMismatchException();
                    }
                    invalidDecision = false;
                }
                catch(InputMismatchException e){
                    System.out.println("Please enter a valid age (must be above 0)");
                    scan.next();
                }
            }
            while(invalidDecision);
            
            database.showID(age);
        }

        resume();
    }

    //Contains all ways to add IDs
    public void addID(){
        boolean tryAgain = false;
        int decision = 0;
        boolean invalidDecision = true;

        //All options to add ID to database
        System.out.println(
            "Would you like to add:\n" +
            "1. One ID\n" + 
            "2. A file"
        );
        do{
            try{
                decision = scan.nextInt();
                if (decision < 1 || decision > 2){
                    throw new InputMismatchException();
                }
                invalidDecision = false;
            }
            catch (InputMismatchException e){
                System.out.println("Please enter 1 or 2");
                scan.nextLine();
                
            }
        }
        while(invalidDecision);

        //Adds individual ID
        invalidDecision = true;
        if (decision == 1){
            char position = 'S';

            //Asks for position of the ID in the university
            System.out.println(
                "What is the position of the ID?\n" + 
                "S (Student)\tF (Faculty)\tT (Staff)");
            do{
                try{
                    position = scan.next().charAt(0);
                    position = Character.toUpperCase(position);
                    if(position != 'S'  && position != 'F' && position != 'T'){
                        throw new InputMismatchException();
                    }
                    invalidDecision = false; 
                    scan.nextLine();
                }
                catch(Exception e){
                    System.out.println("Please enter S (Student)\tF(Faculty)\tT(Staff)");
                }
            }
            while(invalidDecision);

            
            String aNum = null, firstName = null, lastName = null, parameter = null;
            Integer age = null;
            invalidDecision = true;

            //Asks for all other information of the ID
            do{
                try{
                    //Obtains A number from user
                    System.out.println(
                        "What is the A Number\n" + 
                        "(Make sure the A is capital and is followed by 8 digits)\t ex. A12345678"
                    );
                    do{
                        try{
                            aNum = scan.nextLine();
                            if(aNum.charAt(0) != 'A' || aNum.length() != 9){
                                throw new Unusable("Invalid A number");
                            }
                            invalidDecision = false;
                        }
                        catch(Unusable e){
                            System.out.println("Please enter a valid A Number\n" + 
                            "(Make sure the A is capital and is followed by 8 digits)\t ex. A12345678");
                        }
                    }
                    while(invalidDecision);

                    invalidDecision = true;
                    //Obtains First Name From User
                    System.out.println("What is the First Name?");
                    do{
                        try{
                            firstName = scan.nextLine();
                            if(firstName == null || firstName.length() == 0){
                                throw new Unusable("Invalid first name");
                            }
                            invalidDecision = false;
                        }
                        catch(Unusable e){
                            System.out.println("Please enter the first name");
                            scan.nextLine();
                        }
                    }
                    while(invalidDecision);

                    invalidDecision = true;
                    //Obtains last name from user
                    System.out.println("What is the Last Name?");
                    do{
                        try{
                            lastName = scan.nextLine();
                            if(lastName == null || lastName.length() == 0){
                                throw new Unusable("Invalid last name");
                            }
                            invalidDecision = false;
                        }
                        catch(Unusable e){
                            System.out.println("Please enter the last name");
                            scan.nextLine();
                        }
                    }
                    while(invalidDecision);

                    invalidDecision = true;
                    //obtain age from user
                    System.out.println("What is the age?");
                    do{
                        try{
                            age = scan.nextInt();
                            if(age < 1){
                                throw new Unusable("Invalid age");
                            }
                            invalidDecision = false;
                            scan.nextLine();
                        }
                        catch(Exception e){
                            System.out.println("Please enter a valid age (it must be above 0)");
                            scan.nextLine();
                        }
                    }
                    while(invalidDecision);

                    //Adds ID based off of position
                    invalidDecision = true;
                    if(position == 'S'){
                        System.out.println(
                            "Please enter the degree being pursued\n" +
                            "BSc\tMSc\tPhD"
                        );
                        do{
                            try{
                                parameter = scan.nextLine();
                                if(!parameter.equals("BSc") && !parameter.equals("MSc") && !parameter.equals("PhD")){
                                    throw new Unusable("Invalid degree");
                                }
                                invalidDecision = false;
                            }
                            catch(Exception e){
                                System.out.println("Please enter a valid degree\nBSc\tMSc\tPhD");
                            }
                        }
                        while(invalidDecision);

                        StudentID sid = new StudentID(position, aNum, firstName, lastName, age, parameter);
                        database.add(sid);        
                    }
                    else if (position == 'T'){
                        System.out.println("Please enter the salary earned");

                        do{
                            try{
                                parameter = scan.nextLine();
                                int salary = Integer.parseInt(parameter);
                                if(salary < 0){
                                    throw new Unusable("Invalid salary");
                                }
                                invalidDecision = false;
                            }
                            catch(Exception e){
                                System.out.println("Please enter a valid salary");
                            }
                        }
                        while(invalidDecision);

                        StaffID sid = new StaffID(position, aNum, firstName, lastName, age, parameter);
                        database.add(sid);
                    }
                    else{
                        System.out.println("Please enter the Department worked for");
                        do{
                            try{
                                parameter = scan.nextLine();
                                if(parameter.equals(null) || parameter.length() < 2){
                                    throw new Unusable("Invalid department");
                                }
                                invalidDecision = false;
                            }
                            catch(Exception e){
                                System.out.println("Please enter a valid department");
                            }
                        }
                        while(invalidDecision);
                        
                        FacultyID fid = new FacultyID(position, aNum, firstName, lastName, age, parameter);
                        database.add(fid);
                    }
                    System.out.println("ID add successful!");
                    tryAgain = false;
                }
                catch(Unusable e){
                    System.out.println(e.toString());                    
                    tryAgain = tryAgain();
                }
            }
            while(tryAgain);
        }


        //Add a file to the database
        else{
            tryAgain = false;
            System.out.println("What is the file name?\n(Remember to inclide file type i.e. .txt or .csv)");
            do{
                try{
                    scan.nextLine();
                    String fileName = scan.nextLine();
                    database.add(fileName);
                    System.out.println("File added successfully!");
                }
                catch(Unusable e){
                    System.out.println(e.toString());
                    tryAgain = tryAgain();
                    scan.nextLine();
                }
                catch(Exception e){
                    System.out.println("An error occured.");
                    tryAgain = tryAgain();
                    scan.nextLine();
                }
            }
            while(tryAgain);
        }

        resume();
    }


    //Contains all ways to remove IDs
    private void removeID(){
        System.out.println(
        "Would you like to remove IDs based off:\n" 
        + "1. Lastname\n" 
        + "2. A Number" 
        );
        Integer decision = null;
        boolean invalidDecision = true;

        do{
            try{
                decision = scan.nextInt();
                if (decision < 1 || decision > 2){
                    throw new InputMismatchException();
                }
                invalidDecision = false;
            }
            catch (InputMismatchException e){
                System.out.println("Please enter 1 or 2");
                scan.next();
            }
        }
        while(invalidDecision);

        scan.nextLine();

        //remove ID based off last name
        if(decision == 1){
            String lastName;
            do{
                try{
                    System.out.println("What is the last name you would like to remove?");
                    lastName = scan.nextLine();
                    database.removeLastName(lastName);
                    invalidDecision = false;
                    System.out.println("Removal Successful");
                }
                catch(Unusable e){
                    System.out.println("Last name not found in database");
                    invalidDecision = tryAgain();
                }
                catch(Exception e){
                    System.out.println("Error occured");
                    invalidDecision = tryAgain();
                }
            }
            while(invalidDecision);
        }

        //remove ID based off A number
        else{
            String ANum;
            do{
                try{
                    System.out.println(
                        "Enter the A number of the id you would like to remove\n" + 
                        "Remember to make sure the A is capital\t ex. A12345678"
                    );

                    ANum = scan.nextLine();
                    database.removeANum(ANum);
                    invalidDecision = false;
                    System.out.println("Removal Successful");
                }
                catch(Unusable e){
                    System.out.println("A number not forund in database");
                    invalidDecision = tryAgain();
                }
                catch(Exception e){
                    System.out.println("Error occured");
                    invalidDecision = tryAgain();
                    scan.next();
                }
            }
            while(invalidDecision);
        }
        resume();
    }
    
    //saves database
    public void save(){
        try{database.save();        }
        catch(Unusable e){
            System.out.println("Database is empty");
        }
        resume();
    }

    //try again if method fails
    private boolean tryAgain(){
        Character c = null;
        boolean invalidDecision = true;

        System.out.println("Would you like to try again?\n Y(yes) N(no)");
        do{
            try{
                c = scan.next().charAt(0);
                c = Character.toUpperCase(c);
                if(c != 'Y' && c != 'N'){
                    throw new InputMismatchException();
                }
                invalidDecision = false;
                scan.nextLine();
            }
            catch(InputMismatchException e) {
                System.out.println("Please enter a valid character: Y(yes) or N(no)");
                scan.nextLine();
            }
        }
        while(invalidDecision);

        if (c == 'Y'){return true;}
        else{return false;}
    }

    //Lets user decide if they need to keep going or are finished
    private void resume(){
        boolean invalidDecision = true;
        Character decision = null;

        System.out.println("Would you like to keep going?\nY(yes) or N(no)");
        do{
            try{
                decision = scan.next().charAt(0);
                decision = Character.toUpperCase(decision);
                if(decision != 'Y' && decision != 'N'){
                    throw new InputMismatchException();
                }
                invalidDecision = false;
            }
            catch (InputMismatchException e){
                System.out.println("Please enter Y(yes) or N(no)");
            }
        }
        while(invalidDecision);

        if(decision == 'Y'){
            mainMenu();
        }
        else{
            invalidDecision = true;
            System.out.println("Would you like to save before you leave?\nY(yes) or N(no)");

            do{
                try{
                    decision = scan.next().charAt(0);
                    decision = Character.toUpperCase(decision);
                    if(decision != 'Y' && decision != 'N'){
                        throw new InputMismatchException();
                    }
                    invalidDecision = false;
                }
                catch (InputMismatchException e){
                    System.out.println("Please enter Y(yes) or N(no)");
                    scan.next();
                }
            }
            while(invalidDecision);

            if (decision == 'Y'){
                try{
                    database.save();
                    System.out.println("Save Successful!\nGoodbye!");
                }
                catch(Unusable e){
                    System.out.println("Database is empty");
                }
            }
            else{
                System.out.println("Goodbye!");
            }
        }
    }
    public static void main(String[] args) {
        DatabaseApp menu = new DatabaseApp();
        menu.mainMenu();
    }
}