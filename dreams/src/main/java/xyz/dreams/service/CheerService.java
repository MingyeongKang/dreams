package xyz.dreams.service;

import java.util.Map;

import xyz.dreams.dto.CheerDTO;

public interface CheerService {
	
	/*
	- 방용환(생성) : 2023/09/19, 응원의 한마디 페이지 정보 출력
	
	- 방용환(수정) : 2023/09/20, 페이징 처리
	 */
	Map<String, Object> getCheerList(int pageNum);

	/*
	- 방용환(생성) : 2023/09/19, 응원의 한마디 페이지 새로운 메모 INSERT
	 */
	void addCheer(CheerDTO cheer);
	
	void changeCheerY(int cheerNo);
	
	void changeCheerN(int cheerNo);
}
