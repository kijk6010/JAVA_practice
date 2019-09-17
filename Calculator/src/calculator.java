import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class calculator extends JFrame implements ActionListener {

	private JPanel panel;
	private JTextField display;
	private JButton[] buttons;
	private String[] labels = {"Backspace","","","+","CE","C","7","8","9","-","sqrt","pow","4","5","6","x","%","sin","1","2","3","/","1/x","cos","","0","-/+",".","=","tan"};
	
	private double result = 0;
	private String operator = "=";
	private boolean startOfNumber = true;
	
	public calculator(){
		display = new JTextField(35);
		panel = new JPanel();
		display.setText("0.0");
		
		panel.setLayout(new GridLayout(5,6));
		buttons = new JButton[40];
		int index =0;
		for(int rows =0; rows < 5; rows++){
			for(int cols =0; cols<6; cols++){
				buttons[index] = new JButton(labels[index]);
				if(cols>=3||(cols==2&&rows==4)){
					buttons[index].setForeground(Color.yellow);
				}
				else
					buttons[index].setForeground(Color.WHITE);
				buttons[index].setBackground(Color.black);
				panel.add(buttons[index]);
				buttons[index].addActionListener(this);
				index++;
			}
		}
		add(display, BorderLayout.NORTH);
		add(panel,BorderLayout.CENTER);
		setVisible(true);
		pack();
	}
		
		
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		if(command.equals("C")){
			startOfNumber = true;
			result = 0;
			operator = "=";
			display.setText("0.0");
		}
		else if(command.equals("Backspace")){
			 String temp = display.getText();
			  temp = temp.substring(0,temp.length()-1);
			  display.setText(temp); 
		}
		else if(command.equals("CE")){
			String temp = display.getText();
			  temp = temp.substring(0,temp.length()-temp.length());
			  display.setText(temp); 
		}
		else if(command.equals("-/+")){
			display.setText(""+-Double.parseDouble(display.getText()));
		}
		else if(command.equals("1/x")){
			display.setText(""+1/Double.parseDouble(display.getText()));
		}
		else if(command.equals("0")||command.equals("1")||command.equals("2")||command.equals("3")||command.equals("4")||command.equals("5")||command.equals("6")||command.equals("7")||command.equals("8")||command.equals("9")||command.equals(".")){
			if(startOfNumber==true)
				display.setText(command);
			else
				display.setText(display.getText()+command);
			startOfNumber = false;
		}
		
		else {
			
			if(startOfNumber){
				if(command.equals("-")){
					display.setText(command);
					startOfNumber = false;
				}
				else operator = command;
			}
			else{
				double x = Double.parseDouble(display.getText());
				calculate(x);
				operator = command;
				startOfNumber = true;
			}
		}
	}
	
	private void calculate(double n){
		if(operator.equals("+")){
			result += n;
		display.setText(""+result);
		}
		else if(operator.equals("-")){
			result -= n;
		display.setText(""+result);
		}
		else if(operator.equals("x")){
			result *= n;
		display.setText(""+result);
		}
		else if(operator.equals("/")){
			result /= n;
		display.setText(""+result);
		}
		else if(operator.equals("%")){
			result = result % n;
		display.setText(""+result);
		}
		else if(operator.equals("sqrt")){
			result = Math.sqrt(n);
		display.setText(""+result);
		}
		else if(operator.equals("pow")){
			result = Math.pow(result,n);
		}
		else if(operator.equals("sin")){
			result = Math.sin(getRadian(n));
		}
		else if(operator.equals("cos")){
			result = Math.cos(getRadian(n));
		}
		else if(operator.equals("tan")){
			result = Math.tan(getRadian(n));
		}
		else if(operator.equals("="))
			result = n;
		
		display.setText(""+result);
	}


	private double getRadian(double num) {
		// TODO Auto-generated method stub
		return num*Math.PI/180.0;
	}
}
	

