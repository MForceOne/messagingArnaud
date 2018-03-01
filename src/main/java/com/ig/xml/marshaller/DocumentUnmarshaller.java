package com.ig.xml.marshaller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.SequenceInputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.input.ReaderInputStream;
import org.springframework.stereotype.Service;

import com.ig.xml.model.Document;

@Service
public class DocumentUnmarshaller {

	private JAXBContext context;

	@PostConstruct
	public void init() {
		try {
			context = JAXBContext.newInstance(Document.class);
		} catch (JAXBException e) {
			throw new RuntimeException("Could not instanciate JaxbContext");
		}
	}

	public Document unmarshall(File file) {
		Document document = null;

		try {
			Unmarshaller unmarshaller = context.createUnmarshaller();

			FileInputStream fis = new FileInputStream(file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					fis));
			reader.readLine();
			@SuppressWarnings("deprecation")
			InputStream in2 = new ReaderInputStream(reader);
			List<InputStream> streams = Arrays.asList(new ByteArrayInputStream(
					"<Orders>".getBytes()), in2, new ByteArrayInputStream(
					"</Orders>".getBytes()));
			InputStream cntr = new SequenceInputStream(
					Collections.enumeration(streams));

			document = (Document) unmarshaller.unmarshal(cntr);
		} catch (JAXBException | IOException e) {
			throw new RuntimeException("Could not unmarshall the file");
		}
		return document;
	}
}
