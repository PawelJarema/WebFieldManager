package web.field.model.entity;

import com.j256.ormlite.field.DatabaseField;

public class User {
	
	@DatabaseField
	public int UserId;
	
	@DatabaseField
    public int TenantId;
	
	@DatabaseField
    public String Title;
	
	@DatabaseField
    public String LastName;
	
	@DatabaseField
    public String FirstName;
	
	@DatabaseField
    public String Email;
	
	@DatabaseField
    public String VAT;
	
	@DatabaseField
    public int CreateUserId;
	
	@DatabaseField
    public long CreateDate;
	
	@DatabaseField
    public int ModifiedUserId;
	
	@DatabaseField
    public long ModifiedDate;
	
	@DatabaseField
    public Boolean FlagTemp;
	
	@DatabaseField
    public boolean FlagActive;
	
	@DatabaseField
    public long StartDate;
	
	@DatabaseField
    public long EndDate;
	
	@DatabaseField
	private String Token;
	
	public User() {
		// all persisted classes must define a no-arg constructor
		// with at least package visibility
	}
	
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	public int getTenantId() {
		return TenantId;
	}
	public void setTenantId(int tenantId) {
		TenantId = tenantId;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getVAT() {
		return VAT;
	}
	public void setVAT(String vAT) {
		VAT = vAT;
	}
	public int getCreateUserId() {
		return CreateUserId;
	}
	public void setCreateUserId(int createUserId) {
		CreateUserId = createUserId;
	}
	public long getCreateDate() {
		return CreateDate;
	}
	public void setCreateDate(long createDate) {
		CreateDate = createDate;
	}
	public int getModifiedUserId() {
		return ModifiedUserId;
	}
	public void setModifiedUserId(int modifiedUserId) {
		ModifiedUserId = modifiedUserId;
	}
	public long getModifiedDate() {
		return ModifiedDate;
	}
	public void setModifiedDate(long modifiedDate) {
		ModifiedDate = modifiedDate;
	}
	public Boolean getFlagTemp() {
		return FlagTemp;
	}
	public void setFlagTemp(Boolean flagTemp) {
		FlagTemp = flagTemp;
	}
	public boolean isFlagActive() {
		return FlagActive;
	}
	public void setFlagActive(boolean flagActive) {
		FlagActive = flagActive;
	}
	public long getStartDate() {
		return StartDate;
	}
	public void setStartDate(long startDate) {
		StartDate = startDate;
	}
	public long getEndDate() {
		return EndDate;
	}
	public void setEndDate(long endDate) {
		EndDate = endDate;
	}

	public String getToken() {
		return Token;
	}

	public void setToken(String token) {
		Token = token;
	}
    
    
}
