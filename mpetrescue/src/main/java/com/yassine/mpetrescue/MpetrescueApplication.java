package com.yassine.mpetrescue;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.yassine.mpetrescue.service.MediaService;

@SpringBootApplication
public class MpetrescueApplication implements CommandLineRunner {

	@Resource
	MediaService mediaService;

	public static void main(String[] args) {

		SpringApplication.run(MpetrescueApplication.class, args);

	}

	@Override
	public void run(String... arg) throws Exception {
		mediaService.deleteAll();
		mediaService.init();
	}
}
