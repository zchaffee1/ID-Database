public class FacultyID extends ID{
    FacultyID(char position, String aNum, String firstName, String lastName, int age, String parameter)throws Unusable {
        super(position, aNum, firstName, lastName, age, parameter);
    }

    @Override
    protected void setParameter(String parameter) throws Unusable{
        if (parameter.equals("AS") || parameter.equals("AURB") || parameter.equals("ARCH") || parameter.equals("AAH") || parameter.equals("BIOL") || parameter.equals("BME") || parameter.equals("BUS") || parameter.equals("CHE"
       ) || parameter.equals("CHEM") || parameter.equals("CAE") || parameter.equals("COM") || parameter.equals("CS") || parameter.equals("CSP") || parameter.equals("ECON") || parameter.equals("ECE") || parameter.equals("EG" 
       ) || parameter.equals("EMGT") || parameter.equals("ENVE") || parameter.equals("EMS") || parameter.equals("FMC") || parameter.equals("FDSN") || parameter.equals("HIST") || parameter.equals("HUM") || parameter.equals("INTM" 
       ) || parameter.equals("ITM") || parameter.equals("ID") || parameter.equals("IDN") || parameter.equals("IDX") || parameter.equals("IPMM") || parameter.equals("IPRO") || parameter.equals("ITMD") || parameter.equals("ITMO" 
       ) || parameter.equals("ITMS") || parameter.equals("ITMT") || parameter.equals("LA") || parameter.equals("UG-LCHS") || parameter.equals("LIT") || parameter.equals("MSC") || parameter.equals("MAX") || parameter.equals("MSF" 
       ) || parameter.equals("MS") || parameter.equals("MSED") || parameter.equals("MATH") || parameter.equals("MBA") || parameter.equals("MMAE") || parameter.equals("MILS") || parameter.equals("NS") || parameter.equals("PHIL" 
       ) || parameter.equals("PS") || parameter.equals("PSYC") || parameter.equals("PA") || parameter.equals("SCI") || parameter.equals("SSCI") || parameter.equals("SOC") || parameter.equals("SSB") || parameter.equals("SMGT") || parameter.equals("TECH")){
            this.parameter = parameter;
        }
        else{
            throw new Unusable("Invalid Faculty Position");
        }
    }
}