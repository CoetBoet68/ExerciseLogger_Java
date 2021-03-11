import java.text.DecimalFormat;

public class recordObject {
    private String day = "";
    private String hour = "";
    private int amount = 0;
    private double calorie = 0.0;
    private int e_ID = 0;
    private DecimalFormat df = new DecimalFormat("#.##");
    public recordObject(int eID, String _day, String _hour, int _amount, double _calorie){
        e_ID = eID;
        day = _day;
        hour = _hour;
        amount = _amount;
        calorie = ((double)_amount)*_calorie;
        
    }
    public int getExID(){
        return e_ID;
    }
    public String getDay(){
        return day;
    }
    public String getHour(){
        return hour;
    }
    public int getAmount(){
        return amount;
    }
    public double getCalorie(){
        return calorie;
    }
    public void setID(int _ID){
        e_ID = _ID;
    }
    public void setDay(String _day){
        day = _day;
    }
    public void setHour(String _hour){
        hour = _hour;
    }
    public void setAmount(int _amount){
        amount = _amount;
    }
    public void setCalorie(double _calorie){
        calorie = _calorie;
    }
    @Override
    public String toString(){
        return e_ID + " " + day + " " + hour + " " + amount + " " + calorie;
    }
    public String[] getTableValue(){
        String _day = day;
        String _hour = hour;
        String _amount = amount + "";
        String _calorie = df.format(calorie);
        return new String[]{_day, _hour, _amount, _calorie};
    }
    public String getInsertQuery(){
        return "INSERT INTO RECORDS(ID, e_ID, amount, day, hour) "+
                "VALUES(0, " + e_ID + ", " + amount + ", '" + day + "', '" + hour + "');";
    }
    public String getMaxQuery(){
        return "UPDATE Exercise SET e_Max = " + amount + " WHERE e_ID = " + e_ID +";";
    }
    
}
