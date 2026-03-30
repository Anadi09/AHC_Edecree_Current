package com.pdms.model;

public class PetitionerDetails {
	private String petitioner_details;

	public PetitionerDetails() {
		// TODO Auto-generated constructor stub
	}

	public PetitionerDetails(String petitioner_details) {
		super();
		this.petitioner_details = petitioner_details;
	}

	public String getPetitioner_details() {
		return petitioner_details;
	}

	public PetitionerDetails setPetitioner_details(String petitioner_details) {
		this.petitioner_details = petitioner_details;
		return new PetitionerDetails(this.petitioner_details);
	}

}
