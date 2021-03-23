package com.priyasoft.gui;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import com.priyasoft.service.SQLEditorService;

public class SQLEditorFrame extends Frame implements ActionListener {

	Label l;
	TextArea ta;
	Button b;
	boolean status;
	SQLEditorService editorservice;
	
	public SQLEditorFrame()
	{
		this.setVisible(true);
		this.setSize(500,700);
		this.setTitle("SQL Editor Frame");
		this.setLayout(new FlowLayout());
		this.setBackground(Color.green);
		this.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		l=new Label("Enter SQL Query");
		ta=new TextArea(5,50);
		b=new Button("Execute");
		b.addActionListener(this);
		
		Font f=new Font("consolas",Font.BOLD,15);
		l.setFont(f);
		ta.setFont(f);
		b.setFont(f);
		
		this.add(l);
		this.add(ta);
		this.add(b);
		editorservice=new SQLEditorService();
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		String query=ta.getText();
		
		status=editorservice.execute(query);
		repaint();
	}
	public void paint(Graphics g)
	{
		Font f=new Font("consolas",Font.BOLD,15);
		g.setFont(f);
		if(status==true){
			ArrayList<String> data = editorservice.getData();
			g.drawString(data.get(0), 50, 300);
			int y=300;
			for(int i=1;i<data.size();i++)
			{
				String row=data.get(i);
				y=y+40;
				g.drawString(row,50,y);
			}
		}else{
			int rowCount=editorservice.getRowCount();
			g.drawString("Row Count :"+rowCount,50, 300);
		}
	}

}
