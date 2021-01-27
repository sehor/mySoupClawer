package clawer.data;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class DataUtils {
  
	@Autowired
	MongoOperations operations;
	
	@SuppressWarnings("unchecked")
	public <T, K> List<T> getFielsFromDB(String fieldName,Criteria criteria,Class<K> clazz){
		List<T> results=new ArrayList<>();
		
		Query query=new Query();
		query.addCriteria(criteria);
		query.fields().include(fieldName);
		for(K k:operations.find(query, clazz)) {
			try {
			Field field=clazz.getDeclaredField(fieldName);
			field.setAccessible(true);
			
			if(field.getType()==List.class) {
				List<T> values=(List<T>) field.get(k);
				results.addAll(values);
			}else {
				T value=(T)field.get(k);
				results.add(value);
			}
			 
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return results;
	}
	
	
	public static List<String> differStringList(List<String> list1,List<String> list2){
		
		return list1.stream().filter(e->!list2.contains(e)).collect(Collectors.toList());
	}
}
