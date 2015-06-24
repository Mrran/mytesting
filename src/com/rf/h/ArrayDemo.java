package com.rf.h;

import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import de.innosystec.unrar.Archive;
import de.innosystec.unrar.rarfile.FileHeader;

public class ArrayDemo {
	
	public static void main(String[] args) throws ParseException {
		
		TreeMap<String, String> map=new TreeMap<String,String>();
		map.put("key0", "value0");
		map.put("key1", "value1");
		map.put("key2", "value2");
		map.put("key3", "value3");
		map.put("key4", "value4");
		map.put("key5", "value5");
		map.put("key6", "value6");
		for (Iterator<Map.Entry<String, String>>it=map.entrySet().iterator();it.hasNext();) {
			Map.Entry<String, String> entry=it.next();
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
		
		
		//TreeSet<E>
		//ArrayList<E>
		/*Collection<String> c=new ArrayList<String>();
		c.add("111");
		System.out.println(c.size());*/
		
		LinkedList<String> list=new LinkedList<String>();
		list.add("C");
		list.add("D");
		list.add("E");
		list.add("F");
		list.add("G");
		list.add("H");
		list.add("I");
		list.addFirst("A");
		list.addLast("Z");
		
		System.out.println(list);
		
		
		
		
		/*c.add("000");
		c.add("111");
		c.add("222");
		c.add("333");
		c.add("444");
		Iterator it=c.iterator();
		for (;it.hasNext();) {
			String s=(String) it.next();
			System.out.println(s);
		}*/
		
	//	boolean result=a.unrar("/mnt/sdcard/testing.rar","/mnt/sdcard/aaaaa");
	//	unRarFile("E:\\newcentury_p7000g.rar", "E:\\aaaaa");
		
	//	Iterator
	//	Collection
	//	AbstractList<E>
	}

	/**
	 * 根据原始rar路径，解压到指定文件夹下.
	 * 
	 * @param srcRarPath
	 *            原始rar路径
	 * @param dstDirectoryPath
	 *            解压到的文件夹
	 */
	public static void unRarFile(String srcRarPath, String dstDirectoryPath) {
		if (!srcRarPath.toLowerCase().endsWith(".rar")) {
			System.out.println("非rar文件！");
			return;
		}
		File dstDiretory = new File(dstDirectoryPath);
		if (!dstDiretory.exists()) {// 目标目录不存在时，创建该文件夹
			dstDiretory.mkdirs();
		}
		Archive a = null;
		try {
			a = new Archive(new File(srcRarPath));
			if (a != null) {
				a.getMainHeader().print(); // 打印文件信息.
				FileHeader fh = a.nextFileHeader();
				while (fh != null) {
					if (fh.isDirectory()) { // 文件夹
						File fol = new File(dstDirectoryPath + File.separator
								+ fh.getFileNameString());
						fol.mkdirs();
					} else { // 文件
						File out = new File(dstDirectoryPath + File.separator
								+ fh.getFileNameString().trim());
						// System.out.println(out.getAbsolutePath());
						try {// 之所以这么写try，是因为万一这里面有了异常，不影响继续解压.
							if (!out.exists()) {
								if (!out.getParentFile().exists()) {// 相对路径可能多级，可能需要创建父目录.
									out.getParentFile().mkdirs();
								}
								out.createNewFile();
							}
							FileOutputStream os = new FileOutputStream(out);
							a.extractFile(fh, os);
							os.close();
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
					fh = a.nextFileHeader();
				}
				a.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
