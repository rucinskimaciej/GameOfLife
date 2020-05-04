package online.rumac.life.play.commandLine;

import online.rumac.life.board.Board;
import online.rumac.life.output.Printable;

import java.io.IOException;
import java.util.Arrays;

public class PlayWithCommandLine implements Printable {

    @Override
    public void boardToScreen(Board board) {
//        try {
//            Thread.sleep(50);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        clrScr();

        String boardToString = "";

        StringBuilder sb = new StringBuilder();
        for (String[] strings : board.getBoard()) {
            sb.append(Arrays.toString(strings)).append("\n");
        }

        boardToString = sb.toString()
                .replace(",", "")
                .replace("[", "")
                .replace("]","");

        System.out.println(boardToString);
    }

    private static void clrScr() {
        //Clears Screen in java
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {
            //
        }
    }

}
