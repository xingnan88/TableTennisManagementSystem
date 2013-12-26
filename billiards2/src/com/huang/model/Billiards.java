package com.huang.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="billiards")
public class Billiards
{
	private int id;
	private Date start;
	private Date end;
	private long time;
	private double price;
	private int tableNo;
	@Id
	@GeneratedValue
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public Date getStart()
	{
		return start;
	}
	public void setStart(Date start)
	{
		this.start = start;
	}
	public Date getEnd()
	{
		return end;
	}
	public void setEnd(Date end)
	{
		this.end = end;
	}
	public long getTime()
	{
		return time;
	}
	public void setTime(long time)
	{
		this.time = time;
	}
	public double getPrice()
	{
		return price;
	}
	public void setPrice(double price)
	{
		this.price = price;
	}
	public int getTableNo()
	{
		return tableNo;
	}
	public void setTableNo(int tableNo)
	{
		this.tableNo = tableNo;
	}
}
