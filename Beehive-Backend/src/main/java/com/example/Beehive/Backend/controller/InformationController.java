package com.example.Beehive.Backend.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.example.Beehive.Backend.model.Information;
import com.example.Beehive.Backend.repository.InformationRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class InformationController {

	@Autowired
	private InformationRepo informationRepo;

	@GetMapping("/getInfo")
	public ResponseEntity<List<Information>> getAllInfo(
			@RequestParam(value = "search", required = false) String searchString) {
		try {
			List<Information> foundInfo = new ArrayList<>();
			// Check if search string is present and not empty
			if (searchString != null && !searchString.isEmpty()) {
				// Split the search string into individual words
				String[] searchWords = searchString.split("\\s+");

				// Find information containing any of the search words
				for (Information information : informationRepo.findAll()) {
					boolean found = false;
					for (String searchWord : searchWords) {
						// Check if any field in the information object contains the search word
						// (case-insensitive)
						if (information.getName().toLowerCase().contains(searchWord.toLowerCase())
								|| information.getContact().toLowerCase().contains(searchWord.toLowerCase())
								|| information.getAddress().toLowerCase().contains(searchWord.toLowerCase())
								|| information.getContent().toLowerCase().contains(searchWord.toLowerCase())) {
							found = true;
							break; // No need to search further for the current information object
						}
					}
					if (found) {
						foundInfo.add(information);
					}
				}
				
			} else {
				// If no search string is provided, return all information
				foundInfo = informationRepo.findAll();
			}
			
			if (foundInfo.isEmpty())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			return new ResponseEntity<>(foundInfo, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/addInformation")
	public ResponseEntity<Information> addInfo(@RequestParam(value = "file", required = false) MultipartFile file,
	                                          @RequestBody Information infoData) throws IOException {

	    // Replace "YOUR_CLIENT_API_KEY" with your actual ImgBB API key
	    final String API_KEY = "YOUR_CLIENT_API_KEY";
	    final String API_URL = "https://api.imgbb.com/1/upload";

	    // Save information data first
	    Information savedInfo = informationRepo.save(infoData);

	    String fileUrl="file_url";
	    // Handle file upload if a file is present
	    if (file != null && !file.isEmpty()) {
//	        fileUrl = uploadImageToImgBB(file, API_KEY, API_URL);
	        savedInfo.setImage_url("Api_Key_Unavailable_for_upload");
	        savedInfo = informationRepo.save(informationRepo.findById(savedInfo.getId()).get()); // Persist updated information
	    }

	    return new ResponseEntity<>(savedInfo, HttpStatus.OK);
	}

	private String uploadImageToImgBB(MultipartFile file, String apiKey, String apiUrl) throws IOException {
	    // Create a RestTemplate instance for making HTTP requests
	    RestTemplate restTemplate = new RestTemplate();

	    // Prepare request headers with API key and content type
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.MULTIPART_FORM_DATA);
	    headers.set("Authorization", "key=" + apiKey);

	    // Prepare multipart request entity with file data
	    MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
	    parts.add("image", file.getResource());

	    HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(parts, headers);
	    ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, requestEntity, String.class);


	    // Check for successful upload and return image URL
	    if (response.getStatusCode() == HttpStatus.OK) {
	        Map<String, String> data = new ObjectMapper().readValue(response.getBody(), Map.class);
//	        return data.get("data").get("url");
	        return "url";
	    } else {
	        throw new RuntimeException("Error uploading image to ImgBB: " + response.getStatusCode());
	    }
	}

}
