package sherif.messengerApp.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import sherif.messengerApp.database.DatabaseClass;
import sherif.messengerApp.exception.DataNotFoundException;
import sherif.messengerApp.model.Message;

public class MessageService {
	
	
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public MessageService ()
	{
		messages.put(1L,new Message(1L,"Hello World!","sherif"));
		messages.put(2L,new Message(2L,"Hello Egypt!","ahmed"));
		
	}
	
	public List<Message> getAllMessages()
	{
		
		return new ArrayList<Message>(messages.values());
	}
	
	public List<Message> getAllMessageForYear(int year)
	{
		List<Message> messageForYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for(Message message : messages.values())
		{
			cal.setTime(message.getCreated());
			if(cal.get(Calendar.YEAR) == year)
			{
				messageForYear.add(message);
				
			}
		}
		
		return messageForYear;
	}
	
	public List<Message> getAllMessagePagination(int start, int size)
	{
		ArrayList<Message> list = new ArrayList<Message>(messages.values());
		if(start + size > list.size())
		{
		  return new ArrayList<Message>();
		}	
		
		return list.subList(start, start+size);
		
		
	}
	
	public Message getMessage(Long id)
	{
		Message message = messages.get(id);
		if (message == null)
		{
			throw new DataNotFoundException("message with id " + id + " Notfound");
		}
		return message;
	}
	
	public Message addMessage(Message message)
	{
		message.setId(messages.size() +1);
		return messages.put(message.getId(), message);
	}
	
	public Message updateMessage(Message message)
	{
		if(messages.size() <= 0)
		{
			return null;
		}
		
	    messages.put(message.getId(), message);
	    return message;
	}
	
	public Message removeMessage(Long id)
	{
		return messages.remove(id);
	}
	
	

}
