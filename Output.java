public void printOutput () {
    PrintWriter writer = null;
    try {
        writer = new PrintWriter(this.fileName + ".out", "UTF-8");

        int noOfSlices = pizzaCutter.cutSlices.size();

        writer.print(noOftags);
        writer.println();

        for (int j = 0; j < noOfSlices; j++) {
            Slice cutSlice = pizzaCutter.cutSlices.get(j);
            writer.print(cutSlice.startX + " " + cutSlice.startY + " " + cutSlice.endX + " " + cutSlice.endY);
            writer.println();
        }
        writer.close();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    }