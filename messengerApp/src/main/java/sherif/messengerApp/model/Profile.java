package sherif.messengerApp.model;

import java.util.Date;

public class Profile {
	
	private Long id;
	private String profileName;
	private String firstName;
	private String lstName;
	private Date created;
	
	public Profile ()
	{
		
	}
	
	public Profile(Long id, String profileName, String firstName, String lstName) {
		this.id = id;
		this.profileName = profileName;
		this.firstName = firstName;
		this.lstName = lstName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProfileName() {
		return profileName;
	}
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLstName() {
		return lstName;
	}
	public void setLstName(String lstName) {
		this.lstName = lstName;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
	
	

}
