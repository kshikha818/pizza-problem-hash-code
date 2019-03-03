public class PizzaCutter {
   
    Pizza pizza;
    ArrayList<Slice> cutSlices = new ArrayList<>();
   
    public PizzaCutter (Pizza pizza) {
       this.pizza = pizza;
    }
   
    public void cutPizza() {
        int noPerMiniSlice = pizza.maxCellsPerSlice/2;
        for (int i = 0; i < pizza.rows; i++) {
            for (int j = 0; j < pizza.cols; j++) {
                String cellKey = pizza.getCellHashKey(i, j);
                Cell cell = pizza.cells.get(cellKey);
                ArrayList<Cell> sliceCells = getMiniSlice(cell.x, cell.y, noPerMiniSlice);

                if (j+1 >= pizza.cols) {
                    if (checkForSufficient(sliceCells)) {
                        insertASlice(sliceCells);
                        continue;
                    }
                }
                ArrayList<Cell> nextSliceCells = getMiniSlice(i, j+1, noPerMiniSlice);
                if (checkForSufficient(sliceCells)) {
                    if (checkForSufficient(nextSliceCells)) {
                        insertASlice(sliceCells);
                    } else {
                        sliceCells.addAll(nextSliceCells);
                        insertASlice(sliceCells);
                        j++;
                    }
                } else {
                    if (checkForSufficient(nextSliceCells)) {
                        sliceCells.addAll(nextSliceCells);
                        insertASlice(sliceCells);
                        j++;
                    } else {
                        sliceCells.addAll(nextSliceCells);
                        if (checkForSufficient(sliceCells)) {
                            insertASlice(sliceCells);
                            j++;
                        }
                        continue;
                    }
                }
            }
            i = i + noPerMiniSlice - 1;
        }
    }
   
   public void insertASlice(ArrayList<Cell> sliceCells) {
        this.markAllSliceCellsAsCut(sliceCells);
        Cell firstSlicedCell = getLeastCell(sliceCells);
        Cell lastSlicedCell = getMaxCell(sliceCells);
        Slice slice = new Slice();
        slice.startX = firstSlicedCell.x;
        slice.startY = firstSlicedCell.y;
        slice.endX = lastSlicedCell.x;
        slice.endY = lastSlicedCell.y;
        cutSlices.add(slice);
    }

    public boolean checkForSufficient(ArrayList<Cell> sliceCells) {
        return this.doesSliceSatisfyMaximumAreaRule(sliceCells) &&
                allCellsAreNotPreviouslyCut(sliceCells) &&
                this.doesSliceSatisfyMinimumIngredientRule(sliceCells);
    }
   
    private void markAllSliceCellsAsCut(ArrayList<Cell> sliceCells) {
        for (int i = 0; i < sliceCells.size(); i++) {
            Cell cell = sliceCells.get(i);
            cell.cutOut = true;
            String cellHashKey = pizza.getCellHashKey(cell.x, cell.y);
            pizza.cells.put(cellHashKey, cell);
        }
    }
  
  public ArrayList<Cell> getMiniSlice(int i, int j, int noPerMiniSlice) {
        HashMap<String, Cell> cellsToSlice = new HashMap();
        for (int m = 0; m < noPerMiniSlice; m++) {
            int x = m + i;
            int y = j;
            if (x >= pizza.rows || y >= pizza.cols) {
                continue;
            }
            String cellKey = pizza.getCellHashKey(m + i, j);
            Cell cell = pizza.cells.get(cellKey);
            cellsToSlice.put(cellKey, cell);
        }
        return new ArrayList<>(cellsToSlice.values());
    }
    
    private boolean allCellsAreNotPreviouslyCut(ArrayList<Cell> sliceCells) {
        for (int i = 0; i < sliceCells.size(); i++) {
            Cell cell = sliceCells.get(i);
            if (cell == null) {
                return false;
            }
            if (cell.cutOut) {
                return false;
            }
        }
        return true;
    }
    
    private boolean doesSliceSatisfyMinimumIngredientRule(ArrayList<Cell> sliceCells){
        int mushroomsCount = 0;
        int tomatoCount = 0;
        for (int i = 0; i < sliceCells.size(); i++) {
            Cell cell = sliceCells.get(i);
            if (cell.ingredient == 'T') {
                tomatoCount++;
            } else {
                mushroomsCount++;
            }
        }
        return (mushroomsCount >= pizza.minIngredientEachPerSlice && tomatoCount >= pizza.minIngredientEachPerSlice);
    }
    
    private Cell getLeastCell(ArrayList<Cell> slicedCells)
    {
        int minimumCellNumber = Integer.MAX_VALUE;
        int size = slicedCells.size();
        Cell leastCell = null;
        for (int i = 0; i < size; i++) {
            Cell cell = slicedCells.get(i);
            String cellKey = pizza.getCellHashKey(cell.x, cell.y);
            int cellKeyIntValue = Integer.parseInt(cellKey);
            if (cellKeyIntValue < minimumCellNumber) {
                minimumCellNumber = cellKeyIntValue;
                leastCell = cell;
            }
        }
        return leastCell;
    }
    
    private boolean doesSliceSatisfyMaximumAreaRule(ArrayList<Cell> sliceCells) {
        return sliceCells.size() <= pizza.maxCellsPerSlice;
    }
    
    private Cell getMaxCell(ArrayList<Cell> slicedCells)
    {
        int maxCellNumber = 0;
        int size = slicedCells.size();
        Cell maxCell = null;
        for (int i = 0; i < size; i++) {
            Cell cell = slicedCells.get(i);
            String cellKey = pizza.getCellHashKey(cell.x, cell.y);
            int cellKeyIntValue = Integer.parseInt(cellKey);
            if (cellKeyIntValue > maxCellNumber) {
                maxCellNumber = cellKeyIntValue;
                maxCell = cell;
            }
        }
        return maxCell;
    }
}