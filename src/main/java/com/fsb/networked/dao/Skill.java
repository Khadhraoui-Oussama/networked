package com.fsb.networked.dao;

public class Skill
{
	private String title;
	private String technology;
	private String description;
	private String level;
	
	public Skill(String title, String technology, String description, String level)
	{
		super();
		this.title = title;
		this.technology = technology;
		this.description = description;
		this.level = level;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
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

	public String getLevel()
	{
		return level;
	}

	public void setLevel(String level)
	{
		this.level = level;
	}
	
}
