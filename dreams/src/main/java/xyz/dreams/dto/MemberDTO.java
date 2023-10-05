package xyz.dreams.dto;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/*
이름                널?       유형            
----------------- -------- ------------- 
MEMBER_ID         NOT NULL VARCHAR2(30)  
MEMBER_PW         NOT NULL VARCHAR2(50)  
MEMBER_EMAIL      NOT NULL VARCHAR2(30)  
MEMBER_PHONE      NOT NULL VARCHAR2(30)  
MEMBER_NAME       NOT NULL VARCHAR2(30)  
MEMBER_PCODE               VARCHAR2(30)  
MEMBER_ADDRESS1            VARCHAR2(100) 
MEMBER_ADDRESS2            VARCHAR2(100) 
MEMBER_STATUS     NOT NULL NUMBER        
MEMBER_REG_DATE   NOT NULL DATE          
MEMBER_LAST_LOGIN NOT NULL DATE   
*/ 
//강민경(수정): 2023/9/9, 밸리데이션을 사용한 메세지 적용 
@Data
public class MemberDTO {
   @NotEmpty(message = "아이디를 반드시 입력해 주세요.")
   @Pattern(regexp= "^[a-zA-Z0-9]{4,12}$", message="아이디는 4 ~12자의 영문 대소문자의 숫자로만 작성 가능합니다.")
   private String memberId;
   @NotEmpty(message = "비밀번호를 입력해 주세요.")
   @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[~!@#$%^&*_-]).{6,20}$", message = "비밀번호는 영문자,숫자,특수문자가 반드시 하나이상 포함된 6~20 범위의 문자로만 작성 가능합니다.")
   private String memberPw;
   @NotEmpty(message = "이메일을 입력해 주세요.")
   @Email(message = "입력한 이메일이 형식에 맞지 않습니다..")
   private String memberEmail;
   @Pattern(regexp="^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$", message="전화번호는 -를 포함한 전화번호형식으로 숫자로만 입력해 주세요.")
   private String memberPhone;
   @NotEmpty(message = "이름을 입력해 주세요.")
   private String memberName;
   private String memberPcode;
   @NotEmpty(message="주소를 입력해 주세요.")
   private String memberAddress1;
   @NotEmpty(message="상세 주소를 입력해 주세요.")
   private String memberAddress2;
   private int memberStatus;

   //파이널 때 다시 정의함
   @DateTimeFormat(pattern="yyyy-MM-dd")
   private Date memberRegDate;
   private Date memberLastLogin;
   
   
}