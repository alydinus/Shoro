import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class Salesman extends JFrame implements ActionListener {


    JLabel label;
    JComboBox comboBox;
    JComboBox comboBox2;


    Salesman() {


        this.setTitle("Продавец");
        this.setSize(500, 300);
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        label = new JLabel("Приветствую дорогой, Продавец!");
        label.setBounds(0, 0, 200, 200);
        JLabel label1 = new JLabel("Пожалуйста наберите номер меню для работы с программой");
        label1.setBounds(100, 50, 200, 200);
        label1.setFont(new Font("Arial", Font.PLAIN, 15));


        String[] options = {"Показать весь список товаров для продажи", "Искать товар по названию", "Искать товар по дате", "Показать отчет по продаже", "Сделать заказ отсутствующего товара", "Удалить заказ", "Выход"};
        comboBox = new JComboBox(options);


        label.setFont(new Font("Arial", Font.PLAIN, 15));
        comboBox.addActionListener(this);
        this.add(label);
        this.add(label1);
        this.add(comboBox);

        this.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == comboBox && comboBox.getSelectedItem() == "Показать весь список товаров для продажи") {
            Methods.showFile("sale.txt");
        }
        if (e.getSource() == comboBox && comboBox.getSelectedItem() == "Искать товар по названию") {
            searchGoodsByName();

        }
        if (e.getSource() == comboBox && comboBox.getSelectedItem() == "Искать товар по дате") {
            searchGoodsByDate();

        }
        if (e.getSource() == comboBox && comboBox.getSelectedItem() == "Показать отчет по продаже") {
           Methods.showFile("sold.txt");

        }
        if (e.getSource() == comboBox && comboBox.getSelectedItem() == "Сделать заказ отсутствующего товара") {
            makeOrder();

        }
        if (e.getSource() == comboBox && comboBox.getSelectedItem() == "Удалить заказ") {
            removeOrder();

        }
        if (e.getSource() == comboBox && comboBox.getSelectedItem() == "Выход") {
            this.dispose();
            System.out.println("Программа завершена, мы будем рады вашему возвращению!");

        }
    }
    public void searchGoodsByName(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("•\tНапишите название товара для поиска:>>");
        String goods = scanner.next();
        try {
            File file = new File("sale.txt");
            Scanner scanner1 = new Scanner(file);
            while (scanner1.hasNextLine()) {
                String data = scanner1.nextLine();
                if (data.contains(goods)) {
                    System.out.println("Товар " + data + " есть в наличии!") ;
                    break;
                } else if (!scanner1.hasNextLine()) {
                    System.out.println("Такой товар не найден :(");
                }
            }
        } catch (Exception e) {

        }
    }
    public void searchGoodsByDate(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("•\tНапишите дату для поиска:>>");
        String date = scanner.next();
    }
    public void makeOrder(){
        Scanner scanner = new Scanner(System.in);
        try {
            File file = new File("outOfStock.txt");
            Scanner scanner1 = new Scanner(file);
            while (scanner1.hasNextLine()) {
                int i = 1;
                String data = scanner1.nextLine();
                System.out.println(i + "." + data);
                i++;
            }
            System.out.println("Выберите товар, который хотите заказать.");
            int choice = scanner.nextInt();
            System.out.println("Выберите количество, которое хотите заказать.");
            int howMuch = scanner.nextInt();
        } catch (Exception e) {}
    }
    public void removeOrder(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Какой заказ вы бы хотели удалить? >>>");
        try {
            File file = new File("sold.txt");
            String thingToDelete = scanner.next();
            FileInputStream inputStream = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            int x = inputStream.read(buffer);
            String content = new String(buffer);

            content = content.replaceAll(thingToDelete, "");

            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(content.getBytes());

            inputStream.close();
            outputStream.close();
            System.out.println("Товар успешно удален!");


        } catch (Exception e) {}
    }

}

