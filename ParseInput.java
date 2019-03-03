public void parseInput () {
  int bufferSize = 8 * 1024;
  BufferedReader bufferedReader = null;
  try {
      bufferedReader = new BufferedReader(new FileReader(this.fileName + ".in"), bufferSize);
      String line = bufferedReader.readLine();
      String[] firstLine = line.split(" ");
      pizza = new Pizza();

      pizza.rows = Integer.parseInt(firstLine[0]);
      pizza.cols = Integer.parseInt(firstLine[1]);
      pizza.rowLength = Integer.toString(pizza.rows).length();
      pizza.colLength = Integer.toString(pizza.cols).length();
      pizza.minIngredientEachPerSlice = Integer.parseInt(firstLine[2]);
      pizza.maxCellsPerSlice = Integer.parseInt(firstLine[3]);
      pizza.cells = new HashMap<>();

      for (int i = 0; i < pizza.rows; i++) {
          String l = bufferedReader.readLine();
          char[] arr = l.toCharArray();
          for (int j = 0; j < pizza.cols; j++) {
              String cellHashKey = pizza.getCellHashKey(i, j);
              Cell cell = new Cell();
              cell.x = i;
              cell.y = j;
              cell.ingredient = arr[j];
              pizza.cells.put(cellHashKey, cell);
          }
       }
  } catch (IOException e) {
     e.printStackTrace();
  }
}