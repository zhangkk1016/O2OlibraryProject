package cn.hnust.book.model;

import cn.hnust.book.dao.MyList;


public class ReleaseService {
	
	//����ͼ��
	public Boolean release(int bookId, int status){
		boolean result = false;
		MyList listRelease = new MyList();
		result = listRelease.updateStatus(bookId, status);
		return result;
	}
	
}
