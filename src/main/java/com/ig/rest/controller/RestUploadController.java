package com.ig.rest.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ig.jms.JmsPublisher;
import com.ig.rest.model.BrokerDetails;
import com.ig.service.StorageService;
import com.ig.xml.marshaller.DocumentUnmarshaller;
import com.ig.xml.model.Document;

@RestController
@RequestMapping("/api")
public class RestUploadController {

	@Autowired
	StorageService storageService;

	@Autowired
	JmsPublisher publisher;

	@Autowired
	DocumentUnmarshaller documentUnmarshaller;

	List<String> files = new ArrayList<String>();
	private final Path rootLocation = Paths.get("upload-dir");

	@PostMapping("/uploadfile")
	public String uploadFileMulti(
			@RequestParam("uploadfile") MultipartFile file,
			@RequestParam("brokerDetails") String brokerDetailsJson)
			throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		BrokerDetails brokerDetails = null;
		try {
			brokerDetails = mapper.readValue(brokerDetailsJson,
					BrokerDetails.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			storageService.store(file);
			Path path = Paths.get(rootLocation.toString(),
					file.getOriginalFilename());
			Document document = documentUnmarshaller.unmarshall(path.toFile());
			publisher.send(brokerDetails, document.getOrders());
			files.add(file.getOriginalFilename());
			return "You successfully processed - " + file.getOriginalFilename();
		} catch (Exception e) {
			throw new Exception(
					"FAIL : " + e.getMessage());
		}
	}

}
