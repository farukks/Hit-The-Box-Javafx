package sample;

import javafx.animation.FadeTransition;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class Cell extends StackPane {
    // column, row, and life are introduced as integers.
    int column;
    int row;
    int life = -1;

    ///////////////////////////Here is the basic logic of the game.
/////// type -- life/////// Since the game progresses on the boxes and the points provided by the boxes, the box types are determined from here.
/////// wall   -- -1/////// wall type defined as -1.
/////// empty  -- 0//////// empty type defined as 0.
/////// mirror -- 1//////// mirror type defined as 1.
/////// wood   -- 2//////// wood type defined as 2.
///////////////////////////Here is the basic logic of the game.
    public Cell(int row, int column,int life) {

        this.column = column;
        this.row = row;
        this.life = life;

        this.render();
    }

    // animation
    public void animate(){

        // by calling FadeTransition, opacity is decrease from 1 to 0.
        FadeTransition ft = new FadeTransition(Duration.millis(150), this);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.play();

        // when decrease animation finished.
        ft.setOnFinished(event -> {
            // render colored by integer life.
            render();

            // play animation increase opacity from 0 to 1.
            FadeTransition ft2 = new FadeTransition(Duration.millis(150), this);
            ft2.setFromValue(0.0);
            ft2.setToValue(1.0);
            ft2.play();
        });

    }

    public void render(){
        // with the getStyleClass().remove method, the style files are removed.
        this.getStyleClass().remove("cell-wall");
        this.getStyleClass().remove("cell-empty");
        this.getStyleClass().remove("cell-mirror");
        this.getStyleClass().remove("cell-wood");

        // Different cases are determined with switch, style file is assigned to each of them by getStyleClass (). add method for each type.
        switch (life){
            case -1:{
                this.getStyleClass().add("cell-wall"); // css style for wall type.
                break;
            }
            case 0:{
                this.getStyleClass().add("cell-empty"); // css style for empty type.
                break;
            }
            case 1:{
                this.getStyleClass().add("cell-mirror"); // css style for mirror type.
                break;
            }
            case 2:{
                this.getStyleClass().add("cell-wood"); // css style for wood type.
                break;
            }
        }
    }

    // the settings of the highlight() method for adding visually.
    // opacity set to 0.5
    public void highlight() {
        setOpacity(0.5);
    }
    // when the user pulls the mouse over the cell, unhighlight is activated and the opacity default value returns to 1.
    public void unhighlight() {
        setOpacity(1);
    }
    // the toString() method returns this.column and this.row .
    public String toString() {
        return this.column + "/" + this.row;
    }
    // the int getColumn() method returns to the column.
    public int getColumn() {
        return column;
    }
    // the setColumn(int column) method sets this.column as a column.
    public void setColumn(int column) {
        this.column = column;
    }
    // the getRow () method returns row.
    public int getRow() {
        return row;
    }
    // the setRow(int row) method sets this.row as a row.
    public void setRow(int row) {
        this.row = row;
    }
    // the getLife () method returns to life.
    public int getLife() {
        return life;
    }
    // the setLife (int life) method sets this.life as life.
    public void setLife(int life) {
        this.life = life;
    }
}