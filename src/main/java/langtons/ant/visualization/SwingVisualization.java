package langtons.ant.visualization;

import langtons.ant.simulation.Ant;
import langtons.ant.simulation.Simulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SwingVisualization extends JPanel {

    private final Simulation simulation = new Simulation();
    private final int scale = 10;

    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
        simulation.setFieldSize(width / scale, height / scale);
    }

    public void init() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Ant ant = new Ant();
                ant.x = e.getX() / scale;
                ant.y = e.getY() / scale;
                simulation.addAnt(ant);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        simulation.forEachField((x, y, fieldColor) -> {
            g.setColor(fieldColor == Simulation.WHITE ? Color.white : Color.black);
            g.fillRect(x * scale, y * scale, scale, scale);
        });
    }

    public void simulate() {
        simulation.simulate();
    }

    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("Langton's Ant - Click to add ant");

        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        SwingVisualization visualization = new SwingVisualization();
        visualization.setSize(frame.getWidth(), frame.getHeight());
        visualization.init();
        frame.add(visualization);

        frame.setVisible(true);

        Timer timer = new Timer(10, e -> {
            visualization.simulate();
            visualization.repaint();
        });
        timer.start();
    }
}
