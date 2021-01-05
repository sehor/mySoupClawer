package clawer.domain.urlTree;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clawer.domain.urlTree.UrlTree;

@Service
public class UrlTreeServiceImpl implements UrlTreeService {
	@Autowired
	UrlTreeRepository repository;

	@Override
	public UrlTree addUrlTree(UrlTree urlTree) {
		return repository.save(urlTree);
	}

	@Override
	public UrlTree getUrlTree(String id) {
		return repository.findById(id).get();
	}

	@Override
	public UrlTree updateUrlTree(UrlTree urlTree) {
		return repository.save(urlTree);
	}

	@Override
	public void deleteUrlTree(UrlTree urlTree) {
		repository.delete(urlTree);
	}

	@Override
	public void deleteUrlTree(String id) {
		repository.deleteById(id);
	}

	@Override
	public List<UrlTree> getAllUrlTree(){
	   return repository.findAll();
	}

}

