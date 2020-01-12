package com.lushunde.gui.transfer;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class TransferUtil {

	/**
	 * ��Դ�ļ� ��ȡ ���� д�뵽txt
	 * 
	 * @param srcpath
	 * @param txtpath
	 * @param readSixze
	 */
	public static List<String> srcFiletoBase64txt(String srcpath, String txtpath, Integer readSixze) {
		final Base64.Encoder encoder = Base64.getEncoder(); // ����

		if (readSixze == null || readSixze == 0) {
			readSixze = 1 * 1024 * 1024;
		}

		List<String> result = new ArrayList<>();

		DataInputStream dis = null;
		DataOutputStream dos = null;
		try {

			File srcfile = new File(srcpath);
			File tagfile = new File(txtpath);
			dis = new DataInputStream(new FileInputStream(srcfile));
			dos = new DataOutputStream(new FileOutputStream(tagfile));

			int available = dis.available();
			byte[] b = null;
			int curr = 0;
			while (curr < available) {
				int start = curr;
				int len = readSixze;
				if (start + len > available) {
					len = available - start;
				}
				curr = start + len;
				b = new byte[len];
				dis.read(b, 0, len);
				System.out.println("��ȡ�ļ��ֽڣ�" + start + "  ��ȡ�ַ����ȣ�" + len);
				String encodedText = encoder.encodeToString(b) + "\n";
				dos.write(encodedText.getBytes());
			}
			result.add("�������ļ�·����" + srcpath);
			result.add("������ļ�·����" + txtpath);
			result.add("�������ļ���С��" + srcfile.length());
			result.add("������ļ���С��" + tagfile.length());
			result.add("������ɣ�������=" + (tagfile.length() * 100 / srcfile.length()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (dis != null) {
				try {
					dis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (dos != null) {
				try {
					dos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;

	}

	/**
	 * ���ļ� ��ȡ ���� д��ԭ���ļ�
	 * 
	 * @param txtpath
	 * @param tagpath
	 * @return
	 */
	public static List<String> base64txttoTagFile(String txtpath, String tagpath) {
		final Base64.Decoder decoder = Base64.getDecoder();

		List<String> result = new ArrayList<>();

		BufferedReader dis = null;
		DataOutputStream dos = null;
		try {
			File txtFile = new File(txtpath);
			File zipFile = new File(tagpath);

			dis = new BufferedReader(new FileReader(txtFile));
			dos = new DataOutputStream(new FileOutputStream(zipFile));

			String str = null;
			int line = 0;
			while (true) {
				str = dis.readLine();
				if (str == null) {
					break;
				}
				++line;
				System.out.println("��ȡ�ļ�������" + line + "  ��ȡ�ַ����ȣ�" + str.length());
				byte[] decode = decoder.decode(str);
				dos.write(decode);
			}

			result.add("�������ļ�·����" + txtpath);
			result.add("������ļ�·����" + tagpath);
			result.add("�������ļ���С��" + txtFile.length());
			result.add("������ļ���С��" + zipFile.length());
			result.add("������ɣ�������=" + (zipFile.length() * 100 / txtFile.length()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (dis != null) {
				try {
					dis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (dos != null) {
				try {
					dos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return result;
	}

}
