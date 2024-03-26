package com.fsb.networked.dto;

import org.json.JSONObject;

import java.time.LocalDate;

public class EducationDTO
{
	private String diploma;
	private String institute;
	private String description;
	private String type;
	private LocalDate startDate;
	private LocalDate endDate;

	public EducationDTO(String diploma, String institute, String type, String description, LocalDate startDate, LocalDate endDate)
	{
		super();
		this.diploma = diploma;
		this.institute = institute;
		this.description = description;
		this.type = type;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public EducationDTO() {

	}

	public LocalDate getStartDate()
	{
		return startDate;
	}
	
	public void setStartDate(LocalDate startDate)
	{
		this.startDate = startDate;
	}

	public LocalDate getEndDate()
	{
		return endDate;
	}

	public void setEndDate(LocalDate endDate)
	{
		this.endDate = endDate;
	}

	public String getInstitute()
	{
		return institute;
	}
	public void setInstitute(String institute)
	{
		this.institute = institute;
	}

	public String getDiploma() {
		return diploma;
	}

	public void setDiploma(String diploma) {
		this.diploma = diploma;
	}

	public String getDescription()
	{
		return this.description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}



	public JSONObject toJSON() {
		JSONObject educationJSON = new JSONObject();
		educationJSON.put("diploma", diploma);
		educationJSON.put("institute", institute);
		educationJSON.put("description", description);
		educationJSON.put("type", type);
		educationJSON.put("startDate", startDate);
		educationJSON.put("endDate", endDate);
		return educationJSON;
	}
}
