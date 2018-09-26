package com.yassine.mpetrescue.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.yassine.mpetrescue.util.ConstantsSetting;

@Service
public class MediaServiceImpl implements MediaService {

	private final Path rootLocation = Paths.get(ConstantsSetting.ROOT_LOCATION);
	private final Path mediaLocation = Paths.get(ConstantsSetting.MEDIA_LOCATION);
	private final Path usersFolder = Paths.get(ConstantsSetting.MEDIA_LOCATION + "\\" + ConstantsSetting.TYPE_USERS);
	private final Path animalsFolder = Paths
			.get(ConstantsSetting.MEDIA_LOCATION + "\\" + ConstantsSetting.TYPE_ANIMALS);
	private final Path casFolder = Paths.get(ConstantsSetting.MEDIA_LOCATION + "\\" + ConstantsSetting.TYPE_CAS);
	private final Path articlesFolder = Paths
			.get(ConstantsSetting.MEDIA_LOCATION + "\\" + ConstantsSetting.TYPE_ARTICLES);

	public void store(MultipartFile file) {
		try {
			Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
		} catch (Exception e) {
			throw new RuntimeException("FAIL!");
		}
	}

	public Resource loadFile(String filename) {
		try {
			Path file = Paths.get(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("FAIL!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("FAIL!");
		}
	}

	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	public void init() {
		try {
			Files.createDirectory(rootLocation);
			Files.createDirectory(mediaLocation);
			Files.createDirectory(usersFolder);
			Files.createDirectory(animalsFolder);
			Files.createDirectory(casFolder);
			Files.createDirectory(articlesFolder);
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize storage!");
		}
	}

}
