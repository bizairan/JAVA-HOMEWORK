package 第一次作业;
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.InputStream;  
import java.math.BigInteger;  
import java.security.MessageDigest; 

public class hash {
	//计算某个文件的SHA1哈希值，参考java课件
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

	//计算某个字符串的SHA1哈希值
	public static String SHA1checkstr(String s) throws Exception{
		MessageDigest complete = MessageDigest.getInstance("SHA-1");
		complete.update(s.getBytes());
		return convert(complete);
	}
	
	
	//将获得的MessageDigest数据转化为字符串数据
	private static String convert(MessageDigest complete) {
		// TODO Auto-generated method stub
		byte[] hash = complete.digest();
		String text="";
		for (byte each : hash) {
			text += Integer.toString(each&0xFF, 16);
		}
		return text;
	}

	//对一个文件夹进行深度优先遍历，参考java课程课件
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
	
	//计算一个文件夹的哈希值
	public static String SHA1Directory(String path) throws Exception{
		 File directory = new File(path);
	        File[] fs = directory.listFiles();
	        String s = "";
	        // 对文件夹内容进行深度优先遍历
	        for (File f: fs) {
	            // 遍历到文件则将文件名与该文件的哈希值存入字符串s中
	            if (f.isFile()) {
	                s += f.getName();
	                s += SHA1checksum(f);
	            }
	            // 如果遍历到文件夹则进入该文件夹对计算文件夹哈希值的方法进行递归调用
	            if (f.isDirectory()) {
	                s += f.getName();
	                s += SHA1Directory(path + File.separator + f.getName());
	            }
	        }
	        // 完成遍历之后得到一个有该文件夹下所有文件的文件名与哈希值的字符串s，返回字符串s的哈希值作为整个文件夹的哈希值
	        return SHA1checkstr(s);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		System.out.println("请输入需要计算哈希值的文件夹位置：");
		String path=input.nextLine();
		try {
			System.out.println("文件夹的SHA-1哈希值是：");
			System.out.println(SHA1Directory(path));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
