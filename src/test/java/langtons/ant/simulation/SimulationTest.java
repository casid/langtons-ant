package langtons.ant.simulation;

import langtons.ant.simulation.Ant;
import langtons.ant.simulation.Direction;
import langtons.ant.simulation.Simulation;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SimulationTest {

    private Simulation simulation;
    private Ant ant;

    @Before
    public void setUp() {
        simulation = new Simulation();
        simulation.setFieldSize(5, 5);

        ant = new Ant();
        ant.x = 2;
        ant.y = 2;
        ant.direction = Direction.UP;
        simulation.addAnt(ant);
    }

    @Test
    public void initialFields() {
        thenAllFieldsAre(Simulation.WHITE);
    }

    @Test
    public void antOnWhiteField() {
        simulation.setField(2, 2, Simulation.WHITE);

        simulation.simulate();

        thenFieldIs(2, 2, Simulation.BLACK);
        assertThat(ant.direction).isEqualTo(Direction.RIGHT);
        thenAntPositionIs(3, 2);
    }

    @Test
    public void antOnBlackField() {
        simulation.setField(2, 2, Simulation.BLACK);

        simulation.simulate();

        thenFieldIs(2, 2, Simulation.WHITE);
        assertThat(ant.direction).isEqualTo(Direction.LEFT);
        thenAntPositionIs(1, 2);
    }

    @Test
    public void antOnFieldBorder_top() {
        ant.x = 0;
        ant.y = 0;
        ant.direction = Direction.LEFT;

        simulation.simulate();

        thenAntPositionIs(0, 4);
    }

    @Test
    public void antOnFieldBorder_bottom() {
        ant.x = 0;
        ant.y = 4;
        ant.direction = Direction.RIGHT;

        simulation.simulate();

        thenAntPositionIs(0, 0);
    }

    @Test
    public void antOnFieldBorder_left() {
        ant.x = 0;
        ant.y = 0;
        ant.direction = Direction.DOWN;

        simulation.simulate();

        thenAntPositionIs(4, 0);
    }

    @Test
    public void antOnFieldBorder_right() {
        ant.x = 4;
        ant.y = 0;
        ant.direction = Direction.UP;

        simulation.simulate();

        thenAntPositionIs(0, 0);
    }

    @Test
    public void addAntOutOfField() {
        ant = new Ant();
        ant.x = -1;
        ant.y = -1;
        simulation.addAnt(ant);

        thenAntPositionIs(4, 4);
    }

    private void thenAllFieldsAre(boolean color) {
        simulation.forEachField((x, y, fieldColor) -> assertThat(fieldColor).isEqualTo(color));
    }

    private void thenFieldIs(int x, int y, boolean color) {
        assertThat(simulation.getField(x, y)).isEqualTo(color);
    }

    private void thenAntPositionIs(int x, int y) {
        assertThat(ant.x).isEqualTo(x);
        assertThat(ant.y).isEqualTo(y);
    }
}