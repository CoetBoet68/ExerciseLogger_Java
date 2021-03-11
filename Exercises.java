import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Exercises {
    private ResultSet rs;
    private DB_Comms db = new DB_Comms();
    private ArrayList<exValues> allExes = new ArrayList();
    
    public Exercises(){
        try {
            updateArray();
        } catch (SQLException ex) {
            Logger.getLogger(Exercises.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void updateArray() throws SQLException{
        allExes.removeAll(allExes);
        rs = db.query("SELECT * FROM Exercise");
        while(rs.next()){
            int id = rs.getInt("e_ID");
            String name = rs.getString("e_Name");
            double calories = rs.getDouble("e_caloriePerRep");
            String unit = rs.getString("e_Unit");
            int goal = rs.getInt("e_Goal");
            int max = rs.getInt("e_Max");
            allExes.add(new exValues(id,name,calories,unit,goal,max));
        }
    }
    public int getNumberOfExercises() throws SQLException{
        int amo = 0;
        rs = db.query("SELECT COUNT(*) AS num FROM Exercise");
        if(rs.next()){
            amo = rs.getInt("num");
        }
        return amo;
    }
    public void addExercise(exValues ex) throws SQLException{
        db.update(ex.insertSpecific());
        updateArray();
    }
    public void deleteExercise(int index) throws SQLException{
        db.update(allExes.get(index).deleteSpecific());
        updateArray();
    }
    public void modifyExercise(int index, int newVal) throws SQLException{
        db.update(allExes.get(index).updateSpecific(newVal));
        updateArray();
    }
    public String getFieldValues(int exIndex, int valIndex){
        if(valIndex == 0){
            return allExes.get(exIndex).getGoal() + "";
        }else if(valIndex == 1){
            return allExes.get(exIndex).getMax() + "";
        }else{
            return allExes.get(exIndex).getName();
        }
    }
    public int getIDOfIndex(int index){
        return allExes.get(index).getID();
    }
}
