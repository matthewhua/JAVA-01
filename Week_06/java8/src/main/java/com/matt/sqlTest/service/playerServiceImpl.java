package com.matt.sqlTest.service;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Matthew
 * @date 2021/3/1 16:58
 */
@Service
public class playerServiceImpl
{

	@Resource PlayerService playerService;

	public void addServer(Long cid, byte[] data, String name)
	{
		playerService.addServer(cid, data, name);
	}
}
