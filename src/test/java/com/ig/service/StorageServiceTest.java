package com.ig.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

public class StorageServiceTest {

	private StorageService storageService;
	private MultipartFile file;

	@Before
	public void setUp() throws Exception {
		storageService = new StorageService();
		Path path = Paths.get("/path/to/the/file.txt");
		String name = "file.txt";
		String originalFileName = "file.txt";
		String contentType = "text/plain";
		byte[] content = null;
		try {
			content = Files.readAllBytes(path);
		} catch (final IOException e) {
		}
		file = new MockMultipartFile(name, originalFileName, contentType,
				content);
	}

	@Test
	public void testStore() {

		storageService.store(file);
		assertTrue(Paths.get("upload-dir").resolve("file.txt").toFile()
				.exists());
	}

	@Test
	public void testStoreFail() {
		MultipartFile file = null;
		boolean excNotTriggered = true;
		try {
			storageService.store(file);
		} catch (Exception e) {
			assertEquals("Impossible to save file on the server",
					e.getMessage());
			excNotTriggered = false;
		}
		if (excNotTriggered) {
			fail("An exception should have been triggered");
		}
	}

	@Test
	public void testInit() {
		storageService.init();
		assertTrue(Paths.get("upload-dir").toFile().exists());
	}

}
