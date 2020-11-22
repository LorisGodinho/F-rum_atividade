package Forum_Loris_Godinho;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Soldado")
public class SoldadoController {
    @Autowired
    private SoldadoRepository repo;

    @GetMapping
    public List<Soldado> getAll() {
        return repo.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Soldado> getById(@PathVariable("id") String id) {
    	try {
    		return ResponseEntity.status(HttpStatus.OK).body(repo.findById(id).get());
		} catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
		}
    }
    
    @PostMapping 
    public ResponseEntity<String> post(@RequestBody Soldado recruta) {
        repo.save(recruta); 
        return ResponseEntity.status(HttpStatus.CREATED).body(recruta.getId());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Void> put(@PathVariable("id") String id, @RequestBody Soldado soldado) {
        if (!id.equals(soldado.getId())) {
            throw new RuntimeException("Confira os IDs do Soldado e do URL"); 
        }
        repo.save(soldado);
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
    	try {
    		repo.deleteById(id);
    		return ResponseEntity.ok().build();
		} catch (Exception e) {
    		return ResponseEntity.notFound().build();
		}
    }

}