package xyz.dreams.controller;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import xyz.dreams.dto.GoodsDTO;
import xyz.dreams.dto.QnaDTO;
import xyz.dreams.service.GoodsService;
import xyz.dreams.service.QnaService;
import xyz.dreams.service.ReviewService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/goods")
public class GoodsController {
	private final GoodsService goodsService;
	private final QnaService qnaService;
	private final ReviewService reviewService;

	/*
	- 방용환(수정) : 2023/09/11, 굿즈 메인 페이지에서 굿즈 출력
	q:검색 키워드, column:정렬순서, minPrice:최소 금액. maxPrice:최대 금액
	등의 값을 받아서 해당 조건들에 맞는 굿즈들 출력 
	
	- 방용환(수정) : 2023/09/12, 굿즈 메인 페이지에서 굿즈 출력
	uniform:유니폼 카테고리, cap:모자 카테고리, fan:팬 상품 카테고리
	등의 값을 받아서 해당 조건들에 맞는 굿즈들 출력
	
	- 방용환(수정) : 2023/09/18, 리뷰순 정렬 기능 추가

	- 방용환(수정) : 2023/09/19, 별점순 정렬 기능 추가
	 */
	
	// 검색 조건을 설정하기 전에 전체 범위로 이름순으로 출력하기 위해 defaultValue 설정
	@RequestMapping("/main")
	public String view(String q, @RequestParam(defaultValue = "goods_code") String column,
			@RequestParam(defaultValue = "9999999") int maxPrice, @RequestParam(defaultValue = "0") String minPrice,
			@RequestParam(defaultValue = "true") Boolean uniform, @RequestParam(defaultValue = "true") Boolean cap,
			@RequestParam(defaultValue = "true") Boolean fan, Model model) {

		// 띄어쓰기 무시하고 검색하기 위해 검색 키워드(q)에서 띄어쓰기를 제거하는 작업
		if (q != null) {
			q = q.replaceAll(" ", "");
		}
		
		// 검색 키워드(q), column:정렬순서, minPrice:최소 금액. maxPrice:최대 금액,
		// uniform:유니폼 카테고리, cap:모자 카테고리, fan:팬 상품 카테고리 등의 값을
		// 페이지 불러오기 이후에도 jsp에 그대로 보여주고, goodsService에 인자로 넘기기 위한 Map
		Map<String, Object> map = new HashMap<>();
		map.put("q", q);
 
		map.put("column", column);

		map.put("maxPrice", maxPrice);
		map.put("minPrice", minPrice);

		map.put("uniform", uniform);
		map.put("cap", cap);
		map.put("fan", fan);

		// 검색 조건들을 goodsService.getGoodsList(Map<String, Object> map)에 인자로 넘김
		List<GoodsDTO> goodsList = goodsService.getGoodsList(map);
		model.addAttribute("map", map);
		model.addAttribute("goodsList", goodsList);
		model.addAttribute("goodsCount", goodsList.size());
		
		
		return "goods/goods_main";
	}

	/*
	- 방용환(수정) : 2023/08/23, 굿즈 디테일 페이지에서 해당 굿즈 정보 출력
	goodsCode가 아닌 goodsName을 전달해 해당 이름에 해당하는 굿즈 정보 출력
	
	- 방용환(수정) : 2023/09/15, 굿즈 사이즈마다 재고 및 구매 가능 여부 출력
	
	- 오진서(수정) : 2023/09/20, QnA 리스트 출력
	
	- 방용환(수정) : 2023/09/23 : 페이징 처리
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(@RequestParam String goodsName, @RequestParam(defaultValue = "1") int reviewPageNum,
			@RequestParam(defaultValue = "1") int qnaPageNum, Model model) {
		GoodsDTO goodsDetail = goodsService.getGoodsDetail(goodsName);
		model.addAttribute("goodsDetail", goodsDetail);

		// 오진서 (2023/09/18) : 페이지에서 Q&A리스트 출력
		Map<String, Object> qnaMap = qnaService.getQnaList(qnaPageNum, goodsName);
		model.addAttribute("qnaPager", qnaMap.get("qnaPager"));
		model.addAttribute("qnaList", qnaMap.get("qnaList"));

		// 강민경(2023/09/20): 상품 상세 페이지에서 리뷰 출력
		Map<String, Object> reviewMap = reviewService.getReviewList(reviewPageNum, goodsName);
		model.addAttribute("reviewPager", reviewMap.get("reviewPager"));
		model.addAttribute("goodsReview", reviewMap.get("reviewList"));

		//강민경: 굿즈 상세페이지에서 리뷰 갯수 출력 기능 
		int reviewCount = reviewService.selectReviewCount(goodsName);
		model.addAttribute("reviewCount", reviewCount);
		return "goods/goods_detail";
	}

	//강민경(2023-09-20): 상세페이지에서 리뷰 삭제 기능 
	@RequestMapping(value = "/detail/delete", method = RequestMethod.GET)
	public String reviewDelete(@RequestParam("revNo") int revNo, @RequestParam("goodsName") String goodsName ) throws UnsupportedEncodingException {
		reviewService.deleteReview(revNo);
		goodsName=URLEncoder.encode(goodsName, "utf-8");
		return "redirect:/goods/detail?goodsName="+goodsName;
	}
	

//	장바구니로 굿즈 정보 넘기기
	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	public String purchaseCart(@ModelAttribute GoodsDTO goods, RedirectAttributes attributes) {

		attributes.addFlashAttribute("goods", goods);
		
		return "redirect:/cart/purchase";
	}
	 
//	결제페이지로 굿즈 정보 바로 넘기기
	@RequestMapping(value = "/detail/order", method = RequestMethod.POST)
	public String purchaseOrder(@ModelAttribute GoodsDTO goods, RedirectAttributes attributes) {

		goods.setGoodsPrice(goods.getGoodsPrice() * goods.getGoodsCount());

		attributes.addFlashAttribute("goods", goods);
		
		return "redirect:/order/new";
	}
	
    
	// 오진서 - 9/19(수정)  Q&A 작성 페이지로 이동	
	// 오진서 - 9/21 Q&A 작성 페이지로 이동
	@RequestMapping(value = "/qna/write", method = RequestMethod.GET)
	public String showQnaWriteForm(@RequestParam String goodsCode, @RequestParam String goodsName, Model model) {
		model.addAttribute("goodsCode", goodsCode);
		model.addAttribute("goodsName", goodsName);

		return "goods/goods_qna_write"; // JSP 페이지 이름
	}
	
	// 오진서 - 09/19(수정) Q&A 작성 하기
	// 오진서 - 09/21 - 링크 Q&A 작성 하기
	@RequestMapping(value = "/write/add", method = RequestMethod.POST)
	public String qnaWritePOST(@ModelAttribute QnaDTO qna) throws Exception{
		String goodsName = qnaService.enrollQna(qna); // 등록 //+ ???? 이걸주석처리하면 요청한곳으로 잘가는데, 풀면 405 뜸
		// (+추가) String goodsName = 함으로써 qnaService에 값을 받아옴
		
		String encodedGoodsName = URLEncoder.encode(goodsName, "utf-8"); //한글로 url되서 404 뜨는거라 이거하면 ㄱㅊ아진대
		return "redirect:/goods/detail?goodsName=" + encodedGoodsName; // 입력후 굿즈메인페이지로 이동 **수정완^^
	}	
	
	// 오진서 - 9/22 도전 !! ,,, Q&A 답변페이지로 이동
	@RequestMapping(value = "/qna/rewrite", method = RequestMethod.GET)
	public String showQnaReWriteForm(@RequestParam String goodsCode, @RequestParam int qnaNo, @RequestParam String goodsName, Model model) {
		model.addAttribute("goodsCode", goodsCode);
		model.addAttribute("qnaNo", qnaNo);
		model.addAttribute("goodsName", goodsName);
		
		return "goods/goods_qna_rewrite"; // JSP 페이지 이름
	}	
	
	// 오진서 - 09/22 -  Q&A 답변 작성 하기
	@RequestMapping(value = "/rewrite/add", method = RequestMethod.POST)
	public String qnaReWritePOST(@ModelAttribute QnaDTO qna) throws Exception{
		// Q&A 답변을 추가하고 결과로 반환된 상품 이름을 받음
		String goodsName = qnaService.addQnaReply(qna);
		// (+추가) String goodsName = 함으로써 qnaService에 값을 받아옴
		
		String encodedGoodsName = URLEncoder.encode(goodsName, "utf-8"); //한글로 url되서 404 뜨는거라 이거하면 ㄱㅊ아진대
		
		return "redirect:/goods/detail?goodsName=" + encodedGoodsName; // 입력후 굿즈메인페이지로 이동
	}

	
	
	
	
//	// 오진서 - 9/19 Q&A 목록 도전!!!!!! 목록을 받아와야하니칸 GET 방식
//	// 정보받아와서 목록 출력하고 싶어....
//	@RequestMapping(value = "/detail/qna", method = RequestMethod.GET)
//	public String qnaList(@RequestParam String goodsCode, Model model,
//			@RequestParam Map<String,Object> map) {
//		// 9/19 - 맵넣어줘야한대 ▲
//		
//		model.addAttribute("qnaList", qnaService.getQnaList(map)); 
//	//model안에 qnaService안에있는 getQnaList 를 넣을거임 (qnaListqnaList)란 이름으로
//		return "goods/goods_detail";
//	}
//// 500 떠서 잠시 묻어둠


	
    
}