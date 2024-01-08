import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Provider extends JFrame implements ActionListener {
    JComboBox comboBox;

    Provider() {
        JFrame frame = new JFrame("Доставщик");
        this.setSize(500, 400);
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel text1 = new JLabel("Приветствую дорогой, Поставщик!");
        JLabel text2 = new JLabel("Пожалуйста наберите номер меню для работы с программой");

        Font font = new Font("Arial", Font.PLAIN, 15);

        String[] options = {"Показать список товаров для поставки", "Показать количество поставляемого товара", "Показать товар с самым большим количеством заказов для доставки",
                "Показать товар с самым меньшим количеством заказов для доставки", "Выход"};

        comboBox = new JComboBox(options);
        comboBox.addActionListener(this);


        text2.setFont(font);
        text1.setFont(font);


        this.add(text1);
        this.add(text2);
        this.add(comboBox);
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == comboBox && comboBox.getSelectedItem() == "Показать список товаров для поставки") {
            Methods.showFile("need_material.txt");
        }
        if (e.getSource() == comboBox && comboBox.getSelectedItem() == "Показать количество поставляемого товара") {
            System.out.println(Methods.getQuantityOfGoods("need_material.txt"));
        }
        if (e.getSource() == comboBox && comboBox.getSelectedItem() == "Показать товар с самым большим количеством заказов для доставки") {
            mostPopularGood("delivered.txt");
        }
        if (e.getSource() == comboBox && comboBox.getSelectedItem() == "Показать товар с самым меньшим количеством заказов для доставки") {
            LeastPopularGood("delivered.txt");
        }
        if (e.getSource() == comboBox && comboBox.getSelectedItem() == "Выход") {
            System.out.println("Программа завершена, мы будем рады вашему возвращению!");
            this.dispose();
        }

    }
    private void mostPopularGood(String path){
        try {
            File file = new File(path);
            FileReader fileReader = new FileReader(file);
            HashMap<String, Integer> countMap = new HashMap<>();

            Scanner scanner = new Scanner(fileReader);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split(" ");
                for (String word : words) {
                    if (countMap.containsKey(word)) {
                        countMap.put(word, countMap.get(word) + 1);
                    } else {
                        countMap.put(word, 1);
                    }
                }
            }

            HashMap.Entry<String, Integer> maxEntry = null;
            for (HashMap.Entry<String, Integer> entry : countMap.entrySet()) {
                if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
                    maxEntry = entry;
                }
            }

            System.out.println("Товар с наибольшим количеством заказов: " + maxEntry.getKey());

            fileReader.close();

        }catch (Exception e){}
    }
    private void LeastPopularGood(String path){
        try {
            File file = new File(path);
            FileReader fileReader = new FileReader(file);
            HashMap<String, Integer> countMap = new HashMap<>();

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split(" ");
                for (String word : words) {
                    if (countMap.containsKey(word)) {
                        countMap.put(word, countMap.get(word) + 1);
                    } else {
                        countMap.put(word, 1);
                    }
                }
            }

            Set<HashMap.Entry<String, Integer>> entries = countMap.entrySet();
            HashMap.Entry<String, Integer> minEntry = entries.stream().min(Map.Entry.comparingByValue()).get();

            System.out.println("Товар с наименьшим количеством заказов: " + minEntry.getKey());

            bufferedReader.close();
            fileReader.close();

        }catch (Exception e){}
    }

}
