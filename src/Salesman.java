import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class Salesman extends JFrame implements ActionListener {


    JLabel text1;
    JLabel text2;
    JLabel backGround;
    JRadioButton option1;
    JRadioButton option2;
    JRadioButton option3;
    JRadioButton option4;
    JRadioButton option5;
    JRadioButton option6;
    JRadioButton option7;
    ButtonGroup buttonGroup;




    Salesman() {


        this.setTitle("Продавец");
        this.setIconImage(frame.shoroIcon.getImage());
        this.setSize(500, 500);
        this.setLocation(600,200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);



        text1 = new JLabel("Приветствую дорогой, Продавец!");
        text1.setFont(frame.font);
        text1.setBounds(100, 30, 400, 30);
        text1.setForeground(Color.white);


        text2 = new JLabel("Пожалуйста выберите номер меню!");
        text2.setBounds(95, 55, 500, 30);
        text2.setFont(frame.font);
        text2.setForeground(Color.white);

        backGround = new JLabel();
        backGround.setIcon(frame.shoroImage);

        option1 = new JRadioButton("Показать весь список товаров для продажи ");
        option2 = new JRadioButton("Искать товар по названию");
        option3 = new JRadioButton("Искать товар по дате");
        option4 = new JRadioButton("Показать отчет по продаже ");
        option5 = new JRadioButton("Сделать заказ отсутствующего товара");
        option6 = new JRadioButton("Удалить заказ");
        option7 = new JRadioButton("Выход");

        buttonGroup = new ButtonGroup();
        buttonGroup.add(option1);
        buttonGroup.add(option2);
        buttonGroup.add(option3);
        buttonGroup.add(option4);
        buttonGroup.add(option5);
        buttonGroup.add(option6);
        buttonGroup.add(option7);




        option1.setBounds(100,100,300,30);
        option1.setFocusable(false);
        option1.addActionListener(this);

        option2.setBounds(100,135,300,30);
        option2.setFocusable(false);
        option2.addActionListener(this);

        option3.setBounds(100,170,300,30);
        option3.setFocusable(false);
        option3.addActionListener(this);

        option4.setBounds(100,205,300,30);
        option4.setFocusable(false);
        option4.addActionListener(this);

        option5.setBounds(100,240,300,30);
        option5.setFocusable(false);
        option5.addActionListener(this);

        option6.setBounds(100,275,300,30);
        option6.setFocusable(false);
        option6.addActionListener(this);

        option7.setBounds(100,310,300,30);
        option7.setFocusable(false);
        option7.addActionListener(this);





        this.add(text1);
        this.add(text2);
        this.add(option1);
        this.add(option2);
        this.add(option3);
        this.add(option4);
        this.add(option5);
        this.add(option6);
        this.add(option7);
        this.add(backGround);

        this.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == option1) {
            Methods.showFile("sale.txt");
        }
        if (e.getSource() == option2) {
            searchGoodsByName();

        }
        if (e.getSource() == option3) {
            searchGoodsByDate();

        }
        if (e.getSource() == option4) {
           Methods.showFile("sold.txt");

        }
        if (e.getSource() == option5) {
            makeOrder();

        }
        if (e.getSource() == option6) {
            removeOrder();

        }
        if (e.getSource() == option7) {
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
                String data = scanner1.nextLine();
                System.out.println( data);
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

