package com.sist.homework;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;


//"�θ޴�" ������,����,������ ������ �� �̺�Ʈó���� ���Ͽ� ActionListener�������̽��� �����ϵ��� ��
public class SistNote extends JFrame implements ActionListener{
	
	String fileName = "�������";
	
	//�޸����� �Է� �� ����� ���Ͽ� "������"�� ������ �Է��� �� �ִ� JTextArea�� �ɹ������� ����
	JTextArea jta;
	
	//������ �����̸��� ����� �����̸��� �����ϱ� ���� JFileChooser�� �ɹ������� ����
	JFileChooser jfc;
	
	//�����ڿ��� JTextArea ��ü�� �����Ͽ� �����ӿ� ����
	public SistNote() {
		jta = new JTextArea();
	
		//JFileChooser ��ü�� ����
		jfc = new JFileChooser("c:/myData");
		
		//add(jta);
		//�ؽ�Ʈ����� �ٷ� �����ӿ� ������
		//ȭ���� ��� ���ڵ��� �� ����
		//�׷��� �ؽ�Ʈ����� �ٷ� ���� �ʰ�
		//��ũ���� �ڵ����� ������ִ� JScrollPane���� ���μ� �����ӿ� ��ƾ� ��
		//�����ӿ� ���� �� ->JScrollPane
		JScrollPane jsp = new JScrollPane(jta);
		add(jsp);
		
		//�޴������ ũ�� ���ϱ�
		//�޴��ٸ� ���� ��ü ����
		JMenuBar jmb = new JMenuBar();
		
		//
		JMenu mn_file = new JMenu("����");
		
		//
		JMenuItem file_new = new JMenuItem("������");
		JMenuItem file_open = new JMenuItem("����");
		JMenuItem file_save = new JMenuItem("����");
		
		//"�θ޴�" ������,����,������ "�ָ޴�" ���Ͽ� ����
		mn_file.add(file_new);
		mn_file.add(file_open);
		mn_file.add(file_save);
		
		//
		jmb.add(mn_file);
		
		//�����ӿ� �޴��ٸ� ����
		setJMenuBar(jmb);
		
		//��ġ�� �θ޴� ������� ���Ŀ� ����
		//"�θ޴�" ������,����,���忡 ���Ͽ� �޴��� ������ �� � ���� �ϵ��� �̺�Ʈ�� ���
		//�Ű������� �̺�Ʈó����簴ü�� �����ؾ� �ϴµ�, �� Ŭ����(SistNote) �ڽ��� ó���ϵ��� �ϱ� ���Ͽ�
		//this�� ���� => ���� �����ϳ� -> ���� �Ѵ�
		file_new.addActionListener( this );
		file_open.addActionListener( this );
		file_save.addActionListener( this );
		
		
		//�������� ũ�⸦ �����ϰ� �������� �����ֵ��� ����
		setSize(800,600);
		setVisible(true);
		setTitle(fileName);
		
		
		
		/*â�� ó���� ��������  ȭ�鿡 3�ʵ��� "�ȳ��ϼ���" ���
		 getText�� �ݴ�
		 
		jta.setText("�ȳ��ϼ���");
		try {
			Thread.sleep(3000);
		}catch (Exception e) {
			// TODO: handle exception
		}
		jta.setText("");
		*/
	}

	//�θ޴� ������ �� �޼ҵ尡 ����
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//�޴� �߿� ���� ���������� �ľ��ϱ� ���Ͽ�
		//ActionEvent�� �޼ҵ� �߿� getActionCommand �޼ҵ带 �̿��Ͽ�
		//������ �޴��� "����"�� ������
		String cmd = e.getActionCommand();
		
		//������ �޴��� ���� ������ ��ó���� �ϵ��� ��
		if(cmd.equals("������")) {
			if(fileName.equals("�������")) {
				int a = JOptionPane.showConfirmDialog(this, "�����Ͻðڽ��ϱ�?", "����" ,JOptionPane.YES_NO_OPTION);
				if(a == JOptionPane.YES_OPTION) {
					int re = jfc.showSaveDialog(this);
					if(re == 0) {
						try {
							File file = jfc.getSelectedFile();
							FileWriter fw = new FileWriter(file);
							fw.write(jta.getText());
							fw.close();
							JOptionPane.showMessageDialog(this, "������ �����Ͽ����ϴ�.");
							fileName = file.getName();
							fileName = fileName.substring(0,fileName.indexOf("."));
							setTitle(fileName);
						} catch (Exception ex) {
							System.out.println("���ܹ߻�:"+ex.getMessage());
						}
					}
				}
			}
			
			//�ؽ�Ʈ����� �����ϰ� �����
			jta.setText("");
			fileName = "�������";
			setTitle(fileName);
			
		}else if(cmd.equals("����")) {
			if(fileName.equals("�������")) {
				int a = JOptionPane.showConfirmDialog(this, "�����Ͻðڽ��ϱ�?", "����" ,JOptionPane.YES_NO_OPTION);
				if(a == JOptionPane.YES_OPTION) {
					int re = jfc.showSaveDialog(this);
					if(re == 0) {
						try {
							File file = jfc.getSelectedFile();
							FileWriter fw = new FileWriter(file);
							fw.write(jta.getText());
							fw.close();
							JOptionPane.showMessageDialog(this, "������ �����Ͽ����ϴ�.");
							fileName = file.getName();
							fileName = fileName.substring(0,fileName.indexOf("."));
							setTitle(fileName);
						} catch (Exception ex) {
							System.out.println("���ܹ߻�:"+ex.getMessage());
						}
					}
				}
			}
			
			try {
				//"����" �޴��� ������ ��� �ִ� � ������ ������� �����ϱ� ���� ���̾�α� ���
				int re = jfc.showOpenDialog(this);
				
				//���̾�α׿��� "���"�� ������ �ʰ� "����"�� ������ �� ������ ������ �о���� ó���� �ϵ��� ��
				if(re == 0) {
					//���̾�α׿��� ������ ���������� ������
					File file = jfc.getSelectedFile();
						
						//�� ������ ������ ��ǻ�͸޸𸮷� �о���̱� ���� ��Ʈ�� ����
						FileReader fr = new FileReader(file);
						
						//������ ��� ������ ��� ���� ���ڿ� ������ ����
						//���Ϸκ��� �ѱ��ھ� �о�ͼ� �����ϱ� ���Ͽ� ""���� �ʱ�ȭ
						String data = "";
						
						//���Ϸ� ���� �ѱ��ھ� �б� ���� ���� ����
						int ch;
						
						//�ݺ����� �̿��Ͽ� ������ ���� �ƴҵ��� �ѱ��ھ� �о� ����
						while(true) {
							
							//���Ϸκ��� �ѱ��ھ� �о����
							//���ڿ� �ش��ϴ� �������� ��ȯ ��
							//���� ���̻� �о���� ������ ���� ������ ���� �����ϸ� -1�� ��ȯ
							ch = fr.read();
							
							//���Ϸκ��� ���̻� �о���� ������ ������ �ݺ����� Ż��
							if(ch == -1) {
								break;
							}
							
							//������ ���� �ƴ϶�� �о���� ������ ���ڸ� �ٽ� ���ڷ� ��ȯ�Ͽ�
							//���ڿ� ���� data�� ���� ��
							data = data + (char)ch;
						}	
						//������ ������ ��� �о���� �������� while�� Ż����
						//while�� Ż���������� ������ ��� ������ String ���� data�� ����� ����
						//data�� ������ �ؽ�Ʈ����� ����
						jta.setText(data);
						
						//������ �о�Դٰ� �޽��� ���
						JOptionPane.showMessageDialog(this, "������ �о�Խ��ϴ�.");
						
						//�о�� ������ ������ ��� �ִ� File ��ü�κ��� ���ϸ��� �̾Ƽ� �ɹ�����  fileName�� ����
						fileName = file.getName();
						//String []arr = fileName.split(".");
						//fileName = arr[0];
						fileName = fileName.substring(0,fileName.indexOf("."));
						
						//���ϸ��� �������� ����ǥ���ٷ� ����
						setTitle(fileName);
				}	
			} catch (Exception ex) {
				System.out.println("���ܹ߻�:"+ex.getMessage());
			}
				
			
		}else if(cmd.equals("����")) {
			//"����"�޴��� ������ ��� � �����̸����� ������ ������ �����ϵ��� ���̾�α� ���
			//�Ű����� this�� �� ���̾�α׸� � ������������ ��� ������ ����
			//���� �� ������ SistNote�� ����� �ǹ̷� ��ü�ڽ��� �ǹ��ϴ� this ����
			//showSaveDialog���� ����ڴ� ������ ���� ���� �ְ� ��Ҹ� ���� ���� ����
			//��Ҹ� �����µ� ���� �����ϴ� ���ɾ ó���ϸ� ���
			//�׷��� �� �޼ҵ�� ������ ������ 0�� ��ȯ�ϰ� ��Ҹ� ������ 1�� ��ȯ
			//�� ��ȯ�ϴ� ���� ������ ��� �� ���� ���� �״��� ��ó���� �ٸ��� �� �� ����
			int re = jfc.showSaveDialog(this);	
			
			//"�����ϱ�"���̾�α׿��� "����"�� ������ �� ���������� ���� ���ɾ���� �����ϵ��� ��
			if(re == 0) {
				//���̾�α׿��� ������ ������ ������ �о� ��
				File file = jfc.getSelectedFile();
				
				//�� ������ ���Ϸ� �ؽ�Ʈ����� ������ ������ ����ϱ� ���� ��Ʈ���� ����
				//���ڴ����� ����� ���� Writer�� �ļ��� FileWriter�� �̿�
				//����°� ���õ� ��� �����ڿ� �޼ҵ���� ���ܸ� �����ϰ� ����
				//�� ���ܵ��� RunTimeException�� �ļյ��� �ƴϱ� ������
				//����ڰ� �ݵ�� ����ó�� �ؾ� ��
				try {
					FileWriter fw = new FileWriter(file);
					
					//�ؽ�Ʈ�������� ������ ������ �о�ͼ� ���Ϸ� ��� ��
					fw.write( jta.getText() );
					
					//������ ����� �������Ƿ� ������ �ݾ���
					fw.close();
					
					//��������� �˷��� ������ �ֿܼ� ������� ���� ���̾�α׸� ���
					JOptionPane.showMessageDialog(this, "���Ϸ� �����Ͽ����ϴ�.");
					
					//������ ������ ������ ���� �ִ� ��ü File file�� ���� �����̸��� �̾ƿͼ�
					//�ɹ����� fileName�� ����
					//fileName = file.getName();
					//�޸�.txt
					//.���� �и��Ͽ� ���ϸ��� �̾ƿ�
					fileName = file.getName();
					//String []arr = fileName.split(".");
					//fileName = arr[0];
					//split�޼ҵ�� .���� �и� �Ұ���
					fileName = fileName.substring(0,fileName.indexOf("."));
					
					//���ϸ��� ����ǥ���ٿ� ����
					setTitle(fileName);
					
				}catch (Exception ex) {	//���ܺ����̸��� �̺�Ʈ�����̸��� �ٸ��� ��������� ��
					System.out.println("���ܹ߻�:"+ex.getMessage());	
					//���ܰ� �߻��ϸ� ���ܸ޽����� ����Ͽ�
					//�޽����� �ľ��Ͽ� ������ �ذ��ϵ��� ��
				}
				
			}
			
			
		}
	}
}
