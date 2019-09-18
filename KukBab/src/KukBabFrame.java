import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class KukBabFrame extends JFrame implements ActionListener,ItemListener {
	private int sum =0;
	private JPanel[] p= new JPanel[5];
	private JButton[] b= new JButton[2];
	JTextArea ta;
	JScrollPane s;
	ImageIcon image;
	private JTextField text;
	private JLabel message;
	private ButtonGroup bg;
	public welcome_panel welcomeP = new welcome_panel();
	public size_panel sizeP = new size_panel();
	public type_panel typeP = new type_panel();
	public submenu_panel submenuP = new submenu_panel();
	
	public KukBabFrame() {
		//창의 가로,세로 길이 설정
		this.setSize(500,200);
		//윈도우창 종료 시 프로세스까지 깔끔하게 닫을 수 있게 해줌.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//창의 크기를 조절할 수 없게 한다.
		this.setResizable(false);
		this.setTitle("국밥 주문");
		
		for(int i=0; i<5;i++)
			p[i] = new JPanel();
		
		p[0].setLayout(new GridLayout(2,1));
		p[1].setLayout(new BorderLayout());
		p[1].add(welcomeP,BorderLayout.NORTH);
		p[1].add(sizeP,BorderLayout.EAST);
		p[1].add(typeP,BorderLayout.WEST);
		p[1].add(submenuP,BorderLayout.CENTER);
		welcomeP.setBackground(Color.orange);
	
		sizeP.setBackground(Color.white);
		typeP.setBackground(Color.white);
		submenuP.setBackground(Color.white);
		
		add(p[1]);
		
		String[] buttonStr= {"주문","취소"};
		for(int i=0;i<2;i++) {
			b[i]=new JButton(buttonStr[i]);
			p[4].add(b[i]);
			b[i].addActionListener(this);
		}
		p[1].add(p[4],BorderLayout.SOUTH);
		
		ta= new JTextArea(10,35);
		ta.setEditable(false);
		ta.setBackground(Color.white);
		s= new JScrollPane();
		p[2].add(ta);
		p[2].add(s);
		s.setViewportView(ta);
		text = new JTextField(35);
		text.setBackground(Color.cyan);
		text.setEditable(false);
		p[3].add(text);
		p[0].add(p[1]);
		p[0].add(p[2]);
		add(p[3],BorderLayout.SOUTH);
		add(p[0],BorderLayout.NORTH);
		pack();
		//각 리스너 정의부분
		for(int i=0;i<3;i++) {
			typeP.rb[i].addActionListener(this);
			sizeP.rb[i].addActionListener(this);
			
			typeP.rb[i].addItemListener(this);
			sizeP.rb[i].addItemListener(this);
		}
		for(int i=0;i<4;i++) {
			submenuP.cb[i].addActionListener(this);
			
			submenuP.cb[i].addItemListener(this);
		}
		setVisible(true);
	}
	
	class welcome_panel extends JPanel{
		welcome_panel(){
			message = new JLabel("지현 원조 순대국밥집에 오신 것을 환영합니다.");
			image = new ImageIcon("Son.PNG");
			message.setIcon(image);
			add(message);
		}
	}
	
	class size_panel extends JPanel{
		public JRadioButton[] rb = new JRadioButton[3];
		public size_panel(){
			setLayout(new GridLayout(3,1));
			rb[0] = new JRadioButton("어린이(-2천원)");
			rb[1] = new JRadioButton("보통(추가없음)");
			rb[2] = new JRadioButton("특(+1천원)");
			
			bg = new ButtonGroup();
			for(int i=0;i<3;i++) {
				bg.add(rb[i]);
			}
			setBorder(BorderFactory.createTitledBorder("국밥사이즈"));
			for(int i=0;i<3;i++) {
				add(rb[i]);
			}
		}
		public int calc() {
			int[] cost = {-2000, 0, 1000};
			for(int i=0;i<rb.length;i++) {
				if(rb[i].isSelected()) {
					return cost[i];
				}
			}
			return 0;
		}
	}
	
	class type_panel extends JPanel{
		public JRadioButton[] rb = new JRadioButton[3];
		public type_panel(){
			setLayout(new GridLayout(3,1));
			
			rb[0] = new JRadioButton("순대국밥(6천원)");
			rb[1] = new JRadioButton("얼큰순대국밥(7천원)");
			rb[2] = new JRadioButton("고기만국밥(6천5백원");
			bg = new ButtonGroup();
			for(int i=0;i<3;i++) {
				bg.add(rb[i]);
			}
			setBorder(BorderFactory.createTitledBorder("국밥 메뉴"));
			for(int i=0;i<3;i++) {
				add(rb[i]);
			}
		}
		public int calc() {
			int[] cost= {6000,7000,6500};
			for(int i=0;i<rb.length;i++) {
				if(rb[i].isSelected()) {
					return cost[i];
				}
			}
			return 0;
		}
	}
	
	class submenu_panel extends JPanel{
		public JCheckBox[] cb = new JCheckBox[4];
		
		public submenu_panel(){
			setLayout(new GridLayout(4,1));
			cb[0]=new JCheckBox("찰순대(5천원)");
			cb[1]=new JCheckBox("토종순대(1만원)");
			cb[2]=new JCheckBox("머리고기(1만원)");
			cb[3]=new JCheckBox("모듬순대(2만원)");
		
			setBorder(new TitledBorder("추가메뉴"));
			for(int i=0;i<4;i++) {
				add(cb[i]);
			}
		}
		public int calc() {
			int[] cost= {5000,10000,10000,20000};
			int ct=0;
			for(int i=0;i<cb.length;i++) {
				if(cb[i].isSelected()) {
					ct+=cost[i];
				}
			}
			return ct;
		}
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==typeP.rb[0]) {
			if(typeP.rb[0].isSelected()) {
				ta.setText(ta.getText()+"\n"+typeP.rb[0].getText()+"를 선택     >+6000");
			}
		}
		else if(e.getSource()==typeP.rb[1]) {
			if(typeP.rb[1].isSelected()) {
				ta.setText(ta.getText()+"\n"+typeP.rb[1].getText()+"를 선택     >+7000");
			}
		}
		else if(e.getSource()==typeP.rb[2]) {
			if(typeP.rb[2].isSelected()) {
				ta.setText(ta.getText()+"\n"+typeP.rb[2].getText()+"를 선택     >+6500");
			}
		}
		
		if(e.getSource()==submenuP.cb[0]) {
			if(submenuP.cb[0].isSelected()) {
				ta.setText(ta.getText()+"\n"+submenuP.cb[0].getText()+"를 선택     >+5000");
			}
		}
		if(e.getSource()==submenuP.cb[1]) {
			if(submenuP.cb[1].isSelected()) {
				ta.setText(ta.getText()+"\n"+submenuP.cb[1].getText()+"를 선택     >+10000");
			}
		}
		if(e.getSource()==submenuP.cb[2]) {
			if(submenuP.cb[2].isSelected()) {
				ta.setText(ta.getText()+"\n"+submenuP.cb[2].getText()+"를 선택     >+10000");
			}
		}
		if(e.getSource()==submenuP.cb[3]) {
			if(submenuP.cb[3].isSelected()) {
				ta.setText(ta.getText()+"\n"+submenuP.cb[3].getText()+"를 선택     >+20000");
			}
		}
		
		if(e.getSource()==sizeP.rb[0]) {
			if(sizeP.rb[0].isSelected()) {
				ta.setText(ta.getText()+"\n"+sizeP.rb[0].getText()+"를 선택     >-2000");
			}
		}
		else if(e.getSource()==sizeP.rb[1]) {
			if(sizeP.rb[1].isSelected()) {
				ta.setText(ta.getText()+"\n"+sizeP.rb[1].getText()+"를 선택     >+   0");
			}
		}
		else if(e.getSource()==sizeP.rb[2]) {
			if(sizeP.rb[2].isSelected()) {
				ta.setText(ta.getText()+"\n"+sizeP.rb[2].getText()+"를 선택     >+1000");
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==b[0]) {
			sum = sizeP.calc()+typeP.calc()+submenuP.calc();
			this.text.setText("총계"+sum+"원");
		}
		if(e.getSource()==b[1]) {
			typeP.rb[0].setSelected(true);
			
			sizeP.rb[1].setSelected(true);
			
			submenuP.cb[0].setSelected(false);
			submenuP.cb[1].setSelected(false);
			submenuP.cb[2].setSelected(false);
			submenuP.cb[3].setSelected(false);
			
			ta.setText("");
			sum = 0;
			this.text.setText("총계"+sum+"원");
		}
	}
}
