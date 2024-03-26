package com.fsb.networked.dto;

import org.json.JSONObject;

public class SkillDTO
{
	private String title;
	private String technology;
	private String description;
	private String level;
	
	public SkillDTO(String title, String technology, String description, String level)
	{
		super();
		this.title = title;
		this.technology = technology;
		this.description = description;
		this.level = level;
	}

    public SkillDTO() {

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

	public JSONObject toJSON() {
		JSONObject skillJSON = new JSONObject();
		skillJSON.put("title", title);
		skillJSON.put("technology", technology);
		skillJSON.put("description", description);
		skillJSON.put("level", level);
		return skillJSON;
	}
}
