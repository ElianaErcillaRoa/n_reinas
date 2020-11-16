import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.*;

public class Reinas extends JFrame {

    private static final long serialVersionUID = 1L;

    private int NUM_REINAS;
    private int[] solution;
    private final static int SIZE = 600;

    public Reinas(int NUM_REINAS) {
        this.NUM_REINAS = NUM_REINAS;
        solution = new int[NUM_REINAS];
        init();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(SIZE, SIZE);

        BorderLayout gestorLayout = new BorderLayout();
        // GridLayout gestor = new GridLayout(3,3);
        // setLayout(gestor);
        setLayout(gestorLayout);

        JButton btn1 = new JButton("norte");
        JButton btn2 = new JButton("B2");
        JButton btn3 = new JButton("B3");
        JButton btn4 = new JButton("B4");
        JTextField txtnumber = new JTextField(5);

        add(BorderLayout.NORTH, btn1);
        add(BorderLayout.SOUTH, btn2);
        add(BorderLayout.CENTER, getBoard());
        add(BorderLayout.EAST, btn3);
        add(BorderLayout.WEST, btn4);

        setLocationRelativeTo(this);
        setVisible(true);

        // String strArray = Arrays.toString(solution);
        // System.out.println(strArray);
    }

    public JPanel getBoard() {
        JPanel panel = new JPanel();
        GridLayout gestor = new GridLayout(NUM_REINAS, NUM_REINAS);
        panel.setLayout(gestor);
        for (int i = 0; i < NUM_REINAS; i++) {
            for (int j = 0; j < NUM_REINAS; j++) {
                JButton cell = new JButton("x");
                cell.setBackground(Color.WHITE);
                if ((i + j) % 2 == 0) {
                    cell.setBackground(Color.GRAY);
                }
                cell.setEnabled(false);
                panel.add(cell);
            }
        }
        return panel;
    }

    public void getOptions() {
    }

    public void getResult() {

    }

    public void init() {
        for (int i = 0; i < solution.length; i++) {
            solution[i] = -1;
        }
    }

    public void searchSolution() {
        init();
        backtracking(solution, 0);
    }

    public boolean backtracking(int[] solucion, int reina) {
        boolean success = false;
        // CONDICION PARA EVALUAR SI HEMOS PROBADO TODAS LAS REINAS
        if (reina < NUM_REINAS) {// CASO BASE
            do {
                solucion[reina]++;// [0,-1,-1,-1] // [0,2,-1,-1]
                // Es para determinar las soluciones parciales
                boolean valid = isValid(solution, reina);
                String strSol = Arrays.toString(solucion);
                System.out.println(strSol + " " + (valid ? "SOL PARCIAL " : "")
                        + (valid && (reina == NUM_REINAS - 1) ? "SOLUTION COMPLETA" : ""));
                if (valid) {
                    // reina = reina + 1;
                    success = backtracking(solucion, reina + 1);
                }
            } while (solution[reina] < (NUM_REINAS - 1) && (!success));
            solucion[reina] = -1;
        } else {

        }
        return success;
    }

    // ESTUDIAR Y EXPLICAR LA SIGUIENTE CLASE COMO ES QUE SE DETERMINA
    // SI LA RESTRINCCION SE CUMPLE O NO (FILA Y DIAGONALES)
    public boolean isValid(int[] solution, int reina) {
        boolean ok = true;
        for (int i = 0; i < reina; i++) {
            if ((solution[i] == solution[reina]) || (Math.abs(solution[i] - solution[reina]) == Math.abs(i - reina))) {
                ok = false;
                break;
            }
        }
        return ok;
    }

    public static void main(String[] args) {
        Reinas reina = new Reinas(7);
        reina.searchSolution();
    }
}