package commons;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MapUserID<K, V> {

	public Map<K, V> map = Collections.synchronizedMap(new HashMap<K,V>());
	
	public synchronized K getKeyByValue(V val)
	{
		for(K key:map.keySet())
			if(map.get(key) == val || map.get(key).equals(val))
				return key;
		return null;
	}
	
	public synchronized V put(K key,V value)
	{
		for(V val:valueSet())
		{
			if(val.equals(value)&&val.hashCode() == value.hashCode())
			{
				throw new RuntimeException("不能有重复ID");
			}
		}
		return map.put(key, value);
	}
	
	public synchronized Set<V> valueSet()
	{
		Set<V> result = new HashSet<V>();
	        for(V s : map.values()){
	        	result.add(s);
	        }
	        return result;
	}
	
}
