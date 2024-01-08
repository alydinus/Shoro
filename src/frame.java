import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frame extends JFrame implements ActionListener {
    JComboBox comboBox;

    frame(){
        String[] persons= {"Продавец","Доставщик","Поставщик"};
        comboBox = new JComboBox(persons);
        comboBox.setBounds(100,100,100,60);

        comboBox.addActionListener(this);

        this.setTitle("Шоро");
        this.add(comboBox);
        this.setSize(500,500);
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);






        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == comboBox){
            if (comboBox.getSelectedItem() == "Продавец"){
                new Salesman();



            }
        }
        if (e.getSource() == comboBox){
            if(comboBox.getSelectedItem() == "Доставщик"){
                new DeliveryMan();

            }
        }
        if(e.getSource() == comboBox){
            if(comboBox.getSelectedItem() == "Поставщик"){
                new Provider();
            }
        }


        this.dispose();
    }
}
