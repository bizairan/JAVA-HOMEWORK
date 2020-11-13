package ��һ����ҵ;
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.InputStream;  
import java.math.BigInteger;  
import java.security.MessageDigest; 

public class hash {
	//����ĳ���ļ���SHA1��ϣֵ���ο�java�μ�
	public static byte[] SHA1checksum(File f) throws Exception{
		FileInputStream is = new FileInputStream(f);
		byte[] buffer=new byte[1024];
		MessageDigest complete = MessageDigest.getInstance("SHA-1");
		int numRead=0;
		do {
			numRead=is.read(buffer);
			if(numRead>0) {
				complete.update(buffer,0,numRead);
			}
		}while(numRead!=-1);
		is.close();
		return complete.digest();
		}

	//����ĳ���ַ�����SHA1��ϣֵ
	public static String SHA1checkstr(String s) throws Exception{
		MessageDigest complete = MessageDigest.getInstance("SHA-1");
		complete.update(s.getBytes());
		return convert(complete);
	}
	
	
	//����õ�MessageDigest����ת��Ϊ�ַ�������
	private static String convert(MessageDigest complete) {
		// TODO Auto-generated method stub
		byte[] hash = complete.digest();
		String text="";
		for (byte each : hash) {
			text += Integer.toString(each&0xFF, 16);
		}
		return text;
	}

	//��һ���ļ��н���������ȱ������ο�java�γ̿μ�
	public static void deep_first_search(String path) {
		File dir=new File(path);
		File[] fs=dir.listFiles();
		for(int i=0;i<fs.length;i++) {
			if(fs[i].isFile()) {
				System.out.println("file "+fs[i].getName());
				deep_first_search(path+File.separator+fs[i].getName());
			}
		}
	}
	
	//����һ���ļ��еĹ�ϣֵ
	public static String SHA1Directory(String path) throws Exception{
		 File directory = new File(path);
	        File[] fs = directory.listFiles();
	        String s = "";
	        // ���ļ������ݽ���������ȱ���
	        for (File f: fs) {
	            // �������ļ����ļ�������ļ��Ĺ�ϣֵ�����ַ���s��
	            if (f.isFile()) {
	                s += f.getName();
	                s += SHA1checksum(f);
	            }
	            // ����������ļ����������ļ��жԼ����ļ��й�ϣֵ�ķ������еݹ����
	            if (f.isDirectory()) {
	                s += f.getName();
	                s += SHA1Directory(path + File.separator + f.getName());
	            }
	        }
	        // ��ɱ���֮��õ�һ���и��ļ����������ļ����ļ������ϣֵ���ַ���s�������ַ���s�Ĺ�ϣֵ��Ϊ�����ļ��еĹ�ϣֵ
	        return SHA1checkstr(s);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		System.out.println("��������Ҫ�����ϣֵ���ļ���λ�ã�");
		String path=input.nextLine();
		try {
			System.out.println("�ļ��е�SHA-1��ϣֵ�ǣ�");
			System.out.println(SHA1Directory(path));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
