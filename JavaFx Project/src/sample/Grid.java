package sample;

import javafx.scene.layout.Pane;
// in this section, we have defined integer rows columns width and height.
// with Cells [] [] we have defined cell as an array.
//
public class Grid extends Pane {
    int rows;
    int columns;

    int width;
    int height;

    Cell[][] cells;
    // we created the grid in this part with the values of rows and columns.
    public Grid( int rows, int columns, int width, int height) {
        this.columns = columns;
        this.rows = rows;
        this.width = width;
        this.height = height;

        cells = new Cell[rows][columns];
    }

    // we set the necessary calculations as double. We found it by dividing width by column and dividing by height by dividing rows.
    // if x and y are, we made these calculations by multiplying the result by column and row.
    // we adjusted the layouts, we made the preferred height and length settings from the calculations.
    public void add(Cell cell, int row, int column) {

        cells[row][column] = cell;

        // calculate cell x,y,width,height
        double w = width / columns;
        double h = height / rows;
        double x = w * column;
        double y = h * row;

        cell.setLayoutX(x);
        cell.setLayoutY(y);
        cell.setPrefWidth(w);
        cell.setPrefHeight(h);

        getChildren().add(cell);

    }
    // getCell(int row, int column) returns back to cells[row][column].
    public Cell getCell(int row, int column) {
        return cells[row][column];
    }
    // we wrote it to remove the highlight on cells[row][col] with unhighlight() .
    public void unhighlight() {
        for( int row=0; row < rows; row++) {
            for( int col=0; col < columns; col++) {
                cells[row][col].unhighlight();
            }
        }
    }

    // check grid have no wood cell and mirror cell
    public boolean isNoWoodAndMirror(){
        boolean isEmpty = true;
        for( int row=0; row < rows; row++) {
            for( int col=0; col < columns; col++) {
                if(cells[row][col].getLife() > 0){
                    isEmpty = false;
                    break;
                }
            }
            if(!isEmpty){
                break;
            }
        }
        return isEmpty;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}