package com.example.demo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class HelloController {
    @FXML
    Label lblTurn, lblResult, lblTaken, wonLabel;
    @FXML
    Button btnStart, vacaB, dessertB, animalsB, flowersB, again;
    @FXML
    GridPane gdpPlayGrid;
    @FXML
    ImageView back1;
    @FXML
    ListView allwords;

    private GridSpot[][] board = new GridSpot[10][10];
    ArrayList<String> words = new ArrayList<>();
    private Button[][] boardSpotsBTN = new Button[10][10];
    int rowIndex, colIndex;
    private boolean tie = false;
    String theme = "";
    private int rowInd1;
    private int colInd1;
    private int rowInd2;
    private int colInd2;
    private int turn = 0;

    public void colorPicker(int x, int y) {
        if (theme.equals("vacation")) {
            boardSpotsBTN[x][y].setStyle("-fx-background-color: #CFE3FF; -fx-border-color: #91BCFC; -fx-border-width: 2px;");
        }
        if (theme.equals("flowers")) {
            boardSpotsBTN[x][y].setStyle("-fx-background-color: #ff689f; -fx-border-color: #ff005b; -fx-border-width: 2px;");
        }
        if (theme.equals("dessert")) {
            boardSpotsBTN[x][y].setStyle("-fx-background-color: #d390f5; -fx-border-color: #c23fff; -fx-border-width: 2px;");
        }
        if (theme.equals("animals")) {
            boardSpotsBTN[x][y].setStyle("-fx-background-color: #ffd67f; -fx-border-color: #ffaf00; -fx-border-width: 2px;");
        }
    }

    public void colorPicker2(int x, int y) {
        if (theme.equals("vacation")) {
            boardSpotsBTN[x][y].setStyle("-fx-background-color: #CFE3FF; -fx-border-color: #000000; -fx-border-width: 3px;");
        }
        if (theme.equals("flowers")) {
            boardSpotsBTN[x][y].setStyle("-fx-background-color: #ff689f; -fx-border-color: #000000; -fx-border-width: 3px;");
        }
        if (theme.equals("dessert")) {
            boardSpotsBTN[x][y].setStyle("-fx-background-color: #d390f5; -fx-border-color: #000000; -fx-border-width: 3px;");
        }
        if (theme.equals("animals")) {
            boardSpotsBTN[x][y].setStyle("-fx-background-color: #ffd67f; -fx-border-color: #000000; -fx-border-width: 3px;");
        }
    }

    @FXML
    protected void playagain() {
        for (int i = 0; i < boardSpotsBTN.length; i++) {
            for (int j = 0; j < boardSpotsBTN.length; j++) {
                boardSpotsBTN[i][j].setStyle("-fx-background-color: #d3d3d3; -fx-border-color: #d3d3d3; -fx-border-width: 2px;");
                boardSpotsBTN[i][j].setText("-");
            }
        }
        again.setVisible(false);
        vacaB.setDisable(false);
        dessertB.setDisable(false);
        animalsB.setDisable(false);
        flowersB.setDisable(false);
        wonLabel.setVisible(false);
    }

    @FXML
    protected void vaca() {
        theme = "vacation";
        words.addAll(Arrays.asList("beach", "surfboard", "sea", "ocean", "sand", "swim", "dolphin", "crab"));
        btnStart.setDisable(false);
        vacaB.setDisable(true);
        dessertB.setDisable(true);
        animalsB.setDisable(true);
        flowersB.setDisable(true);
        try {
            FileInputStream input1 = new FileInputStream("src/main/Pictures/beachback.jpg");

            back1.setImage(new Image(input1));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void dessert() {
        theme = "dessert";
        words.addAll(Arrays.asList("pastry", "cake", "candy", "truffle", "pie", "cookies", "mousse"));
        btnStart.setDisable(false);
        vacaB.setDisable(true);
        dessertB.setDisable(true);
        animalsB.setDisable(true);
        flowersB.setDisable(true);
        try {
            FileInputStream input1 = new FileInputStream("src/main/Pictures/dessertback.jpg");

            back1.setImage(new Image(input1));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void animals() {
        theme = "animals";
        words.addAll(Arrays.asList("cow", "lion", "elephant", "chicken", "cat", "puppy", "giraffe", "hippo"));
        btnStart.setDisable(false);
        vacaB.setDisable(true);
        dessertB.setDisable(true);
        animalsB.setDisable(true);
        flowersB.setDisable(true);
        try {
            FileInputStream input1 = new FileInputStream("src/main/Pictures/farmback.jfif");

            back1.setImage(new Image(input1));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void flowers() {
        theme = "flowers";
        words.addAll(Arrays.asList("rose", "lily", "lotus", "tulip", "hibiscus", "gardenia", "daisy", "poppy"));
        btnStart.setDisable(false);
        vacaB.setDisable(true);
        dessertB.setDisable(true);
        animalsB.setDisable(true);
        flowersB.setDisable(true);
        try {
            FileInputStream input1 = new FileInputStream("src/main/Pictures/flowerback.jpg");

            back1.setImage(new Image(input1));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void start() {
        gdpPlayGrid.setHgap(10);
        gdpPlayGrid.setVgap(10);
        gdpPlayGrid.setPadding(new Insets(10));
        gdpPlayGrid.setGridLinesVisible(true);
        gdpPlayGrid.setAlignment(Pos.CENTER);

        allwords.getItems().addAll(words);

        btnStart.setDisable(true);
        for (int i = 0; i < boardSpotsBTN.length; i++) {
            for (int j = 0; j < boardSpotsBTN.length; j++) {
                boardSpotsBTN[i][j] = new Button();
                boardSpotsBTN[i][j].setText("-");
                boardSpotsBTN[i][j].setPrefHeight(400);
                boardSpotsBTN[i][j].setPrefWidth(400);
                board[i][j] = new GridSpot();
                gdpPlayGrid.add(boardSpotsBTN[i][j], j, i);
            }
        }

        for (String word : words) {
            int rndR = (int) (Math.random() * 9);
            int rndC = (int) (Math.random() * 9);
            int rndD = (int) ((Math.random() * 3));

            while (rndR + word.length() > 10) {
                rndR--;
            }
            while (rndC + word.length() > 10) {
                rndC--;
            }
            boolean check = checkWord(rndR, rndC, rndD, word);
            while (!check) {
                rndR = (int) (Math.random() * 9);
                rndC = (int) (Math.random() * 9);
                rndD = (int) (Math.random() * 3);

                while (rndR + word.length() > 10) {
                    rndR--;
                }
                while (rndC + word.length() > 10) {
                    rndC--;
                }

                check = checkWord(rndR, rndC, rndD, word);
            }

            if (rndD == 0) {
                for (int m = 0; m < word.length(); m++) {
                    boardSpotsBTN[rndR][m + rndC].setText(String.valueOf(word.charAt(m)));
                    board[rndR][rndC + m].setBtnText(word.charAt(m));

                    colorPicker(rndR, (rndC + m));
                }
            }
            if (rndD == 1) {
                for (int m = 0; m < word.length(); m++) {
                    boardSpotsBTN[rndR + m][rndC].setText(String.valueOf(word.charAt(m)));
                    board[rndR + m][rndC].setBtnText(word.charAt(m));

                    colorPicker((rndR + m), rndC);
                }
            }
            if (rndD == 2) {
                for (int m = 0; m < word.length(); m++) {
                    if (check) {
                        boardSpotsBTN[rndR + m][m + rndC].setText(String.valueOf(word.charAt(m)));
                        board[rndR + m][rndC + m].setBtnText(word.charAt(m));

                        colorPicker((rndR + m), (rndC + m));
                    }
                }
            }
        }
        fillBoard();
        EventHandler z = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                turn++;
                System.out.println("Click: " + turn);
                String findWord = "";
                rowIndex = GridPane.getRowIndex(((Button) t.getSource()));
                colIndex = GridPane.getColumnIndex(((Button) t.getSource()));
                if (turn % 2 == 1) {
                    rowInd1 = rowIndex;
                    colInd1 = colIndex;
                    rowInd2 = -1;
                    colInd2 = -1;
                } else if (turn % 2 == 0) {
                    rowInd2 = rowIndex;
                    colInd2 = colIndex;
                    turn = 0;
                }
                if (rowInd2 != -1 && colInd2 != -1) {
                    if (rowInd1 == rowInd2 && colInd1 == colInd2) {
                        return;
                    } else if (rowInd1 == rowInd2 && colInd1 != colInd2) {
                        for (int x = colInd1; x <= colInd2; x++) {
                            String letterClicked = boardSpotsBTN[rowInd1][x].getText();
                            findWord += letterClicked;
                            for (int k = 0; k <= words.size() - 1; k++) {
                                if ((words.get(k)).equals(findWord)) {
                                    words.remove(k);
                                    allwords.getItems().remove(k);
                                    for (int i = colInd1; i <= colInd2; i++) {
                                        colorPicker2(rowInd1, i);
                                        if (words.size() == 0) {
                                            wonLabel.setVisible(true);
                                            again.setVisible(true);
                                        }
                                    }
                                }
                            }
                        }
                        turn = 0;
                    } else if (rowInd1 != rowInd2 && colInd1 == colInd2) {
                        for (int x = rowInd1; x <= rowInd2; x++) {
                            String letterClicked = boardSpotsBTN[x][colInd1].getText();
                            findWord += letterClicked;
                            for (int k = 0; k <= words.size() - 1; k++) {
                                if ((words.get(k)).equals(findWord)) {
                                    words.remove(k);
                                    allwords.getItems().remove(k);
                                    for (int i = rowInd1; i <= rowInd2; i++) {
                                        colorPicker2(i, colInd1);
                                        if (words.size() == 0) {
                                            wonLabel.setVisible(true);
                                            again.setVisible(true);
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        for (int i = 0; i <= rowInd2 - rowInd1; i++) {
                            String letterClicked = boardSpotsBTN[rowInd1 + i][colInd1 + i].getText();
                            findWord += letterClicked;
                            for (int k = 0; k <= words.size() - 1; k++) {
                                if ((words.get(k)).equals(findWord)) {
                                    words.remove(k);
                                    allwords.getItems().remove(k);
                                    for (int x = 0; x <= rowInd2 - rowInd1; x++) {
                                        colorPicker2((rowInd1 + x), (colInd1 + x));
                                    }
                                    if (words.size() == 0) {
                                        wonLabel.setVisible(true);
                                        again.setVisible(true);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        };

        for (int i = 0; i < boardSpotsBTN.length; i++) {
            for (int j = 0; j < boardSpotsBTN.length; j++) {
                boardSpotsBTN[i][j].setOnAction(z);
            }
        }
    }

    public boolean checkWord(int rndR, int rndC, int rndD, String word) {
        boolean check = true;
        if (rndD == 0) {
            for (int m = 0; m < word.length(); m++) {
                if (boardSpotsBTN[rndR][m + rndC].getText().equals(word.charAt(m)) || boardSpotsBTN[rndR][m + rndC].getText().equals("-")) {
                    System.out.println(boardSpotsBTN[rndR][m + rndC].getText());
                    System.out.println(word.charAt(m));
                    System.out.println(" ");
                } else {
                    check = false;
                }
            }
        }
        if (rndD == 1) {
            for (int m = 0; m < word.length(); m++) {
                if (boardSpotsBTN[rndR + m][rndC].getText().equals(word.charAt(m)) || boardSpotsBTN[rndR + m][rndC].getText().equals("-")) {
                    System.out.println(boardSpotsBTN[rndR][m + rndC].getText());
                    System.out.println(word.charAt(m));
                    System.out.println(" ");
                } else {
                    check = false;
                }
            }
        }
        if (rndD == 2) {
            for (int m = 0; m < word.length(); m++) {
                if (boardSpotsBTN[rndR + m][rndC + m].getText().equals(word.charAt(m)) || boardSpotsBTN[rndR + m][rndC + m].getText().equals("-")) {
                    System.out.println(boardSpotsBTN[rndR][m + rndC].getText());
                    System.out.println(word.charAt(m));
                    System.out.println(" ");
                } else {
                    check = false;
                }
            }
        }
        return check;
    }

    public void fillBoard() {
        for (int i = 0; i < boardSpotsBTN.length; i++) {
            for (int j = 0; j < boardSpotsBTN.length; j++) {
                if (board[i][j].getBtnText() == "-") {
                    Random r = new Random();
                    boardSpotsBTN[i][j].setText(String.valueOf((char) (r.nextInt(26) + 'a')));
                    if (theme.equals("vacation")) {
                        boardSpotsBTN[i][j].setStyle("-fx-background-color: #CFE3FF; -fx-border-color: #91BCFC; -fx-border-width: 2px;");
                    }
                    if (theme.equals("flowers")) {
                        boardSpotsBTN[i][j].setStyle("-fx-background-color: #ff689f; -fx-border-color: #ff005b; -fx-border-width: 2px;");
                    }
                    if (theme.equals("dessert")) {
                        boardSpotsBTN[i][j].setStyle("-fx-background-color: #d390f5; -fx-border-color: #c23fff; -fx-border-width: 2px;");
                    }
                    if (theme.equals("animals")) {
                        boardSpotsBTN[i][j].setStyle("-fx-background-color: #ffd67f; -fx-border-color: #ffaf00; -fx-border-width: 2px;");
                    }
                    board[i][j].setBtnText((((char) (r.nextInt(26) + 'a'))));
                }
            }
        }
    }
}