public abstract class ID implements Printable{
    private String firstName, lastName, ANumber;
    private int age;
    private char position;
    protected String parameter;

    ID (char position, String aNum, String firstName, String lastName, int age, String parameter) throws Unusable{
        setPosition(position);
        setANumber(aNum);
        setFirstName(firstName);
        setLastName(lastName);
        setAge(age);
        setParameter(parameter);
    }

    private void setANumber(String ANumber) throws Unusable{
        if(ANumber.length() != 9 || ANumber.charAt(0)!= 'A'){
            throw new Unusable("Invalid ANumber");
        }
        this.ANumber = ANumber;
    }

    private void setFirstName(String firstName) throws Unusable{
        if(firstName.length() == 0){
            throw new Unusable("First Name Empty");
        }

        this.firstName = firstName;
    }

    private void setLastName(String lastName) throws Unusable{
        if(lastName.length() == 0){
            throw new Unusable("Last Name Empty");
        }

        this.lastName = lastName;
    }

    private void setAge(int age) throws Unusable{
        if(age <= 0){
            throw new Unusable("Invalid age");
        }
        this.age = age;
    }

    private void setPosition(char position) throws Unusable{
        position = Character.toUpperCase(position);
        if(position != 'S' && position != 'F' && position != 'T'){
            throw new Unusable("Invalid position");
        }
        this.position = position;
    }

    @Override
    public void print() {
        System.out.printf("%-10c%-12s%-15s%-20s%-5d%-10s\n", 
                          getPosition(), getANum(), getFirstName(), 
                          getLastName(), getAge(), getParameter());
    }
    

    protected abstract void setParameter(String parameter) throws Unusable;

    public String getFirstName(){return this.firstName;}
    public String getLastName(){return this.lastName;}
    public String getANum(){return this.ANumber;}
    public int getAge(){return this.age;}
    public char getPosition(){return this.position;}
    public String getParameter(){return this.parameter;}
}
