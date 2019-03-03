public class Pizza1 { 
    int rows;
    int cols;
    int minIngredientEachPerSlice;
    int maxCellsPerSlice;
    HashMap<String, Cell> cells;
    int rowLength;
    int colLength;
    
    public String getCellHashKey (int x, int y) {
        return String.format("%0" + rowLength + "d", x) +    String.format("%0" + colLength + "d", y);
    }
}