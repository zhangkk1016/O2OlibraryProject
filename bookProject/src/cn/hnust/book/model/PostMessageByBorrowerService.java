package cn.hnust.book.model;

import cn.hnust.book.dao.MyList;
import cn.hnust.book.dao.Notice;
import cn.hnust.book.dao.Transaction;


public class PostMessageByBorrowerService {
	
	//插入通知
	public boolean postMessage(int startUserId,int receiveUserId, int bookId, String studentPhone,String message){
		boolean result = false;
		Notice notice = new Notice();
		Transaction transaction = new Transaction();
		MyList myList = new MyList();		
		int temp1 = notice.InsertMessage(bookId, startUserId, receiveUserId, studentPhone, message);
		int temp2 = transaction.InsertTransation(bookId, startUserId, receiveUserId);
		int status = 2;//更新状态为对接中
		if(temp1 > 0 && temp2 > 0){
			result = true;
			myList.updateStatus(bookId, status);
		}
		return result;
	}
	
	
}
