package com.fsb.networked.dao;

import java.time.LocalDate;

public class Job
{
	private String position;
	private String technology;
	private String description;
	private String type;
	private LocalDate startDate;
	private LocalDate endDate;
	
	public Job(String position, String technology, String type, String description,LocalDate startDate, LocalDate endDate)
	{
		super();
		this.position = position;
		this.technology = technology;
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
	public String getTechnology()
	{
		return technology;
	}
	public void setTechnology(String technology)
	{
		this.technology = technology;
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
	
	
}
