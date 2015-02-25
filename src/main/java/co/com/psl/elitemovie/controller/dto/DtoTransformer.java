package co.com.psl.elitemovie.controller.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.BeanUtils;

public class DtoTransformer {

	@SuppressWarnings("unchecked")
	public static <K, T> T toDto(K entity, Class<T> dtoClass) {
		Object dto;
		try {
			dto = dtoClass.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		BeanUtils.copyProperties(entity, dto);
		return (T) dto;
	}

	public static <K, T> List<T> toDto(Collection<K> entityList,
			Class<T> dtoClass) {
		List<T> resultList = new ArrayList<T>();
		for (K entity : entityList) {
			resultList.add(toDto(entity, dtoClass));
		}
		return resultList;
	}

}
