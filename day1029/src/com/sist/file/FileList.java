package com.sist.file;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class FileList extends JFrame {

	JList<String> list;
	public FileList() {
		
		//JLIst에 목록으로 사용할 배열을 만듬
		String []arr = {"사과","포도","수박","딸기","사과","포도","수박","딸기","사과","포도","수박","딸기","사과","포도","수박","딸기","사과","포도","수박","딸기"};
		list = new JList<String>(arr);
		
		//JList도 JTextArea와 마찬가지로 프레임에 담지 않고 JScrollPane로 감싸서 담아야 함
		//add(list);
		
		JScrollPane jsp_list = new JScrollPane(list);
		add(jsp_list);
		
		setSize(400,300);
		setVisible(true);
		
		
	}
}
