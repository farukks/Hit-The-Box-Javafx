package sample;

import javafx.scene.control.Button;
import javafx.scene.text.Text;


public class CellLogic {

    // we set this part as static because we want to provide global access.
    // we defined score, currentLevel, highScore variables as integer.
    static int score = 0;
    static int currentLevel = 1;
    static int highScore = 0;
    // we have defined it as scoreText, currentLevelText, highScoreText, logText as text and nextLevelBtn as button.
    static Text scoreText;
    static Text currentLevelText;
    static Text highScoreText;
    static Button nextLevelBtn;
    static Text logText;
    // we defined the log as string.
    static String log;

    public static void setLogic(Cell cell,Grid grid){

        // mouse hover cell.
        cell.setOnMouseEntered(e ->{
            cell.highlight();
        });
        // mouse stop hover cell.
        cell.setOnMouseExited(e ->{
            cell.unhighlight();
        });

        // click cell.
        cell.setOnMouseClicked(e -> {
            log = "";

            log = String.format("Box(%d,%d)",cell.getRow(),cell.getColumn());

            // we are looking to explore 4 neighbour around a cell.
            int[] col_direction = new int[]{0, 0, 1, -1};
            int[] row_direction = new int[]{-1, 1, 0, 0};

            // we check if cell is mirror or wood.
            if(cell.getLife() > 0){

                // we count cell got hit.
                int neighbourEffectCount = 1;

                // at this part we decrease life and re-render cell.
                cell.setLife(cell.getLife() - 1);
                cell.animate();

                // we check 4 neighbour cell.
                for (int i = 0; i < 4; i++) {
                    int neighbourRow = cell.getRow() + row_direction[i];
                    int neighbourCol = cell.getColumn() + col_direction[i];

                    // we check if outside the grid.
                    if(neighbourRow < 0 || neighbourCol < 0 || neighbourRow > grid.getRows() - 1 || neighbourCol > grid.getColumns()){
                        continue;
                    }
                    Cell neighbourCell = grid.getCell( neighbourRow,neighbourCol );
                    System.out.println(neighbourCell.toString());

                    // we cell is wood or mirror.
                    if(neighbourCell.getLife() > 0){
                        neighbourCell.setLife(neighbourCell.getLife() - 1);
                        neighbourCell.animate();

                        // we count total cell got hit.
                        neighbourEffectCount++;

                        log += String.format(" Hit(%d,%d)",neighbourCell.getRow(),neighbourCell.getColumn());
                    }
                }
                // we calculate score by total cell got hit.
                CellLogic.calculateScore(neighbourEffectCount);

                // we check if finish all cell.
                checkShowNextLevel(grid);

            }

        });
    }
    // The next level button becomes visible here. In order for the button to become visible, no wood or mirror type block should remain on the grid.
    public static void checkShowNextLevel(Grid grid){
        boolean isEmpty = grid.isNoWoodAndMirror();

        if(isEmpty){
            nextLevelBtn.setVisible(true);
        }

    }
    // score is calculated here. The score to be earned for each different event over neighboring blocks affected by the switch is specified here.
    public static void calculateScore(int neighbourEffectCount){
        int score = 0;
        switch (neighbourEffectCount){
            case 1:
                score = -3;
                break;
            case 2:
                score = -1;
                break;
            case 3:
                score = 1;
                break;
            case 4:
                score = 2;
                break;
            case 5:
                score = 4;
                break;
        }

        CellLogic.score += score;
        System.out.println("s: " + CellLogic.score);

        scoreText.setText(String.valueOf( CellLogic.score));

        if(CellLogic.score > CellLogic.highScore){
            CellLogic.highScore = CellLogic.score;
            highScoreText.setText("HS: " + CellLogic.highScore);
            SaveAndLoadData.saveHighScore(CellLogic.highScore);
        }

        log += " : " + score + " points";
        logText.setText(log);

    }

    public static Button getNextLevelBtn() {
        return nextLevelBtn;
    }

    public static void setNextLevelBtn(Button nextLevelBtn) {
        CellLogic.nextLevelBtn = nextLevelBtn;
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        CellLogic.score = score;
    }

    public static int getCurrentLevel() {
        return currentLevel;
    }

    public static void setCurrentLevel(int currentLevel) {
        CellLogic.currentLevel = currentLevel;
    }

    public static Text getScoreText() {
        return scoreText;
    }

    public static void setScoreText(Text scoreText) {
        CellLogic.scoreText = scoreText;
    }

    public static Text getCurrentLevelText() {
        return currentLevelText;
    }

    public static void setCurrentLevelText(Text currentLevelText) {
        CellLogic.currentLevelText = currentLevelText;
    }

    public static int getHighScore() {
        return highScore;
    }

    public static void setHighScore(int highScore) {
        CellLogic.highScore = highScore;
    }

    public static Text getHighScoreText() {
        return highScoreText;
    }

    public static void setHighScoreText(Text highScoreText) {
        CellLogic.highScoreText = highScoreText;
    }
    public static Text getLogText() {
        return logText;
    }

    public static void setLogText(Text logText) {
        CellLogic.logText = logText;
    }

}
