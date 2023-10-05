package xyz.dreams.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

import lombok.RequiredArgsConstructor;
import xyz.dreams.dao.ReviewDAO;
import xyz.dreams.dto.ReviewDTO;
import xyz.dreams.util.Pager;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
	private final ReviewDAO reviewDAO;

	// 강민경: 리뷰 등록
	@Transactional(rollbackFor = Exception.class) // 리뷰 등록 도중 작성을 중단할 경우 리뷰 등록 안되도록 롤백
	@Override
	public void enrollReview(ReviewDTO review) {
		// 엔터키, 특수문자를 db에 그대로 전달
		review.setRevCont(HtmlUtils.htmlEscape(review.getRevCont()));
		reviewDAO.enrollReview(review);
	}

	// 강민경: 리뷰 글 수정
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void modifyReview(ReviewDTO review) {
		review.setRevCont(HtmlUtils.htmlEscape(review.getRevCont()));
		reviewDAO.modifyReview(review);
	}

	// 강민경: 리뷰 삭제
	// int revno만 맞으면 삭제하도록 처리
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void deleteReview(int revNo) {
		reviewDAO.deleteReview(revNo);
	}

	// 강민경: 목록보기 + 페이징 처리
	@Override
	public Map<String, Object> getReviewList(int reviewPageNum, String goodsName) {
		int totalBoard = reviewDAO.selectReviewCount(goodsName);
		int pageSize = 10;
		int blockSize = 5;

		// pager 클래스로 객체를 생성하여 저장 - 생성자 매개변수에 페이징 처리 관련 값을 전달
		// => Pager 객체 : 페이징 처리 관련 값이 저장된 객체
		Pager pager = new Pager(reviewPageNum, totalBoard, pageSize, blockSize);

		// FileBoardDAO 클래스의 selectFileBoardList() 메소드를 호출하기 위해 매개변수에 전달하여
		// 저장될 Map 객체(시작 행번호, 종료 행번호) 생성
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startRow", pager.getStartRow());
		map.put("endRow", pager.getEndRow());
		map.put("goodsName", goodsName);
		List<ReviewDTO> reviewList = reviewDAO.selectReviewList(map);

		// Controller 클래스에게 제공되는 데이타 처리 결과값을 반환하기 위한 Map 객체 생성
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("reviewPager", pager);
		resultMap.put("reviewList", reviewList);

		return resultMap;
	}

	// 강민경(2023/09/23): 굿즈 상세페이지에서 리뷰 갯수 출력
	@Override
	public int selectReviewCount(String goodsName) {
		return reviewDAO.selectReviewCount(goodsName);
	}

	// 이소영(추가) : 2023-09-25 review mypage list
	@Override
	public List<ReviewDTO> findByMemberId(String memeberId) {
		return reviewDAO.findByMemberId(memeberId);
	}

}
