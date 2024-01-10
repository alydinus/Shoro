import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class DeliveryMan extends JFrame implements ActionListener {
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
    DeliveryMan() {
        this.setTitle("Доставщик");
        this.setIconImage(frame.shoroIcon.getImage());
        this.setSize(500, 500);
        this.setLocation(600,200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        text1 = new JLabel("Приветствую дорогой, Доставщик!");
        text1.setFont(frame.font);
        text1.setBounds(100,30,400,30);
        text1.setForeground(Color.white);


        text2 = new JLabel("Пожалуйста выберите номер меню!");
        text2.setFont(frame.font);
        text2.setBounds(95,65,350,30);
        text2.setForeground(Color.white);


        option1 = new JRadioButton("Показать список товаров для доставки ");
        option2 = new JRadioButton("Показать доставленные заказы");
        option3 = new JRadioButton("Доставить заказ");
        option4 = new JRadioButton("Показать количество доставленных товаров");
        option5 = new JRadioButton("Показать количество заказанных товаров ");
        option6 = new JRadioButton("Показать мой заработок ");
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






        backGround = new JLabel();
        backGround.setIcon(frame.shoroImage);





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
            Methods.showFile("sold.txt");
        }
        if (e.getSource() == option2) {
            Methods.showFile("delivered.txt");
        }
        if (e.getSource() == option3) {
            try {
                deliverOrder();
            }catch (IOException ex){}


        }
        if (e.getSource() == option4) {
            System.out.println(Methods.getQuantityOfGoods("Доставленные товары.txt"));
        }
        if (e.getSource() == option5) {
            System.out.println(Methods.getQuantityOfGoods("sale.txt"));
        }
        if (e.getSource() == option6) {
            System.out.println("Ваш заработок: " + Methods.getQuantityOfGoods("delivered.txt") * 200 + " сом!");
        }
        if (e.getSource() == option7) {
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
