package com.lushunde.gui.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class FielChooserActionListener implements ActionListener{
	@Override
    public void actionPerformed(ActionEvent e) {

          JButton jbt= (JButton) e.getSource();
              //���ļ�ѡ�����Ի���
//              int status=((JFileChooser) jbt).showOpenDialog(this);
//              
//               
//
//                     try {
//                         //��ѡ�е��ļ�����Ϊ�ļ�����
//                         File file=jfc.getSelectedFile();
//                         Scanner scanner=new Scanner(file);
//                         String info="";
//                         while(scanner.hasNextLine())
//                         {
//
//                             String str=scanner.nextLine();
//                             info+=str+"\r\n";
//                         }
//                         //�Ѷ�ȡ�����ݴ浽�ı�����
//                         ta.setText(info);
//
//                    } catch (FileNotFoundException e1) {
//                        System.out.println("ϵͳû���ҵ����ļ�");
//                        e1.printStackTrace();
//                    }


                

          

    }
}
