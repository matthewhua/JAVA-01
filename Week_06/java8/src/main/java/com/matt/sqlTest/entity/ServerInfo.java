package com.matt.sqlTest.entity;

/**
 * @author Matthew
 * @date 2021/3/1 17:21
 */
public class ServerInfo
{
	int id;
	String ip;
	int port;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getIp()
	{
		return ip;
	}

	public void setIp(String ip)
	{
		this.ip = ip;
	}

	public int getPort()
	{
		return port;
	}

	public void setPort(int port)
	{
		this.port = port;
	}
}
