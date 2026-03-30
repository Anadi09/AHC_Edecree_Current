package com.eDecree.model;

import java.util.Date;

public class AdvocateDTO {
		
		
	    private String name;
		
		
	    private String aor;
		
		
	    private String mobile;
		private String caseType;
		private String caseNo;
		private String caseYear;
	    private boolean selected;
	    private Date tillDate;
	    private Boolean editable;
	    
	    
	    public AdvocateDTO() {
	    	
	    }
	    
	    
		public AdvocateDTO(String name, String aor, String mobile, String caseType, String caseNo, String caseYear,
				boolean selected, Date tillDate, Boolean editable) {
			super();
			this.name = name;
			this.aor = aor;
			this.mobile = mobile;
			this.caseType = caseType;
			this.caseNo = caseNo;
			this.caseYear = caseYear;
			this.selected = selected;
			this.tillDate = tillDate;
			this.editable = editable;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAor() {
			return aor;
		}
		public void setAor(String aor) {
			this.aor = aor;
		}
		public String getMobile() {
			return mobile;
		}
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		public String getCaseType() {
			return caseType;
		}
		public void setCaseType(String caseType) {
			this.caseType = caseType;
		}
		public String getCaseNo() {
			return caseNo;
		}
		public void setCaseNo(String caseNo) {
			this.caseNo = caseNo;
		}
		public String getCaseYear() {
			return caseYear;
		}
		public void setCaseYear(String caseYear) {
			this.caseYear = caseYear;
		}
		public boolean isSelected() {
			return selected;
		}
		public void setSelected(boolean selected) {
			this.selected = selected;
		}
		public Date getTillDate() {
			return tillDate;
		}
		public void setTillDate(Date tillDate) {
			this.tillDate = tillDate;
		}
		public Boolean getEditable() {
			return editable;
		}
		public void setEditable(Boolean editable) {
			this.editable = editable;
		}
	    
	   
		@Override
		public String toString() {
		    return "AdvocateDTO{" +
		            "name='" + name + '\'' +
		            ", aor='" + aor + '\'' +
		            ", mobile='" + mobile + '\'' +
		            ", caseType='" + caseType + '\'' +
		            ", caseNo='" + caseNo + '\'' +
		            ", caseYear='" + caseYear + '\'' +
		            ", selected=" + selected +
		            ", tillDate=" + tillDate +
		            ", editable=" + editable +
		            '}';
		}

		
	    
	


   
	
    
}

