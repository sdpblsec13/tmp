import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;


public class Main extends JFrame implements ActionListener{

	JTextField txfield1 = new JTextField(15);
	JTextField txfield2 = new JTextField(15);
	JTextField txfield3 = new JTextField(15);
	JTextField txfield4 = new JTextField(15);
	
	double num11;
    double num12;
    double num21;
    double num22;
    double num31;
    double num32;
    int subjectsNum;
    int testNum;
    
    JComboBox<String> cb1, cb2, cb3,cb4;     // メニューやボタン用のコンボボックス
    static  String[] labelStr = {"科目名","課題", "テスト","出席"};
    static String[] subjects =  {"コンピュータシステム","Critical Listening","基礎論理回路","データベースシステム","オブジェクト指向プログラミング(2)","SDPBL(2)","ハードウェア記述言語"};
	
	static String[] actions = {"0%","10%","20%","30%","40%","50%","60%","70%","80%","90%","100%"};
    
	JPanel pn1;
	Button btCal;
	
	double max,min,half;

    public static void main(String[] args)  throws IOException{
         Main frame = new Main();
        frame.setTitle("東京都市大学");
        frame.setSize(640, 480);
        frame.setLocationRelativeTo(null);
    	frame.setResizable(false);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        
    }
   


    public  Main() {
    	
    	  Container contentPane = getContentPane();
          contentPane.setBackground(Color.ORANGE);
    
    	//コンテイナー
    	Container pane = getContentPane();
    	
    	 JPanel titlePanel = new JPanel();
         JLabel titleLabel = new JLabel("    ～GRADE CALCULATOR～");
         titleLabel.setFont(new Font("GSゴシック",Font.PLAIN,40));
         titleLabel.setForeground(Color.BLACK);
         titlePanel.setBackground(Color.ORANGE);
         titlePanel.add(titleLabel);
        getContentPane().add(titlePanel,BorderLayout.NORTH);
        
        
        //kokokara
        
        pn1 = new JPanel();
    	pn1.setLayout(new FlowLayout(FlowLayout.LEFT));  	
    	pn1.setPreferredSize(new Dimension(315,170));
    	
    	JPanel pn2 = new JPanel();
    	pn2.setLayout(new FlowLayout(FlowLayout.RIGHT));  	
    	pn2.setPreferredSize(new Dimension(295,170));
    	
    	
    	JPanel top = new JPanel();
    	top.setLayout(new FlowLayout(FlowLayout.CENTER));  	
    	top.setPreferredSize(new Dimension(615,40));
    	
    	JLabel lb1 = new JLabel(labelStr[0]);
    	lb1.setForeground(Color.black);
    	top.add(lb1);
    	
    	cb1 = new JComboBox<String>();
    	cb1.setEditable(false);
    	for(int i=0;i<subjects.length;i++) {
    		cb1.addItem(subjects[i]);
    	}
    	cb1.addActionListener(this);  // コンボボックスにリスナ(アクション監視)付加
        top.add(cb1);                 // パネルにコンボボックスを付加
        pane.add(top);
        pane.setLayout(new FlowLayout(FlowLayout.LEFT));// コンテナにパネル追加
      
        
        //課題
        JLabel lb2 = new JLabel(labelStr[1]);  // "Color"ラベルの生成
        lb2.setForeground(Color.black);        // ラベルの文字色を黒とする
        top.add(lb2);                          // パネルにラベルを付加

        cb2 = new JComboBox<String>();                // コンボボックス生成
        cb2.setEditable(false);               // コンボボックスの編集は不可とする
        for (int i=0; i<actions.length; i++) {   // 色メニュー項目数分繰り返し
          cb2.addItem(actions[i]);               // 色メニュー項目追加
        }

        cb2.addActionListener(this);  // コンボボックスにリスナ(アクション監視)付加
        top.add(cb2);                 // パネルにコンボボックスを付加
        pane.add(top); 
      
        
        //テスト
        JLabel lb3 = new JLabel(labelStr[2]);  // "Filled"ラベルの生成
        lb3.setForeground(Color.black);        // ラベルの文字色を黒とする
        top.add(lb3);                          // パネルにラベルを付加

        cb3 = new JComboBox<String>();                // コンボボックス生成
        cb3.setEditable(false);               // コンボボックスの編集は不可とする
        for (int i=0; i<actions.length; i++) {   // 塗りつぶしメニュー項目数分繰り返し
          cb3.addItem(actions[i]);               // 塗りつぶしメニュー項目追加
        }

        cb3.addActionListener(this);  // コンボボックスにリスナ(アクション監視)付加
        top.add(cb3);                 // パネルにコンボボックスを付加
        pane.add(top);                  // コンテナにパネル追加
        
         //出席
        JLabel lb4 = new JLabel(labelStr[3]);  // "Filled"ラベルの生成
        lb4.setForeground(Color.black);        // ラベルの文字色を黒とする
        top.add(lb4);                          // パネルにラベルを付加

        cb4 = new JComboBox<String>();                // コンボボックス生成
        cb4.setEditable(false);               // コンボボックスの編集は不可とする
        for (int i=0; i<actions.length; i++) {   // 塗りつぶしメニュー項目数分繰り返し
          cb4.addItem(actions[i]);               // 塗りつぶしメニュー項目追加
        }

        cb4.addActionListener(this);  // コンボボックスにリスナ(アクション監視)付加
        top.add(cb4);                 // パネルにコンボボックスを付加
       
        
      //  FlowLayout layout = new FlowLayout();
        //layout.setAlignment(FlowLayout.RIGHT);
        getContentPane().add(txfield1,BorderLayout.PAGE_END);
        txfield1.addActionListener(this);
        getContentPane().add(txfield2,BorderLayout.PAGE_END);
        txfield2.addActionListener(this);
        getContentPane().add(txfield3,BorderLayout.PAGE_END);
        txfield3.addActionListener(this);
        
       // txfield1.setText("　　課題個数"); 
        
      
        JLabel task = new JLabel("Task Number");
        task.setFont(new Font("GSゴシック",Font.PLAIN,15));
        task.setForeground(Color.BLACK);
        pn2.add(task); 
        pn2.add(txfield1);
     
        
        JLabel testScore = new JLabel("       Test Score");
        testScore.setFont(new Font("GSゴシック",Font.PLAIN,15));
        testScore.setForeground(Color.BLACK);
        testScore.setFocusable(false);
        testScore.setPreferredSize(new Dimension(110, 100));
        add(testScore, BorderLayout.EAST); 
        pn2.add(testScore);    
        pn2.add(txfield2);
     
        JLabel attendance = new JLabel("Attendances");
        attendance.setFont(new Font("GSゴシック",Font.PLAIN,15));
        pn2.add(attendance);
        pn2.add(txfield3);
        
       
        getContentPane().add(pn2);
       
        
        
//        JFreechart
       DefaultPieDataset dataset = new DefaultPieDataset();
        //dataset.setValue("work", 0);
        //dataset.setValue("test", 0);
        //dataset.setValue("attendancs", 0);
        
        // チャートを作成
        JFreeChart chart = ChartFactory.createPieChart(
                "Grade", dataset, true, true, false);
        
        // チャートパネルを作成してフレームに追加
        ChartPanel chartPanel = new ChartPanel(chart);
        add(chartPanel);
        chartPanel.setFocusable(false);
        chartPanel.setPreferredSize(new Dimension(315, 170));
        add(chartPanel, BorderLayout.EAST); 
     // （1）グラフ全体の背景色の設定
        chart.setBackgroundPaint(Color.WHITE);

        // （2）グラフ全体の境界線の設定
        chart.setBorderVisible(true);
        chart.setBorderPaint(Color.BLACK);
        chart.setBorderStroke(new BasicStroke(10));
    
   
	
			JLabel resultLabel1 = new JLabel();
	        resultLabel1.setFocusable(false);
	        resultLabel1.setPreferredSize(new Dimension(615, 15));
	        add(resultLabel1, BorderLayout.SOUTH);		
	        resultLabel1.setText("課題 max = ???% min = ???% half = ???%");
	       getContentPane().add(resultLabel1);
	      
	       JLabel resultLabel2 = new JLabel();
	        resultLabel2.setFocusable(false);
	        resultLabel2.setPreferredSize(new Dimension(615, 25));
	        add(resultLabel2, BorderLayout.SOUTH); 	
	        resultLabel2.setText("テスト = ???% ");
	       getContentPane().add(resultLabel2);
	       
	       JLabel resultLabel3 = new JLabel();
	        resultLabel3.setFocusable(false);
	        resultLabel3.setPreferredSize(new Dimension(615, 15));
	        add(resultLabel3, BorderLayout.SOUTH); 		
	        resultLabel3.setText("出席 = ???% ");
	       getContentPane().add(resultLabel3);
	       
	       JLabel resultLabel4 = new JLabel();
	        resultLabel4.setFocusable(false);
	        resultLabel4.setPreferredSize(new Dimension(615, 40));
	        add(resultLabel4, BorderLayout.SOUTH); 	
	        resultLabel4.setText("TOTALmax = ???% TOTALmin = ???% TOTALhalf = ???%");
	       getContentPane().add(resultLabel4);
	     
	       /*
	       JLabel gradeLabel = new JLabel();
	        gradeLabel.setFocusable(false);
	        gradeLabel.setPreferredSize(new Dimension(400, 400));
	        add(gradeLabel, BorderLayout.EAST); 	
	        gradeLabel.setText("成績 = ?");
	       pn1.add(gradeLabel);
	      */
	     
          	//ボタン
	       btCal = new Button("Calculate");
	       getContentPane().add(btCal);
		
		 btCal.addActionListener(new ActionListener() {
	            //ボタンがクリックされたときに行う処理を書く。
	    
	            public void actionPerformed(ActionEvent ae) {
	            	resultLabel1.setText("課題 max = "+String.format("%.1f", max)+"%"+ "  min = "+String.format("%.1f", min)+"%"+ "  half = "+String.format("%.1f", half)+"% "  );
	            	resultLabel2.setText("テスト = "+String.format("%.1f",num22)+"%"  );
	            	resultLabel3.setText("出席 = "+String.format("%.1f",num32)+"%"  );
	            	resultLabel4.setText("TOTALmax = "+String.format("%.1f",(max + num22 +num32))+"%  TOTALmin = "+String.format("%.1f",(min + num22 +num32))+"%  TOTALhalf = "+String.format("%.1f",(half + num22 +num32))+"%"  );
//	            	円チャート
	            	double total = max + num22 + num32;
	               	double blank = 100 - total; 
	                dataset.setValue("Total", total);
//	                dataset.setValue("Test", num22);
//	                dataset.setValue("Attendances", num32);
	                dataset.setValue("",blank);
	        
	               	
	                
	     	       //円チャートラベル
	               	String label;
	               	
	               	if(total >= 90) {
	               		label = "秀";
	               	}else if(total >= 80) {
	               		label = "優";
	               	}else if(total >= 70) {
	               		label = "良";
	               	}else {
	               		label = "可";
	               	}
	            	
	     	      	JLabel gradeLabel = new JLabel(label);
	     	    
	                 gradeLabel.setFont(new Font("GSゴシック",Font.PLAIN,60));
	                 gradeLabel.setForeground(Color.black);
	                 gradeLabel.setFocusable(false);
	                 gradeLabel.setPreferredSize(new Dimension(60, 150));    
	                 add(gradeLabel, BorderLayout.CENTER); 
	               	chartPanel.add(gradeLabel);
	               
	               	

	            }
	        });
		
		
    	
 
    	
    }
    
 
   public void actionPerformed(ActionEvent evt) {
	   	
	   	if(evt.getSource() == cb1) {
	   		judge();
	   	}

	    if (evt.getSource() == cb2) {   // 図形の種類のメニューが選択された場合は
	      score1Operation();//課題
	    }

	    if (evt.getSource() == cb3) {   // 図形の色のメニューが選択された場合は
	      score2Operation();//テスト
	    }

	    if (evt.getSource() == cb4) {   // 塗りつぶしのメニューが選択された場合は
	      score3Operation();//出席
	    }
	    
	    if(evt.getSource() == txfield1) {
	    	txfield1Operation();//課題個数
	    }
	    if(evt.getSource() == txfield2) {
	    	txfield2Operation();//テストの点数
	    }
	    if(evt.getSource() == txfield3) {
	    	txfield3Operation();//出席回数
	    }
    }
   
   void judge() {
	   String selStr;
	   selStr = (String)cb1.getSelectedItem();
	   int i = 0;
	   while(selStr != subjects[i]){
		   i++;
	   	}
	   if(i == 1 || i==4 || i==5 || i==6) { //週１の教科
		   subjectsNum = 7;
		   testNum = 1;
	   }else{//週２の教科
		  subjectsNum = 14;
		  testNum = 2;
	   }
	   System.out.println("subejectsNum = "+subjectsNum);
		   
	   
	   
   }
   
   void score1Operation() { 
	   String selStr;  // 選択された文字列記憶用変数
	   selStr = (String)cb2.getSelectedItem();  // 選択された項目の文字列を得る
	   int i = 0;
	   
	   while(selStr != actions[i]){
	   i++;
   		}
	   num11 = i*10;
	   System.out.println("課題 "+num11+"%");
	 
       
   }
   
   void score2Operation() { 
	   String selStr;  // 選択された文字列記憶用変数
	   selStr = (String)cb3.getSelectedItem();// 選択された項目の文字列を得る
	   int i = 0;
	  
	   while(selStr != actions[i]){
		   i++;
	   		}
		   num21 = i*10;
		   System.out.println("テスト "+num21+"%");
   }
   
   void score3Operation() { 
	  
	   String selStr;  // 選択された文字列記憶用変数
	   selStr = (String)cb4.getSelectedItem();  // 選択された項目の文字列を得る
	   int i = 0;
	   
	   while(selStr != actions[i]){
	   i++;
   		}
	   num31 = i*10;
	   System.out.println("出席 "+num31+"%");
	  
   }
   
   void txfield1Operation() {
	String selStr;

	selStr = txfield1.getText();
	num12 = Integer.parseInt(selStr);//提出した課題の個数

	num11 = num11 / subjectsNum ;
	
	
	max = num11 * num12;//100%
	
	min = num11 * num12 * 0.3;//30%
	
	half = num11 * num12 *0.5;//50%

	System.out.println("課題１個当たり"+num11+"%");
	System.out.println("max = "+max+"  min = "+min+"  half = "+half);
	
   }
   void txfield2Operation() {

		String selStr;
		selStr = txfield2.getText();
		num22 = Integer.parseInt(selStr);//テストの点数

		num22 = num21 * num22 *0.01;
		

		System.out.println("テストの比率"+num21+"%のとき100%換算だと"+num22+"%とれてるよ");
		
		
	   }

   void txfield3Operation() {
		String selStr;
	
		selStr = txfield3.getText();
		num32 = Integer.parseInt(selStr);//出席した回数

		num31 = num31 / subjectsNum ;
		num32 = num31 * num32;
	
		System.out.println("出席１回当たり"+num31+"%");
		
	   }


}
