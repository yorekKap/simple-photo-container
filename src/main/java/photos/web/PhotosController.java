package photos.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import photos.FileManager;
import photos.exceptions.PhotoErrorException;
import photos.exceptions.PhotoNotFoundException;

@Controller
@RequestMapping("/")
public class PhotosController {

	@Autowired
	FileManager photos;
	
	List<String> photosToDeleteNames = new ArrayList<String>();
			
	@RequestMapping(path = {"/", "/index"})
	public String onIndex(Model model){
		
		if(!photos.isEmpty()){
			
			model.addAttribute("photos", photos.iterator());
			model.addAttribute("delete_photos", photosToDeleteNames);
		}
		
		return "index";
		
	}
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String onView(Model model, @RequestParam("photo_name") String name){
		
		if(photos.containsFile(name)){
			
			model.addAttribute("photo_name", name);
			return "result";
			
		}
		
		else{
			throw new PhotoNotFoundException();
			
		}
	}
	
	@RequestMapping(value = "/add_photo", method = RequestMethod.POST)
	public String onAddPhoto(Model model, @RequestParam MultipartFile photo,
							 @RequestParam(name = "photo_name") String photoName){
		
		if(photo.isEmpty() || photoName.isEmpty()){
			throw new PhotoErrorException();
		}
		
		else{
				try {
		
					photos.addFile(photoName, photo.getBytes());
					model.addAttribute("photo_name", photoName);
					return "result";
				
				} catch (IOException e) {
				throw new PhotoErrorException();
			}
		}
		
	}
	
	@RequestMapping("/photo/{photo_name}")
	private ResponseEntity<byte[]> onPhoto(@PathVariable("photo_name") String photoName) {
        byte[] bytes = photos.getFile(photoName);
        if (bytes == null)
            throw new PhotoNotFoundException();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
    }
	
	@RequestMapping("/delete/{photo_name}")
	public String onDelete(Model model, @PathVariable("photo_name") String photoName) {
		if (photos.remove(photoName) == null)
			throw new PhotoNotFoundException();
	  
		else if(!photos.isEmpty()){
			
			model.addAttribute("photos", photos.iterator());
			
		}
		
		return "index";
		
	
	}
	
	@RequestMapping("/chooseToDelete/{photo_name}")
	public String onChooseToDelete(Model model, @PathVariable("photo_name") String photoName){
		
		if(photosToDeleteNames.contains(photoName)){
			
			photosToDeleteNames.remove(photoName);
			
		}
		
		else{
			
			photosToDeleteNames.add(photoName);
			
		}
		
		return "redirect:/";
	}

	@RequestMapping("/deleteChoosen")
	public String onDeleteChoosen(Model model){
		
		for(String s : photosToDeleteNames){
			
			if(photos.containsFile(s)){
				
				photos.remove(s);
				
			}
			
		}
		
		photosToDeleteNames.clear();
			
		return "redirect:/";
		
	}
	
}
