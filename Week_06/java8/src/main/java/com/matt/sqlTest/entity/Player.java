package com.matt.sqlTest.entity;

/**
 * @author Matthew
 * @date 2021/3/1 15:29
 */
public class Player
{
	int cid;
	byte[] data;
	char name;

	public int getCid()
	{
		return cid;
	}

	public void setCid(int cid)
	{
		this.cid = cid;
	}

	public byte[] getData()
	{
		return data;
	}

	public void setData(byte[] data)
	{
		this.data = data;
	}

	public char getName()
	{
		return name;
	}

	public void setName(char name)
	{
		this.name = name;
	}
}
