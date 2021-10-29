package com.sist.draw;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.awt.Color;


//MyFrame클래스 자신이 JMenuItem을 눌렀을 때 이벤트처리 담당자가 되도록 하기 위하여
//ActionListener 인터페이스 구현
public class MyFrame extends JFrame implements ActionListener{

	private LinePanel lp;
	
	//저장할 파일명과 열어올 파일명을 선택할 수 있도록 하는 JFileChooser를 맴버변수로 선언
	JFileChooser jfc;
	
	//색상을 선택하기 위한 다이얼로그 JColorChooser를 맴버로 선언
	JColorChooser jcc;
	
	public MyFrame() {
		lp = new LinePanel();
		add(lp);
		
		//JFileChooser 객체 생성
		jfc = new JFileChooser("c:/myData");
		
		//JColorChooser 객체 생성
		jcc = new JColorChooser();
		
		//메뉴바를 생성
		JMenuBar jmb = new JMenuBar();
		
		//주메뉴 "파일" 생성
		JMenu mn_file = new JMenu("파일");
		
		//주메뉴 "그리기도구" 생성
		JMenu mn_draw = new JMenu("그리기도구");
		
		//주메뉴 "그리기색상" 생성
		JMenu mn_color = new JMenu("그리기색상");
		
		//부메뉴 "새파일" 생성
		JMenuItem file_new = new JMenuItem("새파일");
		
		//부메뉴 "열기"를 생성
		JMenuItem file_open = new JMenuItem("열기");
		
		//부메뉴 "저장"을 생성
		JMenuItem file_save = new JMenuItem("저장");
		
		//주메뉴 그리기도구에 담길 부메뉴 "선","사각형","원"
		JMenuItem draw_line = new JMenuItem("선");
		JMenuItem draw_rect = new JMenuItem("사각형");
		JMenuItem draw_oval = new JMenuItem("원");
		
		//부메뉴들을 주메뉴에 담는다
		mn_file.add(file_new);
		mn_file.add(file_open);
		mn_file.add(file_save);
		
		//선,사각형,원 부메뉴들을 "그리기도구" 주메뉴에 담음
		mn_draw.add(draw_line);
		mn_draw.add(draw_rect);
		mn_draw.add(draw_oval);
		
		//주메뉴 "그리기색상"에 담길 부메뉴 만듬
		JMenuItem color_red = new JMenuItem("빨강");
		JMenuItem color_blue = new JMenuItem("파랑");
		JMenuItem color_green = new JMenuItem("초록");
		JMenuItem color_other = new JMenuItem("다른색상");
		
		//빨강,파랑,초록,다른색상들을 "그리기색상" 주메뉴에 담음
		mn_color.add(color_red);
		mn_color.add(color_blue);
		mn_color.add(color_green);
		mn_color.add(color_other);
		
		//주메뉴를 메뉴바에 담음
		jmb.add(mn_file);
		
		//"그리기도구" 주메뉴를 메뉴바에 담음
		jmb.add(mn_draw);
		
		//"그리기색상" 주메뉴를 메뉴바에 담음
		jmb.add(mn_color);
		
		//메뉴바를 프레임에 설정
		this.setJMenuBar(jmb);
		
		//각각의 JMenuItem에 대하여 이벤트 등록
		file_new.addActionListener(this);
		file_open.addActionListener(this);
		file_save.addActionListener(this);
		
		//그리기도구의 부메뉴들에 대해서도 이벤트 등록
		draw_line.addActionListener(this);
		draw_rect.addActionListener(this);
		draw_oval.addActionListener(this);
		
		//그리기색상의 부메뉴들에 대해서도 이벤트 등록
		color_red.addActionListener(this); 
		color_blue.addActionListener(this); 
		color_green.addActionListener(this); 
		color_other.addActionListener(this); 
		
		
		setSize(600,400);
		setVisible(true);
		setTitle("선그리기");
		
	}

	
	//각각의 JMenuItem을 누르면 actionperformed메소드가 동작
	@Override
	public void actionPerformed(ActionEvent e) {
		//JMenuItem중에 어떤 메뉴가 눌러졌는지 파악하기 위하여 매개변수 ActionEvent의 getActionCommand()이용
		String cmd = e.getActionCommand();
		//System.out.println(cmd+"가 눌러짐");
		
		if(cmd.equals("저장")) {
			try {
				
				System.out.println("저장합니다.");
				//어디에 어떤 이름으로 저장할지 선택하도록 JFileChooser 다이얼로그를 띄움
				int re = jfc.showSaveDialog(this);
				
				//"저장하기"다이얼로그에서 "저장"을 누를 수도 있고 "취소"를 누를 수도 있음
				//"저장"을 누르면 0이 반환 
				if(re == 0) {
					
					//다이얼로그에서 선택한 파일을 가져옴
					File file = jfc.getSelectedFile();
					
					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
					oos.writeObject(lp.list);
					oos.close();
				}	
			} catch (Exception ex) {
				System.out.println("예외발생:"+ex.getMessage());
			}
		}else if(cmd.equals("열기")) {
			
			try {
				
				System.out.println("저장합니다.");
				int re = jfc.showOpenDialog(this);
				
				if(re == 0) {
					File file = jfc.getSelectedFile();
					
					ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
					lp.list = (ArrayList<GraphicInfo>)ois.readObject();
					lp.repaint();
					ois.close();
				}
			} catch (Exception ex) {
				System.out.println("예외발생:"+ex.getMessage());
			}
			
			
		}else if(cmd.equals("새파일")) {
			
			//사용자가 그린 그래픽이 담겨 있는 리스트를 모두 지움
			lp.list.clear();
			
			//그래픽을 그리는 판넬을 다시 그림
			lp.repaint();
			
		}else if(cmd.equals("선")) {					//"그리기도구"메뉴에 대한 처리들
			lp.drawType = 0;
		}else if(cmd.equals("사각형")) {
			lp.drawType = 1;
		}else if(cmd.equals("원")) {
			lp.drawType = 2;
		}else if(cmd.equals("빨강")) {				//"그리기색상" 메뉴에 대한 처리
			lp.drawColor = Color.RED;
		}else if(cmd.equals("파랑")) {
			lp.drawColor = Color.BLUE;
		}else if(cmd.equals("초록")) {
			lp.drawColor = Color.GREEN;
		}else if(cmd.equals("다른색상")) {
			//다른 색상을 선태하도록 JColorChooser 다이얼로그를 띄움
			Color color = jcc.showDialog(this, "그리기색상", Color.RED);
			 if(color != null) {
				 lp.drawColor = color;
			 }
		}
		
	}
}
