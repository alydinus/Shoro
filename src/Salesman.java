import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.logging.LoggingPermission;

public class Salesman extends JFrame implements ActionListener{


    JLabel label;
    JComboBox comboBox;
    JComboBox comboBox2;



    Salesman() {



        this.setTitle("Продавец");
        this.setSize(500,300);
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        label = new JLabel("Приветствую дорогой, Продавец!");
        label.setBounds(0,0,200,200);
        JLabel label1 = new JLabel("Пожалуйста наберите номер меню для работы с программой");
        label1.setBounds(100,50,200,200);
        label1.setFont(new Font("Arial",Font.PLAIN,15));


        String[] options = {"Показать весь список товаров для продажи","Искать товар по названию","Искать товар по дате","Показать отчет по продаже","Сделать заказ отсутствующего товара","Удалить заказ","Выход"};
        comboBox = new JComboBox(options);


        label.setFont(new Font("Arial",Font.PLAIN,15));
        comboBox.addActionListener(this);
        this.add(label);
        this.add(label1);
        this.add(comboBox);

        this.setVisible(true);





    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == comboBox && comboBox.getSelectedItem() == "Показать весь список товаров для продажи"){
            this.dispose();
            chooseOption(1);
        }
        if(e.getSource() == comboBox && comboBox.getSelectedItem() == "Искать товар по названию"){
            this.dispose();
            chooseOption(2);

        }if(e.getSource() == comboBox && comboBox.getSelectedItem() == "Искать товар по дате"){
            this.dispose();
            chooseOption(3);

        }if(e.getSource() == comboBox && comboBox.getSelectedItem() == "Показать отчет по продаже"){
            this.dispose();
            chooseOption(4);

        }if(e.getSource() == comboBox && comboBox.getSelectedItem() == "Сделать заказ отсутствующего товара"){
            this.dispose();
            chooseOption(5);

        }if(e.getSource() == comboBox && comboBox.getSelectedItem() == "Удалить заказ"){
            this.dispose();
            chooseOption(6);

        }if(e.getSource() == comboBox && comboBox.getSelectedItem() == "Выход"){
            this.dispose();
            chooseOption(7);

        }
    }

    void chooseOption(int option) {
        Scanner scanner = new Scanner(System.in);
        if (option == 1) {
            try {
                FileReader reader = new FileReader("sale.txt");
                int data = reader.read();
                while(data != -1){
                    System.out.print((char)data);
                    data = reader.read();
                }
                reader.close();

            } catch (Exception e) {
                System.out.println("Неверный путь до файла :(");
            }
        }
        else if(option == 2){
                System.out.println("•\tНапишите название товара для поиска:>>");
                String goods = scanner.next();
                try {
                    File file = new File("sale.txt");
                    Scanner scanner1 = new Scanner(file);
                    while(scanner1.hasNextLine()){
                        String data = scanner1.nextLine();
                        if (data.contains(goods)){
                            System.out.println(data);
                            break;
                        }
                        else if(!scanner1.hasNextLine()){
                            System.out.println("Такой товар не найден :(");
                        }
                    }
                }catch (Exception e){

                }
        }
        else if (option == 3){
                System.out.println("•\tНапишите дату для поиска:>>");
                String date = scanner.next();

        }
        else if (option == 4){
            try {
                File file = new File("sold.txt");
                Scanner scanner1 = new Scanner(file);
                while (scanner1.hasNextLine()){
                    String data = scanner1.nextLine();
                    System.out.println(data);
                }
            }catch (Exception e){

            }
        }else if(option == 5){
            try {
                File file = new File("outOfStock.txt");
                Scanner scanner1 = new Scanner(file);
                while (scanner1.hasNextLine()){
                    int i = 1;
                    String data = scanner1.nextLine();
                    System.out.println(i + "." + data);
                    i++;
                }
                System.out.println("Выберите товар, который хотите заказать.");
                int choice = scanner.nextInt();
                System.out.println("Выберите количество, которое хотите заказать.");
                int howMuch = scanner.nextInt();
            }catch (Exception e){

            }
        }
        else if (option == 6){

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


            }catch (Exception e){}

        }
        else if (option == 7) {
            System.out.println("Программа завершена, мы будем рады вашему возвращению!");

        }

    }

}

