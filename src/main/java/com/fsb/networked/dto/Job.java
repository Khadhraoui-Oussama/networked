package com.fsb.networked.dto;

import org.json.JSONObject;

import java.time.LocalDate;

public class Job
{
	private String position;
	private String company;
	private String description;
	private String type;
	private LocalDate startDate;
	private LocalDate endDate;
	
	public Job(String position, String company, String type, String description,LocalDate startDate, LocalDate endDate)
	{
		super();
		this.position = position;
		this.company = company;
		this.description = description;
		this.type = type;
		this.startDate = startDate;
		this.endDate = endDate;
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

	public String getPosition()
	{
		return position;
	}
	public void setPosition(String position)
	{
		this.position = position;
	}
	public String getCompany()
	{
		return company;
	}
	public void setCompany(String technology)
	{
		this.company = technology;
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
		JSONObject jobJSON = new JSONObject();
		jobJSON.put("position", position);
		jobJSON.put("company", company);
		jobJSON.put("description", description);
		jobJSON.put("type", type);
		jobJSON.put("startDate", startDate);
		jobJSON.put("endDate", endDate);

		return jobJSON;
	}
}
