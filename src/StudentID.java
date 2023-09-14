public class StudentID extends ID{
    StudentID(char position, String aNum, String firstName, String lastName, int age, String parameter)throws Unusable {
        super(position, aNum, firstName, lastName, age, parameter);
    }

    @Override
    protected void setParameter(String parameter) throws Unusable{
        if(parameter.equals("BSc") || parameter.equals("MSc") || parameter.equals("PhD")){
            this.parameter = parameter;
        }
        else{
            throw new Unusable("Invalid position parameter");
        }   
    }
}
