package langtons.ant.simulation;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    public static final boolean BLACK = true;
    public static final boolean WHITE = false;

    private boolean[][] fields;
    private List<Ant> ants = new ArrayList<>();

    public void setFieldSize(int width, int height) {
        fields = new boolean[width][height];
    }

    public int getFieldWidth() {
        return fields.length;
    }

    public int getFieldHeight() {
        return fields[0].length;
    }

    public void forEachField(FieldVisitor fieldVisitor) {
        int width = fields.length;
        for (int x = 0; x < width; ++x) {
            boolean[] column = fields[x];
            int height = column.length;
            for (int y = 0; y < height; ++y) {
                fieldVisitor.visit(x, y, column[y]);
            }
        }
    }

    public boolean getField(int x, int y) {
        return fields[x][y];
    }

    public void setField(int x, int y, boolean color) {
        fields[x][y] = color;
    }

    public void addAnt(Ant ant) {
        ants.add(ant);
        clampAntToField(ant);
    }

    public void simulate() {
        for (Ant ant : ants) {
            rotateAnt(ant);
            flipColor(ant);
            moveAntForward(ant);
        }
    }

    private void moveAntForward(Ant ant) {
        ant.x += ant.direction.x;
        ant.y += ant.direction.y;
        clampAntToField(ant);
    }

    private void clampAntToField(Ant ant) {
        if (ant.y < 0) {
            ant.y = getFieldHeight() - 1;
        } else if (ant.y >= getFieldHeight()) {
            ant.y = 0;
        }

        if (ant.x < 0) {
            ant.x = getFieldWidth() - 1;
        } else if (ant.x >= getFieldWidth()) {
            ant.x = 0;
        }
    }

    private void rotateAnt(Ant ant) {
        boolean color = fields[ant.x][ant.y];
        if (color == WHITE) {
            ant.direction = ant.direction.rotateRight();
        }
        if (color == BLACK) {
            ant.direction = ant.direction.rotateLeft();
        }
    }

    private void flipColor(Ant ant) {
        fields[ant.x][ant.y] = !fields[ant.x][ant.y];
    }
}
