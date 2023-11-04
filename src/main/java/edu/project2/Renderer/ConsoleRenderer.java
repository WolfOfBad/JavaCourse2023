package edu.project2.Renderer;

import edu.project2.Maze.Cell;
import edu.project2.Maze.Maze;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleRenderer implements Renderer {
    Logger logger = LogManager.getLogger();

    @Override
    public void render(Maze maze) {
        logger.info("Unsolved labyrinth is:");

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < maze.getHeight(); i++) {
            sb.delete(0, sb.length());
            for (int j = 0; j < maze.getWidth(); j++) {
                sb.append(renderCell(maze.getCell(i, j)));
            }
            logger.info(sb.toString());
        }
    }

    @Override
    public void render(Maze maze, List<Cell> path) {
        logger.info("Solved labyrinth is:");

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < maze.getHeight(); i++) {
            sb.delete(0, sb.length());
            for (int j = 0; j < maze.getWidth(); j++) {
                if (path.contains(maze.getCell(i, j))) {
                    sb.append(renderPathCell());
                } else {
                    sb.append(renderCell(maze.getCell(i, j)));
                }
            }
            logger.info(sb.toString());
        }
    }

    @SuppressWarnings("MultipleStringLiterals")
    private String renderCell(Cell cell) {
        return switch (cell.getType()) {
            case WALL -> ConsoleColors.WHITE_BACKGROUND + "   " + ConsoleColors.RESET;
            case PASSAGE -> "   ";
        };
    }

    private String renderPathCell() {
        return ConsoleColors.RED + " ● " + ConsoleColors.RESET;
    }

}
