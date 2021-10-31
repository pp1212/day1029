package com.sist.file03;

import java.io.File;
import java.io.FileReader;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;

public class FileList extends JFrame implements MouseListener, ActionListener{
	JList<Vector<String>> list;
	JTextArea jta;
	String path = "c:/myData";
	
	//JList�� �����͸� ���� vector�� ����
	Vector vector;
	
	public FileList() {
		
		//���͸� ����
		vector = new Vector<String>();
		
		
		//���丮(����)�� File��ü�� ����
		File dir = new File(path);
		
		//���丮 �ȿ� �ִ� ��� �����̸��� ���丮�̸��� ����� �迭�� ���� ��
		String []arr = dir.list();
		
		//���丮�� ���ϸ���� ���Ϳ� ����
		for(String str :arr) {
			vector.add(str);
		}
		
		
		//������ �������� JList�� ����
		list = new JList<Vector<String>>(vector);
		
		//JList�� �̺�Ʈ�� ���
		list.addMouseListener(this);
		
		
		//JList�� JTextArea�� ���������� �����ӿ� ���� �ʰ� JScrollPane�� ���μ� ��ƾ� ��
		//add(list);
		JScrollPane jsp_list = new JScrollPane(list);
		
		
		jta = new JTextArea();
		JScrollPane jsp_area = new JScrollPane(jta);
		
		//"����"�� ���� ��ư�� ����
		JButton btn_delete = new JButton("����");
		
		//��ư�� ���� �̺�Ʈ ��� ->  actionPerformed�� ��
		btn_delete.addActionListener(this);
		
		
		
		//JList�� ��� �ִ� jsp_list�� �������� ���ʿ� ����
		add(jsp_list,BorderLayout.WEST);
		
		//�ؽ�Ʈ����� ��� �ִ� jsp_area�� �������� ��� ����
		add(jsp_area,BorderLayout.CENTER);
		
		//������ ���� ��ư�� �������� �Ʒ��ʿ� ����
		add(btn_delete,BorderLayout.SOUTH);
		
		setSize(800,600);
		setVisible(true);
		
	}

	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		//JList���� ������ �����̸��� ������
		int idx = list.getSelectedIndex();
		String fileName = (String)vector.get(idx);
		System.out.println(fileName);
		
		try {
			//������ �����̸��� ������ �ִ� ��θ� ���ļ� File��ü Ȥ�� String���� ����� 
			//������ �б� ���� ��Ʈ���� ����
			FileReader fr = new FileReader(path+"/"+fileName);
			
			//������ ������ ��� �о�� �����ϱ� ���� ���ڿ� ������ ����� ""���� �ʱ�ȭ
			String data = "";
			
			//���Ϸκ��� �ѱ��ھ� �о���̱� ���� ������ ����
			int ch;
			
			//�ݺ����� �̿��Ͽ� ������ ������ ��� �о� ����
			while(true) {
				//���Ϸκ��� �ѱ��ھ� ����
				ch = fr.read();
				
				//������ ���� �����ϸ� �о�� ���� -1. �׶� �ݺ����� Ż��
				if(ch == -1) {
					break;
				}
				
				//������ ���� �ƴϸ� �о�� ���ڸ� data�� ����
				data = data + (char)ch;
			}
			
			//���� JList���� ������ ������ ������ �� �о���̸� while�� Ż��
			//���� ������ ������ ��� ������ ���ڿ� ���� data�� ����Ǿ� ����
			//�̰��� �ؽ�Ʈ����� ���
			jta.setText(data);
			
			//����� ������ �ݾ���
			fr.close();
			
		} catch (Exception ex) {
			System.out.println("���ܹ߻�:"+ex.getMessage());
		}
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//�����ϱ� ���Ͽ� ������ ������ File��ü�� ����
		File file = new File(path + "/" +list.getSelectedValue());
		
		//�� ������ ������
		file.delete();
		
		//�ؽ�Ʈ����� ������ �����
		jta.setText("");
		
		
		//c:/myData ������ ���ϸ���� �ٽ� �о�ͼ� Vector�� ������ �ٲٰ� 
		//JList�� �ٽ� �׸����� ��û
		File dir = new File(path);
		String []arr = dir.list();
		
		//���͸� ������ ����� �ٽ� �о�� ���ϸ���� �����
		vector.clear();
		for(String f : arr) {
			vector.add(f);
		}
		
		//JList�� ����� �ٲ� ������ �������� �ٽ� �׷��ֵ��� ��û
		list.updateUI();
		
		
		JOptionPane.showMessageDialog(this, "������ ������ �����Ͽ����ϴ�.");
		
		
		
	}
}
