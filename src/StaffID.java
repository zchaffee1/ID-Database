public class StaffID extends ID{
    StaffID(char position, String aNum, String firstName, String lastName, int age, String parameter)throws Unusable {
        super(position, aNum, firstName, lastName, age, parameter);
    }

    @Override
    protected void setParameter(String parameter) throws Unusable{
        int salary;
        try{
            salary = Integer.parseInt(parameter);
        }
        catch(Exception e){
            throw new Unusable("Invalid salary");
        }
        
        if(salary < 0){
            throw new Unusable("Invalid salary");
        }

        this.parameter = parameter;
    }
}
