package sample;

import javafx.geometry.Insets;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Scanner;

public class SaveAndLoadData {

    // load level from level.txt file
    // return a new grid
    public static Grid loadLevel(int level){

        Grid grid = new Grid(10,10,620,620);
        grid.setLayoutX(50);

        // load data from file into map, use it to check row,col have in file;
        HashMap<String,Integer> loadedDataMap = new HashMap<>();

        try {
            File file = new File("src/levels/level"+level+".txt");

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                // read line by line from file.
                String line = scanner.nextLine();

                // split string by "," : "a,b,c" -> ["a","b","c"]
                String[] items = line.split(",");

                //type -- life
                //empty  -- 0
                //mirror -- 1
                //wood   -- 2
                switch (items[0]){
                    case "Empty":{
                        // if type is empty , put into map key: row,col,value: life
                        loadedDataMap.put(items[1] + "," + items[2],0);
                        break;
                    }
                    case "Mirror":{
                        loadedDataMap.put(items[1] + "," + items[2],1);
                        break;
                    }
                    case "Wood":{
                        loadedDataMap.put(items[1] + "," + items[2],2);
                        break;
                    }
                }
            }
            scanner.close();

        }catch (Exception e){
            System.out.println("error: " + e);
        }

        for(int y = 0 ; y < 10; y++){
            for(int x = 0;x < 10;x++){
                Cell cell;

                // if map exist row and col  : create cell by life from map.
                if(loadedDataMap.get(y+","+x) != null){
                    cell = new Cell(y,x,loadedDataMap.get(y+","+x));
                }
                else{
                    // create cell by life = -1 (wall)
                    cell = new Cell(y,x,-1);
                }

                cell.setPadding(new Insets(2,2,2,2));

                // set logic when interact with cell.
                CellLogic.setLogic(cell,grid);

                grid.add(cell,y,x);
            }
        }

        return grid;
    }

    // save high score into file.
    public static void saveHighScore(int score){
        try {
            File output = new File("src/levels/highScore.txt");
            FileWriter writer = new FileWriter(output);

            writer.write(String.valueOf(score));
            writer.flush();
            writer.close();
        }catch (Exception e){
            System.out.println("file error: " + e);
        }
    }

    // load high score from file.
    public static int loadHighScore(){
        int score = 0;
        try {
            File file = new File("src/levels/highScore.txt");

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String scoreText = scanner.nextLine();
                score = Integer.valueOf(scoreText);
            }
            scanner.close();

        }catch (Exception e){
            System.out.println("file error: " + e);
            score = 0;
        }

        return score;
    }

    // save grid data, current level , current score.
    public static void saveGame(Grid grid, Integer currentLevel,Integer currentScore){
        try {
            File output = new File("src/levels/game.txt");
            FileWriter writer = new FileWriter(output);

            // write current Level into file.
            writer.write(String.valueOf(currentLevel) + "\n");
            // write current score into file.
            writer.write(String.valueOf(currentScore) + "\n");

            // write all cells that is not wall into file.
            for(int row = 0 ; row < grid.getRows();row++){
                for(int col = 0 ; col < grid.getColumns();col++){
                    if(grid.getCell(row,col).getLife() == 1){
                        writer.write(String.format("Mirror,%d,%d\n",row,col));
                    }
                    else if(grid.getCell(row,col).getLife() == 2){
                        writer.write(String.format("Wood,%d,%d\n",row,col));
                    }
                    else if(grid.getCell(row,col).getLife() == 0){
                        writer.write(String.format("Empty,%d,%d\n",row,col));
                    }
                }
            }

            writer.flush();
            writer.close();
        }catch (Exception e){
            System.out.println("file error: " + e);
        }
    }

    public static LoadGameData loadGame(){
        int currentLevel = 0;
        int score = 0;
        Grid grid = new Grid(10,10,620,620);
        grid.setLayoutX(50);
        HashMap<String,Integer> loadedDataMap = new HashMap<>();

        try {
            File file = new File("src/levels/game.txt");

            Scanner scanner = new Scanner(file);

            // read currentLevel text from file.
            if(scanner.hasNextLine()){
                String levelText = scanner.nextLine();
                currentLevel = Integer.valueOf(levelText);
            }
            if(scanner.hasNextLine()){
                String scoreText = scanner.nextLine();
                score = Integer.valueOf(scoreText);
            }

            // read grid data nearly same as load level function().
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String[] items = line.split(",");

                switch (items[0]){
                    case "Empty":{
                        loadedDataMap.put(items[1] + "," + items[2],0);
                        break;
                    }
                    case "Mirror":{
                        loadedDataMap.put(items[1] + "," + items[2],1);
                        break;
                    }
                    case "Wood":{
                        loadedDataMap.put(items[1] + "," + items[2],2);
                        break;
                    }
                }
            }
            scanner.close();

        }catch (Exception e){
            System.out.println("error: " + e);
            return null;
        }

        for(int y = 0 ; y < 10; y++){
            for(int x = 0;x < 10;x++){
                Cell cell;
                if(loadedDataMap.get(y+","+x) != null){
                    cell = new Cell(y,x,loadedDataMap.get(y+","+x));
                }
                else{
                    cell = new Cell(y,x,-1);
                }

                cell.setPadding(new Insets(2,2,2,2));


                CellLogic.setLogic(cell,grid);

                grid.add(cell,y,x);
            }
        }

        // data loaded in here.
        return new LoadGameData(grid,currentLevel,score);

    }

}
