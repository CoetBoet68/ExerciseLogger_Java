
public class exValues {
    private int ID,goal, max;
    private String name,unit;
    private double calories;
    
    public exValues(int _ID, String _name, double _calories, String _unit, int _Goal, int _Max){
        ID = _ID;
        name = _name;
        calories = _calories;
        unit = _unit;
        goal = _Goal;
        max = _Max;
    }
    public int getID(){
        return ID;
    }
    public String getName(){
        return name;
    }
    public double getCalories(){
        return calories;
    }
    public String getUnit(){
        return unit;
    }
    public int getGoal(){
        return goal;
    }
    public int getMax(){
        return max;
    }
    public void setName(String _name){
        name = _name;
    }
    public void setCalories(double _calories){
        calories = _calories;
    }
    public void setUnit(String _unit){
        unit = _unit;
    }
    public void setGoal(int _goal){
        goal = _goal;
    }
    public void setMax(int _max){
        max = _max;
    }
    public String insertSpecific(){
        return "INSERT INTO Exercise (e_ID, e_Name, e_caloriePerRep, e_Unit, e_Goal, e_Max) " +
               "VALUES (0,'" + name + "'," + calories + ",'" + unit + "'," + goal + "," + max +");";
    }
    public String deleteSpecific(){
        return "DELETE * FROM Exercise WHERE e_Name ='" + name + "';";
    }
    public String updateSpecific(int newGoal){
        return "UPDATE Exercise SET e_Goal = " + newGoal + " WHERE e_ID = " + ID + ";";
    }
}
