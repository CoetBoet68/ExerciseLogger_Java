import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class recordsHolder {
    private ArrayList<recordObject> records = new ArrayList();
    ResultSet rs;
    DB_Comms db = new DB_Comms();
    private int ID;
    private double cals;
    private SimpleDateFormat daysf = new SimpleDateFormat("MM/dd");
    private SimpleDateFormat hoursf = new SimpleDateFormat("hh:mm");
    
    public recordsHolder(int _ID) throws SQLException{
        double calories = getCalories(_ID);
        rs = db.query("SELECT day, hour, amount " +
                        "FROM Records " +
                            "WHERE e_ID = "+ _ID + ";");
        while(rs.next()){
            String day = rs.getString("day");
            String hour = rs.getString("hour");
            int amount = rs.getInt("amount");
            records.add(new recordObject(_ID, day, hour, amount, calories));
        }
        ID = _ID;
        cals = calories;
    }
    private double getCalories(int exID) throws SQLException{
        double calories = 0.0;
        rs = db.query("SELECT e_caloriePerRep " +
                "FROM Exercise " +
                "WHERE e_ID = " + exID + ";");
        if(rs.next()){
            calories = rs.getDouble("e_caloriePerRep");
        }
        return calories;
    }
    public ArrayList<recordObject> getArray(){
        return records;
    }
    public void addRecord(int amount) throws SQLException{
        Timestamp tm = new Timestamp(System.currentTimeMillis());
        String hour = hoursf.format(tm);
        String day = daysf.format(tm);
        records.add(new recordObject(ID, day, hour, amount, cals));
        db.update(records.get(records.size()-1).getInsertQuery());
        updateMax();
    }
    public void updateMax() throws SQLException{
        int max = records.get(0).getAmount();
        int index = 0;
        for(int i = 1; i < records.size(); i++){
            if(records.get(1).getAmount() > max){
                max = records.get(i).getAmount();
                index = i;
            }
        }
        String maxUpdate = records.get(index).getMaxQuery();
        db.update(maxUpdate);
    }
}
