package com.lushunde.gui.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class FielChooserActionListener implements ActionListener{
	@Override
    public void actionPerformed(ActionEvent e) {

          JButton jbt= (JButton) e.getSource();
              //打开文件选择器对话框
//              int status=((JFileChooser) jbt).showOpenDialog(this);
//              
//               
//
//                     try {
//                         //被选中的文件保存为文件对象
//                         File file=jfc.getSelectedFile();
//                         Scanner scanner=new Scanner(file);
//                         String info="";
//                         while(scanner.hasNextLine())
//                         {
//
//                             String str=scanner.nextLine();
//                             info+=str+"\r\n";
//                         }
//                         //把读取的数据存到文本框中
//                         ta.setText(info);
//
//                    } catch (FileNotFoundException e1) {
//                        System.out.println("系统没有找到此文件");
//                        e1.printStackTrace();
//                    }


                

          

    }
}
