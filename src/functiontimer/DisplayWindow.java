/**
 * 
 */
package functiontimer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import cs2.Button;
import cs2.Shape;
import cs2.TextShape;
import cs2.Window;
import cs2.WindowSide;

/**
 * @author Krish Ganotra
 * @version 12.02.2021
 */
public class DisplayWindow {

    private Window window;

    private Window infoWindow;
    private Button infoButton;

    private Button[] structButtons;
    private ArrayList<Button> chosenStructButtons;

    private Button[] funcButtons;
    private ArrayList<Button> chosenFuncButtons;
    private LinkedHashMap<String, String> funcs;

    private Button runButton;
    private RunTimer<String> timer;

    private static int STRUCT_SIZE = 100000;
    private static int SORTED_STRUCT_SIZE = 10000;
    private static int STRUCT_SIZE_LIMIT = 1000000;
    private static int SORTED_STRUCT_SIZE_LIMIT = 30000;

    private static double PERC = Math.random();
    private static String EXTRA = "Extra Element";

    private static String[] structs = { "ArrayBasedBag",
            "ArrayBasedList", "ArrayBasedQueue", "ArrayBasedStack",
            "BinarySearchTree", "DLL", "LLBasedDeque",
            "LLBasedStack", "SLL", "SortedArray", "SortedLL" };

    public DisplayWindow() {
        infoWindow = new Window();
        infoWindow.setTitle("Instructions");

        infoButton = new Button("Continue");
        infoButton.onClick(this, "drawMain");

        infoWindow.addShape(new TextShape(0, 0,
                "Add the data structures you would like to compare by clicking on them on the left."));
        infoWindow.addShape(new TextShape(0, 15,
                "You will see these data structures be added to a list on the top."));
        infoWindow.addShape(new TextShape(0, 30,
                "To increase the size of the data structure, click on the data structure on the top."));
        infoWindow.addShape(new TextShape(0, 45,
                "Keep clicking to remove the data structure from the list."));

        infoWindow.addShape(new TextShape(0, 80,
                "Choose the functions on the right that you would like to run on the data structures."));
        infoWindow.addShape(new TextShape(0, 95,
                "You will see these functions be added to a list on the bottom."));
        infoWindow.addShape(new TextShape(0, 110,
                "Click on a function at the bottom to remove it."));

        infoWindow.addShape(new TextShape(0, 140,
                "Once you have selected all your desired data structures and functions, hit RUN."));
        infoWindow.addShape(new TextShape(0, 170,
                "Press \"Continue\" below to continue to the program."));
        infoWindow.addShape(new TextShape(0, 185,
                "This window will remain open under the program."));
        infoWindow.addButton(infoButton, WindowSide.SOUTH);

    }

    /**
     * @throws MalformedURLException
     * 
     */
    public void drawMain(Button button) throws MalformedURLException {
        window = new Window();
        window.setTitle("Data Structure Timer");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        window.setSize(width, height);

        runButton = new Button("RUN");
        runButton.onClick(this, "clickedRunButton");
        runButton.setBackgroundColor(new Color(0, 255, 0));
        timer = new RunTimer<String>();
        chosenStructButtons = new ArrayList<Button>();
        chosenFuncButtons = new ArrayList<Button>();

        structButtons = new Button[structs.length];
        for (int i = 0; i < structs.length; i++) {
            structButtons[i] = new Button(structs[i]);
            structButtons[i].onClick(this, "clickedStructButton");
            window.addButton(structButtons[i], WindowSide.WEST);
        }
        window.addButton(new Button(""), WindowSide.WEST);
        window.addButton(new Button(""), WindowSide.WEST);
        window.addButton(new Button(""), WindowSide.WEST);
        window.addButton(new Button(""), WindowSide.WEST);
        window.addButton(runButton, WindowSide.WEST);

        funcs = new LinkedHashMap<String, String>();

        funcs.put("Get first element of struct", "getFirst");
        funcs.put("Get last element of struct", "getLast");
        funcs.put("Get a random element of struct", "getRandIndex");
        funcs.put("Check if struct contains its first element",
                "containsFirst");
        funcs.put("Check if struct contains its last element", "containsLast");
        funcs.put("Check if struct contains some random object",
                "containsRandObj");
        funcs.put("Add a new element at the front of struct", "addFront");
        funcs.put("Add a new element to the end of struct", "addEnd");
        funcs.put("Add a new element anywhere in the struct", "addAnywhere");
        funcs.put("Add a new element to a random index in struct",
                "addRandInd");
        funcs.put("Remove 0th index of struct", "removeFirstInd");
        funcs.put("Remove last index of struct", "removeLastInd");
        funcs.put("Remove a random index of struct", "removeRandInd");
        funcs.put("Remove first object in struct", "removeFirstObj");
        funcs.put("Remove last object in struct", "removeLastObj");
        funcs.put("Remove a random object in struct", "removeRandObj");

        funcButtons = new Button[funcs.size()];
        int i = 0;
        for (String func : funcs.keySet()) {
            funcButtons[i] = new Button(func);
            funcButtons[i].onClick(this, "clickedFuncButton");
            window.addButton(funcButtons[i], WindowSide.EAST);
            i++;
        }

        JFrame frame = new JFrame();
        ImageIcon icon = new ImageIcon(new URL(
                "https://miro.medium.com/max/2680/1*TXcoRpFxDTdRl0YYawE3XA.png"));
        JLabel label = new JLabel(icon);
        frame.add(label);
        frame.setTitle(
                "https://medium.com/omarelgabrys-blog/data-structures-a-quick-comparison-6689d725b3b0");
        frame.pack();
        frame.setVisible(true);

    }

    @SuppressWarnings({ "deprecation", "unchecked" })
    public TimedStructure<String> makeStruct(String className, int size)
            throws ClassNotFoundException, InstantiationException,
            IllegalAccessException {
        Class<DataStructure<String>> cls = (Class<DataStructure<String>>) Class
                .forName("functiontimer." + className);
        DataStructure<String> struct = cls.newInstance();
        for (int i = 0; i < size; i++) {
            struct.addAnywhere("Element " + i);
        }
        return new TimedStructure<String>(struct, PERC, EXTRA);
    }

    public void extendStruct(TimedStructure<String> struct, int extraSize) {
        DataStructure<String> data = struct.getData();
        int currSize = data.length();
        for (int i = 0; i < extraSize; i++) {
            data.addAnywhere("Element " + (currSize + i));
        }
    }

    public void clickedStructButton(Button button)
            throws ClassNotFoundException, InstantiationException,
            IllegalAccessException {
        TimedStructure<String> newStruct;
        if (button.getTitle().equals("SortedArray")
                || button.getTitle().equals("SortedLL")) {
            newStruct = makeStruct(button.getTitle(), SORTED_STRUCT_SIZE);
        }
        else {
            newStruct = makeStruct(button.getTitle(), STRUCT_SIZE);
        }
        timer.addStruct(newStruct);

        Button newButton = new Button(newStruct.toString());
        newButton.onClick(this, "clickedChosenStructButton");
        chosenStructButtons.add(newButton);
        window.addButton(newButton, WindowSide.NORTH);
    }

    public void clickedFuncButton(Button button) {
        timer.addFunc(funcs.get(button.getTitle()));

        Button newButton = new Button(button.getTitle());
        newButton.onClick(this, "clickedChosenFuncButton");
        chosenFuncButtons.add(newButton);
        window.addButton(newButton, WindowSide.SOUTH);
    }

    public void clickedRunButton(Button button) throws NoSuchMethodException,
            SecurityException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        PERC = Math.random();
        timer.updatePerc(PERC);
        double[][] times = timer.run();
        drawGraph(times);
    }

    public void clickedChosenStructButton(Button button)
            throws ClassNotFoundException, InstantiationException,
            IllegalAccessException {
        int ind = chosenStructButtons.indexOf(button);
        TimedStructure<String> struct = timer.getStruct(ind);
        DataStructure<String> data = struct.getData();

        if (data instanceof SortedArray || data instanceof SortedLL) {
            if (data.length() < SORTED_STRUCT_SIZE_LIMIT) {
//                String clsName = struct.getClass().getSimpleName();
//                TimedStructure<String> newStruct = makeStruct(clsName,
//                        struct.length() + SORTED_STRUCT_SIZE);
//                timer.replaceStruct(newStruct, ind);
//                button.setTitle(newStruct.toString());
                extendStruct(struct, SORTED_STRUCT_SIZE);
                button.setTitle(struct.toString());
            }
            else {
                chosenStructButtons.remove(ind);
                timer.removeStruct(ind);
                window.removeButton(button, WindowSide.NORTH);
            }
        }
        else {
            if (data.length() < STRUCT_SIZE_LIMIT) {
//                String clsName = struct.getClass().getSimpleName();
//                TimedStructure<String> newStruct = makeStruct(clsName,
//                        struct.length() + STRUCT_SIZE);
//                timer.replaceStruct(newStruct, ind);

                extendStruct(struct, STRUCT_SIZE);
                button.setTitle(struct.toString());
            }
            else {
                chosenStructButtons.remove(ind);
                timer.removeStruct(ind);
                window.removeButton(button, WindowSide.NORTH);
            }
        }

    }

    public void clickedChosenFuncButton(Button button) {
        int ind = chosenFuncButtons.indexOf(button);
        chosenFuncButtons.remove(ind);
        timer.removeFunc(ind);
        window.removeButton(button, WindowSide.SOUTH);
    }

    public void drawGraph(double[][] times) {
//        ArrayList<String> funcNames = timer.getFuncNames();
//        ArrayList<TimedStructure<String>> structArr = timer.getStructArr();
//
//        // Printing
//        int width = 0;
//        for (int i = 0; i < funcNames.size(); i++) {
//            System.out.print(funcNames.get(i));
//            System.out.print("  ");
//            width += 2 + funcNames.get(i).length();
//        }
//        System.out.println();
//        for (int i = 0; i < width; i++) {
//            System.out.print("_");
//        }
//        System.out.println();
//
//        for (int i = 0; i < times.length; i++) {
//            System.out.print(structArr.get(i));
//            System.out.print("|  ");
//            for (int j = 0; j < times[i].length; j++) {
//                System.out.print(times[i][j]);
//                System.out.print("  ");
//            }
//            System.out.println();
//        }

        // Bar Graph
        window.removeAllShapes();
        int sections = (times.length * 2) - 1;
        int margin = window.getGraphPanelWidth() / 15;
        int sectionSize = (window.getGraphPanelWidth() - 2 * margin) / sections;
        int windowHeight = window.getGraphPanelHeight();
        double scalar = 0.8 * windowHeight / findMax(times);
        if (scalar < 0)
            scalar = 0;

        for (int i = 0; i < times.length; i++) {
            int sectionStartX = sectionSize * (2 * i) + margin;
            int barWidth = sectionSize / times[i].length;
            for (int j = 0; j < times[i].length; j++) {
                int x = (int) (sectionStartX + j * barWidth);
                int barHeight = (int) (times[i][j] * scalar);
                int y = windowHeight - barHeight;
                Color barColor = new Color(255, j * 255 / times[i].length, 255);
                Shape bar = new Shape(x, y, barWidth, barHeight, barColor);
                chosenStructButtons.get(j).setBackgroundColor(barColor);
                window.addShape(bar);

                DecimalFormat df = new DecimalFormat("0.00E0");
                String barLabel = times[i][j] == -1 ? "N/A"
                        : df.format(times[i][j]) + " ns";
                window.addShape(new TextShape(
                        x + (barWidth - barLabel.length() * 7) / 2, y - 20,
                        barLabel));
            }
        }
    }

    private double findMax(double[][] arr) {
        double maxVal = -1;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] > maxVal) {
                    maxVal = arr[i][j];
                }
            }
        }
        return maxVal;
    }
}
