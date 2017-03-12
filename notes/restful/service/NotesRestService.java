package notes.restful.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import mongo.model.Note;
import mongo.service.NoteService;


@CrossOrigin( methods={RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.OPTIONS,RequestMethod.HEAD}, 
              origins = {"http://176.195.58.172:8888","http://localhost:8100"}, 
             maxAge = 3600)	
@RestController
@RequestMapping("/service")
public class NotesRestService {
    
	@Autowired
	NoteService noteService;
	
	
	@RequestMapping(value="/notes/find_by_user/{user_name}",method=RequestMethod.GET)
	List<Note> getNotesByUsername(@PathVariable(value="user_name" )String username) {
		List<Note> notes = noteService.getNotesByUsername(username);
		return notes;
    }
	
	
	@RequestMapping(value="/notes/find_by_id/{id}",method=RequestMethod.GET)
	Note getNoteById(@PathVariable(value="id" )Long id) {
		return noteService.findById(id);
    }	
	
	
	@RequestMapping(value="/notes/save_one_note",method=RequestMethod.POST)
	String saveOneNote(@RequestBody(required=true) Note instance) {
		noteService.saveOneNote(instance);
		return "OK";
    }	
			
	@RequestMapping(value="/notes/{id}/delete_one_note",method=RequestMethod.DELETE)
	String deleteOneNote(@PathVariable(value="id") Long id) {
		noteService.deleteOneNote(id);
		return "OK";
    }	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> rulesForCustomerNotFound(HttpServletRequest req, Exception e) 
	{
	   System.out.println(e.getMessage());
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
}
// 176.195.58.172:8888