public void parseInput () {
  int bufferSize = 8 * 1024;
  BufferedReader bufferedReader = null;
  try {
      bufferedReader = new BufferedReader(new FileReader(this.fileName + ".in"), bufferSize);
      String line = bufferedReader.readLine();
      String[] firstLine = line.split(" ");
       = new ();

      image.row = Integer.parseInt(firstLine[0]);
      image.col = Integer.parseInt(firstLine[1]);
      image.row = Integer.toString(image.row).length();
      image.col = Integer.toString(image.col).length();
      image.cells = new HashMap<>();

      for (int i = 0; i < image.row; i++) {
          String l = bufferedReader.readLine();
          char[] arr = l.toCharArray();
          for (int j = 0; j < image.col; j++) {
              String cellHashKey = image.getCellHashKey(i, j);
              Cell cell = new Cell();
              cell.x = i;
              cell.y = j;
              cell.ingredient = arr[j];
              image.cells.put(cellHashKey, cell);
          }
       }
  } catch (IOException e) {
     e.printStackTrace();
  }
}