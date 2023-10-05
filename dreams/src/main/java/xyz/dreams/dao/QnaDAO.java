package xyz.dreams.dao;

import java.util.List;

import java.util.Map;

import xyz.dreams.dto.QnaDTO;

public interface QnaDAO {
	
	/* QnA  등록*/
	int enrollQna(QnaDTO qna);
	
	/* QnA 수정*/
	int modifyQna(QnaDTO qna);
	
	/* QnA 삭제*/
	int deleteQna(int qnaNo);
	
	/* 오진서 9/22 - QnA 답변등록 */
	int addQnaReply(QnaDTO qna);
	
	int selectQnaCount(String goodsName);
	
	/* QnA 페이징 처리*/
	List<QnaDTO> selectQnaList(Map<String, Object> map);
	
	//이소영(추가) : 2023-09-21
	List<QnaDTO> findByMemberId(String memberId);


}
