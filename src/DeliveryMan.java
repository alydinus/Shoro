import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class DeliveryMan extends JFrame implements ActionListener {
    JComboBox comboBox;

    DeliveryMan() {
        JFrame frame = new JFrame("Доставщик");
        this.setSize(500, 400);
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel text1 = new JLabel("Приветствую дорогой, Доставщик!");
        JLabel text2 = new JLabel("Пожалуйста наберите номер меню для работы с программой");

        Font font = new Font("Arial", Font.PLAIN, 15);

        String[] options = {"Показать список товаров для доставки", "Показать доставленные заказы", "Доставить заказ", "Показать количество доставленных товаров"
                , "Показать количество заказанных товаров", "Показать мой заработок", "Выход"};
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
        if (e.getSource() == comboBox && comboBox.getSelectedItem() == "Показать список товаров для доставки") {
            Methods.showFile("sold.txt");
        }
        if (e.getSource() == comboBox && comboBox.getSelectedItem() == "Показать доставленные заказы") {
            Methods.showFile("delivered.txt");
        }
        if (e.getSource() == comboBox && comboBox.getSelectedItem() == "Доставить заказ") {
            try {
                deliverOrder();
            }catch (IOException ex){}


        }
        if (e.getSource() == comboBox && comboBox.getSelectedItem() == "Показать количество доставленных товаров") {
            System.out.println(Methods.getQuantityOfGoods("Доставленные товары.txt"));
        }
        if (e.getSource() == comboBox && comboBox.getSelectedItem() == "Показать количество заказанных товаров") {
            System.out.println(Methods.getQuantityOfGoods("sale.txt"));
        }
        if (e.getSource() == comboBox && comboBox.getSelectedItem() == "Показать мой заработок") {
            System.out.println("Ваш заработок: " + Methods.getQuantityOfGoods("delivered.txt") * 200 + " сом!");
        }
        if (e.getSource() == comboBox && comboBox.getSelectedItem() == "Выход") {
            this.dispose();
            System.out.println("Программа завершена, мы будем рады вашему возвращению!");
        }


    }
    public void deliverOrder() throws IOException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите название товара");
        String good = scanner.next();
        File file = new File("sale.txt");
        FileInputStream inputStream = new FileInputStream(file);

        byte[] buffer = new byte[(int) file.length()];
        int x = inputStream.read(buffer);
        String content = new String(buffer);

        content = content.replaceAll(good, "");

        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(content.getBytes());
        FileWriter writer = new FileWriter("delivered.txt",true);
        writer.append(good+"\n");
        writer.close();
        inputStream.close();





    }
}
